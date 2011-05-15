package com.opower.connectionpool.specification

import com.opower.connectionpool._
import java.sql.Connection
import javax.sql.DataSource
import java.sql.SQLException
import org.junit.runner.RunWith
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.MustMatchers
import org.specs.mock.Mockito
import org.specs.specification._


@RunWith(classOf[JUnitRunner])
class ConnectionPoolSpec extends FeatureSpec with GivenWhenThen with MustMatchers with Mockito with DefaultExampleExpectationsListener {
 
  feature("The user can get connections from and return connections to the pool") {
 
    info("As a programmer")
    info("I want to be able to get and return connections from a pool")
    info("So that I can reuse open connections")
 
    def getMockDataSource():DataSource = {
        val ds = mock[DataSource]
        ds.getConnection returns mock[Connection]
        ds
    }

    def defaultConnectionPool():ConnectionPool = {
        new SimpleConnectionPool(getMockDataSource)
    }

    scenario("it gives out and accepts back connections") {

      given("a connection pool")
      val cp = defaultConnectionPool

      when("when I borrow a connection")
      val conn = cp.getConnection

      then("it will not be null")
      conn must not be (null)
      then("and I can give it back")
      cp.releaseConnection(conn)
    }
  }
}

