package org.javiervegas.connectionpool

import com.opower.connectionpool.ConnectionPool
import java.sql.Connection
import org.apache.openjpa.lib.jdbc._;

class PooledConnection (val conn: Connection, val cp: ConnectionPool) extends DelegatingConnection(conn) with Connection {
  	override def enforceAbstract = {}

  	override def close:Unit = {
		cp.releaseConnection(this)
	}

}
