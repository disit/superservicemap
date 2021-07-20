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

package servicemap;

import java.io.File;
import java.net.InetAddress;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.WKTReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@JsonSerialize
public class MySQLManager {

	private final String IPADDRESS = "localhost";// "localhost";
	private final String DATABASE = "ServiceMap";
	private final String SMTABLE = "servicemaps";
	private final String GRAPHTABLE = "graph";
	private final String SMGRAPHTABLE = "servicemapgraph";
	private final String DEPENTABLE = "dependency";
	private final String CACHETABLE = "cache";
        private final String SMPTABLE = "servicemaps_priority";
        private final String ORGMETA = "orgmeta";

	private String USERNAME = "root"; // default
	private String PASSWORD = "password"; // default
        private int TMPCACHEXP = 60;

	public MySQLManager() throws Exception {
		
            try {
                
                // This will load the MySQL driver, each DB has its own driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                File settings = new File("ssm-settings.xml");
                if(!settings.exists()) {
                  System.out.println("WARNING missing "+settings.getAbsolutePath());
                  settings = new File("settings.xml");
                  if(!settings.exists()) {
                    System.out.println("ERROR missing "+settings.getAbsolutePath());
                  }
                }
                Document doc = docBuilder.parse(settings);
                doc.getDocumentElement().normalize();

                NodeList listOfSettings = doc.getElementsByTagName("label");

                for (int temp = 0; temp < listOfSettings.getLength(); temp++) {
                        Node nNode = listOfSettings.item(temp);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element eElement = (Element) nNode;
                                if(eElement.getAttribute("id").equalsIgnoreCase("username")) {
                                        USERNAME =eElement.getElementsByTagName("value").item(0).getTextContent();
                                }else if(eElement.getAttribute("id").equalsIgnoreCase("password")) {
                                        PASSWORD =eElement.getElementsByTagName("value").item(0).getTextContent();
                                }else if(eElement.getAttribute("id").equalsIgnoreCase("tmpcachexp")) {
                                        TMPCACHEXP =Integer.parseInt(eElement.getElementsByTagName("value").item(0).getTextContent());
                                }
                        }
                }

            } catch (Exception e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
                e.printStackTrace();
                    throw e;
            }
		
	}

	public List<ServiceMap> getAll() throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                //connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());
                preparedStatement = connection.prepareStatement("SELECT * FROM " + SMTABLE);
                resultSet = preparedStatement.executeQuery();
                return getQueryResult(resultSet);
            } catch (SQLException e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);
                    throw e;
            } finally {
                close(connection, preparedStatement, resultSet);
            }
	}
        
        public List<ServiceMap> getAll(String api, String format) throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                    // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                    connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());
                    preparedStatement = connection.prepareStatement("SELECT * FROM "  + SMTABLE + " sm left join " + SMPTABLE + " smp on smp.api = '"+api+"' and smp.format = '"+format+"' and smp.servicemaps_id = sm.id where coalesce(smp.priority,0) <> -1 order by smp.priority desc");
                    resultSet = preparedStatement.executeQuery();
                    return getQueryResult(resultSet);
            } catch (SQLException e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);
                    throw e;
            } finally {
                    close(connection, preparedStatement, resultSet);
            }
	}

	public ServiceMap getById(String id) throws Exception {
            Connection connection = null;            
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;	
            try {
                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());

                preparedStatement = connection
                                .prepareStatement("SELECT * FROM "  + SMTABLE + " WHERE id= ? ;");

                preparedStatement.setString(1, id);

                resultSet = preparedStatement.executeQuery();

                List<ServiceMap> smList = getQueryResult(resultSet);

                if (!smList.isEmpty()) {
                        return smList.get(0);
                }
                return null;
            } catch (Exception e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);
                    throw e;
            } finally {
                    close(connection, preparedStatement, resultSet);
            }
	}

	public String getSMIdFromServiceUriCache(String uri) throws Exception {
            
            String smid = null;	
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            
            try {
                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());

                preparedStatement = connection
                               .prepareStatement("SELECT * FROM "  + CACHETABLE + " WHERE serviceUri= ? and (temporary is null or temporary = 0 or ? < cast(insertTime as unsigned)+?);");

                preparedStatement.setString(1, uri);
                preparedStatement.setInt(2, (int) (System.currentTimeMillis() / 1000L));
                preparedStatement.setInt(3, TMPCACHEXP);

                resultSet = preparedStatement.executeQuery();

                List<String> smList = new ArrayList<>();

                while (resultSet.next()) {
                        smList.add(resultSet.getString("idServiceMap"));
                }
                
                if(smList.size() > 1) {
                    try {
                        preparedStatement = connection.prepareStatement("DELETE FROM "  + CACHETABLE + " WHERE serviceUri = ? ");
                        preparedStatement.setString(1, uri);
                        preparedStatement.executeUpdate();
                    }
                    catch(Exception ee) {}
                    return null;
                }

                if (!smList.isEmpty()) {
                        preparedStatement = connection.prepareStatement(
                                        "UPDATE "  + CACHETABLE + " SET lastAccessTime=? WHERE serviceUri=?");
                        preparedStatement.setInt(1, (int) (System.currentTimeMillis() / 1000L));
                        preparedStatement.setString(2, uri);
                        preparedStatement.executeUpdate();
                        smid = smList.get(0);
                }
			
            } 
            catch (Exception e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);
            } 
            finally {
                close(connection, preparedStatement, resultSet);
            }
            
            return smid;
            
	}

	public String getUrlPrefixFromSMid(String smID) throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {

                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());

                preparedStatement = connection
                                .prepareStatement("SELECT urlPrefix FROM "  + SMTABLE + " WHERE id= ? ;");

                preparedStatement.setString(1, smID);

                resultSet = preparedStatement.executeQuery();

                if(resultSet.next()) {
                        return resultSet.getString("urlPrefix"); //
                }

                return null;
            } catch (Exception e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));    
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);
                    throw e;
            } finally {
                    close(connection, preparedStatement, resultSet);
            }
	}
	
	public String getUrlPrefixFromIp(String ip) throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());

                preparedStatement = connection
                                .prepareStatement("SELECT urlPrefix FROM "  + SMTABLE + " WHERE ip= ? ;");

                preparedStatement.setString(1, ip);

                resultSet = preparedStatement.executeQuery();

                if(resultSet!=null) {
                        return resultSet.getString("urlPrefix"); 			}

                return null;
            } catch (Exception e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));    
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);             
                    throw e;
            } finally {
                    close(connection, preparedStatement, resultSet);
            }
	}

	public List<Graph> getGraphs(String smId) throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;            
            List<Graph> graphs = new ArrayList<>();
            try {
                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());

                preparedStatement = connection.prepareStatement("SELECT uri, boundingBox FROM " 
                                + GRAPHTABLE + " JOIN "  + SMGRAPHTABLE + " ON " + GRAPHTABLE + ".id="
                                + SMGRAPHTABLE + ".graph" + " WHERE serviceMap=?");

                preparedStatement.setString(1, smId);

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                        Graph g = new Graph();
                        g.setUri(URI.create(resultSet.getString("uri")));
                        String recon = resultSet.getString("boundingBox");
                        if (recon != null) {
                                g.setBoundingBox((Polygon) new WKTReader().read(recon));
                        } else {
                                g.setBoundingBox(null);
                        }
                        g.setDependencies(getDependency(g.getUri()));
                        graphs.add(g);
                }
                return graphs;
            } catch (SQLException e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));    
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);
                    throw e;
            } finally {
                    close(connection, preparedStatement, resultSet);
            }
	}

	public List<Graph> getIntersectingGraphs(Geometry competenceArea) throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;            
            List<Graph> graphs = new ArrayList<>();
            try {
                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());
                preparedStatement = connection.prepareStatement("SELECT * FROM "  + GRAPHTABLE);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Geometry tmpg = unmarshal(resultSet.getString("boundingBox"));
                    if (competenceArea.intersects(tmpg)) {
                        // TODO check intersections here... check sw validity
                        Graph g = new Graph();
                        g.setUri(URI.create(resultSet.getString("uri")));
                        String recon = resultSet.getString("boundingBox");
                        if (recon != null) {
                                g.setBoundingBox((Polygon) new WKTReader().read(recon));
                        } else {
                                g.setBoundingBox(null);
                        }
                        g.setDependencies(getDependency(g.getUri()));
                        graphs.add(g);
                    }
                }
            } catch (SQLException e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));    
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);
                    throw e;
            } finally {
                    close(connection, preparedStatement, resultSet);
            }
            /*
             * for(int i=0; i<graphs.size();i++) {
             * System.out.println(graphs.get(i).getUri()); }
             */
            return graphs;
	}
		
	public List<String> getIPIntersectingServicempas(Geometry competenceArea) throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;		
            List<String> ips = new ArrayList<>();
            try {
                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());
                preparedStatement = connection.prepareStatement("SELECT * FROM "  + SMTABLE);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {

                    Geometry tmpg = unmarshal(resultSet.getString("competenceArea"));
                    if (competenceArea.intersects(tmpg)) {
                        ips.add(resultSet.getString("ip"));
                    }
                }
            } catch (SQLException e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));    
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);                
                    throw e;
            } finally {
                    close(connection, preparedStatement, resultSet);
            }
            return ips;
	}
	
	

	public List<String> getGraphOwner(String graph) throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;            
            List<String> ips = new ArrayList<>();
            try {
                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());
                preparedStatement = connection.prepareStatement("SELECT DISTINCT " + SMTABLE + ".ip FROM " 
                                + SMTABLE + " JOIN "  + SMGRAPHTABLE + " ON " + SMTABLE + ".id=" + SMGRAPHTABLE
                                + ".serviceMap" + " JOIN "  + GRAPHTABLE + " ON " + GRAPHTABLE + ".id="
                                + SMGRAPHTABLE + ".graph" + " WHERE " + GRAPHTABLE + ".uri=?");
                preparedStatement.setString(1, graph);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    ips.add(resultSet.getString("ip"));
                }
                return ips;
            } catch (SQLException e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));    
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);                
                    throw e;
            } finally {
                close(connection, preparedStatement, resultSet);
            }
	}

	public List<String> getCompetenceGraphs(Geometry wkt) throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;            
            List<String> graphs = new ArrayList<>();
            try {
                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());
                preparedStatement = connection
                                .prepareStatement("SELECT uri, boundingBox FROM "  + GRAPHTABLE);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Polygon bb = (Polygon) new WKTReader().read(resultSet.getString("boundingBox"));
                    if (wkt.intersects(bb)) {
                        graphs.add(resultSet.getString("uri"));
                    }
                }
                return graphs;
            } catch (SQLException e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));    
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);                
                    throw e;
            } finally {
                    close(connection, preparedStatement, resultSet);
            }
	}

	public List<String> getResponsibles(Geometry wkt) throws Exception {
            List<String> ips = new ArrayList<>();
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());

                preparedStatement = connection
                                .prepareStatement("SELECT ip, competenceArea FROM "  + SMTABLE);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Geometry ca = new WKTReader().read(resultSet.getString("competenceArea"));
                    if (ca.intersects(wkt)) {
                        ips.add(resultSet.getString("ip"));
                    }
                }
                return ips;
            } catch (SQLException e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));    
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);                
                    throw e;
            } finally {
                close(connection, preparedStatement, resultSet);
            }
	}
	
	public List<String> getResponsiblesFormattedForUrl(Geometry wkt) throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;            
            List<String> ips = new ArrayList<>();
            try {
                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());

                preparedStatement = connection
                                .prepareStatement("SELECT ip, competenceArea FROM "  + SMTABLE);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Geometry ca = new WKTReader().read(resultSet.getString("competenceArea"));
                    if (ca.intersects(wkt)) {
                        ips.add("http://"+resultSet.getString("ip")+":8080/ServiceMap");
                    }
                }
                return ips;
            } catch (SQLException e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));    
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);                
                    throw e;
            } finally {
                close(connection, preparedStatement, resultSet);
            }
	}
	
	
	public List<String> getResponsiblesUrlPrefix(Geometry wkt) throws Exception {
            List<String> ips = new ArrayList<>();
            List<String> allIps = new ArrayList<>();
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;            
            try {
                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());

                preparedStatement = connection
                                .prepareStatement("SELECT urlPrefix, competenceArea FROM "  + SMTABLE);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Geometry ca = new WKTReader().read(resultSet.getString("competenceArea"));
                    if (wkt != null && ca.intersects(wkt)) {
                        ips.add(resultSet.getString("urlPrefix"));
                    }
                    allIps.add(resultSet.getString("urlPrefix"));
                }
                return ips.size() > 0 ? ips : allIps;
            } catch (SQLException e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));    
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);                
                    throw e;
            } finally {
                close(connection, preparedStatement, resultSet);
            }
	}

        public List<String> getResponsiblesUrlPrefix(Geometry wkt, String api, String format) throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            List<String> ips = new ArrayList<>();
            List<String> allIps = new ArrayList<>();
            try {

                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());

                preparedStatement = connection
                                .prepareStatement("SELECT urlPrefix, competenceArea FROM "  + SMTABLE + " sm left join " + SMPTABLE + " smp on smp.api = '"+api+"' and smp.format = '"+format+"' and smp.servicemaps_id = sm.id where coalesce(smp.priority,0) <> -1 order by smp.priority desc");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Geometry ca = new WKTReader().read(resultSet.getString("competenceArea"));
                    if (wkt != null && ca.intersects(wkt)) {
                        ips.add(resultSet.getString("urlPrefix"));
                    }
                    allIps.add(resultSet.getString("urlPrefix"));
                }
                return ips.size() > 0 ? ips : allIps;
            } catch (SQLException e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));    
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);                
                    throw e;
            } finally {
                close(connection, preparedStatement, resultSet);
            }
	}
                
	public List<URI> getDependency(URI graphUri) throws Exception {
            // Used local connection, preparedStatement, and resultSet instead of global ones.
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;            
            try {	
                List<URI> depens = new ArrayList<>();
                    
                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());

                preparedStatement = connection.prepareStatement("SELECT uri FROM " 
                                + DEPENTABLE + " WHERE graph=" + "(SELECT id FROM "  + GRAPHTABLE + " WHERE uri=?);");
                preparedStatement.setString(1, graphUri.toString());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    depens.add(URI.create(resultSet.getString("uri")));
                }
                return depens;
            } catch (SQLException e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));    
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);                
                    throw e;
            } finally {
                close(connection, preparedStatement, resultSet);
            }
	}

	public void insert(ServiceMap sm) throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;         
            try {

                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());

                preparedStatement = connection
                                .prepareStatement("INSERT INTO "  + SMTABLE + " VALUES (?, ?, ?, ?)");
                preparedStatement.setString(1, sm.getId());
                preparedStatement.setString(2, sm.getIp().toString());
                preparedStatement.setString(3, sm.getCompetenceArea().toText());
                preparedStatement.setString(4, sm.getIp().toString());
                preparedStatement.executeUpdate();
                for (Graph g : sm.getGraphs()) {
                        insertGraph(sm.getId(), g.getUri());
                }
            } catch (Exception e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));   
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);                
                throw e;
            } finally {
                close(connection, preparedStatement, null);
            }
	}

	public void insertCache(String uri, String id) throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;	
            try {
                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());

                preparedStatement = connection
                                .prepareStatement("INSERT INTO "  + CACHETABLE + "(serviceUri, idServiceMap, insertTime, lastAccessTime) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE idServiceMap = ?, insertTime = ?, lastAccessTime = ?");
                preparedStatement.setString(1, uri);
                preparedStatement.setString(2, id);
                preparedStatement.setInt(3, (int) (System.currentTimeMillis() / 1000L));
                preparedStatement.setInt(4, (int) (System.currentTimeMillis() / 1000L));
                preparedStatement.setString(5, id);
                preparedStatement.setInt(6, (int) (System.currentTimeMillis() / 1000L));
                preparedStatement.setInt(7, (int) (System.currentTimeMillis() / 1000L));
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));       
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);                
                    throw e;
            } finally {
                close(connection, preparedStatement, null);
            }
	}
        
        public void insertCache(String uri, String id, boolean temporary) throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;	
            try {
                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());

                preparedStatement = connection
                                .prepareStatement("INSERT INTO "  + CACHETABLE + "(serviceUri, idServiceMap, insertTime, lastAccessTime, temporary) VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE idServiceMap = ?, insertTime = ?, lastAccessTime = ?");
                preparedStatement.setString(1, uri);
                preparedStatement.setString(2, id);
                preparedStatement.setInt(3, (int) (System.currentTimeMillis() / 1000L));
                preparedStatement.setInt(4, (int) (System.currentTimeMillis() / 1000L));
                preparedStatement.setBoolean(5, temporary);
                preparedStatement.setString(6, id);
                preparedStatement.setInt(7, (int) (System.currentTimeMillis() / 1000L));
                preparedStatement.setInt(8, (int) (System.currentTimeMillis() / 1000L));                        
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));       
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);                
                    throw e;
            } finally {
                    close(connection, preparedStatement, null);
            }
	}

	public void insertGraph(String smId, URI uri) throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;            
            try {
                // It checks if the couple (serviceMap, graph) already exists
                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());

                preparedStatement = connection.prepareStatement(
                                "SELECT COUNT(*) AS c FROM "  + SMGRAPHTABLE + " WHERE serviceMap=? AND graph="
                                                + "(SELECT id FROM "  + GRAPHTABLE + " WHERE uri=?)");
                preparedStatement.setString(1, smId);
                preparedStatement.setString(2, uri.toString());
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                if (Integer.parseInt(resultSet.getString("c")) == 0) {
                        // It checks if the graph already exists in the graph table
                        preparedStatement = connection
                                        .prepareStatement("SELECT id FROM "  + GRAPHTABLE + " WHERE uri=?");
                        preparedStatement.setString(1, uri.toString());
                        resultSet = preparedStatement.executeQuery();
                        if (!resultSet.next()) {
                                preparedStatement = connection.prepareStatement(
                                                "INSERT INTO "  + GRAPHTABLE + " VALUES (default, ?, ?, default)");
                                preparedStatement.setString(1, uri.toString());
                                preparedStatement.setString(2, null);
                                preparedStatement.executeUpdate();

                                preparedStatement = connection.prepareStatement("INSERT INTO "  + SMGRAPHTABLE
                                                + " VALUES (default, ?, (SELECT LAST_INSERT_ID()))");
                                preparedStatement.setString(1, smId);
                                preparedStatement.executeUpdate();
                        } else {
                                preparedStatement = connection.prepareStatement(
                                                "INSERT INTO "  + SMGRAPHTABLE + " VALUES (default, ?, ?)");
                                preparedStatement.setString(1, smId);
                                preparedStatement.setString(2, resultSet.getString("id"));
                                preparedStatement.executeUpdate();
                        }
                }
            } catch (Exception e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));       
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);                
                    throw e;
            } finally {
                close(connection, preparedStatement, resultSet);
            }
	}

	public void insertBoundingBox(URI uri, Polygon wkt) throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;           
            try {
                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());

                preparedStatement = connection
                                .prepareStatement("UPDATE "  + GRAPHTABLE + " SET boundingBox=? WHERE uri=?");
                preparedStatement.setString(1, wkt.toText());
                preparedStatement.setString(2, uri.toString());
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));       
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);                
                    throw e;
            } finally {
                close(connection, preparedStatement, null);
            }
	}

	public void remove(String smId) throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;          
            try {
                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());

                preparedStatement = connection
                                .prepareStatement("DELETE FROM "  + SMGRAPHTABLE + " WHERE serviceMap=? ;");
                preparedStatement.setString(1, smId);
                preparedStatement.executeUpdate();

                preparedStatement = connection
                                .prepareStatement("DELETE FROM "  + SMTABLE + " WHERE id=? ;");
                preparedStatement.setString(1, smId);
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));       
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);                
                    throw e;
            } finally {
                close(connection, preparedStatement, null);
            }
	}

	public void removeGraph(String smId, URI uri) throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;         
            try {
                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());

                preparedStatement = connection
                                .prepareStatement("DELETE FROM "  + SMGRAPHTABLE + " WHERE serviceMap=? AND graph="
                                                + "(SELECT id FROM "  + GRAPHTABLE + " WHERE uri=?);");
                preparedStatement.setString(1, smId);
                preparedStatement.setString(2, uri.toString());
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));       
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);                
                    throw e;
            } finally {
                close(connection, preparedStatement, null);
            }
	}

	public void updateId(String actualSmId, String ip, String newId) throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;           
            try {
                // connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());

                preparedStatement = connection
                                .prepareStatement("INSERT INTO "  + SMTABLE + " VALUES (?, ?)");
                preparedStatement.setString(1, newId);
                preparedStatement.setString(2, ip);
                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement(
                                "UPDATE "  + SMGRAPHTABLE + " SET serviceMap=? WHERE serviceMap=?");
                preparedStatement.setString(1, newId);
                preparedStatement.setString(2, actualSmId);
                preparedStatement.executeUpdate();

                preparedStatement = connection
                                .prepareStatement("DELETE FROM "  + SMTABLE + " WHERE id=? ;");
                preparedStatement.setString(1, actualSmId);
                preparedStatement.executeUpdate();
            } catch (Exception e) {

                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));   
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);                
                    throw e;
            } finally {
                close(connection, preparedStatement, null);
            }
	}

	public void updateGraph(String smId, URI actualUri, URI newUri) throws Exception {
            try {
                removeGraph(smId, actualUri);
                insertGraph(smId, newUri);
            } catch (Exception e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));       
                e.printStackTrace();
                    throw e;
            } 
	}

	private List<ServiceMap> getQueryResult(ResultSet rs) throws Exception {
		List<ServiceMap> smList = new ArrayList<>();
		while (rs.next()) {
			ServiceMap sm = new ServiceMap();
			sm.setId(rs.getString("id"));
			sm.setIp(InetAddress.getByName(rs.getString("ip")));
			sm.setGraphs(getGraphs(sm.getId()));
			sm.setCompetenceArea(new WKTReader().read(rs.getString("competenceArea")));
			smList.add(sm);
		}
		return smList;
	}

	
	private void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws Exception {
		try {
                    if (resultSet != null) {
			resultSet.close();
                    }	
                    if (preparedStatement != null) {
			preparedStatement.close();
                    }
                    if (connection != null) {
                        connection.close(); 
                    }
		} catch (Exception e) {
                    System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));       
                    e.printStackTrace();
			throw e;
		}
	}

	public static Geometry unmarshal(String wkt) throws Exception {
		return wkt != null ? new WKTReader().read(wkt) : null;
	}

	public JSONArray getMergeJson(ArrayList<JSONArray> xyz) throws JSONException {
            JSONArray result = null;
            JSONObject obj = new JSONObject();
            obj.put("key", result);
            for (JSONArray tmp : xyz) {
                for (int i = 0; i < tmp.length(); i++) {
                    obj.append("key", tmp.getJSONObject(i));				
                }
            }
            return obj.getJSONArray("key");
	}
        
        public String getOrgBbox(String org) throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                //connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());
                preparedStatement = connection.prepareStatement("SELECT bbox FROM " + ORGMETA + " WHERE org = ?");
                preparedStatement.setString(1, org);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    return resultSet.getString("bbox");                    
                }
                else {                          
                    //close(connection, preparedStatement, resultSet);
                    //connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());
                    preparedStatement = connection.prepareStatement("SELECT bbox FROM " + ORGMETA + " WHERE org IS NULL");
                    resultSet = preparedStatement.executeQuery();
                    resultSet.next();
                    return resultSet.getString("bbox");
                }
            } catch (SQLException e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);                
                    throw e;
            } finally {
                close(connection, preparedStatement, resultSet);
            }
	}
        
        public String getOrgTZ(String org) throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                //connection = DriverManager.getConnection("jdbc:mysql://" + IPADDRESS + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false&serverTimezone=UTC");
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());
                preparedStatement = connection.prepareStatement("SELECT tz FROM " + ORGMETA + " WHERE org = ?");
                preparedStatement.setString(1, org);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    return resultSet.getString("tz");                    
                }
                else {      
                    //close(connection, preparedStatement, resultSet);
                    //connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());
                    preparedStatement = connection.prepareStatement("SELECT tz FROM " + ORGMETA + " WHERE org IS NULL");
                    resultSet = preparedStatement.executeQuery();
                    resultSet.next();
                    return resultSet.getString("tz");
                }
            } catch (SQLException e) {
                System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
                e.printStackTrace();
                System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                System.out.println(preparedStatement);                
                    throw e;
            } finally {
                close(connection, preparedStatement, resultSet);
            }
	}
        
        public boolean getAuth(String prefix) throws Exception {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            boolean doAuth = true;
            try {
                connection = ConnectionPool.getConnection(new Object(){}.getClass().getEnclosingMethod().getName());
                preparedStatement = connection.prepareStatement("SELECT auth FROM "  + SMTABLE + " where urlPrefix = ? ");
                preparedStatement.setString(1, prefix);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    doAuth = resultSet.getBoolean("auth");
                }
                
            } catch (SQLException e) {
                if(!"Unknown column 'auth' in 'field list'".equals(e.getMessage())) {
                    System.out.println(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
                    e.printStackTrace();
                    System.out.println("THE SUBMITTED QUERY FOLLOWS (REMARK: IF THE FORMAL PARAMETERS ARE NOT REPLACED BY THE ACTUAL PARAMETERS IN THE QUERY BELOW HERE, IT IS VERY LIKELY THAT A CONNECTION ISSUE OCCURRED BEFORE THAT THE QUERY COULD BE ACTUALLY SUBMITTED):");
                    System.out.println(preparedStatement);
                }
            } 
            finally{
                close(connection, preparedStatement, resultSet);
            }
            return doAuth;
	}
          
}
