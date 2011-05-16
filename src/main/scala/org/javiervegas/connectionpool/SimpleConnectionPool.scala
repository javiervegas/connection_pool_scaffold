package com.opower.connectionpool
import java.sql.Connection
import java.sql.SQLException
import javax.sql.DataSource
import scala.collection.mutable.Queue

class SimpleConnectionPool(val ds: DataSource, val size: Int = 10) extends ConnectionPool {

  private val availableConnectionPool = new Queue[Connection]
  availableConnectionPool ++= (1 to size) map {_ => ds.getConnection}

  def getConnection(): Connection = {
    try {
      availableConnectionPool.dequeue
    } catch {
      case _ => throw new SQLException
    }
  }

  def releaseConnection(connection: Connection): Unit = {
    availableConnectionPool += connection
  }
}
