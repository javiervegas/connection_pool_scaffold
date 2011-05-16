package com.opower.connectionpool

import java.sql.Connection
import org.apache.openjpa.lib.jdbc._;

class PooledConnection (val conn: Connection) extends DelegatingConnection(conn) with Connection {
  override def enforceAbstract = {}
}
