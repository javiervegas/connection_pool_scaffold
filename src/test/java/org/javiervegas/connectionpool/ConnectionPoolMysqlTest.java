
package org.javiervegas.connectionpool;

import com.opower.connectionpool.ConnectionPool;
import java.sql.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class ConnectionPoolMysqlTest {
    
    public ConnectionPoolMysqlTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testConnection() throws Exception {
        ConnectionPool cp = null; 
        try {
            cp = new SimpleConnectionPool("jdbc:mysql://localhost/test","php","php","com.mysql.jdbc.Driver", 8);
            Connection conn = cp.getConnection();
            PreparedStatement ps = conn.prepareStatement("SHOW TABLES");
            assertTrue(ps.executeQuery().next());
        } catch (SQLException sqle) {
            assertTrue(false);
        }
    }

}
