package org.javiervegas.connectionpool

import com.opower.connectionpool.ConnectionPool
import java.sql.Connection
import java.sql.SQLException
import java.sql.DriverManager
import javax.sql.DataSource
import scala.collection.mutable.Queue

class SimpleConnectionPool(val url: String , val userName: String, val password: String, driver: String, val size: Int = 10) extends ConnectionPool {

  Class.forName(driver).newInstance()

  private val availableConnectionPool = new Queue[Connection]
  (1 to size) foreach {_ => 
    availableConnectionPool.enqueue(new PooledConnection(createConnection, this))
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
      case conn: PooledConnection => availableConnectionPool.enqueue(conn)
      case _ => throw new RuntimeException
    }
  }
}
