package org.javiervegas.connectionpool

import com.opower.connectionpool.ConnectionPool
import java.sql._
import java.util.Map
import java.util.Properties

class PooledConnection (val conn: Connection, val cp: ConnectionPool) extends Connection {
  	    
		def  createStatement = {
            conn.createStatement
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def  prepareStatement(string: String) = {
            conn.prepareStatement(string)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def prepareCall(string: String) = {
            conn.prepareCall(string)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def nativeSQL(string: String) = {
            conn.nativeSQL(string)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def setAutoCommit(bln: Boolean) = {
            conn.setAutoCommit(bln)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def getAutoCommit = {
            conn.getAutoCommit
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def commit = {
            conn.commit
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def rollback = {
            conn.rollback
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def close = {
            //conn.close
			cp.releaseConnection(this)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def isClosed = {
            conn.isClosed
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def getMetaData = {
            conn.getMetaData
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def setReadOnly(bln: Boolean) = {
            conn.setReadOnly(bln)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def isReadOnly = {
            conn.isReadOnly
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def setCatalog(string: String) = {
            conn.setCatalog(string)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def getCatalog = {
            conn.getCatalog
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def setTransactionIsolation(i: Int) = {
            conn.setTransactionIsolation(i)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def getTransactionIsolation = {
            conn.getTransactionIsolation
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def getWarnings = {
            conn.getWarnings
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def clearWarnings = {
            conn.clearWarnings
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def  createStatement(i: Int, i1: Int) = {
            conn.createStatement(i, i1)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def prepareStatement(string: String, i: Int, i1: Int) = {
            conn.prepareStatement(string, i, i1)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def prepareCall(string: String, i: Int, i1: Int) = {
            conn.prepareCall(string, i, i1)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def getTypeMap = {
            conn.getTypeMap
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def setTypeMap(map: Map[String, Class[_]]) = {
            conn.setTypeMap(map)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def setHoldability(i: Int) = {
            conn.setHoldability(i)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def getHoldability = {
            conn.getHoldability
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def setSavepoint = {
            conn.setSavepoint
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def setSavepoint(string: String) = {
            conn.setSavepoint(string)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def rollback(svpnt: Savepoint) = {
            conn.rollback(svpnt)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def releaseSavepoint(svpnt: Savepoint) = {
            conn.releaseSavepoint(svpnt)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def createStatement(i: Int, i1: Int, i2: Int)  = {
            conn.createStatement(i, i1, i2)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def prepareStatement(string: String, i: Int, i1: Int, i2: Int) = {
            conn.prepareStatement(string, i, i1, i2)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def prepareCall(string: String, i: Int, i1: Int, i2: Int) = {
            conn.prepareCall(string, i, i1, i2)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def prepareStatement(string: String, i: Int) = {
            conn.prepareStatement(string, i)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def prepareStatement(string: String, Ints: scala.Array[Int]) = {
            conn.prepareStatement(string, Ints)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def prepareStatement(string: String, strings: scala.Array[String]) = {
            conn.prepareStatement(string, strings)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def createClob = {
            conn.createClob
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def createBlob = {
            conn.createBlob
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def createNClob = {
            conn.createNClob
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def createSQLXML = {
            conn.createSQLXML
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def isValid(i: Int) = {
            conn.isValid(i)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def setClientInfo(string: String, string1: String) = {
            conn.setClientInfo(string, string1)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def setClientInfo(prprts: Properties) = {
            conn.setClientInfo(prprts)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def getClientInfo(string: String) = {
            conn.getClientInfo(string)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def getClientInfo = {
            conn.getClientInfo
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def createArrayOf(string: String, os: scala.Array[Object]) = {
            conn.createArrayOf(string, os)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def createStruct(string: String, os: scala.Array[Object]) = {
            conn.createStruct(string, os)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def  unwrap[T](typ: Class[T]): T = {
            conn.unwrap(typ)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

	    def isWrapperFor(typ: Class[_]) = {
            conn.isWrapperFor(typ)
	        //throw new UnsupportedOperationException("Not supported yet.");
	    }

}

