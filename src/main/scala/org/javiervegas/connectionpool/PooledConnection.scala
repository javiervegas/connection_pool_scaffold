package com.opower.connectionpool

import java.sql.Connection
import org.apache.openjpa.lib.jdbc._;

class PooledConnection (val conn: Connection, val cp: ConnectionPool) extends DelegatingConnection(conn) with Connection {
  override def enforceAbstract = {}
}
