package org.javiervegas.connectionpool

import com.opower.connectionpool.ConnectionPool
import java.sql.Connection
import java.sql.SQLException
import java.sql.Driver
import java.sql.DriverManager
import javax.sql.DataSource
import scala.collection.mutable.Queue

class SimpleConnectionPool(val url: String , val userName: String, val password: String, driver: Driver, val size: Int = 10, val timeout: Int = 0) extends ConnectionPool {
  DriverManager.registerDriver(driver)

  private val availableConnectionPool = new Queue[Connection]
  (1 to size) foreach {_ => 
    availableConnectionPool.enqueue(new PooledConnection(createConnection, this))
  }

  def this(url: String, userName: String, password: String, driverName: String, size: Int) {
    this(url, userName, password, Class.forName(driverName).newInstance.asInstanceOf[Driver], size)
  }

  private def createConnection = {
    DriverManager.getConnection(url, userName, password)
  }

  def getConnection(): Connection = {
    try {
      availableConnectionPool.dequeue
    } catch {
      case _ => throw new SQLException
    }
  }

  def releaseConnection(conn: Connection): Unit = {
    conn match {
      case conn: PooledConnection if conn.cp == this => availableConnectionPool.enqueue(conn)
      case _ => throw new RuntimeException
    }
  }
}
