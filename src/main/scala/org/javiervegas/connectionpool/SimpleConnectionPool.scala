package org.javiervegas.connectionpool

import com.opower.connectionpool.ConnectionPool
import java.sql.Connection
import java.sql.SQLException
import java.sql.Driver
import java.sql.DriverManager
import java.util.concurrent.ArrayBlockingQueue
import scala.collection.mutable.HashSet
import java.util.concurrent.TimeUnit
import scala.ref._

class SimpleConnectionPool(val url: String , val userName: String, val password: String, driver: Driver, val size: Int = 10, val timeout: Int = 0) extends ConnectionPool {
  DriverManager.registerDriver(driver)

  private val availableConnections = new ArrayBlockingQueue[Connection](size)
  private val connectionReferences = new HashSet[Reference[Connection]]
  private val connectionReferenceQueue = new ReferenceQueue[Connection]

  (1 to size) foreach {_ => 
    addNewConnection
  }

  def this(url: String, userName: String, password: String, driverName: String, size: Int) {
    this(url, userName, password, Class.forName(driverName).newInstance.asInstanceOf[Driver], size)
  }

  def addNewConnection = { 
    val conn = new PooledConnection(createConnection, this)
    availableConnections.add(conn)
    connectionReferences.add(new WeakReference(conn, connectionReferenceQueue))
  }

  private def createConnection = {
    DriverManager.getConnection(url, userName, password)
  }

  def getConnection(): Connection = {
    availableConnections.poll(timeout, TimeUnit.MILLISECONDS) match {
 	  case conn: Connection => conn
      case _ => connectionReferenceQueue.poll match {
        case ref: Some[Reference[Connection]] => recoverOrCreate(ref.get)
        case None => throw new SQLException(SimpleConnectionPool.PoolDepleted)
      }  
    }
  }

  def recoverOrCreate(ref: Reference[Connection]) = {      
    ref.get match {
 	  case conn: Some[Connection] => conn.get
      case None => { 
        addNewConnection
        getConnection
      }
    }
  }

  def releaseConnection(conn: Connection): Unit = {
    conn match {
      case conn: PooledConnection if conn.cp == this =>  availableConnections.offer(conn)
      case _ => throw new RuntimeException(SimpleConnectionPool.UnknownConnection)
    }
  }

}

object SimpleConnectionPool {
	val PoolDepleted = "ConnectionPool depleted"
    val UnknownConnection = "Connection was not created by this pool, it can not be released"
}
