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
 
    def getMockDataSource() = {
        val ds = mock[DataSource]
        ds.getConnection returns mock[Connection]
        ds
    }

    scenario("a connection pool gives out and accepts back connections") {

      given("a connection pool")
      val cp = new SimpleConnectionPool(getMockDataSource)

      when("when I borrow a connection")
      val conn = cp.getConnection

      then("it will not be null")
      conn must not be (null)
      and("and I can give it back")
      cp.releaseConnection(conn)
    }

    scenario("a default pool must have 10 connections") {

      given("a default connection pool")
      val ds = getMockDataSource
      val cp = new SimpleConnectionPool(ds)

      then("ten connections were created")
      there was 10.times(ds).getConnection
    }                                                                              

    scenario("a new pool must be full of connections") {

      given("a initial pool size")
      val size = 100

      when("when I create a connection pool of that size")
      val ds = getMockDataSource
      val cp = new SimpleConnectionPool(ds, size)

      then("that many connections were created")
      there was size.times(ds).getConnection
    }

    scenario("a pool will error out when I ask for more connections than its size") {

      given("a connection pool of a given size")
      val size = 20
      val cp = new SimpleConnectionPool(getMockDataSource, size)

      when("when I ask for as many connections as its size")
      1 to size foreach { _ => cp.getConnection }
      and("I ask for another connection")
    
      then("SQLException should be thrown")
      evaluating { cp.getConnection() } must produce [SQLException]
    }

    scenario("a pool will make returned connections available for reuse") {

      given("a connection pool of a given size")
      val size = 8
      val ds = getMockDataSource
      val cp = new SimpleConnectionPool(ds, size)

      when("when I retrieve the max number of connections")
      val conns = 1 to size map { _ => cp.getConnection }
      and("I return all of them")
      conns foreach { conn => cp.releaseConnection(conn) }
    
      then("I can borrow them again")
      1 to size foreach { _ => cp.getConnection }
      then("and no new connections were created")
      there was size.times(ds).getConnection
    }

    scenario("a pool will only accept returns of pooled connections") {
      given("a connection pool")
      val cp = new SimpleConnectionPool(getMockDataSource)

      when("when I return a generic non-pooled connection")
      val genericConn = mock[Connection]
    
      then("Exception should be thrown")
      evaluating { cp.releaseConnection(genericConn) } must produce [Exception]
    }

    scenario("a pool will not accept returns of connections created by another pool") {
      given("a connection pool")
      val cp = new SimpleConnectionPool(getMockDataSource)

      when("when I return connections created by another pool")
      val otherPoolConn = (new SimpleConnectionPool(getMockDataSource)).getConnection
    
      then("Exceptions should be thrown")
      evaluating { cp.releaseConnection(otherPoolConn) } must produce [Exception]
    }

  }
}

