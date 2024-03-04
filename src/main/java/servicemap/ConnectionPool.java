/* SuperServiceMap.
   Copyright (C) 2015 DISIT Lab http://www.disit.org - University of Florence
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU Affero General Public License as
   published by the Free Software Foundation, either version 3 of the
   License, or (at your option) any later version.
   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU Affero General Public License for more details.
   You should have received a copy of the GNU Affero General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>. */

//this class provides a database connection pool, using org.apache.commons.dbcp and org.apache.commons.pool libraries

package servicemap;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Daniele Cenni, daniele.cenni@unifi.it
 */
public class ConnectionPool {

  /**
   *
   */
  public static final String DRIVER = "com.mysql.jdbc.Driver";

  /**
   *
   */
  public String URL;

  /**
   *
   */
  public String USERNAME;

  /**
   *
   */
  public String PASSWORD;

  public String validationQuery;
  /**
   *
   */
  public int connections;

  private GenericObjectPool connectionPool = null;

  private static ConnectionPool connPool;
  private static DataSource dataSource;
  private static int maxwait;
  
  /**
   *
   * @param url
   * @param username
   * @param password
     * @param maxConnections
   * @throws IOException
   */
  public ConnectionPool(String url, String username, String password, int maxConnections, String validationQuery) throws IOException {
    URL = url;
    USERNAME = username;
    PASSWORD = password;
    connections = maxConnections;
    this.validationQuery = validationQuery;
  }

  /**
   *
   * @return
   */
  public DataSource setUp()  {
    try {
      /**
       * Load JDBC Driver class.
       */
      Class.forName(ConnectionPool.DRIVER).newInstance();
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
      Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
    }

    /**
     * Creates an instance of GenericObjectPool that holds our pool of
     * connections object.
     */
    connectionPool = new GenericObjectPool();
    // set the max number of connections
    connectionPool.setMaxActive(connections);
    // if the pool is exhausted (i.e., the maximum number of active objects has been reached), the borrowObject() method should simply create a new object anyway
    connectionPool.setWhenExhaustedAction(GenericObjectPool.WHEN_EXHAUSTED_BLOCK); // era GROW
    connectionPool.setMaxWait(maxwait);

    /**
     * Creates a connection factory object which will be use by the pool to
     * create the connection object. We passes the JDBC url info, username and
     * password.
     */
    ConnectionFactory cf = new DriverManagerConnectionFactory(
            URL,
            USERNAME,
            PASSWORD);

    /**
     * Creates a PoolableConnectionFactory that will wraps the connection object
     * created by the ConnectionFactory to add object pooling functionality.
     */
    PoolableConnectionFactory pcf
            = new PoolableConnectionFactory(cf, connectionPool,
                    null, validationQuery, false, true);
    return new PoolingDataSource(connectionPool);
  }

  /**
   *
   * @return
   */
  public GenericObjectPool getConnectionPool() {
    return connectionPool;
  }
 
  public static Connection getConnection(String requester) throws IOException, SQLException {
      
      if (connPool == null || dataSource == null) {
        
        // Default cfg
        
        String urlMySqlDB = "jdbc:mysql://localhost/"; 
        String dbMySql = "ServiceMap";
        String maxConnectionsMySql = "150";
        String userMySql = "";
        String passMySql = "";
        String timezoneMySql = "Europe/Rome";
        String validationQuery = null;
        maxwait = 1000;
        
     
        // Read cfg from file
        
        try {
            
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("settings.xml"));
            doc.getDocumentElement().normalize();

            NodeList listOfSettings = doc.getElementsByTagName("label");

            for (int temp = 0; temp < listOfSettings.getLength(); temp++) {
                Node nNode = listOfSettings.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if(eElement.getAttribute("id").equalsIgnoreCase("username")) {
                        userMySql = eElement.getElementsByTagName("value").item(0).getTextContent();
                    } else if(eElement.getAttribute("id").equalsIgnoreCase("password")) {
                        passMySql = eElement.getElementsByTagName("value").item(0).getTextContent();
                    } else if(eElement.getAttribute("id").equalsIgnoreCase("maxConnections")) {
                        maxConnectionsMySql = eElement.getElementsByTagName("value").item(0).getTextContent();
                    } else if(eElement.getAttribute("id").equalsIgnoreCase("db")) {
                        dbMySql = eElement.getElementsByTagName("value").item(0).getTextContent();
                    } else if(eElement.getAttribute("id").equalsIgnoreCase("urlMySqlDB")) {
                        urlMySqlDB = eElement.getElementsByTagName("value").item(0).getTextContent();
                    } else if(eElement.getAttribute("id").equalsIgnoreCase("timezoneMySql")) {
                        timezoneMySql = eElement.getElementsByTagName("value").item(0).getTextContent();
                    } else if(eElement.getAttribute("id").equalsIgnoreCase("maxWait")) {
                        maxwait = Integer.parseInt(eElement.getElementsByTagName("value").item(0).getTextContent());
                    } else if(eElement.getAttribute("id").equalsIgnoreCase("validationQuery")) {
                        validationQuery = eElement.getElementsByTagName("value").item(0).getTextContent();
                    }
                }
            }
            
        }
        catch(Exception cfgExc) {
            System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
            cfgExc.printStackTrace();
        }
                        
        // Use cfg params
        
        String url = urlMySqlDB+dbMySql+"?useSSL=false&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&serverTimezone="+timezoneMySql;
        int maxConnections = Integer.parseInt(maxConnectionsMySql);
        synchronized(ConnectionPool.class) {
            if(connPool == null) {
                connPool = new ConnectionPool(url, userMySql, passMySql, maxConnections, validationQuery);
                System.out.println("connected "+url+" maxConnections: "+maxConnections);
            }
            if (dataSource == null) {
                dataSource = connPool.setUp();
            }
        }
 
    }

    return dataSource.getConnection();
    
  }
  
}
