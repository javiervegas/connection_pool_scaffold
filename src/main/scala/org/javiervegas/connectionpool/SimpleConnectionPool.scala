package com.opower.connectionpool
import java.sql.Connection
import java.sql.SQLException
import javax.sql.DataSource

class SimpleConnectionPool(val ds: DataSource) extends ConnectionPool {

  def getConnection(): Connection = {
    ds.getConnection
  }

  def releaseConnection(connection: Connection): Unit = {
    //ignore
  }
}
