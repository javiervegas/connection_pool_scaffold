package com.opower.connectionpool

import java.sql.Connection
import java.sql.SQLException
import javax.sql.DataSource
import scala.collection.mutable.Queue

class SimpleConnectionPool(val ds: DataSource, val size: Int = 10) extends ConnectionPool {

  private val availableConnectionPool = new Queue[Connection]
  (1 to size) foreach {_ => 
    availableConnectionPool.enqueue(new PooledConnection(ds.getConnection))
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
