package org.javiervegas.connectionpool

import com.opower.connectionpool.ConnectionPool
import java.sql.Connection
import java.sql.SQLException
import java.sql.Driver
import java.sql.DriverManager
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.TimeUnit
import javax.sql.DataSource

class SimpleConnectionPool(val url: String , val userName: String, val password: String, driver: Driver, val size: Int = 10, val timeout: Int = 0) extends ConnectionPool {
  DriverManager.registerDriver(driver)

  private val availableConnectionPool = new ArrayBlockingQueue[Connection](size)
  (1 to size) foreach {_ => 
    availableConnectionPool.add(new PooledConnection(createConnection, this))
  }

  def this(url: String, userName: String, password: String, driverName: String, size: Int) {
    this(url, userName, password, Class.forName(driverName).newInstance.asInstanceOf[Driver], size)
  }

  private def createConnection = {
    DriverManager.getConnection(url, userName, password)
  }

  def getConnection(): Connection = {
    availableConnectionPool.poll(timeout, TimeUnit.MILLISECONDS) match {
 	  case conn: Connection => conn
      case _ => throw new SQLException
    }
  }

  def releaseConnection(conn: Connection): Unit = {
    conn match {
      case conn: PooledConnection if conn.cp == this => availableConnectionPool.offer(conn)
      case _ => throw new RuntimeException
    }
  }
}
