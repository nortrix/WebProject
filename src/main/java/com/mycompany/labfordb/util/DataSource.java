/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labfordb.util;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 *
 * @author Валик
 */

/*
 CREATE TABLE customers (
 id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
 last_name VARCHAR(100) NOT NULL,
 first_name VARCHAR(100) NOT NULL,
 login VARCHAR(100) NOT NULL,
 password VARCHAR(100) NOT NULL,
 email VARCHAR(255) NOT NULL
 );


 CREATE TABLE orders (
 id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
 date_order TIMESTAMP NOT NULL,
 id_customer INT NOT NULL
 );

 CREATE TABLE orders_theme (
 id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
 id_order INT NOT NULL,
 id_bakery_industry INT,
 id_demography INT
 );

 CREATE TABLE bakery_industry (
 id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
 output_volume INT,
 sale_volume INT,
 number_of_enterprises INT,
 id_district INT NOT NULL
 );

 CREATE TABLE demography (
 id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
 doctors_security INT,
 birthrate INT,
 mortality INT,
 mortality_in_the_working_age INT,
 id_district INT NOT NULL
 );

 CREATE TABLE districts (
 id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
 name VARCHAR(255) NOT NULL
 );

 ALTER TABLE orders ADD FOREIGN KEY (id_customer) REFERENCES customers(id);
 ALTER TABLE orders_theme ADD FOREIGN KEY (id_order) REFERENCES orders(id);
 ALTER TABLE orders_theme ADD FOREIGN KEY (id_bakery_industry) REFERENCES bakery_industry(id);
 ALTER TABLE orders_theme ADD FOREIGN KEY (id_demography) REFERENCES demography(id);
 ALTER TABLE bakery_industry ADD FOREIGN KEY (id_district) REFERENCES districts(id);
 ALTER TABLE demography ADD FOREIGN KEY (id_district) REFERENCES districts(id);

 */
public class DataSource {

    private static DataSource self;

    static {
        System.out.println("Initializing the connection pool");
//          String connectionUrl = "jdbc:h2:./src/main/resources/database/database;AUTO_SERVER=TRUE";
        String connectionUrl = "jdbc:h2:D:/projects/git/WebProject/src/main/webapp/resources/database/database;AUTO_SERVER=TRUE";
//D:\projects\git\WebProject\src\main\webapp\resources\database
//          String connectionUrl = "jdbc:h2:D:/projects/labfordb/src/main/resources/database/database;AUTO_SERVER=TRUE";
        String connectionUserName = "sa";
        String connectionPassword = "sa";
        String connectionDriver = "org.h2.Driver";

        System.out.println("Path is :" + new File("./").getAbsolutePath());
        self = new DataSource(connectionDriver, connectionUrl, connectionUserName, connectionPassword);
    }

    public static DataSource instance() {
        return self;
    }

    private PoolingDataSource dataSource = null;

    private DataSource(String driver, String url, String user,
            String password) {

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        dataSource = setupDataSource(url, user, password);
    }

    public synchronized Connection connection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Connection con) {

        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void close(ResultSet rs, Statement stmt) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception ex) {

        }

    }

    public static void close(Statement stmt) {

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception ex) {

        }

    }

    private PoolingDataSource setupDataSource(String connectURI, String user,
            String password) {
		//
        // First, we'll need a ObjectPool that serves as the
        // actual pool of connections.
        //
        // We'll use a GenericObjectPool instance, although
        // any ObjectPool implementation will suffice.
        //
        GenericObjectPool.Config config = new GenericObjectPool.Config();
        config.maxActive = 150;
        config.maxIdle = 100;
        config.minIdle = 30;
        config.maxWait = 1000;

        ObjectPool connectionPool = new GenericObjectPool(null, config);

		//
        // Next, we'll create a ConnectionFactory that the
        // pool will use to create Connections.
        // We'll use the DriverManagerConnectionFactory,
        // using the connect string passed in the command line
        // arguments.
        //
//		Properties p = new Properties();
//		p.setProperty("user", SQLConstants.USER_NAME);
//		p.setProperty("password", SQLConstants.PASSWORD);
//		p.setProperty("useUnicode", "true");
//		p.setProperty("characterEncoding", "UTF-8");
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
                connectURI, user, password);
//		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
//				connectURI, p);
        //
        // Now we'll create the PoolableConnectionFactory, which wraps
        // the "real" Connections created by the ConnectionFactory with
        // the classes that implement the pooling functionality.
        //
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
                connectionFactory, connectionPool, null, null, false, true);

		//
        // Finally, we create the PoolingDriver itself,
        // passing in the object pool we created.
        //
        PoolingDataSource poolingDataSource = new PoolingDataSource(connectionPool);

        return poolingDataSource;
    }

}
