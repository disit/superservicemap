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

import java.net.URI;
import java.net.URLEncoder;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import static resource.ApiV1Resource.parseDate;

/**
 *
 * @author disit
 */
public class WfsServer {
    
    public static Response getCapabilities(String serverVersion, String serverUrl, double lowerCornerLong, double lowerCornerLat, double upperCornerLong, double upperCornerLat, String t) {
        
        return Response.ok("<?xml version=\"1.0\"?>\n"
                  + "<WFS_Capabilities\n"
                  + "version=\"" + serverVersion + "\"\n"
                  + "xmlns=\"http://www.opengis.net/wfs/2.0\"\n"
                  + "xmlns:gml=\"http://www.opengis.net/gml/3.2\"\n"
                  + "xmlns:fes=\"http://www.opengis.net/fes/2.0\"\n"
                  + "xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n"
                  + "xmlns:ows=\"http://www.opengis.net/ows/1.1\"\n"
                  + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"\n"
                  + "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
                  + "xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0\n"
                  + "http://schemas.opengis.net/wfs/2.0/wfs.xsd\n"
                  + "http://www.opengis.net/ows/1.1\n"
                  + "http://schemas.opengis.net/ows/1.1.0/owsAll.xsd\">\n"
                  + "<ows:ServiceIdentification>\n"
                  + "<ows:Title>Snap4City Web Feature Service</ows:Title>\n"
                  + "<ows:Abstract>Web Feature Service maintained by DISIT Lab, Department of Information Engineering (DINFO), University of Florence (UNIFI), Italy, in the context of the Snap4City project. See http://www.snap4city.org/. Contact paolo.nesi@unifi.it</ows:Abstract>\n"
                  + "<ows:Keywords>\n"
                  + "<ows:Keyword>DISIT</ows:Keyword>\n"
                  + "<ows:Keyword>DINFO</ows:Keyword>\n"
                  + "<ows:Keyword>UNIFI</ows:Keyword>\n"
                  + "<ows:Keyword>Snap4City</ows:Keyword>\n"
                  + "<ows:Type>String</ows:Type>\n"
                  + "</ows:Keywords>\n"
                  + "<ows:ServiceType>WFS</ows:ServiceType>\n"
                  + "<ows:ServiceTypeVersion>" + serverVersion + "</ows:ServiceTypeVersion>\n"
                  + "<ows:Fees>NONE</ows:Fees>\n"
                  + "<ows:AccessConstraints>NONE</ows:AccessConstraints>\n"
                  + "</ows:ServiceIdentification>\n"
                  + "<ows:ServiceProvider>\n"
                  + "<ows:ProviderName>DISIT Lab</ows:ProviderName>\n"
                  + "<ows:ProviderSite xlink:href=\"http://www.disit.org/\"/>\n"
                  + "<ows:ServiceContact>\n"
                  + "<ows:IndividualName>Paolo Nesi</ows:IndividualName>\n"
                  + "<ows:PositionName>Lab Chair</ows:PositionName>\n"
                  + "<ows:ContactInfo>\n"
                  + "<ows:Phone>\n"
                  + "<ows:Voice>+39 055 275 8515</ows:Voice>\n"
                  + "</ows:Phone>\n"
                  + "<ows:Address>\n"
                  + "<ows:DeliveryPoint>Via di Santa Marta, 3</ows:DeliveryPoint>\n"
                  + "<ows:City>Florence</ows:City>\n"
                  + "<ows:AdministrativeArea>Florence</ows:AdministrativeArea>\n"
                  + "<ows:PostalCode>50139</ows:PostalCode>\n"
                  + "<ows:Country>Italy</ows:Country>\n"
                  + "<ows:ElectronicMailAddress>paolo.nesi@unifi.it</ows:ElectronicMailAddress>\n"
                  + "</ows:Address>\n"
                  + "<ows:OnlineResource xlink:href=\"https://www.disit.org/drupal/?q=node/4496\"/>\n"
                  + "<ows:HoursOfService>Mon-Fry 8:00 AM - 8:00 PM</ows:HoursOfService>\n"
                  + "<ows:ContactInstructions>\n"
                  + "Please refer to the Web page https://www.disit.org/drupal/?q=node/4496 as the primary source of information about how to contact us.\n"
                  + "</ows:ContactInstructions>\n"
                  + "</ows:ContactInfo>\n"
                  + "<ows:Role>PointOfContact</ows:Role>\n"
                  + "</ows:ServiceContact>\n"
                  + "</ows:ServiceProvider>\n"
                  + "<ows:OperationsMetadata>\n"
                  + "<ows:Operation name=\"GetCapabilities\">\n"
                  + "<ows:DCP>\n"
                  + "<ows:HTTP>\n"
                  + "<ows:Get xlink:href=\"" + serverUrl + "\"/>\n"
                  + "<ows:Post xlink:href=\"" + serverUrl + "\"/>\n"
                  + "</ows:HTTP>\n"
                  + "</ows:DCP>\n"
                  + "<ows:Parameter name=\"AcceptVersions\">\n"
                  + "<ows:AllowedValues>\n"
                  + "<ows:Value>" + serverVersion + "</ows:Value>\n"
                  + "</ows:AllowedValues>\n"
                  + "</ows:Parameter>\n"
                  + "</ows:Operation>\n"
                  + "<ows:Operation name=\"DescribeFeatureType\">\n"
                  + "<ows:DCP>\n"
                  + "<ows:HTTP>\n"
                  + "<ows:Get xlink:href=\"" + serverUrl + "\"/>\n"
                  + "<ows:Post xlink:href=\"" + serverUrl + "\"/>\n"
                  + "</ows:HTTP>\n"
                  + "</ows:DCP>\n"
                  + "</ows:Operation>\n"
                  + "<ows:Operation name=\"ListStoredQueries\">\n"
                  + "<ows:DCP>\n"
                  + "<ows:HTTP>\n"
                  + "<ows:Get xlink:href=\"" + serverUrl + "\"/>\n"
                  + "<ows:Post xlink:href=\"" + serverUrl + "\"/>\n"
                  + "</ows:HTTP>\n"
                  + "</ows:DCP>\n"
                  + "</ows:Operation>\n"
                  + "<ows:Operation name=\"DescribeStoredQueries\">\n"
                  + "<ows:DCP>\n"
                  + "<ows:HTTP>\n"
                  + "<ows:Get xlink:href=\"" + serverUrl + "\"/>\n"
                  + "<ows:Post xlink:href=\"" + serverUrl + "\"/>\n"
                  + "</ows:HTTP>\n"
                  + "</ows:DCP>\n"
                  + "</ows:Operation>\n"
                  + "<ows:Operation name=\"GetFeature\">\n"
                  + "<ows:DCP>\n"
                  + "<ows:HTTP>\n"
                  + "<ows:Get xlink:href=\"" + serverUrl + "\"/>\n"
                  + "<ows:Post xlink:href=\"" + serverUrl + "\"/>\n"
                  + "</ows:HTTP>\n"
                  + "</ows:DCP>\n"
                  + "</ows:Operation>\n"
                  + "<ows:Parameter name=\"version\">\n"
                  + "<ows:AllowedValues>\n"
                  + "<ows:Value>" + serverVersion + "</ows:Value>\n"
                  + "</ows:AllowedValues>\n"
                  + "</ows:Parameter>\n"
                  + "<!-- ***************************************************** -->\n"
                  + "<!-- * CONFORMANCE DECLARATION * -->\n"
                  + "<!-- ***************************************************** -->\n"
                  + "<ows:Constraint name=\"ImplementsBasicWFS\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</ows:Constraint>\n"
                  + "<ows:Constraint name=\"ImplementsTransactionalWFS\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</ows:Constraint>\n"
                  + "<ows:Constraint name=\"ImplementsLockingWFS\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</ows:Constraint>\n"
                  + "<ows:Constraint name=\"KVPEncoding\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>TRUE</ows:DefaultValue>\n"
                  + "</ows:Constraint>\n"
                  + "<ows:Constraint name=\"XMLEncoding\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</ows:Constraint>\n"
                  + "<ows:Constraint name=\"SOAPEncoding\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</ows:Constraint>\n"
                  + "<ows:Constraint name=\"ImplementsInheritance\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</ows:Constraint>\n"
                  + "<ows:Constraint name=\"ImplementsRemoteResolve\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</ows:Constraint>\n"
                  + "<ows:Constraint name=\"ImplementsResultPaging\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</ows:Constraint>\n"
                  + "<ows:Constraint name=\"ImplementsStandardJoins\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</ows:Constraint>\n"
                  + "<ows:Constraint name=\"ImplementsSpatialJoins\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</ows:Constraint>\n"
                  + "<ows:Constraint name=\"ImplementsTemporalJoins\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</ows:Constraint>\n"
                  + "<ows:Constraint name=\"ImplementsFeatureVersioning\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</ows:Constraint>\n"
                  + "<ows:Constraint name=\"ManageStoredQueries\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</ows:Constraint>\n"
                  + "<!-- ***************************************************** -->\n"
                  + "<!-- * CAPACITY CONSTRAINTS * -->\n"
                  + "<!-- ***************************************************** -->\n"
                  + "<ows:Constraint name=\"CountDefault\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>1000</ows:DefaultValue>\n"
                  + "</ows:Constraint>\n"
                  + "<ows:Constraint name=\"QueryExpressions\">\n"
                  + "<ows:AllowedValues>\n"
                  + "<ows:Value>wfs:StoredQuery</ows:Value>\n"
                  + "</ows:AllowedValues>\n"
                  + "</ows:Constraint>\n"
                  + "<!-- ***************************************************** -->\n"
                  + "</ows:OperationsMetadata>\n"
                  + "<FeatureTypeList>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:Accommodation</Name><Title>Accommodation</Title><Abstract>Hotels and similar structures.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:Advertising</Name><Title>Advertising</Title><Abstract>Advertising-related services.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:AgricultureAndLivestock</Name><Title>AgricultureAndLivestock</Title><Abstract>Activities and services relating to agriculture and livestock farming.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:" + t + "Air_quality_monitoring_station</Name><Title>" + t + "Air_quality_monitoring_station</Title><Abstract>The instances of this class are dust-level detectors in the air. Each instance represents a specific detector, of a specific type, located in a specific location located through a pair of geospatial coordinates. On the other hand, for each detector there is at most one instance representing it.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:Bike_rack</Name><Title>Bike_rack</Title><Abstract>Each instance of this class represents a bike rack.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:BusStop</Name><Title>Bus Stop</Title><Abstract>Business activities, services to the citizen, offices, services in general, which can be located at one point.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:CarParkSensor</Name><Title>CarParkSensor</Title><Abstract>Sensor collecting data inside a parking lot.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:Charging_stations</Name><Title>Charging_stations</Title><Abstract>The instances of this class represent, among all business activities, certain supply of columns for charging power supplies and related services.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:CivilAndEdilEngineering</Name><Title>CivilAndEdilEngineering</Title><Abstract>Services related to civil and construction engineering.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:CulturalActivity</Name><Title>CulturalActivity</Title><Abstract>Libraries, archives, museums and other cultural activities.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:EducationAndResearch</Name><Title>EducationAndResearch</Title><Abstract>Services such as schools for all ages and training schools.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:Emergency</Name><Title>Emergency</Title><Abstract>Any sort of emergency services.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:Entertainment</Name><Title>Entertainment</Title><Abstract>Entertainment services for the citizen.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:Environment</Name><Title>Environment</Title><Abstract>Environmentally friendly services.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:Ferry_stop</Name><Title>Ferry_stop</Title><Abstract>The instances of this class are each a liner ferry port.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:FinancialService</Name><Title>FinancialService</Title><Abstract>Banks, monetary institutions and other financial services.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:First_aid</Name><Title>First_aid</Title><Abstract>The instances of this class represent, among all service locations, some places of First Aid.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:Fuel_station</Name><Title>Fuel_station</Title><Abstract>The instances of this class represent, among all business activities, certain retail trade activities of automotive fuel.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:GovernmentOffice</Name><Title>GovernmentOffice</Title><Abstract>Government offices open to the public.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:HealthCare</Name><Title>HealthCare</Title><Abstract>Hospitals, medical studios, analysis laboratories and other facilities providing health services.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:IndustryAndManufacturing</Name><Title>IndustryAndManufacturing</Title><Abstract>Services related to industry and work.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:IoTActuator</Name><Title>IoTActuator</Title><Abstract>The instances of this class represent each an actuator.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:IoTSensor</Name><Title>IoTSensor</Title><Abstract>Any type of sensing device.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:Irrigator</Name><Title>Irrigator</Title><Abstract>Each instance of this class represents an irrigator.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:MiningAndQuarring</Name><Title>MiningAndQuarring</Title><Abstract>Services related to mining and quarrying.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:Monitoring_camera</Name><Title>Monitoring_camera</Title><Abstract>Cameras used in surveillance systems.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:Noise_level_sensor</Name><Title>Noise_level_sensor</Title><Abstract>Device that detects noise pollution in the environment in which it is located.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:People_counter</Name><Title>People_counter</Title><Abstract>A device used to count people in the surrounding area.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:Pollen_monitoring_station</Name><Title>Pollen_monitoring_station</Title><Abstract>The instances of this class each represent an allergen level detector in the air.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:SensorSite</Name><Title>SensorSite</Title><Abstract>Traffic sensor releasing traffic info</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:Service</Name><Title>Service</Title><Abstract>Business activities, government offices, some sensing devices, and other services.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:Smart_waste_container</Name><Title>Smart_waste_container</Title><Abstract>Each instance of this class represents a garbage dump with advanced features.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:ShoppingAndService</Name><Title>ShoppingAndService</Title><Abstract>Shops, malls, stores, all forms of public sale activities.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:TourismService</Name><Title>TourismService</Title><Abstract>Activities of travel agency services, tour operators and booking services.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:TransferServiceAndRenting</Name><Title>TransferServiceAndRenting</Title><Abstract>Car parks, railway stations or buses, everything that must be located on a map and refers to transportation.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:Underpass</Name><Title>Underpass</Title><Abstract>The instances of this class represent each an underpass.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:UtilitiesAndSupply</Name><Title>UtilitiesAndSupply</Title><Abstract>Supply of utilities and services.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:Weather_sensor</Name><Title>Weather_sensor</Title><Abstract>Weather sensor releasing information on weather forecasts.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:Wholesale</Name><Title>Wholesale</Title><Abstract>Wholesale of anything.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:WineAndFood</Name><Title>WineAndFood</Title><Abstract>Restaurants, wine bars and all other food and wine activities.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "    <FeatureType xmlns:km4c=\"http://www.disit.org/km4city/schema#\"><Name>km4c:Event</Name><Title>Event</Title><Abstract>Events scheduled by the city of Florence and dusk.</Abstract><DefaultCRS>http://www.opengis.net/def/crs/EPSG/0/4326</DefaultCRS><ows:WGS84BoundingBox><ows:LowerCorner>" + lowerCornerLong + " " + lowerCornerLat + "</ows:LowerCorner><ows:UpperCorner>" + upperCornerLong + " " + upperCornerLat + "</ows:UpperCorner></ows:WGS84BoundingBox></FeatureType>\n"
                  + "</FeatureTypeList>\n"
                  + "<fes:Filter_Capabilities>\n"
                  + "<fes:Conformance>\n"
                  + "<fes:Constraint name=\"ImplementsQuery\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>TRUE</ows:DefaultValue>\n"
                  + "</fes:Constraint>\n"
                  + "<fes:Constraint name=\"ImplementsAdHocQuery\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</fes:Constraint>\n"
                  + "<fes:Constraint name=\"ImplementsFunctions\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</fes:Constraint>\n"
                  + "<fes:Constraint name=\"ImplementsMinStandardFilter\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</fes:Constraint>\n"
                  + "<fes:Constraint name=\"ImplementsStandardFilter\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</fes:Constraint>\n"
                  + "<fes:Constraint name=\"ImplementsMinSpatialFilter\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</fes:Constraint>\n"
                  + "<fes:Constraint name=\"ImplementsSpatialFilter\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</fes:Constraint>\n"
                  + "<fes:Constraint name=\"ImplementsMinTemporalFilter\">\n"
                  + "<ows:NoValues/>\n"
                  + "</fes:Constraint>\n"
                  + "<fes:Constraint name=\"ImplementsTemporalFilter\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</fes:Constraint>\n"
                  + "<fes:Constraint name=\"ImplementsVersionNav\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</fes:Constraint>\n"
                  + "<fes:Constraint name=\"ImplementsSorting\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</fes:Constraint>\n"
                  + "<fes:Constraint name=\"ImplementsExtendedOperators\">\n"
                  + "<ows:NoValues/>\n"
                  + "<ows:DefaultValue>FALSE</ows:DefaultValue>\n"
                  + "</fes:Constraint>\n"
                  + "</fes:Conformance>\n"
                  + "</fes:Filter_Capabilities>\n"
                  + "</WFS_Capabilities>").status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
        
    }
    
    public static Response describeAllFeatureTypes(String serverVersion, String t, String xx) {
        
        return Response.ok("<?xml version=\"1.0\" ?>\n"
                    + "<schema \n"
                    + "targetNamespace=\"http://www.disit.org/km4city/schema#\" \n"
                    + "xmlns:km4c=\"http://www.disit.org/km4city/schema#\" \n"
                    + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" \n"
                    + "xmlns=\"http://www.w3.org/2001/XMLSchema\" \n"
                    + "xmlns:gml=\"http://www.opengis.net/gml/3.2\" \n"
                    + "elementFormDefault=\"qualified\" version=\"" + serverVersion + "\"> \n"
                    + "<import namespace=\"http://www.opengis.net/gml/3.2\" schemaLocation=\"http://schemas.opengis.net/gml/3.2.1/gml.xsd\"/> \n\n"
                    + "<!-- =============================================\n"
                    + "define global elements\n"
                    + "============================================= -->\n"
                    + "\n"
                    + "<element name=\"km4c:Service\" type=\"km4c:Service\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:Accommodation\" type=\"km4c:Accommodation\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:Advertising\" type=\"km4c:Advertising\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:AgricultureAndLivestock\" type=\"km4c:AgricultureAndLivestock\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:CivilAndEdilEngineering\" type=\"km4c:CivilAndEdilEngineering\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:CulturalActivity\" type=\"km4c:CulturalActivity\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:EducationAndResearch\" type=\"km4c:EducationAndResearch\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:Emergency\" type=\"km4c:Emergency\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:Entertainment\" type=\"km4c:Entertainment\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:Environment\" type=\"km4c:Environment\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:FinancialService\" type=\"km4c:FinancialService\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:GovernmentOffice\" type=\"km4c:GovernmentOffice\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:HealthCare\" type=\"km4c:HealthCare\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:IndustryAndManufacturing\" type=\"km4c:IndustryAndManufacturing\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:IoTSensor\" type=\"km4c:IoTSensor\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:CarParkSensor\" type=\"km4c:CarParkSensor\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:Noise_level_sensor\" type=\"km4c:Noise_level_sensor\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:SensorSite\" type=\"km4c:SensorSite\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:Weather_sensor\" type=\"km4c:Weather_sensor\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:MiningAndQuarring\" type=\"km4c:MiningAndQuarring\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:ShoppingAndService\" type=\"km4c:ShoppingAndService\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:TourismService\" type=\"km4c:TourismService\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:TransferServiceAndRenting\" type=\"km4c:TransferServiceAndRenting\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:UtilitiesAndSupply\" type=\"km4c:UtilitiesAndSupply\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:Wholesale\" type=\"km4c:Wholesale\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:WineAndFood\" type=\"km4c:WineAndFood\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:Bike_rack\" type=\"km4c:Bike_rack\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:" + t + "Air_quality_monitoring_station\" type=\"km4c:" + t + "Air_quality_monitoring_station\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:First_aid\" type=\"km4c:First_aid\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:Charging_stations\" type=\"km4c:Charging_stations\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:Fuel_station\" type=\"km4c:Fuel_station\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:Monitoring_camera\" type=\"km4c:Monitoring_camera\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:People_counter\" type=\"km4c:People_counter\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:Pollen_monitoring_station\" type=\"km4c:Pollen_monitoring_station\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:Irrigator\" type=\"km4c:Irrigator\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:Smart_waste_container\" type=\"km4c:Smart_waste_container\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:Ferry_stop\" type=\"km4c:Ferry_stop\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:IoTActuator\" type=\"km4c:IoTActuator\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:Underpass\" type=\"km4c:Underpass\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:BusStop\" type=\"km4c:BusStop\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "<element name=\"km4c:Event\" type=\"km4c:Event\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "\n"
                    + "<!-- ============================================\n"
                    + "define complex types (classes)\n"
                    + "============================================ -->\n"
                    + "\n" + xx
                    + "<complexType name=\"Service\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"Accommodation\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"Advertising\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"AgricultureAndLivestock\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"CivilAndEdilEngineering\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n" + xx
                    + "\n"
                    + "<complexType name=\"CulturalActivity\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"EducationAndResearch\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"Emergency\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"Entertainment\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"Environment\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n" + xx
                    + "\n"
                    + "<complexType name=\"FinancialService\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"GovernmentOffice\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"HealthCare\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"IndustryAndManufacturing\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n" + xx
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"IoTSensor\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"CarParkSensor\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"Noise_level_sensor\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_measuredTime\" type=\"xsd:dateTime\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_nextTime\" type=\"xsd:dateTime\" />\n"                            
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_LAeq\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_LAmax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_dateObserved\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_dateObservedFrom\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_dateObservedTo\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_location\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_measurand\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_noiseLAeqAvg2h\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_sonometerClass\" type=\"xsd:string\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"SensorSite\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n" + xx
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"Weather_sensor\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_measuredTime\" type=\"xsd:dateTime\" />\n"      
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_nextTime\" type=\"xsd:dateTime\" />\n"                             
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_minGroundTemperature\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_minTemperature\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_visibility\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_dewPoint\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_snow10m\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_snow\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windGust\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_sunrise\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_weather\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_maxTemperature\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerc\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_snow3h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeed\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirection\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_rain1h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_sunset\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverOkta\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidity\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_snow1h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_pressure\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperature\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_rain\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_atmosfericPressureForecast_003h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_atmosfericPressureForecast_009h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_atmosfericPressureForecast_030h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerForecast_015h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerForecast_021h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerForecast_033h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_003h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_006h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_015h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_018h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_027h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirectionForecast_015h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirectionForecast_021h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirectionForecast_024h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidityForecast_003h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidityForecast_009h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidityForecast_024h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_024h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_030h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_057h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_003h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_009h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_012h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_018h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_021h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_024h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeedForecast_003h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeedForecast_009h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeedForecast_024h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_atmosfericPressureForecast_006h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_atmosfericPressureForecast_018h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_atmosfericPressureForecast_021h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_atmosfericPressureForecast_024h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerForecast_003h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerForecast_009h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerForecast_018h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerForecast_027h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_021h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_024h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_039h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirectionForecast_003h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirectionForecast_006h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirectionForecast_018h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirectionForecast_036h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidityForecast_012h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidityForecast_015h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidityForecast_021h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidityForecast_030h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_003h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_012h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_015h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_021h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_027h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_006h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_015h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_033h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_maxTemperatureForecast_006h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_maxTemperatureForecast_024h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_maxTemperatureForecast_027h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_minTemperatureForecast_006h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_minTemperatureForecast_012h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_minTemperatureForecast_021h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_minTemperatureForecast_024h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeedForecast_006h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeedForecast_015h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeedForecast_018h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeedForecast_033h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_atmosfericPressureForecast_012h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_atmosfericPressureForecast_015h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_atmosfericPressureForecast_033h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerForecast_006h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerForecast_012h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerForecast_024h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_009h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_012h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_033h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirectionForecast_009h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirectionForecast_012h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirectionForecast_057h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidityForecast_006h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidityForecast_018h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidityForecast_027h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_006h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_009h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_018h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_039h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_036h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_039h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_045h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_maxTemperatureForecast_012h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_maxTemperatureForecast_015h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_maxTemperatureForecast_045h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_minTemperatureForecast_009h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_minTemperatureForecast_015h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_minTemperatureForecast_039h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeedForecast_012h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeedForecast_021h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeedForecast_030h\" type=\"xsd:float\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"Bike_rack\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n" + xx
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"" + t + "Air_quality_monitoring_station\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_measuredTime\" type=\"xsd:dateTime\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_nextTime\" type=\"xsd:dateTime\" />\n"                             
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_EnfuserAQI\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_NO\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_NO2\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_O3\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_PM10\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_PM2_5\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_RealTimeAQI\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_RealTimeDeltaAQI\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_SO2\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityAQI\" type=\"xsd:integer\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityCO\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityNO\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityNO2\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityNOAvg2h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityO3\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM10\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM10AverageLastHour\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM10Avg2h\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM10Enfuser\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM10Gral\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM10RealTimeDelta\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM10RealTimeDeltaGral\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM2_5\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM2_5AverageLastHour\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM2_5Enfuser\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM2_5RealTimeDelta\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualitySO2\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityTRSC\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_dateObserved\" type=\"xsd:dateTime\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_dateObservedFrom\" type=\"xsd:dateTime\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_dateObservedTo\" type=\"xsd:dateTime\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_location\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_reliability\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_source\" type=\"xsd:anyURI\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"First_aid\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"Charging_stations\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n" + xx
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"Fuel_station\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"Monitoring_camera\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"People_counter\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n" + xx
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"Pollen_monitoring_station\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"Irrigator\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n" + xx
                    + "\n"
                    + "<complexType name=\"Smart_waste_container\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"MiningAndQuarring\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"ShoppingAndService\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n" + xx
                    + "\n"
                    + "<complexType name=\"TourismService\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"TransferServiceAndRenting\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"UtilitiesAndSupply\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"Wholesale\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n" + xx
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"WineAndFood\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"Ferry_stop\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"IoTActuator\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"Underpass\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "\n"
                    + "<complexType name=\"BusStop\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"agency\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"agencyUri\" type=\"xsd:anyURI\" />\n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>\n"
                    + "<complexType name=\"Event\">\n"
                    + "    <complexContent>\n"
                    + "        <extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <sequence>            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />     \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"startDate\" type=\"xsd:dateTime\" />     \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"startTime\" type=\"xsd:string\" />  \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"endDate\" type=\"xsd:dateTime\" />  \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"endTime\" type=\"xsd:string\" />  \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"eventCategory\" type=\"xsd:string\" />  \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"place\" type=\"xsd:string\" />  \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"freeEvent\" type=\"xsd:string\" />  \n"
                    + "            <element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"price\" type=\"xsd:string\" />  \n"
                    + "        </sequence>\n"
                    + "        </extension>\n"
                    + "    </complexContent>\n"
                    + "</complexType>       \n"
                    + "</schema>\n"
                    + "").status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
        
    }
    
    public static Response describeFeatureType(String typeName, String t) throws WfsException {
        if ("km4c:BusStop".equals(typeName)) {
              String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + //"<xsd:import namespace=\"http://www.opengis.net/gml/3.2\" schemaLocation=\"http://geoserv.weichand.de:8080/geoserver/schemas/gml/3.2.1/gml.xsd\"/>" +
                    "<xsd:complexType name=\"BusStopType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"agency\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"agencyUri\" type=\"xsd:anyURI\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"BusStop\" type=\"km4c:BusStopType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:Event".equals(typeName)) {

            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + //"<xsd:import namespace=\"http://www.opengis.net/gml/3.2\" schemaLocation=\"http://geoserv.weichand.de:8080/geoserver/schemas/gml/3.2.1/gml.xsd\"/>" +
                    "<xsd:complexType name=\"EventType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />     \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"startDate\" type=\"xsd:dateTime\" />     \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"startTime\" type=\"xsd:string\" />  \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"endDate\" type=\"xsd:dateTime\" />  \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"endTime\" type=\"xsd:string\" />  \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"eventCategory\" type=\"xsd:string\" />  \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"place\" type=\"xsd:string\" />  \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"freeEvent\" type=\"xsd:string\" />  \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"price\" type=\"xsd:string\" />  \n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"Event\" type=\"km4c:EventType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();

          } else if ("km4c:Service".equals(typeName)) {

            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + //"<xsd:import namespace=\"http://www.opengis.net/gml/3.2\" schemaLocation=\"http://geoserv.weichand.de:8080/geoserver/schemas/gml/3.2.1/gml.xsd\"/>" +
                    "<xsd:complexType name=\"ServiceType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"Service\" type=\"km4c:ServiceType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";

            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:Accommodation".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"AccommodationType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"Accommodation\" type=\"km4c:AccommodationType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:Advertising".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"AdvertisingType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"Advertising\" type=\"km4c:AdvertisingType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:Ferry_stop".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"Ferry_stopType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"Ferry_stop\" type=\"km4c:Ferry_stopType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:IoTActuator".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"IoTActuatorType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"IoTActuator\" type=\"km4c:IoTActuatorType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:Underpass".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"UnderpassType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"Underpass\" type=\"km4c:UnderpassType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:AgricultureAndLivestock".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"AgricultureAndLivestockType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"AgricultureAndLivestock\" type=\"km4c:AgricultureAndLivestockType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:CivilAndEdilEngineering".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"CivilAndEdilEngineeringType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"CivilAndEdilEngineering\" type=\"km4c:CivilAndEdilEngineeringType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:CulturalActivity".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"CulturalActivityType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"CulturalActivity\" type=\"km4c:CulturalActivityType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:EducationAndResearch".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"EducationAndResearchType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"EducationAndResearch\" type=\"km4c:EducationAndResearchType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:Emergency".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"EmergencyType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"Emergency\" type=\"km4c:EmergencyType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:Entertainment".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"EntertainmentType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"Entertainment\" type=\"km4c:EntertainmentType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:Environment".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"EnvironmentType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"Environment\" type=\"km4c:EnvironmentType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:FinancialService".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"FinancialServiceType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"FinancialService\" type=\"km4c:FinancialServiceType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:GovernmentOffice".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"GovernmentOfficeType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"GovernmentOffice\" type=\"km4c:GovernmentOfficeType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:HealthCare".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"HealthCareType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"HealthCare\" type=\"km4c:HealthCareType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:IndustryAndManufacturing".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"IndustryAndManufacturingType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"IndustryAndManufacturing\" type=\"km4c:IndustryAndManufacturingType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:IoTSensor".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"IoTSensorType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"IoTSensor\" type=\"km4c:IoTSensorType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:CarParkSensor".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"CarParkSensorType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"CarParkSensor\" type=\"km4c:CarParkSensorType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:Noise_level_sensor".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"Noise_level_sensorType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_measuredTime\" type=\"xsd:dateTime\" />\n"  
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_nextTime\" type=\"xsd:dateTime\" />\n"                     
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_LAeq\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_LAmax\" type=\"xsd:string\" />\n"                   
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_dateObserved\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_dateObservedFrom\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_dateObservedTo\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_location\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_measurand\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_noiseLAeqAvg2h\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_sonometerClass\" type=\"xsd:string\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"Noise_level_sensor\" type=\"km4c:Noise_level_sensorType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:SensorSite".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"SensorSiteType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"SensorSite\" type=\"km4c:SensorSiteType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:Weather_sensor".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"Weather_sensorType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_measuredTime\" type=\"xsd:dateTime\" />\n"      
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_nextTime\" type=\"xsd:dateTime\" />\n"                       
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_minGroundTemperature\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_minTemperature\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_visibility\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_dewPoint\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_snow10m\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_snow\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windGust\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_sunrise\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_weather\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_maxTemperature\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerc\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_snow3h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeed\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirection\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_rain1h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_sunset\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverOkta\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidity\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_snow1h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_pressure\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperature\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_rain\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_atmosfericPressureForecast_003h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_atmosfericPressureForecast_009h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_atmosfericPressureForecast_030h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerForecast_015h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerForecast_021h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerForecast_033h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_003h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_006h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_015h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_018h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_027h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirectionForecast_015h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirectionForecast_021h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirectionForecast_024h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidityForecast_003h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidityForecast_009h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidityForecast_024h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_024h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_030h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_057h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_003h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_009h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_012h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_018h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_021h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_024h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeedForecast_003h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeedForecast_009h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeedForecast_024h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_atmosfericPressureForecast_006h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_atmosfericPressureForecast_018h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_atmosfericPressureForecast_021h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_atmosfericPressureForecast_024h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerForecast_003h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerForecast_009h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerForecast_018h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerForecast_027h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_021h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_024h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_039h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirectionForecast_003h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirectionForecast_006h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirectionForecast_018h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirectionForecast_036h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidityForecast_012h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidityForecast_015h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidityForecast_021h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidityForecast_030h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_003h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_012h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_015h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_021h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_027h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_006h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_015h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_033h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_maxTemperatureForecast_006h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_maxTemperatureForecast_024h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_maxTemperatureForecast_027h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_minTemperatureForecast_006h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_minTemperatureForecast_012h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_minTemperatureForecast_021h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_minTemperatureForecast_024h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeedForecast_006h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeedForecast_015h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeedForecast_018h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeedForecast_033h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_atmosfericPressureForecast_012h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_atmosfericPressureForecast_015h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_atmosfericPressureForecast_033h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerForecast_006h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerForecast_012h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_cloudCoverPerForecast_024h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_009h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_012h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_seaLevelPressureForecast_033h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirectionForecast_009h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirectionForecast_012h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windDirectionForecast_057h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidityForecast_006h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidityForecast_018h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airHumidityForecast_027h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_006h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_009h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_018h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_groundLevelPressureForecast_039h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_036h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_039h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airTemperatureForecast_045h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_maxTemperatureForecast_012h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_maxTemperatureForecast_015h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_maxTemperatureForecast_045h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_minTemperatureForecast_009h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_minTemperatureForecast_015h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_minTemperatureForecast_039h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeedForecast_012h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeedForecast_021h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_windSpeedForecast_030h\" type=\"xsd:float\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"Weather_sensor\" type=\"km4c:Weather_sensorType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:Bike_rack".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"Bike_rackType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"Bike_rack\" type=\"km4c:Bike_rackType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if (("km4c:" + t + "Air_quality_monitoring_station").equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"" + t + "Air_quality_monitoring_stationType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_measuredTime\" type=\"xsd:dateTime\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_nextTime\" type=\"xsd:dateTime\" />\n"                       
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_EnfuserAQI\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_NO\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_NO2\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_O3\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_PM10\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_PM2_5\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_RealTimeAQI\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_RealTimeDeltaAQI\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_SO2\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityAQI\" type=\"xsd:integer\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityCO\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityNO\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityNO2\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityNOAvg2h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityO3\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM10\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM10AverageLastHour\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM10Avg2h\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM10Enfuser\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM10Gral\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM10RealTimeDelta\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM10RealTimeDeltaGral\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM2_5\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM2_5AverageLastHour\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM2_5Enfuser\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityPM2_5RealTimeDelta\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualitySO2\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_airQualityTRSC\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_dateObserved\" type=\"xsd:dateTime\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_dateObservedFrom\" type=\"xsd:dateTime\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_dateObservedTo\" type=\"xsd:dateTime\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_location\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_reliability\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"RT_source\" type=\"xsd:anyURI\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"" + t + "Air_quality_monitoring_station\" type=\"km4c:" + t + "Air_quality_monitoring_stationType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:First_aid".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"First_aidType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"First_aid\" type=\"km4c:First_aidType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:Charging_stations".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"Charging_stationsType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"Charging_stations\" type=\"km4c:Charging_stationsType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:Fuel_station".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"Fuel_stationType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"Fuel_station\" type=\"km4c:Fuel_stationType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:Monitoring_camera".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"Monitoring_cameraType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"Monitoring_camera\" type=\"km4c:Monitoring_cameraType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:People_counter".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"People_counterType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"People_counter\" type=\"km4c:People_counterType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:Pollen_monitoring_station".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"Pollen_monitoring_stationType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"Pollen_monitoring_station\" type=\"km4c:Pollen_monitoring_stationType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:Irrigator".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"IrrigatorType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"Irrigator\" type=\"km4c:IrrigatorType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:Smart_waste_container".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"Smart_waste_containerType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"Smart_waste_container\" type=\"km4c:Smart_waste_containerType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:MiningAndQuarring".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"MiningAndQuarringType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"MiningAndQuarring\" type=\"km4c:MiningAndQuarringType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:ShoppingAndService".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"ShoppingAndServiceType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"ShoppingAndService\" type=\"km4c:ShoppingAndServiceType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:TourismService".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"TourismServiceType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"TourismService\" type=\"km4c:TourismServiceType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:TransferServiceAndRenting".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"TransferServiceAndRentingType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"TransferServiceAndRenting\" type=\"km4c:TransferServiceAndRentingType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:UtilitiesAndSupply".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"UtilitiesAndSupplyType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"UtilitiesAndSupply\" type=\"km4c:UtilitiesAndSupplyType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:Wholesale".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"WholesaleType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"Wholesale\" type=\"km4c:WholesaleType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else if ("km4c:WineAndFood".equals(typeName)) {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.disit.org/km4city/schema#\">\n"
                    + "<xsd:complexType name=\"WineAndFoodType\">\n"
                    + "    <xsd:complexContent>\n"
                    + "        <xsd:extension base=\"gml:AbstractFeatureType\">\n"
                    + "        <xsd:sequence>            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"serviceUri\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"name\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"typeLabel\" type=\"xsd:string\" />       \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"1\" nillable=\"false\" name=\"geometry\" type=\"gml:PointPropertyType\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"city\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"cap\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"province\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"address\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"civic\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"phone\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"fax\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"website\" type=\"xsd:anyURI\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"email\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"note\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"description\" type=\"xsd:string\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"linkDBpedia\" type=\"xsd:anyURI\" />            \n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"avgStars\" type=\"xsd:float\" />\n"
                    + "            <xsd:element maxOccurs=\"1\" minOccurs=\"0\" nillable=\"true\" name=\"starsCount\" type=\"xsd:integer\" />\n"
                    + "        </xsd:sequence>\n"
                    + "        </xsd:extension>\n"
                    + "    </xsd:complexContent>\n"
                    + "</xsd:complexType>\n"
                    + "  <xsd:element name=\"WineAndFood\" type=\"km4c:WineAndFoodType\" substitutionGroup=\"gml:AbstractFeature\"/>\n"
                    + "</xsd:schema>";
            return Response.ok(response).status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/xml").header("Cache-Control", "no-cache").build();
          } else {
            throw new WfsException(WfsException.NOT_FOUND, typeName);
          }
        
    }
    
    public static Response listStoredQueries(String serverVersion, String serverUrl) {
        return Response.ok("<?xml version=\"1.0\" ?>\n<wfs:ListStoredQueriesResponse xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:fes=\"http://www.opengis.net/fes/2.0\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:ows=\"http://www.opengis.net/ows/1.1\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 " + serverUrl + "?service=WFS&amp;version=" + serverVersion + "&amp;request=DescribeFeatureType\"><wfs:StoredQuery id=\"urn:ogc:def:query:OGC-WFS::GetFeatureById\"><wfs:Title xml:lang=\"en\">Get feature by identifier</wfs:Title><wfs:ReturnFeatureType/></wfs:StoredQuery></wfs:ListStoredQueriesResponse>").status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/gml+xml;version=3.2").header("Cache-Control", "no-cache").build();
    }
    
    public static Response describeStoredQueries(String serverUrl, String serverVersion, String t) {
        return Response.ok("<?xml version=\"1.0\" ?>\n<wfs:DescribeStoredQueriesResponse xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:fes=\"http://www.opengis.net/fes/2.0\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:ows=\"http://www.opengis.net/ows/1.1\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 " + serverUrl + "?service=WFS&amp;version=" + serverVersion + "&amp;request=DescribeFeatureType\">\n"
                  + "<wfs:StoredQueryDescription id=\"urn:ogc:def:query:OGC-WFS::GetFeatureById\">\n"
                  + "<wfs:Title xml:lang=\"en\">Get feature by identifier</wfs:Title>\n"
                  + "<wfs:Parameter name=\"ID\" type=\"xs:string\"/><wfs:QueryExpressionText isPrivate=\"false\" language=\"urn:ogc:def:queryLanguage:OGC-WFS::WFSQueryExpression\" returnFeatureTypes=\"km4c:Service,km4c:Accommodation,km4c:Advertising,km4c:AgricultureAndLivestock,km4c:CivilAndEdilEngineering,km4c:CulturalActivity,km4c:EducationAndResearch,km4c:Emergency,km4c:Entertainment,km4c:Environment,km4c:FinancialService,km4c:GovernmentOffice,km4c:HealthCare,km4c:IndustryAndManufacturing,km4c:IoTSensor,km4c:CarParkSensor,km4c:Noise_level_sensor,km4c:SensorSite,km4c:Weather_sensor,km4c:Bike_rack,km4c:" + t + "Air_quality_monitoring_station,km4c:First_aid,km4c:Charging_stations,km4c:Fuel_station,km4c:Monitoring_camera,km4c:People_counter,km4c:Pollen_monitoring_station,km4c:Irrigator,km4c:Smart_waste_container,km4c:MiningAndQuarring,km4c:ShoppingAndService,km4c:TourismService,km4c:TransferServiceAndRenting,km4c:UtilitiesAndSupply,km4c:Wholesale,km4c:WineAndFood,km4c:BusStop,km4c:Event,km4c:Ferry_stop,km4c:IoTActuator,km4c:Underpass\"><fes:PropertyIsEqualTo>\n"
                  + "<fes:ValueReference>serviceUri</fes:ValueReference>\n"
                  + "<fes:Literal>${ID}</fes:Literal>\n"
                  + "</fes:PropertyIsEqualTo></wfs:QueryExpressionText></wfs:StoredQueryDescription>\n"
                  + "</wfs:DescribeStoredQueriesResponse>").status(200).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/gml+xml;version=3.2").header("Cache-Control", "no-cache").build();
    }
    
    public static Response getFeature(HttpServletRequest requestContext, String serverUrl, String serverVersion, String t,
            String timezoneSuffix, String selection, String organization, long tt, String pFilter, long ts, String jsonStr, 
            String nowAsISO, String pResultType, Response r, String pID) throws Exception {

            JSONObject jsonObj = new JSONObject(jsonStr);
            boolean isBusStop = false;
            boolean isEvent = false;
            boolean isFuelStation = false;
            boolean isUrbanBus = false;
            boolean isSmartWasteContainer = false;
            boolean isSmartBench = false;
            boolean isRoute = false;
            boolean isService = false;
            boolean isAirQualityMonitoringStation = false;
            boolean isWeather_sensor = false;

            boolean isFerry_stop = false;
            boolean isIoTActuator = false;
            boolean isIoTSensor = false;
            boolean isNoiseLevelSensor = false;
            boolean isPeople_counter = false;
            boolean isSensorSite = false;
            boolean isUnderpass = false;

            try {
              if (jsonObj.has("BusStop")) {
                isBusStop = true;
              }
            } catch (Exception ignore) {
            }
            try {
              if (jsonObj.has("Event")) {
                isEvent = true;
              }
            } catch (Exception ignore) {
            }
            try {
              if ("Fuel station".equals(((JSONObject) jsonObj.getJSONObject("Service").getJSONArray("features").get(0)).getJSONObject("properties").getString("typeLabel"))) {
                isFuelStation = true;
              }
            } catch (Exception ignore) {
            }
            try {
              if ("Urban bus".equals(((JSONObject) jsonObj.getJSONObject("Service").getJSONArray("features").get(0)).getJSONObject("properties").getString("typeLabel"))) {
                isUrbanBus = true;
              }
            } catch (Exception ignore) {
            }
            try {
              if ("Smart waste container".equals(((JSONObject) jsonObj.getJSONObject("Service").getJSONArray("features").get(0)).getJSONObject("properties").getString("typeLabel"))) {
                isSmartWasteContainer = true;
              }
            } catch (Exception ignore) {
            }
            try {
              if ("Smart bench".equals(((JSONObject) jsonObj.getJSONObject("Service").getJSONArray("features").get(0)).getJSONObject("properties").getString("typeLabel"))) {
                isSmartBench = true;
              }
            } catch (Exception ignore) {
            }
            try {
              if ("Route".equals(((JSONObject) jsonObj.getJSONObject("Service").getJSONArray("features").get(0)).getJSONObject("properties").getString("typeLabel"))) {
                isRoute = true;
              }
            } catch (Exception ignore) {
            }
            try {
              if ("Air quality monitoring station".equals(((JSONObject) jsonObj.getJSONObject("Service").getJSONArray("features").get(0)).getJSONObject("properties").getString("typeLabel"))) {
                isAirQualityMonitoringStation = true;
              }
            } catch (Exception ignore) {
            }
            try {
              if ("Weather sensor".equals(((JSONObject) jsonObj.getJSONObject("Service").getJSONArray("features").get(0)).getJSONObject("properties").getString("typeLabel"))) {
                isWeather_sensor = true;
              }
            } catch (Exception ignore) {
            }

            try {
              if ("Ferry stop".equals(((JSONObject) jsonObj.getJSONObject("Service").getJSONArray("features").get(0)).getJSONObject("properties").getString("typeLabel"))) {
                isFerry_stop = true;
              }
            } catch (Exception ignore) {
            }
            try {
              if ("Sensor".equals(((JSONObject) jsonObj.getJSONObject("Service").getJSONArray("features").get(0)).getJSONObject("properties").getString("typeLabel"))) {
                isIoTSensor = true;
              }
            } catch (Exception ignore) {
            }
            try {
              if ("Actuator".equals(((JSONObject) jsonObj.getJSONObject("Service").getJSONArray("features").get(0)).getJSONObject("properties").getString("typeLabel"))) {
                isIoTActuator = true;
              }
            } catch (Exception ignore) {
            }
            try {
              if ("Noise level sensor".equals(((JSONObject) jsonObj.getJSONObject("Service").getJSONArray("features").get(0)).getJSONObject("properties").getString("typeLabel"))) {
                isNoiseLevelSensor = true;
              }
            } catch (Exception ignore) {
            }
            try {
              if ("People Counter".equals(((JSONObject) jsonObj.getJSONObject("Service").getJSONArray("features").get(0)).getJSONObject("properties").getString("typeLabel"))) {
                isPeople_counter = true;
              }
            } catch (Exception ignore) {
            }
            try {
              if (jsonObj.has("Sensor")) {
                isSensorSite = true;
              }
            } catch (Exception ignore) {
            }
            try {
              if ("Underpass".equals(((JSONObject) jsonObj.getJSONObject("Service").getJSONArray("features").get(0)).getJSONObject("properties").getString("typeLabel"))) {
                isUnderpass = true;
              }
            } catch (Exception ignore) {
            }

            try {
              if (((JSONObject) jsonObj.getJSONObject("Service").getJSONArray("features").get(0)).getJSONObject("properties").has("typeLabel") && !(isAirQualityMonitoringStation || isWeather_sensor || isFerry_stop || isIoTActuator || isIoTSensor || isNoiseLevelSensor || isPeople_counter || isSensorSite || isUnderpass)) {
                isService = true;
              }
            } catch (Exception ignore) {
            }

            if (isBusStop) {
              String serviceUri; // uri
              String name = null;
              double latitude = -1;
              double longitude = -1;
              double distance = -1;
              String city = null;
              String cap = null;
              String province = null;
              String address = null;
              String civic = null;
              String phone = null;
              String fax = null;
              String website; // uri
              String email = null;
              String note = null;
              String description = null;
              String linkDBpedia; // uri
              double avgStars = -1;
              int starsCount = -1;
              String agency = null;
              String agencyUri; // uri     

              try {
                serviceUri = ((JSONArray) jsonObj.getJSONObject("BusStop").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceUri");
                URI uri = URI.create(serviceUri);
              } catch (Exception ignore) {
                serviceUri = null;
              }
              try {
                name = ((JSONArray) jsonObj.getJSONObject("BusStop").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("name");
              } catch (Exception ignore) {
              }
              try {
                longitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("BusStop").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(0);
              } catch (Exception ignore) {
              }
              try {
                latitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("BusStop").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(1);
              } catch (Exception ignore) {
              }
              try {
                distance = ((JSONArray) jsonObj.getJSONObject("BusStop").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("distance");
              } catch (Exception ignore) {
              }
              try {
                city = ((JSONArray) jsonObj.getJSONObject("BusStop").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("city");
              } catch (Exception ignore) {
              }
              try {
                cap = ((JSONArray) jsonObj.getJSONObject("BusStop").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("cap");
              } catch (Exception ignore) {
              }
              try {
                province = ((JSONArray) jsonObj.getJSONObject("BusStop").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("province");
              } catch (Exception ignore) {
              }
              try {
                address = ((JSONArray) jsonObj.getJSONObject("BusStop").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("address");
              } catch (Exception ignore) {
              }
              try {
                civic = ((JSONArray) jsonObj.getJSONObject("BusStop").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("civic");
              } catch (Exception ignore) {
              }
              try {
                phone = ((JSONArray) jsonObj.getJSONObject("BusStop").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("phone");
              } catch (Exception ignore) {
              }
              try {
                fax = ((JSONArray) jsonObj.getJSONObject("BusStop").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("fax");
              } catch (Exception ignore) {
              }
              try {
                website = ((JSONArray) jsonObj.getJSONObject("BusStop").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("website");
                URI uri = URI.create(website);
              } catch (Exception ignore) {
                website = null;
              }
              try {
                email = ((JSONArray) jsonObj.getJSONObject("BusStop").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("email");
              } catch (Exception ignore) {
              }
              try {
                note = ((JSONArray) jsonObj.getJSONObject("BusStop").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("note");
              } catch (Exception ignore) {
              }
              try {
                description = ((JSONArray) jsonObj.getJSONObject("BusStop").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("description");
              } catch (Exception ignore) {
              }
              try {
                linkDBpedia = ((JSONArray) jsonObj.getJSONObject("BusStop").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("linkDBpedia");
                URI uri = URI.create(linkDBpedia);
              } catch (Exception ignore) {
                linkDBpedia = null;
              }
              try {
                avgStars = ((JSONArray) jsonObj.getJSONObject("BusStop").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("avgStars");
              } catch (Exception ignore) {
              }
              try {
                starsCount = ((JSONArray) jsonObj.getJSONObject("BusStop").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getInt("starsCount");
              } catch (Exception ignore) {
              }
              try {
                agency = ((JSONArray) jsonObj.getJSONObject("BusStop").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("agency");
              } catch (Exception ignore) {
              }
              try {
                agencyUri = ((JSONArray) jsonObj.getJSONObject("BusStop").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("agencyUri");
                URI uri = URI.create(agencyUri);
              } catch (Exception ignore) {
                agencyUri = null;
              }

              if (serviceUri == null) {
                throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceUri");
              }

              /*
               try {
               org.opengis.referencing.crs.CoordinateReferenceSystem sourceCRS = org.geotools.referencing.CRS.decode("EPSG:4326");                                    
               org.opengis.referencing.crs.CoordinateReferenceSystem targetCRS = org.geotools.referencing.CRS.decode("EPSG:3857");
               org.opengis.referencing.operation.MathTransform transform = org.geotools.referencing.CRS.findMathTransform(sourceCRS, targetCRS, false);
               org.locationtech.jts.geom.GeometryFactory geometryFactory = new org.locationtech.jts.geom.GeometryFactory(new org.locationtech.jts.geom.PrecisionModel(), 4326);
               org.locationtech.jts.geom.Point point = geometryFactory.createPoint(new org.locationtech.jts.geom.Coordinate(longitude, latitude));
               org.locationtech.jts.geom.Point targetPoint = (org.locationtech.jts.geom.Point) org.geotools.geometry.jts.JTS.transform(point, transform);
               longitude = targetPoint.getX();
               latitude = targetPoint.getY();
               }
               catch(Exception ce) {

               }           
               */
              String response = "<?xml version=\"1.0\" ?>\n"
                      + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd " + serverUrl + "?service=WFS&amp;version=" + serverVersion + "&amp;request=DescribeFeatureType&amp;typeName=km4c%3BusStop http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                      + "<km4c:BusStop gml:id=\"" + serviceUri + "\">\n"
                      + (serviceUri != null && (!"null".equals(serviceUri)) && !serviceUri.isEmpty() ? "   <km4c:serviceUri>" + serviceUri.replaceAll("&", "&amp;") + "</km4c:serviceUri>" : "")
                      + (name != null && (!"null".equals(name)) && !name.isEmpty() ? "   <km4c:name>" + name.replaceAll("&", "&amp;") + "</km4c:name>" : "")
                      + (latitude != -1 && longitude != -1 ? "<km4c:geometry><gml:Point srsDimension=\"2\" srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\"><gml:pos>" + String.valueOf(longitude) + " " + String.valueOf(latitude) + "</gml:pos></gml:Point></km4c:geometry>" : "")
                      + (city != null && (!"null".equals(city)) && !city.isEmpty() ? "   <km4c:city>" + city + "</km4c:city>" : "")
                      + (cap != null && (!"null".equals(cap)) && !cap.isEmpty() ? "   <km4c:cap>" + cap + "</km4c:cap>" : "")
                      + (province != null && (!"null".equals(province)) && !province.isEmpty() ? "   <km4c:province>" + province + "</km4c:province>" : "")
                      + (address != null && (!"null".equals(address)) && !address.isEmpty() ? "   <km4c:address>" + address + "</km4c:address>" : "")
                      + (civic != null && (!"null".equals(civic)) && !civic.isEmpty() ? "   <km4c:civic>" + civic + "</km4c:civic>" : "")
                      + (phone != null && (!"null".equals(phone)) && !phone.isEmpty() ? "   <km4c:phone>" + phone + "</km4c:phone>" : "")
                      + (fax != null && (!"null".equals(fax)) && !fax.isEmpty() ? "   <km4c:fax>" + fax + "</km4c:fax>" : "")
                      + (website != null && (!"null".equals(website)) && !website.isEmpty() ? "   <km4c:website>" + website.replaceAll("&", "&amp;") + "</km4c:website>" : "")
                      + (email != null && (!"null".equals(email)) && !email.isEmpty() ? "   <km4c:email>" + email + "</km4c:email>" : "")
                      + (note != null && (!"null".equals(note)) && !note.isEmpty() ? "   <km4c:note>" + note + "</km4c:note>" : "")
                      + (linkDBpedia != null && (!"null".equals(linkDBpedia)) && !linkDBpedia.isEmpty() ? "   <km4c:linkDBpedia>" + linkDBpedia.replaceAll("&", "&amp;") + "</km4c:linkDBpedia>" : "")
                      + (avgStars > 0 ? "   <km4c:avgStars>" + String.valueOf(avgStars) + "</km4c:avgStars>" : "")
                      + (starsCount > 0 ? "   <km4c:starsCount>" + String.valueOf(starsCount) + "</km4c:starsCount>" : "")
                      + (agency != null && (!"null".equals(agency)) && !agency.isEmpty() ? "   <km4c:agency>" + agency + "</km4c:agency>" : "")
                      + (agencyUri != null && (!"null".equals(agencyUri)) && !agencyUri.isEmpty() ? "   <km4c:agencyUri>" + agencyUri.replaceAll("&", "&amp;") + "</km4c:agencyUri>" : "")
                      + "</km4c:BusStop>"
                      + "</wfs:member></wfs:FeatureCollection>";

              if ("hits".equals(pResultType)) {
                response = "<?xml version=\"1.0\" ?>\n"
                        + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                        + "</wfs:member></wfs:FeatureCollection>";
              }

              return Response.ok(response).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/gml+xml;version=3.2").status(r.getStatus()).header("Cache-Control", "no-cache").build();
            } else if (isEvent) {

              String serviceUri; // uri
              String name = null;
              double latitude = -1;
              double longitude = -1;
              double distance = -1;
              String city = null;
              String cap = null;
              String province = null;
              String address = null;
              String civic = null;
              String phone = null;
              String fax = null;
              String website; // uri
              String email = null;
              String note = null;
              String description = null;
              String linkDBpedia; // uri
              double avgStars = -1;
              int starsCount = -1;
              String startDate = null;
              String startTime = null;
              String endDate = null;
              String endTime = null;
              String eventCategory = null;
              String place = null;
              String freeEvent = null;
              String price = null;

              try {
                serviceUri = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceUri");
                URI uri = URI.create(serviceUri);
              } catch (Exception ignore) {
                serviceUri = null;
              }
              try {
                name = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("name");
              } catch (Exception ignore) {
              }
              try {
                longitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(0);
              } catch (Exception ignore) {
              }
              try {
                latitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(1);
              } catch (Exception ignore) {
              }
              try {
                distance = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("distance");
              } catch (Exception ignore) {
              }
              try {
                city = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("city");
              } catch (Exception ignore) {
              }
              try {
                cap = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("cap");
              } catch (Exception ignore) {
              }
              try {
                province = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("province");
              } catch (Exception ignore) {
              }
              try {
                address = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("address");
              } catch (Exception ignore) {
              }
              try {
                civic = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("civic");
              } catch (Exception ignore) {
              }
              try {
                phone = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("phone");
              } catch (Exception ignore) {
              }
              try {
                fax = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("fax");
              } catch (Exception ignore) {
              }
              try {
                website = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("website");
                URI uri = URI.create(website);
              } catch (Exception ignore) {
                website = null;
              }
              try {
                email = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("email");
              } catch (Exception ignore) {
              }
              try {
                note = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("note");
              } catch (Exception ignore) {
              }
              try {
                description = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("description");
              } catch (Exception ignore) {
              }
              try {
                linkDBpedia = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("linkDBpedia");
                URI uri = URI.create(linkDBpedia);
              } catch (Exception ignore) {
                linkDBpedia = null;
              }
              try {
                avgStars = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("avgStars");
              } catch (Exception ignore) {
              }
              try {
                starsCount = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getInt("starsCount");
              } catch (Exception ignore) {
              }
              try {
                startDate = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("startDate");
              } catch (Exception ignore) {
              }
              try {
                startTime = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("startTime");
              } catch (Exception ignore) {
              }
              try {
                endDate = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("endDate");
              } catch (Exception ignore) {
              }
              try {
                endTime = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("endTime");
              } catch (Exception ignore) {
              }
              try {
                eventCategory = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("eventCategory");
              } catch (Exception ignore) {
              }
              try {
                place = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("place");
              } catch (Exception ignore) {
              }
              try {
                freeEvent = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("freeEvent");
              } catch (Exception ignore) {
              }
              try {
                price = ((JSONArray) jsonObj.getJSONObject("Event").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("price");
              } catch (Exception ignore) {
              }

              if (serviceUri == null) {
                throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceUri");
              }

              /*
               try {
               org.opengis.referencing.crs.CoordinateReferenceSystem sourceCRS = org.geotools.referencing.CRS.decode("EPSG:4326");                                    
               org.opengis.referencing.crs.CoordinateReferenceSystem targetCRS = org.geotools.referencing.CRS.decode("EPSG:3857");
               org.opengis.referencing.operation.MathTransform transform = org.geotools.referencing.CRS.findMathTransform(sourceCRS, targetCRS, false);
               org.locationtech.jts.geom.GeometryFactory geometryFactory = new org.locationtech.jts.geom.GeometryFactory(new org.locationtech.jts.geom.PrecisionModel(), 4326);
               org.locationtech.jts.geom.Point point = geometryFactory.createPoint(new org.locationtech.jts.geom.Coordinate(longitude, latitude));
               org.locationtech.jts.geom.Point targetPoint = (org.locationtech.jts.geom.Point) org.geotools.geometry.jts.JTS.transform(point, transform);
               longitude = targetPoint.getX();
               latitude = targetPoint.getY();
               }
               catch(Exception ce) {

               }
               */
              String response = "<?xml version=\"1.0\" ?>\n"
                      + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd " + serverUrl + "?service=WFS&amp;version=" + serverVersion + "&amp;request=DescribeFeatureType&amp;typeName=km4c%3Event http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                      + "<km4c:Event gml:id=\"" + serviceUri + "\" >\n"
                      + (serviceUri != null && (!"null".equals(serviceUri)) && !serviceUri.isEmpty() ? "   <km4c:serviceUri>" + serviceUri.replaceAll("&", "&amp;") + "</km4c:serviceUri>" : "")
                      + (name != null && (!"null".equals(name)) && !name.isEmpty() ? "   <km4c:name>" + name.replaceAll("&", "&amp;") + "</km4c:name>" : "")
                      + (latitude != -1 && longitude != -1 ? "<km4c:geometry><gml:Point srsDimension=\"2\" srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\"><gml:pos>" + String.valueOf(longitude) + " " + String.valueOf(latitude) + "</gml:pos></gml:Point></km4c:geometry>" : "")
                      + (city != null && (!"null".equals(city)) && !city.isEmpty() ? "   <km4c:city>" + city + "</km4c:city>" : "")
                      + (cap != null && (!"null".equals(cap)) && !cap.isEmpty() ? "   <km4c:cap>" + cap + "</km4c:cap>" : "")
                      + (province != null && (!"null".equals(province)) && !province.isEmpty() ? "   <km4c:province>" + province + "</km4c:province>" : "")
                      + (address != null && (!"null".equals(address)) && !address.isEmpty() ? "   <km4c:address>" + address + "</km4c:address>" : "")
                      + (civic != null && (!"null".equals(civic)) && !civic.isEmpty() ? "   <km4c:civic>" + civic + "</km4c:civic>" : "")
                      + (phone != null && (!"null".equals(phone)) && !phone.isEmpty() ? "   <km4c:phone>" + phone + "</km4c:phone>" : "")
                      + (fax != null && (!"null".equals(fax)) && !fax.isEmpty() ? "   <km4c:fax>" + fax + "</km4c:fax>" : "")
                      + (website != null && (!"null".equals(website)) && !website.isEmpty() ? "   <km4c:website>" + website.replaceAll("&", "&amp;") + "</km4c:website>" : "")
                      + (email != null && (!"null".equals(email)) && !email.isEmpty() ? "   <km4c:email>" + email + "</km4c:email>" : "")
                      + (note != null && (!"null".equals(note)) && !note.isEmpty() ? "   <km4c:note>" + note + "</km4c:note>" : "")
                      + (linkDBpedia != null && (!"null".equals(linkDBpedia)) && !linkDBpedia.isEmpty() ? "   <km4c:linkDBpedia>" + linkDBpedia.replaceAll("&", "&amp;") + "</km4c:linkDBpedia>" : "")
                      + (avgStars > 0 ? "   <km4c:avgStars>" + String.valueOf(avgStars) + "</km4c:avgStars>" : "")
                      + (starsCount > 0 ? "   <km4c:starsCount>" + String.valueOf(starsCount) + "</km4c:starsCount>" : "")
                      + (startDate != null && (!"null".equals(startDate)) && !startDate.isEmpty() ? "   <km4c:startDate>" + String.valueOf(startDate) + "</km4c:startDate>" : "")
                      + (startTime != null && (!"null".equals(startTime)) && !startTime.isEmpty() ? "   <km4c:startTime>" + String.valueOf(startTime) + "</km4c:startTime>" : "")
                      + (endDate != null && (!"null".equals(endDate)) && !endDate.isEmpty() ? "   <km4c:endDate>" + String.valueOf(endDate) + "</km4c:endDate>" : "")
                      + (endTime != null && (!"null".equals(endTime)) && !endTime.isEmpty() ? "   <km4c:endTime>" + String.valueOf(endTime) + "</km4c:endTime>" : "")
                      + (eventCategory != null && (!"null".equals(eventCategory)) && !eventCategory.isEmpty() ? "   <km4c:eventCategory>" + String.valueOf(eventCategory) + "</km4c:eventCategory>" : "")
                      + (place != null && (!"null".equals(place)) && !place.isEmpty() ? "   <km4c:place>" + String.valueOf(place) + "</km4c:place>" : "")
                      + (freeEvent != null && (!"null".equals(freeEvent)) && !freeEvent.isEmpty() ? "   <km4c:freeEvent>" + String.valueOf(freeEvent) + "</km4c:freeEvent>" : "")
                      + (price != null && (!"null".equals(price)) && !price.isEmpty() ? "   <km4c:price>" + String.valueOf(price) + "</km4c:price>" : "")
                      + "</km4c:Event>"
                      + "</wfs:member></wfs:FeatureCollection>";

              if ("hits".equals(pResultType)) {
                response = "<?xml version=\"1.0\" ?>\n"
                        + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                        + "</wfs:member></wfs:FeatureCollection>";
              }

              return Response.ok(response).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/gml+xml;version=3.2").status(r.getStatus()).header("Cache-Control", "no-cache").build();
            } else if (isService) {
              String serviceUri; // uri
              String name = null;
              double latitude = -1;
              double longitude = -1;
              double distance = -1;
              String city = null;
              String cap = null;
              String province = null;
              String address = null;
              String civic = null;
              String phone = null;
              String fax = null;
              String website; // uri
              String email = null;
              String note = null;
              String description = null;
              String linkDBpedia; // uri
              double avgStars = -1;
              int starsCount = -1;
              String typeLabel = null;
              String serviceType = null;
              try {
                serviceUri = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceUri");
                URI uri = URI.create(serviceUri);
              } catch (Exception ignore) {
                serviceUri = null;
              }
              try {
                name = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("name");
              } catch (Exception ignore) {
              }
              try {
                typeLabel = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("typeLabel");
              } catch (Exception ignore) {
              }
              try {
                serviceType = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceType");
              } catch (Exception ignore) {
              }
              try {
                longitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(0);
              } catch (Exception ignore) {
              }
              try {
                latitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(1);
              } catch (Exception ignore) {
              }
              try {
                distance = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("distance");
              } catch (Exception ignore) {
              }
              try {
                city = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("city");
              } catch (Exception ignore) {
              }
              try {
                cap = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("cap");
              } catch (Exception ignore) {
              }
              try {
                province = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("province");
              } catch (Exception ignore) {
              }
              try {
                address = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("address");
              } catch (Exception ignore) {
              }
              try {
                civic = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("civic");
              } catch (Exception ignore) {
              }
              try {
                phone = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("phone");
              } catch (Exception ignore) {
              }
              try {
                fax = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("fax");
              } catch (Exception ignore) {
              }
              try {
                website = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("website");
                URI uri = URI.create(website);
              } catch (Exception ignore) {
                website = null;
              }
              try {
                email = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("email");
              } catch (Exception ignore) {
              }
              try {
                note = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("note");
              } catch (Exception ignore) {
              }
              try {
                description = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("description");
              } catch (Exception ignore) {
              }
              try {
                linkDBpedia = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("linkDBpedia");
                URI uri = URI.create(linkDBpedia);
              } catch (Exception ignore) {
                linkDBpedia = null;
              }
              try {
                avgStars = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("avgStars");
              } catch (Exception ignore) {
              }
              try {
                starsCount = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getInt("starsCount");
              } catch (Exception ignore) {
              }
              if (serviceUri == null) {
                throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceUri");
              }
              if (serviceType == null) {
                throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceType");
              }


              /*String response = "<?xml version=\"1.0\" ?>\n"
                      + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd " + serverUrl + "?service=WFS&amp;version=" + serverVersion + "&amp;request=DescribeFeatureType&amp;typeName=km4c%3Service http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                      + "<km4c:" + serviceType.substring(0, serviceType.indexOf("_")) + " gml:id=\"" + serviceUri + "\">\n"
                      + (serviceUri != null && (!"null".equals(serviceUri)) && !serviceUri.isEmpty() ? "   <km4c:serviceUri>" + serviceUri.replaceAll("&", "&amp;") + "</km4c:serviceUri>" : "")
                      + (name != null && (!"null".equals(name)) && !name.isEmpty() ? "   <km4c:name>" + name.replaceAll("&", "&amp;") + "</km4c:name>" : "")
                      + (typeLabel != null && (!"null".equals(typeLabel)) && !typeLabel.isEmpty() ? "   <km4c:typeLabel>" + typeLabel + "</km4c:typeLabel>" : "")
                      + (latitude != -1 && longitude != -1 ? "<km4c:geometry><gml:Point srsDimension=\"2\" srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\"><gml:pos>" + String.valueOf(longitude) + " " + String.valueOf(latitude) + "</gml:pos></gml:Point></km4c:geometry>" : "")
                      + (city != null && (!"null".equals(city)) && !city.isEmpty() ? "   <km4c:city>" + city + "</km4c:city>" : "")
                      + (cap != null && (!"null".equals(cap)) && !cap.isEmpty() ? "   <km4c:cap>" + cap + "</km4c:cap>" : "")
                      + (province != null && (!"null".equals(province)) && !province.isEmpty() ? "   <km4c:province>" + province + "</km4c:province>" : "")
                      + (address != null && (!"null".equals(address)) && !address.isEmpty() ? "   <km4c:address>" + address + "</km4c:address>" : "")
                      + (civic != null && (!"null".equals(civic)) && !civic.isEmpty() ? "   <km4c:civic>" + civic + "</km4c:civic>" : "")
                      + (phone != null && (!"null".equals(phone)) && !phone.isEmpty() ? "   <km4c:phone>" + phone + "</km4c:phone>" : "")
                      + (fax != null && (!"null".equals(fax)) && !fax.isEmpty() ? "   <km4c:fax>" + fax + "</km4c:fax>" : "")
                      + (website != null && (!"null".equals(website)) && !website.isEmpty() ? "   <km4c:website>" + website.replaceAll("&", "&amp;") + "</km4c:website>" : "")
                      + (email != null && (!"null".equals(email)) && !email.isEmpty() ? "   <km4c:email>" + email + "</km4c:email>" : "")
                      + (note != null && (!"null".equals(note)) && !note.isEmpty() ? "   <km4c:note>" + note + "</km4c:note>" : "")
                      + (linkDBpedia != null && (!"null".equals(linkDBpedia)) && !linkDBpedia.isEmpty() ? "   <km4c:linkDBpedia>" + linkDBpedia.replaceAll("&", "&amp;") + "</km4c:linkDBpedia>" : "")
                      + (avgStars > 0 ? "   <km4c:avgStars>" + String.valueOf(avgStars) + "</km4c:avgStars>" : "")
                      + (starsCount > 0 ? "   <km4c:starsCount>" + String.valueOf(starsCount) + "</km4c:starsCount>" : "")
                      + "</km4c:" + serviceType.substring(0, serviceType.indexOf("_")) + ">"
                      + "</wfs:member></wfs:FeatureCollection>";*/
              String response = bldResp( serviceType.substring(0, serviceType.indexOf("_")),  nowAsISO,  serverUrl,  serverVersion,  serviceType,  serviceUri, name,  typeLabel,  latitude, 
           longitude,  city,  cap,  province,  address,  civic,  phone,  fax,  website,  email,  note,
           linkDBpedia,  avgStars,  starsCount );

              if ("hits".equals(pResultType)) {
                response = "<?xml version=\"1.0\" ?>\n"
                        + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                        + "</wfs:member></wfs:FeatureCollection>";
              }

              return Response.ok(response).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/gml+xml;version=3.2").status(r.getStatus()).header("Cache-Control", "no-cache").build();

            } else if (isAirQualityMonitoringStation) {
              String response = "<?xml version=\"1.0\" ?>\n"
                    + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd " + serverUrl + "?service=WFS&amp;version=" + serverVersion + "&amp;request=DescribeFeatureType&amp;typeName=km4c%3" + t + "Air_quality_monitoring_station http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\">";
                      
                          int l = 0;
            try {
                l = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).length();
            } catch(Exception x) {}
            
              for(int b = 0; b < Math.max(1, l); b++) 
              {
                String serviceUri; // uri
              String name = null;
              double latitude = -1;
              double longitude = -1;
              double distance = -1;
              String city = null;
              String cap = null;
              String province = null;
              String address = null;
              String civic = null;
              String phone = null;
              String fax = null;
              String website; // uri
              String email = null;
              String note = null;
              String description = null;
              String linkDBpedia; // uri
              double avgStars = -1;
              int starsCount = -1;
              String typeLabel = null;
              String serviceType = null;

              Double EnfuserAQI = null;
              Double NO = null;
              Double NO2 = null;
              Double O3 = null;
              Double PM10 = null;
              Double PM2_5 = null;
              Double RealTimeAQI = null;
              Double RealTimeDeltaAQI = null;
              Double SO2 = null;
              JSONObject address_rt = null;
              Integer airQualityAQI = null;
              Double airQualityCO = null;
              Double airQualityNO = null;
              Double airQualityNO2 = null;
              Double airQualityNOAvg2h = null;
              Double airQualityO3 = null;
              Double airQualityPM10 = null;
              Double airQualityPM10AverageLastHour = null;
              Double airQualityPM10Avg2h = null;
              Double airQualityPM10Enfuser = null;
              Double airQualityPM10Gral = null;
              Double airQualityPM10RealTimeDelta = null;
              Double airQualityPM10RealTimeDeltaGral = null;
              Double airQualityPM2_5 = null;
              Double airQualityPM2_5AverageLastHour = null;
              Double airQualityPM2_5Enfuser = null;
              Double airQualityPM2_5RealTimeDelta = null;
              Double airQualitySO2 = null;
              Double airQualityTRSC = null;
              java.util.Date dateObserved = null;
              java.util.Date dateObservedFrom = null;
              java.util.Date dateObservedTo = null;
              JSONObject location = null;
              Double reliability = null;
              String source = null;
              java.util.Date measuredTime = null;
              java.util.Date periodStartTime = null;
              java.util.Date nextMeasuredTime = null;

              try {
                serviceUri = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceUri");
                URI uri = URI.create(serviceUri);
              } catch (Exception ignore) {
                serviceUri = null;
              }
              try {
                name = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("name");
              } catch (Exception ignore) {
              }
              try {
                typeLabel = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("typeLabel");
              } catch (Exception ignore) {
              }
              try {
                serviceType = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceType");
              } catch (Exception ignore) {
              }
              try {
                longitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(0);
              } catch (Exception ignore) {
              }
              try {
                latitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(1);
              } catch (Exception ignore) {
              }
              try {
                distance = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("distance");
              } catch (Exception ignore) {
              }
              try {
                city = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("city");
              } catch (Exception ignore) {
              }
              try {
                cap = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("cap");
              } catch (Exception ignore) {
              }
              try {
                province = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("province");
              } catch (Exception ignore) {
              }
              try {
                address = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("address");
              } catch (Exception ignore) {
              }
              try {
                civic = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("civic");
              } catch (Exception ignore) {
              }
              try {
                phone = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("phone");
              } catch (Exception ignore) {
              }
              try {
                fax = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("fax");
              } catch (Exception ignore) {
              }
              try {
                website = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("website");
                URI uri = URI.create(website);
              } catch (Exception ignore) {
                website = null;
              }
              try {
                email = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("email");
              } catch (Exception ignore) {
              }
              try {
                note = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("note");
              } catch (Exception ignore) {
              }
              try {
                description = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("description");
              } catch (Exception ignore) {
              }
              try {
                linkDBpedia = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("linkDBpedia");
                URI uri = URI.create(linkDBpedia);
              } catch (Exception ignore) {
                linkDBpedia = null;
              }
              try {
                avgStars = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("avgStars");
              } catch (Exception ignore) {
              }
              try {
                starsCount = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getInt("starsCount");
              } catch (Exception ignore) {
              }

              try {
                EnfuserAQI = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("EnfuserAQI").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                NO = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("NO").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                NO2 = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("NO2").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                O3 = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("O3").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                PM10 = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("PM10").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                PM2_5 = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("PM2.5").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                RealTimeAQI = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("RealTimeAQI").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                RealTimeDeltaAQI = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("RealTimeDeltaAQI").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                SO2 = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("SO2").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                address_rt = new JSONObject(((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("address").getString("value"));
              } catch (Exception ignore) {
              }
              try {
                airQualityAQI = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airQualityAQI").getInt("value");
              } catch (Exception ignore) {
              }
              try {
                airQualityCO = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airQualityCO").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airQualityNO = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airQualityNO").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airQualityNO2 = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airQualityNO2").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airQualityNOAvg2h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airQualityNOAvg2h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airQualityO3 = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airQualityO3").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airQualityPM10 = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airQualityPM10").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airQualityPM10AverageLastHour = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airQualityPM10AverageLastHour").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airQualityPM10Avg2h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airQualityPM10Avg2h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airQualityPM10Enfuser = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airQualityPM10Enfuser").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airQualityPM10Gral = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airQualityPM10Gral").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airQualityPM10RealTimeDelta = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airQualityPM10RealTimeDelta").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airQualityPM10RealTimeDeltaGral = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airQualityPM10RealTimeDeltaGral").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airQualityPM2_5 = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airQualityPM2_5").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airQualityPM2_5AverageLastHour = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airQualityPM2_5AverageLastHour").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airQualityPM2_5Enfuser = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airQualityPM2_5Enfuser").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airQualityPM2_5RealTimeDelta = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airQualityPM2_5RealTimeDelta").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airQualitySO2 = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airQualitySO2").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airQualityTRSC = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airQualityTRSC").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                dateObserved = parseDate(((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("dateObserved").getString("value"));
              } catch (Exception ignore) {
              }
              try {
                dateObservedFrom = parseDate(((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("dateObservedFrom").getString("value"));
              } catch (Exception ignore) {
              }
              try {
                dateObservedTo = parseDate(((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("dateObservedTo").getString("value"));
              } catch (Exception ignore) {
              }
              try {
                location = new JSONObject(((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("location").getString("value"));
              } catch (Exception ignore) {
              }
              try {
                reliability = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("reliability").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                source = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("source").getString("value");
                URI uri = URI.create(source);
              } catch (Exception ignore) {
                source = null;
              }
              try { measuredTime = parseDate(((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("measuredTime").getString("value")); } catch(Exception ignore) {}
              try { periodStartTime = parseDate(requestContext.getParameter("fromTime")+ ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("measuredTime").getString("value").substring(19)); } catch(Exception ignore) { periodStartTime = parseDate(requestContext.getParameter("fromTime")+timezoneSuffix); }
              try {
                    if( b > 0) nextMeasuredTime = parseDate(((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b-1).getJSONObject("measuredTime").getString("value"));
                    else nextMeasuredTime = parseDate(requestContext.getParameter("toTime")+ ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(l-1).getJSONObject("measuredTime").getString("value").substring(19));                
              } catch (Exception ignore) {nextMeasuredTime = parseDate(requestContext.getParameter("toTime")+timezoneSuffix); }      
              

              /*if(b == 0) {
                  response = response + "<wfs:member>"
                      + "<km4c:" + t + "Air_quality_monitoring_station gml:id=\"" + serviceUri + "\">\n" // + "#" + ( measuredTime != null ? String.valueOf(measuredTime.getTime()) : String.valueOf(b)) + "\">\n"
                      + (serviceUri != null && (!"null".equals(serviceUri)) && !serviceUri.isEmpty() ? "   <km4c:serviceUri>" + serviceUri.replaceAll("&", "&amp;") + "</km4c:serviceUri>" : "")
                      + (name != null && (!"null".equals(name)) && !name.isEmpty() ? "   <km4c:name>" + name.replaceAll("&", "&amp;") + "</km4c:name>" : "")
                      + (typeLabel != null && (!"null".equals(typeLabel)) && !typeLabel.isEmpty() ? "   <km4c:typeLabel>" + typeLabel + "</km4c:typeLabel>" : "")
                      + (latitude != -1 && longitude != -1 ? "<km4c:geometry><gml:Point srsDimension=\"2\" srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\"><gml:pos>" + String.valueOf(longitude) + " " + String.valueOf(latitude) + "</gml:pos></gml:Point></km4c:geometry>" : "")
                      + ( city != null && (!"null".equals(city)) && !city.isEmpty() ? "   <km4c:city>" + city + "</km4c:city>" : "")
                      + ( cap != null && (!"null".equals(cap)) && !cap.isEmpty() ? "   <km4c:cap>" + cap + "</km4c:cap>" : "")
                      + ( province != null && (!"null".equals(province)) && !province.isEmpty() ? "   <km4c:province>" + province + "</km4c:province>" : "")
                      + ( address != null && (!"null".equals(address)) && !address.isEmpty() ? "   <km4c:address>" + address + "</km4c:address>" : "")
                      + ( civic != null && (!"null".equals(civic)) && !civic.isEmpty() ? "   <km4c:civic>" + civic + "</km4c:civic>" : "")
                      + ( phone != null && (!"null".equals(phone)) && !phone.isEmpty() ? "   <km4c:phone>" + phone + "</km4c:phone>" : "")
                      + ( fax != null && (!"null".equals(fax)) && !fax.isEmpty() ? "   <km4c:fax>" + fax + "</km4c:fax>" : "")
                      + ( website != null && (!"null".equals(website)) && !website.isEmpty() ? "   <km4c:website>" + website.replaceAll("&", "&amp;") + "</km4c:website>" : "")
                      + ( email != null && (!"null".equals(email)) && !email.isEmpty() ? "   <km4c:email>" + email + "</km4c:email>" : "")
                      + ( note != null && (!"null".equals(note)) && !note.isEmpty() ? "   <km4c:note>" + note + "</km4c:note>" : "")
                      + ( linkDBpedia != null && (!"null".equals(linkDBpedia)) && !linkDBpedia.isEmpty() ? "   <km4c:linkDBpedia>" + linkDBpedia.replaceAll("&", "&amp;") + "</km4c:linkDBpedia>" : "")
                      + ( avgStars > 0 ? "   <km4c:avgStars>" + String.valueOf(avgStars) + "</km4c:avgStars>" : "")
                      + ( starsCount > 0 ? "   <km4c:starsCount>" + String.valueOf(starsCount) + "</km4c:starsCount>" : "")
                      + (measuredTime != null ? "<km4c:RT_measuredTime>" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(startTime) + "</km4c:RT_measuredTime>" : "")
                          + (measuredTime != null ? "<km4c:RT_nextTime>" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(measuredTime) + "</km4c:RT_nextTime>" : "")
                           + "</km4c:" + t + "Air_quality_monitoring_station>"
                      + "</wfs:member>";
              }*/

              if (serviceUri == null) {
                throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceUri");
              }

              if (l > 0) response = response + "<wfs:member>"
                      + "<km4c:" + t + "Air_quality_monitoring_station gml:id=\"" + serviceUri + "\">\n" // + "#" + ( measuredTime != null ? String.valueOf(measuredTime.getTime()) : String.valueOf(b)) + "\">\n"
                      + (serviceUri != null && (!"null".equals(serviceUri)) && !serviceUri.isEmpty() ? "   <km4c:serviceUri>" + serviceUri.replaceAll("&", "&amp;") + "</km4c:serviceUri>" : "")
                      + (name != null && (!"null".equals(name)) && !name.isEmpty() ? "   <km4c:name>" + name.replaceAll("&", "&amp;") + "</km4c:name>" : "")
                      + (typeLabel != null && (!"null".equals(typeLabel)) && !typeLabel.isEmpty() ? "   <km4c:typeLabel>" + typeLabel + "</km4c:typeLabel>" : "")
                      + (latitude != -1 && longitude != -1 ? "<km4c:geometry><gml:Point srsDimension=\"2\" srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\"><gml:pos>" + String.valueOf(longitude) + " " + String.valueOf(latitude) + "</gml:pos></gml:Point></km4c:geometry>" : "")
                      + ( city != null && (!"null".equals(city)) && !city.isEmpty() ? "   <km4c:city>" + city + "</km4c:city>" : "")
                      + ( cap != null && (!"null".equals(cap)) && !cap.isEmpty() ? "   <km4c:cap>" + cap + "</km4c:cap>" : "")
                      + ( province != null && (!"null".equals(province)) && !province.isEmpty() ? "   <km4c:province>" + province + "</km4c:province>" : "")
                      + ( address != null && (!"null".equals(address)) && !address.isEmpty() ? "   <km4c:address>" + address + "</km4c:address>" : "")
                      + ( civic != null && (!"null".equals(civic)) && !civic.isEmpty() ? "   <km4c:civic>" + civic + "</km4c:civic>" : "")
                      + ( phone != null && (!"null".equals(phone)) && !phone.isEmpty() ? "   <km4c:phone>" + phone + "</km4c:phone>" : "")
                      + ( fax != null && (!"null".equals(fax)) && !fax.isEmpty() ? "   <km4c:fax>" + fax + "</km4c:fax>" : "")
                      + ( website != null && (!"null".equals(website)) && !website.isEmpty() ? "   <km4c:website>" + website.replaceAll("&", "&amp;") + "</km4c:website>" : "")
                      + ( email != null && (!"null".equals(email)) && !email.isEmpty() ? "   <km4c:email>" + email + "</km4c:email>" : "")
                      + ( note != null && (!"null".equals(note)) && !note.isEmpty() ? "   <km4c:note>" + note + "</km4c:note>" : "")
                      + ( linkDBpedia != null && (!"null".equals(linkDBpedia)) && !linkDBpedia.isEmpty() ? "   <km4c:linkDBpedia>" + linkDBpedia.replaceAll("&", "&amp;") + "</km4c:linkDBpedia>" : "")
                      + ( avgStars > 0 ? "   <km4c:avgStars>" + String.valueOf(avgStars) + "</km4c:avgStars>" : "")
                      + ( starsCount > 0 ? "   <km4c:starsCount>" + String.valueOf(starsCount) + "</km4c:starsCount>" : "")
                      + (measuredTime != null ? "<km4c:RT_measuredTime>" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(measuredTime) + "</km4c:RT_measuredTime>" : "")
                      + (nextMeasuredTime != null ? "<km4c:RT_nextTime>" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(nextMeasuredTime) + "</km4c:RT_nextTime>" : "")
                      + (EnfuserAQI != null && !EnfuserAQI.isNaN() ? "<km4c:RT_EnfuserAQI>" + String.valueOf(EnfuserAQI) + "</km4c:RT_EnfuserAQI>" : "")
                      + (NO != null && !NO.isNaN() ? "<km4c:RT_NO>" + String.valueOf(NO) + "</km4c:RT_NO>" : "")
                      + (NO2 != null && !NO2.isNaN() ? "<km4c:RT_NO2>" + String.valueOf(NO2) + "</km4c:RT_NO2>" : "")
                      + (O3 != null && !O3.isNaN() ? "<km4c:RT_O3>" + String.valueOf(O3) + "</km4c:RT_O3>" : "")
                      + (PM10 != null && !PM10.isNaN() ? "<km4c:RT_PM10>" + String.valueOf(PM10) + "</km4c:RT_PM10>" : "")
                      + (PM2_5 != null && !PM2_5.isNaN() ? "<km4c:RT_PM2_5>" + String.valueOf(PM2_5) + "</km4c:RT_PM2_5>" : "")
                      + (RealTimeAQI != null && !RealTimeAQI.isNaN() ? "<km4c:RT_RealTimeAQI>" + String.valueOf(RealTimeAQI) + "</km4c:RT_RealTimeAQI>" : "")
                      + (RealTimeDeltaAQI != null && !RealTimeDeltaAQI.isNaN() ? "<km4c:RT_RealTimeDeltaAQI>" + String.valueOf(RealTimeDeltaAQI) + "</km4c:RT_RealTimeDeltaAQI>" : "")
                      + (SO2 != null && !SO2.isNaN() ? "<km4c:RT_SO2>" + String.valueOf(SO2) + "</km4c:RT_SO2>" : "")
                      + (address_rt != null ? "<km4c:RT_address>" + address_rt.getString("streetAddress") + ", " + address_rt.getString("postalCode") + " " + address_rt.getString("addressLocality") + ", " + address_rt.getString("addressCountry") + "</km4c:RT_address>" : "")
                      + (airQualityAQI != null ? "<km4c:RT_airQualityAQI>" + String.valueOf(airQualityAQI) + "</km4c:RT_airQualityAQI>" : "")
                      + (airQualityCO != null && !airQualityCO.isNaN() ? "<km4c:RT_airQualityAQI>" + String.valueOf(airQualityAQI) + "</km4c:RT_airQualityAQI>" : "")
                      + (airQualityNO != null && !airQualityNO.isNaN() ? "<km4c:RT_airQualityNO>" + String.valueOf(airQualityNO) + "</km4c:RT_airQualityNO>" : "")
                      + (airQualityNO2 != null && !airQualityNO2.isNaN() ? "<km4c:RT_airQualityNO2>" + String.valueOf(airQualityNO2) + "</km4c:RT_airQualityNO2>" : "")
                      + (airQualityNOAvg2h != null && !airQualityNOAvg2h.isNaN() ? "<km4c:RT_airQualityNOAvg2h>" + String.valueOf(airQualityNOAvg2h) + "</km4c:RT_airQualityNOAvg2h>" : "")
                      + (airQualityO3 != null && !airQualityO3.isNaN() ? "<km4c:RT_airQualityO3>" + String.valueOf(airQualityO3) + "</km4c:RT_airQualityO3>" : "")
                      + (airQualityPM10 != null && !airQualityPM10.isNaN() ? "<km4c:RT_airQualityPM10>" + String.valueOf(airQualityPM10) + "</km4c:RT_airQualityPM10>" : "")
                      + (airQualityPM10AverageLastHour != null && !airQualityPM10AverageLastHour.isNaN() ? "<km4c:RT_airQualityPM10AverageLastHour>" + String.valueOf(airQualityPM10AverageLastHour) + "</km4c:RT_airQualityPM10AverageLastHour>" : "")
                      + (airQualityPM10Avg2h != null && !airQualityPM10Avg2h.isNaN() ? "<km4c:RT_airQualityPM10Avg2h>" + String.valueOf(airQualityPM10Avg2h) + "</km4c:RT_airQualityPM10Avg2h>" : "")
                      + (airQualityPM10Enfuser != null && !airQualityPM10Enfuser.isNaN() ? "<km4c:RT_airQualityPM10Enfuser>" + String.valueOf(airQualityPM10Enfuser) + "</km4c:RT_airQualityPM10Enfuser>" : "")
                      + (airQualityPM10Gral != null && !airQualityPM10Gral.isNaN() ? "<km4c:RT_airQualityPM10Gral>" + String.valueOf(airQualityPM10Gral) + "</km4c:RT_airQualityPM10Gral>" : "")
                      + (airQualityPM10RealTimeDelta != null && !airQualityPM10RealTimeDelta.isNaN() ? "<km4c:RT_airQualityPM10RealTimeDelta>" + String.valueOf(airQualityPM10RealTimeDelta) + "</km4c:RT_airQualityPM10RealTimeDelta>" : "")
                      + (airQualityPM10RealTimeDeltaGral != null && !airQualityPM10RealTimeDeltaGral.isNaN() ? "<km4c:RT_airQualityPM10RealTimeDeltaGral>" + String.valueOf(airQualityPM10RealTimeDeltaGral) + "</km4c:RT_airQualityPM10RealTimeDeltaGral>" : "")
                      + (airQualityPM2_5 != null && !airQualityPM2_5.isNaN() ? "<km4c:RT_airQualityPM2_5>" + String.valueOf(airQualityPM2_5) + "</km4c:RT_airQualityPM2_5>" : "")
                      + (airQualityPM2_5AverageLastHour != null && !airQualityPM2_5AverageLastHour.isNaN() ? "<km4c:RT_airQualityPM2_5AverageLastHour>" + String.valueOf(airQualityPM2_5AverageLastHour) + "</km4c:RT_airQualityPM2_5AverageLastHour>" : "")
                      + (airQualityPM2_5Enfuser != null && !airQualityPM2_5Enfuser.isNaN() ? "<km4c:RT_airQualityPM2_5Enfuser>" + String.valueOf(airQualityPM2_5Enfuser) + "</km4c:RT_airQualityPM2_5Enfuser>" : "")
                      + (airQualityPM2_5RealTimeDelta != null && !airQualityPM2_5RealTimeDelta.isNaN() ? "<km4c:RT_airQualityPM2_5RealTimeDelta>" + String.valueOf(airQualityPM2_5RealTimeDelta) + "</km4c:RT_airQualityPM2_5RealTimeDelta>" : "")
                      + (airQualitySO2 != null && !airQualitySO2.isNaN() ? "<km4c:RT_airQualitySO2>" + String.valueOf(airQualitySO2) + "</km4c:RT_airQualitySO2>" : "")
                      + (airQualityTRSC != null && !airQualityTRSC.isNaN() ? "<km4c:RT_airQualityTRSC>" + String.valueOf(airQualityTRSC) + "</km4c:RT_airQualityTRSC>" : "")
                      + (dateObserved != null ? "<km4c:RT_dateObserved>" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(dateObserved) + "</km4c:RT_dateObserved>" : "")
                      + (dateObservedFrom != null ? "<km4c:RT_dateObservedFrom>" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(dateObservedFrom) + "</km4c:RT_dateObservedFrom>" : "")
                      + (dateObservedTo != null ? "<km4c:RT_dateObservedTo>" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(dateObservedTo) + "</km4c:RT_dateObservedTo>" : "")
                      + (location != null ? "<km4c:RT_location><gml:Point srsDimension=\"2\" srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\"><gml:pos>" + String.valueOf(location.getJSONArray("coordinates").getDouble(1)) + " " + String.valueOf(location.getJSONArray("coordinates").getDouble(0)) + "</gml:pos></gml:Point></km4c:RT_location>" : "")
                      + (reliability != null && !reliability.isNaN() ? "<km4c:RT_reliability>" + String.valueOf(reliability) + "</km4c:RT_reliability>" : "")
                      + (source != null ? "<km4c:RT_source>" + source + "</km4c:RT_source>" : "")
                      + "</km4c:" + t + "Air_quality_monitoring_station>"
                      + "</wfs:member>";
              
                            if( ( l == 0 || periodStartTime != null ) && ( b == l-1 || l == 0) ) {
                                response = response + initFeat("Air_quality_monitoring_station", serviceUri, name, typeLabel, latitude, longitude, city, cap, province, address,           civic, phone, fax, website, email, note, linkDBpedia, avgStars, starsCount, periodStartTime, measuredTime, timezoneSuffix, requestContext);
                            }  
                            
              }
              
              response = response + "</wfs:FeatureCollection>";

            if ("hits".equals(pResultType)) {
              response = "<?xml version=\"1.0\" ?>\n"
                      + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                      + "</wfs:member></wfs:FeatureCollection>";
            }

            return Response.ok(response).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/gml+xml;version=3.2").header("Cache-Control", "no-cache").status(r.getStatus()).build();


            } else if (isWeather_sensor) {
              String response = "<?xml version=\"1.0\" ?>\n"
                    + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd " + serverUrl + "?service=WFS&amp;version=" + serverVersion + "&amp;request=DescribeFeatureType&amp;typeName=km4c%3Weather_sensor http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\">";
            
                          int l = 0;
            try {
                l = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).length();
            } catch(Exception x) {}
            
            for(int b = 0; b < Math.max(1,l); b++) {
                String serviceUri; // uri
              String name = null;
              double latitude = -1;
              double longitude = -1;
              double distance = -1;
              String city = null;
              String cap = null;
              String province = null;
              String address = null;
              String civic = null;
              String phone = null;
              String fax = null;
              String website; // uri
              String email = null;
              String note = null;
              String description = null;
              String linkDBpedia; // uri
              double avgStars = -1;
              int starsCount = -1;
              String typeLabel = null;
              String serviceType = null;

              Double minGroundTemperature = null;
              Double minTemperature = null;
              Double visibility = null;
              Double dewPoint = null;
              Double snow10m = null;
              Double snow = null;
              Double windGust = null;
              String sunrise = null;
              String weather = null;
              Double maxTemperature = null;
              Double cloudCoverPerc = null;
              Double snow3h = null;
              Double windSpeed = null;
              Double windDirection = null;
              Double rain1h = null;
              String sunset = null;
              Double cloudCoverOkta = null;
              Double airHumidity = null;
              Double snow1h = null;
              Double pressure = null;
              Double airTemperature = null;
              Double rain = null;
              Double atmosfericPressureForecast_003h = null;
              Double atmosfericPressureForecast_009h = null;
              Double atmosfericPressureForecast_030h = null;
              Double cloudCoverPerForecast_015h = null;
              Double cloudCoverPerForecast_021h = null;
              Double cloudCoverPerForecast_033h = null;
              Double seaLevelPressureForecast_003h = null;
              Double seaLevelPressureForecast_006h = null;
              Double seaLevelPressureForecast_015h = null;
              Double seaLevelPressureForecast_018h = null;
              Double seaLevelPressureForecast_027h = null;
              Double windDirectionForecast_015h = null;
              Double windDirectionForecast_021h = null;
              Double windDirectionForecast_024h = null;
              Double airHumidityForecast_003h = null;
              Double airHumidityForecast_009h = null;
              Double airHumidityForecast_024h = null;
              Double groundLevelPressureForecast_024h = null;
              Double groundLevelPressureForecast_030h = null;
              Double groundLevelPressureForecast_057h = null;
              Double airTemperatureForecast_003h = null;
              Double airTemperatureForecast_009h = null;
              Double airTemperatureForecast_012h = null;
              Double airTemperatureForecast_018h = null;
              Double airTemperatureForecast_021h = null;
              Double airTemperatureForecast_024h = null;
              Double windSpeedForecast_003h = null;
              Double windSpeedForecast_009h = null;
              Double windSpeedForecast_024h = null;
              Double atmosfericPressureForecast_006h = null;
              Double atmosfericPressureForecast_018h = null;
              Double atmosfericPressureForecast_021h = null;
              Double atmosfericPressureForecast_024h = null;
              Double cloudCoverPerForecast_003h = null;
              Double cloudCoverPerForecast_009h = null;
              Double cloudCoverPerForecast_018h = null;
              Double cloudCoverPerForecast_027h = null;
              Double seaLevelPressureForecast_021h = null;
              Double seaLevelPressureForecast_024h = null;
              Double seaLevelPressureForecast_039h = null;
              Double windDirectionForecast_003h = null;
              Double windDirectionForecast_006h = null;
              Double windDirectionForecast_018h = null;
              Double windDirectionForecast_036h = null;
              Double airHumidityForecast_012h = null;
              Double airHumidityForecast_015h = null;
              Double airHumidityForecast_021h = null;
              Double airHumidityForecast_030h = null;
              Double groundLevelPressureForecast_003h = null;
              Double groundLevelPressureForecast_012h = null;
              Double groundLevelPressureForecast_015h = null;
              Double groundLevelPressureForecast_021h = null;
              Double groundLevelPressureForecast_027h = null;
              Double airTemperatureForecast_006h = null;
              Double airTemperatureForecast_015h = null;
              Double airTemperatureForecast_033h = null;
              Double maxTemperatureForecast_006h = null;
              Double maxTemperatureForecast_024h = null;
              Double maxTemperatureForecast_027h = null;
              Double minTemperatureForecast_006h = null;
              Double minTemperatureForecast_012h = null;
              Double minTemperatureForecast_021h = null;
              Double minTemperatureForecast_024h = null;
              Double windSpeedForecast_006h = null;
              Double windSpeedForecast_015h = null;
              Double windSpeedForecast_018h = null;
              Double windSpeedForecast_033h = null;
              Double atmosfericPressureForecast_012h = null;
              Double atmosfericPressureForecast_015h = null;
              Double atmosfericPressureForecast_033h = null;
              Double cloudCoverPerForecast_006h = null;
              Double cloudCoverPerForecast_012h = null;
              Double cloudCoverPerForecast_024h = null;
              Double seaLevelPressureForecast_009h = null;
              Double seaLevelPressureForecast_012h = null;
              Double seaLevelPressureForecast_033h = null;
              Double windDirectionForecast_009h = null;
              Double windDirectionForecast_012h = null;
              Double windDirectionForecast_057h = null;
              Double airHumidityForecast_006h = null;
              Double airHumidityForecast_018h = null;
              Double airHumidityForecast_027h = null;
              Double groundLevelPressureForecast_006h = null;
              Double groundLevelPressureForecast_009h = null;
              Double groundLevelPressureForecast_018h = null;
              Double groundLevelPressureForecast_039h = null;
              Double airTemperatureForecast_036h = null;
              Double airTemperatureForecast_039h = null;
              Double airTemperatureForecast_045h = null;
              Double maxTemperatureForecast_012h = null;
              Double maxTemperatureForecast_015h = null;
              Double maxTemperatureForecast_045h = null;
              Double minTemperatureForecast_009h = null;
              Double minTemperatureForecast_015h = null;
              Double minTemperatureForecast_039h = null;
              Double windSpeedForecast_012h = null;
              Double windSpeedForecast_021h = null;
              Double windSpeedForecast_030h = null;

              try {
                serviceUri = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceUri");
                URI uri = URI.create(serviceUri);
              } catch (Exception ignore) {
                serviceUri = null;
              }
              try {
                name = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("name");
              } catch (Exception ignore) {
              }
              try {
                typeLabel = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("typeLabel");
              } catch (Exception ignore) {
              }
              try {
                serviceType = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceType");
              } catch (Exception ignore) {
              }
              try {
                longitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(0);
              } catch (Exception ignore) {
              }
              try {
                latitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(1);
              } catch (Exception ignore) {
              }
              try {
                distance = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("distance");
              } catch (Exception ignore) {
              }
              try {
                city = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("city");
              } catch (Exception ignore) {
              }
              try {
                cap = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("cap");
              } catch (Exception ignore) {
              }
              try {
                province = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("province");
              } catch (Exception ignore) {
              }
              try {
                address = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("address");
              } catch (Exception ignore) {
              }
              try {
                civic = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("civic");
              } catch (Exception ignore) {
              }
              try {
                phone = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("phone");
              } catch (Exception ignore) {
              }
              try {
                fax = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("fax");
              } catch (Exception ignore) {
              }
              try {
                website = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("website");
                URI uri = URI.create(website);
              } catch (Exception ignore) {
                website = null;
              }
              try {
                email = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("email");
              } catch (Exception ignore) {
              }
              try {
                note = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("note");
              } catch (Exception ignore) {
              }
              try {
                description = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("description");
              } catch (Exception ignore) {
              }
              try {
                linkDBpedia = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("linkDBpedia");
                URI uri = URI.create(linkDBpedia);
              } catch (Exception ignore) {
                linkDBpedia = null;
              }
              try {
                avgStars = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("avgStars");
              } catch (Exception ignore) {
              }
              try {
                starsCount = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getInt("starsCount");
              } catch (Exception ignore) {
              }

              try {
                minGroundTemperature = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("minGroundTemperature").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                minTemperature = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("minTemperature").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                visibility = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("visibility").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                dewPoint = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("dewPoint").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                snow10m = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("snow10m").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                snow = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("snow").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windGust = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windGust").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                sunrise = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("sunrise").getString("value");
              } catch (Exception ignore) {
              }
              try {
                weather = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("weather").getString("value");
              } catch (Exception ignore) {
              }
              try {
                maxTemperature = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("maxTemperature").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                cloudCoverPerc = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("cloudCoverPerc").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                snow3h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("snow3h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windSpeed = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windSpeed").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windDirection = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windDirection").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                rain1h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("rain1h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                sunset = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("sunset").getString("value");
              } catch (Exception ignore) {
              }
              try {
                cloudCoverOkta = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("cloudCoverOkta").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airHumidity = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airHumidity").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                snow1h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("snow1h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                pressure = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("pressure").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airTemperature = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airTemperature").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                rain = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("rain").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                atmosfericPressureForecast_003h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("atmosfericPressureForecast-003h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                atmosfericPressureForecast_009h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("atmosfericPressureForecast-009h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                atmosfericPressureForecast_030h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("atmosfericPressureForecast-030h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                cloudCoverPerForecast_015h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("cloudCoverPerForecast-015h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                cloudCoverPerForecast_021h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("cloudCoverPerForecast-021h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                cloudCoverPerForecast_033h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("cloudCoverPerForecast-033h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                seaLevelPressureForecast_003h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("seaLevelPressureForecast-003h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                seaLevelPressureForecast_006h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("seaLevelPressureForecast-006h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                seaLevelPressureForecast_015h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("seaLevelPressureForecast-015h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                seaLevelPressureForecast_018h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("seaLevelPressureForecast-018h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                seaLevelPressureForecast_027h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("seaLevelPressureForecast-027h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windDirectionForecast_015h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windDirectionForecast-015h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windDirectionForecast_021h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windDirectionForecast-021h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windDirectionForecast_024h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windDirectionForecast-024h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airHumidityForecast_003h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airHumidityForecast-003h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airHumidityForecast_009h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airHumidityForecast-009h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airHumidityForecast_024h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airHumidityForecast-024h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                groundLevelPressureForecast_024h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("groundLevelPressureForecast-024h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                groundLevelPressureForecast_030h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("groundLevelPressureForecast-030h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                groundLevelPressureForecast_057h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("groundLevelPressureForecast-057h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airTemperatureForecast_003h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airTemperatureForecast-003h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airTemperatureForecast_009h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airTemperatureForecast-009h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airTemperatureForecast_012h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airTemperatureForecast-012h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airTemperatureForecast_018h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airTemperatureForecast-018h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airTemperatureForecast_021h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airTemperatureForecast-021h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airTemperatureForecast_024h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airTemperatureForecast-024h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windSpeedForecast_003h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windSpeedForecast-003h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windSpeedForecast_009h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windSpeedForecast-009h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windSpeedForecast_024h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windSpeedForecast-024h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                atmosfericPressureForecast_006h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("atmosfericPressureForecast-006h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                atmosfericPressureForecast_018h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("atmosfericPressureForecast-018h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                atmosfericPressureForecast_021h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("atmosfericPressureForecast-021h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                atmosfericPressureForecast_024h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("atmosfericPressureForecast-024h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                cloudCoverPerForecast_003h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("cloudCoverPerForecast-003h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                cloudCoverPerForecast_009h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("cloudCoverPerForecast-009h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                cloudCoverPerForecast_018h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("cloudCoverPerForecast-018h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                cloudCoverPerForecast_027h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("cloudCoverPerForecast-027h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                seaLevelPressureForecast_021h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("seaLevelPressureForecast-021h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                seaLevelPressureForecast_024h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("seaLevelPressureForecast-024h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                seaLevelPressureForecast_039h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("seaLevelPressureForecast-039h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windDirectionForecast_003h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windDirectionForecast-003h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windDirectionForecast_006h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windDirectionForecast-006h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windDirectionForecast_018h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windDirectionForecast-018h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windDirectionForecast_036h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windDirectionForecast-036h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airHumidityForecast_012h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airHumidityForecast-012h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airHumidityForecast_015h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airHumidityForecast-015h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airHumidityForecast_021h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airHumidityForecast-021h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airHumidityForecast_030h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airHumidityForecast-030h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                groundLevelPressureForecast_003h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("groundLevelPressureForecast-003h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                groundLevelPressureForecast_012h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("groundLevelPressureForecast-012h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                groundLevelPressureForecast_015h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("groundLevelPressureForecast-015h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                groundLevelPressureForecast_021h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("groundLevelPressureForecast-021h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                groundLevelPressureForecast_027h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("groundLevelPressureForecast-027h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airTemperatureForecast_006h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airTemperatureForecast-006h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airTemperatureForecast_015h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airTemperatureForecast-015h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airTemperatureForecast_033h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airTemperatureForecast-033h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                maxTemperatureForecast_006h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("maxTemperatureForecast-006h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                maxTemperatureForecast_024h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("maxTemperatureForecast-024h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                maxTemperatureForecast_027h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("maxTemperatureForecast-027h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                minTemperatureForecast_006h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("minTemperatureForecast-006h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                minTemperatureForecast_012h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("minTemperatureForecast-012h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                minTemperatureForecast_021h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("minTemperatureForecast-021h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                minTemperatureForecast_024h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("minTemperatureForecast-024h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windSpeedForecast_006h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windSpeedForecast-006h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windSpeedForecast_015h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windSpeedForecast-015h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windSpeedForecast_018h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windSpeedForecast-018h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windSpeedForecast_033h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windSpeedForecast-033h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                atmosfericPressureForecast_012h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("atmosfericPressureForecast-012h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                atmosfericPressureForecast_015h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("atmosfericPressureForecast-015h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                atmosfericPressureForecast_033h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("atmosfericPressureForecast-033h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                cloudCoverPerForecast_006h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("cloudCoverPerForecast-006h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                cloudCoverPerForecast_012h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("cloudCoverPerForecast-012h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                cloudCoverPerForecast_024h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("cloudCoverPerForecast-024h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                seaLevelPressureForecast_009h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("seaLevelPressureForecast-009h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                seaLevelPressureForecast_012h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("seaLevelPressureForecast-012h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                seaLevelPressureForecast_033h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("seaLevelPressureForecast-033h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windDirectionForecast_009h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windDirectionForecast-009h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windDirectionForecast_012h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windDirectionForecast-012h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windDirectionForecast_057h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windDirectionForecast-057h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airHumidityForecast_006h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airHumidityForecast-006h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airHumidityForecast_018h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airHumidityForecast-018h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airHumidityForecast_027h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airHumidityForecast-027h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                groundLevelPressureForecast_006h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("groundLevelPressureForecast-006h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                groundLevelPressureForecast_009h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("groundLevelPressureForecast-009h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                groundLevelPressureForecast_018h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("groundLevelPressureForecast-018h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                groundLevelPressureForecast_039h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("groundLevelPressureForecast-039h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airTemperatureForecast_036h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airTemperatureForecast-036h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airTemperatureForecast_039h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airTemperatureForecast-039h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                airTemperatureForecast_045h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("airTemperatureForecast-045h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                maxTemperatureForecast_012h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("maxTemperatureForecast-012h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                maxTemperatureForecast_015h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("maxTemperatureForecast-015h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                maxTemperatureForecast_045h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("maxTemperatureForecast-045h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                minTemperatureForecast_009h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("minTemperatureForecast-009h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                minTemperatureForecast_015h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("minTemperatureForecast-015h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                minTemperatureForecast_039h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("minTemperatureForecast-039h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windSpeedForecast_012h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windSpeedForecast-012h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windSpeedForecast_021h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windSpeedForecast-021h").getDouble("value");
              } catch (Exception ignore) {
              }
              try {
                windSpeedForecast_030h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("windSpeedForecast-030h").getDouble("value");
              } catch (Exception ignore) {
              }
              java.util.Date measuredTime = null;
              java.util.Date periodStartTime = null;
              java.util.Date nextMeasuredTime = null;
              try { measuredTime = parseDate(((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("measuredTime").getString("value")); } catch(Exception ignore) {}
              try { periodStartTime = parseDate(requestContext.getParameter("fromTime")+ ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("measuredTime").getString("value").substring(19)); } catch(Exception ignore) { periodStartTime = parseDate(requestContext.getParameter("fromTime")+timezoneSuffix); }
              try {
                    if( b > 0) nextMeasuredTime = parseDate(((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b-1).getJSONObject("measuredTime").getString("value"));
                    else nextMeasuredTime = parseDate(requestContext.getParameter("toTime")+ ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(l-1).getJSONObject("measuredTime").getString("value").substring(19));                
              } catch (Exception ignore) {nextMeasuredTime = parseDate(requestContext.getParameter("toTime")+timezoneSuffix); }    
              

              /*if(b == 0) {
                  response = response + "<wfs:member>"
                      + "<km4c:" + t + "Weather_sensor gml:id=\"" + serviceUri + "\">\n" // + "#" + ( measuredTime != null ? String.valueOf(measuredTime.getTime()) : String.valueOf(b)) + "\">\n"
                      + (serviceUri != null && (!"null".equals(serviceUri)) && !serviceUri.isEmpty() ? "   <km4c:serviceUri>" + serviceUri.replaceAll("&", "&amp;") + "</km4c:serviceUri>" : "")
                      + (name != null && (!"null".equals(name)) && !name.isEmpty() ? "   <km4c:name>" + name.replaceAll("&", "&amp;") + "</km4c:name>" : "")
                      + (typeLabel != null && (!"null".equals(typeLabel)) && !typeLabel.isEmpty() ? "   <km4c:typeLabel>" + typeLabel + "</km4c:typeLabel>" : "")
                      + (latitude != -1 && longitude != -1 ? "<km4c:geometry><gml:Point srsDimension=\"2\" srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\"><gml:pos>" + String.valueOf(longitude) + " " + String.valueOf(latitude) + "</gml:pos></gml:Point></km4c:geometry>" : "")
                      + ( city != null && (!"null".equals(city)) && !city.isEmpty() ? "   <km4c:city>" + city + "</km4c:city>" : "")
                      + ( cap != null && (!"null".equals(cap)) && !cap.isEmpty() ? "   <km4c:cap>" + cap + "</km4c:cap>" : "")
                      + ( province != null && (!"null".equals(province)) && !province.isEmpty() ? "   <km4c:province>" + province + "</km4c:province>" : "")
                      + ( address != null && (!"null".equals(address)) && !address.isEmpty() ? "   <km4c:address>" + address + "</km4c:address>" : "")
                      + ( civic != null && (!"null".equals(civic)) && !civic.isEmpty() ? "   <km4c:civic>" + civic + "</km4c:civic>" : "")
                      + ( phone != null && (!"null".equals(phone)) && !phone.isEmpty() ? "   <km4c:phone>" + phone + "</km4c:phone>" : "")
                      + ( fax != null && (!"null".equals(fax)) && !fax.isEmpty() ? "   <km4c:fax>" + fax + "</km4c:fax>" : "")
                      + ( website != null && (!"null".equals(website)) && !website.isEmpty() ? "   <km4c:website>" + website.replaceAll("&", "&amp;") + "</km4c:website>" : "")
                      + ( email != null && (!"null".equals(email)) && !email.isEmpty() ? "   <km4c:email>" + email + "</km4c:email>" : "")
                      + ( note != null && (!"null".equals(note)) && !note.isEmpty() ? "   <km4c:note>" + note + "</km4c:note>" : "")
                      + ( linkDBpedia != null && (!"null".equals(linkDBpedia)) && !linkDBpedia.isEmpty() ? "   <km4c:linkDBpedia>" + linkDBpedia.replaceAll("&", "&amp;") + "</km4c:linkDBpedia>" : "")
                      + ( avgStars > 0 ? "   <km4c:avgStars>" + String.valueOf(avgStars) + "</km4c:avgStars>" : "")
                      + ( starsCount > 0 ? "   <km4c:starsCount>" + String.valueOf(starsCount) + "</km4c:starsCount>" : "")
                      + (measuredTime != null ? "<km4c:RT_measuredTime>" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(startTime) + "</km4c:RT_measuredTime>" : "")
                          + (measuredTime != null ? "<km4c:RT_nextTime>" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(measuredTime) + "</km4c:RT_nextTime>" : "")
                           + "</km4c:" + t + "Weather_sensor>"
                      + "</wfs:member>";
              }*/

              if (serviceUri == null) {
                throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceUri");
              }

              if (l > 0) response = response + "<wfs:member>"
                      + "<km4c:Weather_sensor gml:id=\"" + serviceUri + "\">\n" // "#" + ( measuredTime != null ? String.valueOf(measuredTime.getTime()) : String.valueOf(b)) + "\">\n"
                      + (serviceUri != null && (!"null".equals(serviceUri)) && !serviceUri.isEmpty() ? "   <km4c:serviceUri>" + serviceUri.replaceAll("&", "&amp;") + "</km4c:serviceUri>" : "")
                      + (name != null && (!"null".equals(name)) && !name.isEmpty() ? "   <km4c:name>" + name.replaceAll("&", "&amp;") + "</km4c:name>" : "")
                      + (typeLabel != null && (!"null".equals(typeLabel)) && !typeLabel.isEmpty() ? "   <km4c:typeLabel>" + typeLabel + "</km4c:typeLabel>" : "")
                      + (latitude != -1 && longitude != -1 ? "<km4c:geometry><gml:Point srsDimension=\"2\" srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\"><gml:pos>" + String.valueOf(longitude) + " " + String.valueOf(latitude) + "</gml:pos></gml:Point></km4c:geometry>" : "")
                      + ( city != null && (!"null".equals(city)) && !city.isEmpty() ? "   <km4c:city>" + city + "</km4c:city>" : "")
                      + ( cap != null && (!"null".equals(cap)) && !cap.isEmpty() ? "   <km4c:cap>" + cap + "</km4c:cap>" : "")
                      + ( province != null && (!"null".equals(province)) && !province.isEmpty() ? "   <km4c:province>" + province + "</km4c:province>" : "")
                      + ( address != null && (!"null".equals(address)) && !address.isEmpty() ? "   <km4c:address>" + address + "</km4c:address>" : "")
                      + ( civic != null && (!"null".equals(civic)) && !civic.isEmpty() ? "   <km4c:civic>" + civic + "</km4c:civic>" : "")
                      + ( phone != null && (!"null".equals(phone)) && !phone.isEmpty() ? "   <km4c:phone>" + phone + "</km4c:phone>" : "")
                      + ( fax != null && (!"null".equals(fax)) && !fax.isEmpty() ? "   <km4c:fax>" + fax + "</km4c:fax>" : "")
                      + ( website != null && (!"null".equals(website)) && !website.isEmpty() ? "   <km4c:website>" + website.replaceAll("&", "&amp;") + "</km4c:website>" : "")
                      + ( email != null && (!"null".equals(email)) && !email.isEmpty() ? "   <km4c:email>" + email + "</km4c:email>" : "")
                      + ( note != null && (!"null".equals(note)) && !note.isEmpty() ? "   <km4c:note>" + note + "</km4c:note>" : "")
                      + (linkDBpedia != null && (!"null".equals(linkDBpedia)) && !linkDBpedia.isEmpty() ? "   <km4c:linkDBpedia>" + linkDBpedia.replaceAll("&", "&amp;") + "</km4c:linkDBpedia>" : "")
                      + ( avgStars > 0 ? "   <km4c:avgStars>" + String.valueOf(avgStars) + "</km4c:avgStars>" : "")
                      + ( starsCount > 0 ? "   <km4c:starsCount>" + String.valueOf(starsCount) + "</km4c:starsCount>" : "")
                      + (measuredTime != null ? "<km4c:RT_measuredTime>" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(measuredTime) + "</km4c:RT_measuredTime>" : "")                    
                      + (nextMeasuredTime != null ? "<km4c:RT_nextTime>" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(nextMeasuredTime) + "</km4c:RT_nextTime>" : "")
                      + ( sunset != null && (!"null".equals(sunset)) && !sunset.isEmpty() ? "   <km4c:RT_sunset>" + sunset + "</km4c:RT_sunset>" : "")
                      + ( sunrise != null && (!"null".equals(sunrise)) && !sunrise.isEmpty() ? "   <km4c:RT_sunrise>" + sunrise + "</km4c:RT_sunrise>" : "")
                      + (weather != null && (!"null".equals(weather)) && !weather.isEmpty() ? "   <km4c:RT_weather>" + weather + "</km4c:RT_weather>" : "")
                      + ( minGroundTemperature != null && !minGroundTemperature.isNaN() ? "<km4c:RT_minGroundTemperature>" + String.valueOf(minGroundTemperature) + "</km4c:RT_minGroundTemperature>" : "")
                      + ( minTemperature != null && !minTemperature.isNaN() ? "<km4c:RT_minTemperature>" + String.valueOf(minTemperature) + "</km4c:RT_minTemperature>" : "")
                      + ( visibility != null && !visibility.isNaN() ? "<km4c:RT_visibility>" + String.valueOf(visibility) + "</km4c:RT_visibility>" : "")
                      + ( dewPoint != null && !dewPoint.isNaN() ? "<km4c:RT_dewPoint>" + String.valueOf(dewPoint) + "</km4c:RT_dewPoint>" : "")
                      + (snow10m != null && !snow10m.isNaN() ? "<km4c:RT_snow10m>" + String.valueOf(snow10m) + "</km4c:RT_snow10m>" : "")
                      + (snow != null && !snow.isNaN() ? "<km4c:RT_snow>" + String.valueOf(snow) + "</km4c:RT_snow>" : "")
                      + ( windGust != null && !windGust.isNaN() ? "<km4c:RT_windGust>" + String.valueOf(windGust) + "</km4c:RT_windGust>" : "")
                      + (maxTemperature != null && !maxTemperature.isNaN() ? "<km4c:RT_maxTemperature>" + String.valueOf(maxTemperature) + "</km4c:RT_maxTemperature>" : "")
                      + ( cloudCoverPerc != null && !cloudCoverPerc.isNaN() ? "<km4c:RT_cloudCoverPerc>" + String.valueOf(cloudCoverPerc) + "</km4c:RT_cloudCoverPerc>" : "")
                      + ( snow3h != null && !snow3h.isNaN() ? "<km4c:RT_snow3h>" + String.valueOf(snow3h) + "</km4c:RT_snow3h>" : "")
                      + ( windSpeed != null && !windSpeed.isNaN() ? "<km4c:RT_windSpeed>" + String.valueOf(windSpeed) + "</km4c:RT_windSpeed>" : "")
                      + (windDirection != null && !windDirection.isNaN() ? "<km4c:RT_windDirection>" + String.valueOf(windDirection) + "</km4c:RT_windDirection>" : "")
                      + (rain1h != null && !rain1h.isNaN() ? "<km4c:RT_rain1h>" + String.valueOf(rain1h) + "</km4c:RT_rain1h>" : "")
                      + ( cloudCoverOkta != null && !cloudCoverOkta.isNaN() ? "<km4c:RT_cloudCoverOkta>" + String.valueOf(cloudCoverOkta) + "</km4c:RT_cloudCoverOkta>" : "")
                      + ( airHumidity != null && !airHumidity.isNaN() ? "<km4c:RT_airHumidity>" + String.valueOf(airHumidity) + "</km4c:RT_airHumidity>" : "")
                      + ( snow1h != null && !snow1h.isNaN() ? "<km4c:RT_snow1h>" + String.valueOf(snow1h) + "</km4c:RT_snow1h>" : "")
                      + ( pressure != null && !pressure.isNaN() ? "<km4c:RT_pressure>" + String.valueOf(pressure) + "</km4c:RT_pressure>" : "")
                      + ( airTemperature != null && !airTemperature.isNaN() ? "<km4c:RT_airTemperature>" + String.valueOf(airTemperature) + "</km4c:RT_airTemperature>" : "")
                      + ( rain != null && !rain.isNaN() ? "<km4c:RT_rain>" + String.valueOf(rain) + "</km4c:RT_rain>" : "")
                      + ( atmosfericPressureForecast_003h != null && !atmosfericPressureForecast_003h.isNaN() ? "<km4c:RT_atmosfericPressureForecast_003h>" + String.valueOf(atmosfericPressureForecast_003h) + "</km4c:RT_atmosfericPressureForecast_003h>" : "")
                      + ( atmosfericPressureForecast_009h != null && !atmosfericPressureForecast_009h.isNaN() ? "<km4c:RT_atmosfericPressureForecast_009h>" + String.valueOf(atmosfericPressureForecast_009h) + "</km4c:RT_atmosfericPressureForecast_009h>" : "")
                      + ( atmosfericPressureForecast_030h != null && !atmosfericPressureForecast_030h.isNaN() ? "<km4c:RT_atmosfericPressureForecast_030h>" + String.valueOf(atmosfericPressureForecast_030h) + "</km4c:RT_atmosfericPressureForecast_030h>" : "")
                      + ( cloudCoverPerForecast_015h != null && !cloudCoverPerForecast_015h.isNaN() ? "<km4c:RT_cloudCoverPerForecast_015h>" + String.valueOf(cloudCoverPerForecast_015h) + "</km4c:RT_cloudCoverPerForecast_015h>" : "")
                      + ( cloudCoverPerForecast_021h != null && !cloudCoverPerForecast_021h.isNaN() ? "<km4c:RT_cloudCoverPerForecast_021h>" + String.valueOf(cloudCoverPerForecast_021h) + "</km4c:RT_cloudCoverPerForecast_021h>" : "")
                      + ( cloudCoverPerForecast_033h != null && !cloudCoverPerForecast_033h.isNaN() ? "<km4c:RT_cloudCoverPerForecast_033h>" + String.valueOf(cloudCoverPerForecast_033h) + "</km4c:RT_cloudCoverPerForecast_033h>" : "")
                      + ( seaLevelPressureForecast_003h != null && !seaLevelPressureForecast_003h.isNaN() ? "<km4c:RT_seaLevelPressureForecast_003h>" + String.valueOf(seaLevelPressureForecast_003h) + "</km4c:RT_seaLevelPressureForecast_003h>" : "")
                      + (seaLevelPressureForecast_006h != null && !seaLevelPressureForecast_006h.isNaN() ? "<km4c:RT_seaLevelPressureForecast_006h>" + String.valueOf(seaLevelPressureForecast_006h) + "</km4c:RT_seaLevelPressureForecast_006h>" : "")
                      + ( seaLevelPressureForecast_015h != null && !seaLevelPressureForecast_015h.isNaN() ? "<km4c:RT_seaLevelPressureForecast_015h>" + String.valueOf(seaLevelPressureForecast_015h) + "</km4c:RT_seaLevelPressureForecast_015h>" : "")
                      + ( seaLevelPressureForecast_018h != null && !seaLevelPressureForecast_018h.isNaN() ? "<km4c:RT_seaLevelPressureForecast_018h>" + String.valueOf(seaLevelPressureForecast_018h) + "</km4c:RT_seaLevelPressureForecast_018h>" : "")
                      + ( seaLevelPressureForecast_027h != null && !seaLevelPressureForecast_027h.isNaN() ? "<km4c:RT_seaLevelPressureForecast_027h>" + String.valueOf(seaLevelPressureForecast_027h) + "</km4c:RT_seaLevelPressureForecast_027h>" : "")
                      + ( windDirectionForecast_015h != null && !windDirectionForecast_015h.isNaN() ? "<km4c:RT_windDirectionForecast_015h>" + String.valueOf(windDirectionForecast_015h) + "</km4c:RT_windDirectionForecast_015h>" : "")
                      + ( windDirectionForecast_021h != null && !windDirectionForecast_021h.isNaN() ? "<km4c:RT_windDirectionForecast_021h>" + String.valueOf(windDirectionForecast_021h) + "</km4c:RT_windDirectionForecast_021h>" : "")
                      + ( windDirectionForecast_024h != null && !windDirectionForecast_024h.isNaN() ? "<km4c:RT_windDirectionForecast_024h>" + String.valueOf(windDirectionForecast_024h) + "</km4c:RT_windDirectionForecast_024h>" : "")
                      + ( airHumidityForecast_003h != null && !airHumidityForecast_003h.isNaN() ? "<km4c:RT_airHumidityForecast_003h>" + String.valueOf(airHumidityForecast_003h) + "</km4c:RT_airHumidityForecast_003h>" : "")
                      + ( airHumidityForecast_009h != null && !airHumidityForecast_009h.isNaN() ? "<km4c:RT_airHumidityForecast_009h>" + String.valueOf(airHumidityForecast_009h) + "</km4c:RT_airHumidityForecast_009h>" : "")
                      + ( airHumidityForecast_024h != null && !airHumidityForecast_024h.isNaN() ? "<km4c:RT_airHumidityForecast_024h>" + String.valueOf(airHumidityForecast_024h) + "</km4c:RT_airHumidityForecast_024h>" : "")
                      + ( groundLevelPressureForecast_024h != null && !groundLevelPressureForecast_024h.isNaN() ? "<km4c:RT_groundLevelPressureForecast_024h>" + String.valueOf(groundLevelPressureForecast_024h) + "</km4c:RT_groundLevelPressureForecast_024h>" : "")
                      + ( groundLevelPressureForecast_030h != null && !groundLevelPressureForecast_030h.isNaN() ? "<km4c:RT_groundLevelPressureForecast_030h>" + String.valueOf(groundLevelPressureForecast_030h) + "</km4c:RT_groundLevelPressureForecast_030h>" : "")
                      + ( groundLevelPressureForecast_057h != null && !groundLevelPressureForecast_057h.isNaN() ? "<km4c:RT_groundLevelPressureForecast_057h>" + String.valueOf(groundLevelPressureForecast_057h) + "</km4c:RT_groundLevelPressureForecast_057h>" : "")
                      + ( airTemperatureForecast_003h != null && !airTemperatureForecast_003h.isNaN() ? "<km4c:RT_airTemperatureForecast_003h>" + String.valueOf(airTemperatureForecast_003h) + "</km4c:RT_airTemperatureForecast_003h>" : "")
                      + ( airTemperatureForecast_009h != null && !airTemperatureForecast_009h.isNaN() ? "<km4c:RT_airTemperatureForecast_009h>" + String.valueOf(airTemperatureForecast_009h) + "</km4c:RT_airTemperatureForecast_009h>" : "")
                      + ( airTemperatureForecast_012h != null && !airTemperatureForecast_012h.isNaN() ? "<km4c:RT_airTemperatureForecast_012h>" + String.valueOf(airTemperatureForecast_012h) + "</km4c:RT_airTemperatureForecast_012h>" : "")
                      + ( airTemperatureForecast_018h != null && !airTemperatureForecast_018h.isNaN() ? "<km4c:RT_airTemperatureForecast_018h>" + String.valueOf(airTemperatureForecast_018h) + "</km4c:RT_airTemperatureForecast_018h>" : "")
                      + ( airTemperatureForecast_021h != null && !airTemperatureForecast_021h.isNaN() ? "<km4c:RT_airTemperatureForecast_021h>" + String.valueOf(airTemperatureForecast_021h) + "</km4c:RT_airTemperatureForecast_021h>" : "")
                      + ( airTemperatureForecast_024h != null && !airTemperatureForecast_024h.isNaN() ? "<km4c:RT_airTemperatureForecast_024h>" + String.valueOf(airTemperatureForecast_024h) + "</km4c:RT_airTemperatureForecast_024h>" : "")
                      + ( windSpeedForecast_003h != null && !windSpeedForecast_003h.isNaN() ? "<km4c:RT_windSpeedForecast_003h>" + String.valueOf(windSpeedForecast_003h) + "</km4c:RT_windSpeedForecast_003h>" : "")
                      + ( windSpeedForecast_009h != null && !windSpeedForecast_009h.isNaN() ? "<km4c:RT_windSpeedForecast_009h>" + String.valueOf(windSpeedForecast_009h) + "</km4c:RT_windSpeedForecast_009h>" : "")
                      + ( windSpeedForecast_024h != null && !windSpeedForecast_024h.isNaN() ? "<km4c:RT_windSpeedForecast_024h>" + String.valueOf(windSpeedForecast_024h) + "</km4c:RT_windSpeedForecast_024h>" : "")
                      + ( atmosfericPressureForecast_006h != null && !atmosfericPressureForecast_006h.isNaN() ? "<km4c:RT_atmosfericPressureForecast_006h>" + String.valueOf(atmosfericPressureForecast_006h) + "</km4c:RT_atmosfericPressureForecast_006h>" : "")
                      + ( atmosfericPressureForecast_018h != null && !atmosfericPressureForecast_018h.isNaN() ? "<km4c:RT_atmosfericPressureForecast_018h>" + String.valueOf(atmosfericPressureForecast_018h) + "</km4c:RT_atmosfericPressureForecast_018h>" : "")
                      + ( atmosfericPressureForecast_021h != null && !atmosfericPressureForecast_021h.isNaN() ? "<km4c:RT_atmosfericPressureForecast_021h>" + String.valueOf(atmosfericPressureForecast_021h) + "</km4c:RT_atmosfericPressureForecast_021h>" : "")
                      + ( atmosfericPressureForecast_024h != null && !atmosfericPressureForecast_024h.isNaN() ? "<km4c:RT_atmosfericPressureForecast_024h>" + String.valueOf(atmosfericPressureForecast_024h) + "</km4c:RT_atmosfericPressureForecast_024h>" : "")
                      + ( cloudCoverPerForecast_003h != null && !cloudCoverPerForecast_003h.isNaN() ? "<km4c:RT_cloudCoverPerForecast_003h>" + String.valueOf(cloudCoverPerForecast_003h) + "</km4c:RT_cloudCoverPerForecast_003h>" : "")
                      + ( cloudCoverPerForecast_009h != null && !cloudCoverPerForecast_009h.isNaN() ? "<km4c:RT_cloudCoverPerForecast_009h>" + String.valueOf(cloudCoverPerForecast_009h) + "</km4c:RT_cloudCoverPerForecast_009h>" : "")
                      + ( cloudCoverPerForecast_018h != null && !cloudCoverPerForecast_018h.isNaN() ? "<km4c:RT_cloudCoverPerForecast_018h>" + String.valueOf(cloudCoverPerForecast_018h) + "</km4c:RT_cloudCoverPerForecast_018h>" : "")
                      + ( cloudCoverPerForecast_027h != null && !cloudCoverPerForecast_027h.isNaN() ? "<km4c:RT_cloudCoverPerForecast_027h>" + String.valueOf(cloudCoverPerForecast_027h) + "</km4c:RT_cloudCoverPerForecast_027h>" : "")
                      + ( seaLevelPressureForecast_021h != null && !seaLevelPressureForecast_021h.isNaN() ? "<km4c:RT_seaLevelPressureForecast_021h>" + String.valueOf(seaLevelPressureForecast_021h) + "</km4c:RT_seaLevelPressureForecast_021h>" : "")
                      + ( seaLevelPressureForecast_024h != null && !seaLevelPressureForecast_024h.isNaN() ? "<km4c:RT_seaLevelPressureForecast_024h>" + String.valueOf(seaLevelPressureForecast_024h) + "</km4c:RT_seaLevelPressureForecast_024h>" : "")
                      + ( seaLevelPressureForecast_039h != null && !seaLevelPressureForecast_039h.isNaN() ? "<km4c:RT_seaLevelPressureForecast_039h>" + String.valueOf(seaLevelPressureForecast_039h) + "</km4c:RT_seaLevelPressureForecast_039h>" : "")
                      + ( windDirectionForecast_003h != null && !windDirectionForecast_003h.isNaN() ? "<km4c:RT_windDirectionForecast_003h>" + String.valueOf(windDirectionForecast_003h) + "</km4c:RT_windDirectionForecast_003h>" : "")
                      + ( windDirectionForecast_006h != null && !windDirectionForecast_006h.isNaN() ? "<km4c:RT_windDirectionForecast_006h>" + String.valueOf(windDirectionForecast_006h) + "</km4c:RT_windDirectionForecast_006h>" : "")
                      + ( windDirectionForecast_018h != null && !windDirectionForecast_018h.isNaN() ? "<km4c:RT_windDirectionForecast_018h>" + String.valueOf(windDirectionForecast_018h) + "</km4c:RT_windDirectionForecast_018h>" : "")
                      + ( windDirectionForecast_036h != null && !windDirectionForecast_036h.isNaN() ? "<km4c:RT_windDirectionForecast_036h>" + String.valueOf(windDirectionForecast_036h) + "</km4c:RT_windDirectionForecast_036h>" : "")
                      + ( airHumidityForecast_012h != null && !airHumidityForecast_012h.isNaN() ? "<km4c:RT_airHumidityForecast_012h>" + String.valueOf(airHumidityForecast_012h) + "</km4c:RT_airHumidityForecast_012h>" : "")
                      + ( airHumidityForecast_015h != null && !airHumidityForecast_015h.isNaN() ? "<km4c:RT_airHumidityForecast_015h>" + String.valueOf(airHumidityForecast_015h) + "</km4c:RT_airHumidityForecast_015h>" : "")
                      + ( airHumidityForecast_021h != null && !airHumidityForecast_021h.isNaN() ? "<km4c:RT_airHumidityForecast_021h>" + String.valueOf(airHumidityForecast_021h) + "</km4c:RT_airHumidityForecast_021h>" : "")
                      + ( airHumidityForecast_030h != null && !airHumidityForecast_030h.isNaN() ? "<km4c:RT_airHumidityForecast_030h>" + String.valueOf(airHumidityForecast_030h) + "</km4c:RT_airHumidityForecast_030h>" : "")
                      + ( groundLevelPressureForecast_003h != null && !groundLevelPressureForecast_003h.isNaN() ? "<km4c:RT_groundLevelPressureForecast_003h>" + String.valueOf(groundLevelPressureForecast_003h) + "</km4c:RT_groundLevelPressureForecast_003h>" : "")
                      + ( groundLevelPressureForecast_012h != null && !groundLevelPressureForecast_012h.isNaN() ? "<km4c:RT_groundLevelPressureForecast_012h>" + String.valueOf(groundLevelPressureForecast_012h) + "</km4c:RT_groundLevelPressureForecast_012h>" : "")
                      + ( groundLevelPressureForecast_015h != null && !groundLevelPressureForecast_015h.isNaN() ? "<km4c:RT_groundLevelPressureForecast_015h>" + String.valueOf(groundLevelPressureForecast_015h) + "</km4c:RT_groundLevelPressureForecast_015h>" : "")
                      + ( groundLevelPressureForecast_021h != null && !groundLevelPressureForecast_021h.isNaN() ? "<km4c:RT_groundLevelPressureForecast_021h>" + String.valueOf(groundLevelPressureForecast_021h) + "</km4c:RT_groundLevelPressureForecast_021h>" : "")
                      + ( groundLevelPressureForecast_027h != null && !groundLevelPressureForecast_027h.isNaN() ? "<km4c:RT_groundLevelPressureForecast_027h>" + String.valueOf(groundLevelPressureForecast_027h) + "</km4c:RT_groundLevelPressureForecast_027h>" : "")
                      + ( airTemperatureForecast_006h != null && !airTemperatureForecast_006h.isNaN() ? "<km4c:RT_airTemperatureForecast_006h>" + String.valueOf(airTemperatureForecast_006h) + "</km4c:RT_airTemperatureForecast_006h>" : "")
                      + (airTemperatureForecast_015h != null && !airTemperatureForecast_015h.isNaN() ? "<km4c:RT_airTemperatureForecast_015h>" + String.valueOf(airTemperatureForecast_015h) + "</km4c:RT_airTemperatureForecast_015h>" : "")
                      + ( airTemperatureForecast_033h != null && !airTemperatureForecast_033h.isNaN() ? "<km4c:RT_airTemperatureForecast_033h>" + String.valueOf(airTemperatureForecast_033h) + "</km4c:RT_airTemperatureForecast_033h>" : "")
                      + ( maxTemperatureForecast_006h != null && !maxTemperatureForecast_006h.isNaN() ? "<km4c:RT_maxTemperatureForecast_006h>" + String.valueOf(maxTemperatureForecast_006h) + "</km4c:RT_maxTemperatureForecast_006h>" : "")
                      + ( maxTemperatureForecast_024h != null && !maxTemperatureForecast_024h.isNaN() ? "<km4c:RT_maxTemperatureForecast_024h>" + String.valueOf(maxTemperatureForecast_024h) + "</km4c:RT_maxTemperatureForecast_024h>" : "")
                      + ( maxTemperatureForecast_027h != null && !maxTemperatureForecast_027h.isNaN() ? "<km4c:RT_maxTemperatureForecast_027h>" + String.valueOf(maxTemperatureForecast_027h) + "</km4c:RT_maxTemperatureForecast_027h>" : "")
                      + ( minTemperatureForecast_006h != null && !minTemperatureForecast_006h.isNaN() ? "<km4c:RT_minTemperatureForecast_006h>" + String.valueOf(minTemperatureForecast_006h) + "</km4c:RT_minTemperatureForecast_006h>" : "")
                      + ( minTemperatureForecast_012h != null && !minTemperatureForecast_012h.isNaN() ? "<km4c:RT_minTemperatureForecast_012h>" + String.valueOf(minTemperatureForecast_012h) + "</km4c:RT_minTemperatureForecast_012h>" : "")
                      + ( minTemperatureForecast_021h != null && !minTemperatureForecast_021h.isNaN() ? "<km4c:RT_minTemperatureForecast_021h>" + String.valueOf(minTemperatureForecast_021h) + "</km4c:RT_minTemperatureForecast_021h>" : "")
                      + ( minTemperatureForecast_024h != null && !minTemperatureForecast_024h.isNaN() ? "<km4c:RT_minTemperatureForecast_024h>" + String.valueOf(minTemperatureForecast_024h) + "</km4c:RT_minTemperatureForecast_024h>" : "")
                      + ( windSpeedForecast_006h != null && !windSpeedForecast_006h.isNaN() ? "<km4c:RT_windSpeedForecast_006h>" + String.valueOf(windSpeedForecast_006h) + "</km4c:RT_windSpeedForecast_006h>" : "")
                      + (windSpeedForecast_015h != null && !windSpeedForecast_015h.isNaN() ? "<km4c:RT_windSpeedForecast_015h>" + String.valueOf(windSpeedForecast_015h) + "</km4c:RT_windSpeedForecast_015h>" : "")
                      + ( windSpeedForecast_018h != null && !windSpeedForecast_018h.isNaN() ? "<km4c:RT_windSpeedForecast_018h>" + String.valueOf(windSpeedForecast_018h) + "</km4c:RT_windSpeedForecast_018h>" : "")
                      + ( windSpeedForecast_033h != null && !windSpeedForecast_033h.isNaN() ? "<km4c:RT_windSpeedForecast_033h>" + String.valueOf(windSpeedForecast_033h) + "</km4c:RT_windSpeedForecast_033h>" : "")
                      + ( atmosfericPressureForecast_012h != null && !atmosfericPressureForecast_012h.isNaN() ? "<km4c:RT_atmosfericPressureForecast_012h>" + String.valueOf(atmosfericPressureForecast_012h) + "</km4c:RT_atmosfericPressureForecast_012h>" : "")
                      + ( atmosfericPressureForecast_015h != null && !atmosfericPressureForecast_015h.isNaN() ? "<km4c:RT_atmosfericPressureForecast_015h>" + String.valueOf(atmosfericPressureForecast_015h) + "</km4c:RT_atmosfericPressureForecast_015h>" : "")
                      + ( atmosfericPressureForecast_033h != null && !atmosfericPressureForecast_033h.isNaN() ? "<km4c:RT_atmosfericPressureForecast_033h>" + String.valueOf(atmosfericPressureForecast_033h) + "</km4c:RT_atmosfericPressureForecast_033h>" : "")
                      + ( cloudCoverPerForecast_006h != null && !cloudCoverPerForecast_006h.isNaN() ? "<km4c:RT_cloudCoverPerForecast_006h>" + String.valueOf(cloudCoverPerForecast_006h) + "</km4c:RT_cloudCoverPerForecast_006h>" : "")
                      + ( cloudCoverPerForecast_012h != null && !cloudCoverPerForecast_012h.isNaN() ? "<km4c:RT_cloudCoverPerForecast_012h>" + String.valueOf(cloudCoverPerForecast_012h) + "</km4c:RT_cloudCoverPerForecast_012h>" : "")
                      + ( cloudCoverPerForecast_024h != null && !cloudCoverPerForecast_024h.isNaN() ? "<km4c:RT_cloudCoverPerForecast_024h>" + String.valueOf(cloudCoverPerForecast_024h) + "</km4c:RT_cloudCoverPerForecast_024h>" : "")
                      + ( seaLevelPressureForecast_009h != null && !seaLevelPressureForecast_009h.isNaN() ? "<km4c:RT_seaLevelPressureForecast_009h>" + String.valueOf(seaLevelPressureForecast_009h) + "</km4c:RT_seaLevelPressureForecast_009h>" : "")
                      + ( seaLevelPressureForecast_012h != null && !seaLevelPressureForecast_012h.isNaN() ? "<km4c:RT_seaLevelPressureForecast_012h>" + String.valueOf(seaLevelPressureForecast_012h) + "</km4c:RT_seaLevelPressureForecast_012h>" : "")
                      + ( seaLevelPressureForecast_033h != null && !seaLevelPressureForecast_033h.isNaN() ? "<km4c:RT_seaLevelPressureForecast_033h>" + String.valueOf(seaLevelPressureForecast_033h) + "</km4c:RT_seaLevelPressureForecast_033h>" : "")
                      + ( windDirectionForecast_009h != null && !windDirectionForecast_009h.isNaN() ? "<km4c:RT_windDirectionForecast_009h>" + String.valueOf(windDirectionForecast_009h) + "</km4c:RT_windDirectionForecast_009h>" : "")
                      + ( windDirectionForecast_012h != null && !windDirectionForecast_012h.isNaN() ? "<km4c:RT_windDirectionForecast_012h>" + String.valueOf(windDirectionForecast_012h) + "</km4c:RT_windDirectionForecast_012h>" : "")
                      + ( windDirectionForecast_057h != null && !windDirectionForecast_057h.isNaN() ? "<km4c:RT_windDirectionForecast_057h>" + String.valueOf(windDirectionForecast_057h) + "</km4c:RT_windDirectionForecast_057h>" : "")
                      + ( airHumidityForecast_006h != null && !airHumidityForecast_006h.isNaN() ? "<km4c:RT_airHumidityForecast_006h>" + String.valueOf(airHumidityForecast_006h) + "</km4c:RT_airHumidityForecast_006h>" : "")
                      + ( airHumidityForecast_018h != null && !airHumidityForecast_018h.isNaN() ? "<km4c:RT_airHumidityForecast_018h>" + String.valueOf(airHumidityForecast_018h) + "</km4c:RT_airHumidityForecast_018h>" : "")
                      + ( airHumidityForecast_027h != null && !airHumidityForecast_027h.isNaN() ? "<km4c:RT_airHumidityForecast_027h>" + String.valueOf(airHumidityForecast_027h) + "</km4c:RT_airHumidityForecast_027h>" : "")
                      + ( groundLevelPressureForecast_006h != null && !groundLevelPressureForecast_006h.isNaN() ? "<km4c:RT_groundLevelPressureForecast_006h>" + String.valueOf(groundLevelPressureForecast_006h) + "</km4c:RT_groundLevelPressureForecast_006h>" : "")
                      + ( groundLevelPressureForecast_009h != null && !groundLevelPressureForecast_009h.isNaN() ? "<km4c:RT_groundLevelPressureForecast_009h>" + String.valueOf(groundLevelPressureForecast_009h) + "</km4c:RT_groundLevelPressureForecast_009h>" : "")
                      + ( groundLevelPressureForecast_018h != null && !groundLevelPressureForecast_018h.isNaN() ? "<km4c:RT_groundLevelPressureForecast_018h>" + String.valueOf(groundLevelPressureForecast_018h) + "</km4c:RT_groundLevelPressureForecast_018h>" : "")
                      + ( groundLevelPressureForecast_039h != null && !groundLevelPressureForecast_039h.isNaN() ? "<km4c:RT_groundLevelPressureForecast_039h>" + String.valueOf(groundLevelPressureForecast_039h) + "</km4c:RT_groundLevelPressureForecast_039h>" : "")
                      + ( airTemperatureForecast_036h != null && !airTemperatureForecast_036h.isNaN() ? "<km4c:RT_airTemperatureForecast_036h>" + String.valueOf(airTemperatureForecast_036h) + "</km4c:RT_airTemperatureForecast_036h>" : "")
                      + ( airTemperatureForecast_039h != null && !airTemperatureForecast_039h.isNaN() ? "<km4c:RT_airTemperatureForecast_039h>" + String.valueOf(airTemperatureForecast_039h) + "</km4c:RT_airTemperatureForecast_039h>" : "")
                      + ( airTemperatureForecast_045h != null && !airTemperatureForecast_045h.isNaN() ? "<km4c:RT_airTemperatureForecast_045h>" + String.valueOf(airTemperatureForecast_045h) + "</km4c:RT_airTemperatureForecast_045h>" : "")
                      + ( maxTemperatureForecast_012h != null && !maxTemperatureForecast_012h.isNaN() ? "<km4c:RT_maxTemperatureForecast_012h>" + String.valueOf(maxTemperatureForecast_012h) + "</km4c:RT_maxTemperatureForecast_012h>" : "")
                      + ( maxTemperatureForecast_015h != null && !maxTemperatureForecast_015h.isNaN() ? "<km4c:RT_maxTemperatureForecast_015h>" + String.valueOf(maxTemperatureForecast_015h) + "</km4c:RT_maxTemperatureForecast_015h>" : "")
                      + ( maxTemperatureForecast_045h != null && !maxTemperatureForecast_045h.isNaN() ? "<km4c:RT_maxTemperatureForecast_045h>" + String.valueOf(maxTemperatureForecast_045h) + "</km4c:RT_maxTemperatureForecast_045h>" : "")
                      + ( minTemperatureForecast_009h != null && !minTemperatureForecast_009h.isNaN() ? "<km4c:RT_minTemperatureForecast_009h>" + String.valueOf(minTemperatureForecast_009h) + "</km4c:RT_minTemperatureForecast_009h>" : "")
                      + ( minTemperatureForecast_015h != null && !minTemperatureForecast_015h.isNaN() ? "<km4c:RT_minTemperatureForecast_015h>" + String.valueOf(minTemperatureForecast_015h) + "</km4c:RT_minTemperatureForecast_015h>" : "")
                      + ( minTemperatureForecast_039h != null && !minTemperatureForecast_039h.isNaN() ? "<km4c:RT_minTemperatureForecast_039h>" + String.valueOf(minTemperatureForecast_039h) + "</km4c:RT_minTemperatureForecast_039h>" : "")
                      + ( windSpeedForecast_012h != null && !windSpeedForecast_012h.isNaN() ? "<km4c:RT_windSpeedForecast_012h>" + String.valueOf(windSpeedForecast_012h) + "</km4c:RT_windSpeedForecast_012h>" : "")
                      + ( windSpeedForecast_021h != null && !windSpeedForecast_021h.isNaN() ? "<km4c:RT_windSpeedForecast_021h>" + String.valueOf(windSpeedForecast_021h) + "</km4c:RT_windSpeedForecast_021h>" : "")
                      + ( windSpeedForecast_030h != null && !windSpeedForecast_030h.isNaN() ? "<km4c:RT_windSpeedForecast_030h>" + String.valueOf(windSpeedForecast_030h) + "</km4c:RT_windSpeedForecast_030h>" : "")
                      + "</km4c:Weather_sensor>"
                      + "</wfs:member>";
              
                            if( ( l == 0 || periodStartTime != null ) && ( b == l-1 || l == 0) ) {
                                response = response + initFeat("Weather_sensor", serviceUri, name, typeLabel, latitude, longitude, city, cap, province, address,           civic, phone, fax, website, email, note, linkDBpedia, avgStars, starsCount, periodStartTime, measuredTime, timezoneSuffix, requestContext);
                            }  
                            
            }
            
            response = response + "</wfs:FeatureCollection>";

            if ("hits".equals(pResultType)) {
              response = "<?xml version=\"1.0\" ?>\n"
                      + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                      + "</wfs:member></wfs:FeatureCollection>";
            }

            return Response.ok(response).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/gml+xml;version=3.2").header("Cache-Control", "no-cache").status(r.getStatus()).build();

            } else if (isFerry_stop) {
              String serviceUri; // uri
              String name = null;
              double latitude = -1;
              double longitude = -1;
              double distance = -1;
              String city = null;
              String cap = null;
              String province = null;
              String address = null;
              String civic = null;
              String phone = null;
              String fax = null;
              String website; // uri
              String email = null;
              String note = null;
              String description = null;
              String linkDBpedia; // uri
              double avgStars = -1;
              int starsCount = -1;
              String typeLabel = null;
              String serviceType = null;
              try {
                serviceUri = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceUri");
                URI uri = URI.create(serviceUri);
              } catch (Exception ignore) {
                serviceUri = null;
              }
              try {
                name = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("name");
              } catch (Exception ignore) {
              }
              try {
                typeLabel = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("typeLabel");
              } catch (Exception ignore) {
              }
              try {
                serviceType = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceType");
              } catch (Exception ignore) {
              }
              try {
                longitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(0);
              } catch (Exception ignore) {
              }
              try {
                latitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(1);
              } catch (Exception ignore) {
              }
              try {
                distance = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("distance");
              } catch (Exception ignore) {
              }
              try {
                city = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("city");
              } catch (Exception ignore) {
              }
              try {
                cap = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("cap");
              } catch (Exception ignore) {
              }
              try {
                province = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("province");
              } catch (Exception ignore) {
              }
              try {
                address = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("address");
              } catch (Exception ignore) {
              }
              try {
                civic = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("civic");
              } catch (Exception ignore) {
              }
              try {
                phone = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("phone");
              } catch (Exception ignore) {
              }
              try {
                fax = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("fax");
              } catch (Exception ignore) {
              }
              try {
                website = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("website");
                URI uri = URI.create(website);
              } catch (Exception ignore) {
                website = null;
              }
              try {
                email = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("email");
              } catch (Exception ignore) {
              }
              try {
                note = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("note");
              } catch (Exception ignore) {
              }
              try {
                description = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("description");
              } catch (Exception ignore) {
              }
              try {
                linkDBpedia = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("linkDBpedia");
                URI uri = URI.create(linkDBpedia);
              } catch (Exception ignore) {
                linkDBpedia = null;
              }
              try {
                avgStars = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("avgStars");
              } catch (Exception ignore) {
              }
              try {
                starsCount = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getInt("starsCount");
              } catch (Exception ignore) {
              }
              if (serviceUri == null) {
                throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceUri");
              }
              if (serviceType == null) {
                throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceType");
              }


              /*String response = "<?xml version=\"1.0\" ?>\n"
                      + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd " + serverUrl + "?service=WFS&amp;version=" + serverVersion + "&amp;request=DescribeFeatureType&amp;typeName=km4c%3Service http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                      + "<km4c:Ferry_stop gml:id=\"" + serviceUri + "\">\n"
                      + (serviceUri != null && (!"null".equals(serviceUri)) && !serviceUri.isEmpty() ? "   <km4c:serviceUri>" + serviceUri.replaceAll("&", "&amp;") + "</km4c:serviceUri>" : "")
                      + (name != null && (!"null".equals(name)) && !name.isEmpty() ? "   <km4c:name>" + name.replaceAll("&", "&amp;") + "</km4c:name>" : "")
                      + (typeLabel != null && (!"null".equals(typeLabel)) && !typeLabel.isEmpty() ? "   <km4c:typeLabel>" + typeLabel + "</km4c:typeLabel>" : "")
                      + (latitude != -1 && longitude != -1 ? "<km4c:geometry><gml:Point srsDimension=\"2\" srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\"><gml:pos>" + String.valueOf(longitude) + " " + String.valueOf(latitude) + "</gml:pos></gml:Point></km4c:geometry>" : "")
                      + (city != null && (!"null".equals(city)) && !city.isEmpty() ? "   <km4c:city>" + city + "</km4c:city>" : "")
                      + (cap != null && (!"null".equals(cap)) && !cap.isEmpty() ? "   <km4c:cap>" + cap + "</km4c:cap>" : "")
                      + (province != null && (!"null".equals(province)) && !province.isEmpty() ? "   <km4c:province>" + province + "</km4c:province>" : "")
                      + (address != null && (!"null".equals(address)) && !address.isEmpty() ? "   <km4c:address>" + address + "</km4c:address>" : "")
                      + (civic != null && (!"null".equals(civic)) && !civic.isEmpty() ? "   <km4c:civic>" + civic + "</km4c:civic>" : "")
                      + (phone != null && (!"null".equals(phone)) && !phone.isEmpty() ? "   <km4c:phone>" + phone + "</km4c:phone>" : "")
                      + (fax != null && (!"null".equals(fax)) && !fax.isEmpty() ? "   <km4c:fax>" + fax + "</km4c:fax>" : "")
                      + (website != null && (!"null".equals(website)) && !website.isEmpty() ? "   <km4c:website>" + website.replaceAll("&", "&amp;") + "</km4c:website>" : "")
                      + (email != null && (!"null".equals(email)) && !email.isEmpty() ? "   <km4c:email>" + email + "</km4c:email>" : "")
                      + (note != null && (!"null".equals(note)) && !note.isEmpty() ? "   <km4c:note>" + note + "</km4c:note>" : "")
                      + (linkDBpedia != null && (!"null".equals(linkDBpedia)) && !linkDBpedia.isEmpty() ? "   <km4c:linkDBpedia>" + linkDBpedia.replaceAll("&", "&amp;") + "</km4c:linkDBpedia>" : "")
                      + (avgStars > 0 ? "   <km4c:avgStars>" + String.valueOf(avgStars) + "</km4c:avgStars>" : "")
                      + (starsCount > 0 ? "   <km4c:starsCount>" + String.valueOf(starsCount) + "</km4c:starsCount>" : "")
                      + "</km4c:Ferry_stop>"
                      + "</wfs:member></wfs:FeatureCollection>";*/
                            String response = bldResp( "Ferry_stop",  nowAsISO,  serverUrl,  serverVersion,  serviceType,  serviceUri, name,  typeLabel,  latitude, 
           longitude,  city,  cap,  province,  address,  civic,  phone,  fax,  website,  email,  note,
           linkDBpedia,  avgStars,  starsCount );

              if ("hits".equals(pResultType)) {
                response = "<?xml version=\"1.0\" ?>\n"
                        + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                        + "</wfs:member></wfs:FeatureCollection>";
              }

              return Response.ok(response).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/gml+xml;version=3.2").status(r.getStatus()).header("Cache-Control", "no-cache").build();

            } else if (isIoTActuator) {
              String serviceUri; // uri
              String name = null;
              double latitude = -1;
              double longitude = -1;
              double distance = -1;
              String city = null;
              String cap = null;
              String province = null;
              String address = null;
              String civic = null;
              String phone = null;
              String fax = null;
              String website; // uri
              String email = null;
              String note = null;
              String description = null;
              String linkDBpedia; // uri
              double avgStars = -1;
              int starsCount = -1;
              String typeLabel = null;
              String serviceType = null;
              try {
                serviceUri = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceUri");
                URI uri = URI.create(serviceUri);
              } catch (Exception ignore) {
                serviceUri = null;
              }
              try {
                name = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("name");
              } catch (Exception ignore) {
              }
              try {
                typeLabel = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("typeLabel");
              } catch (Exception ignore) {
              }
              try {
                serviceType = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceType");
              } catch (Exception ignore) {
              }
              try {
                longitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(0);
              } catch (Exception ignore) {
              }
              try {
                latitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(1);
              } catch (Exception ignore) {
              }
              try {
                distance = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("distance");
              } catch (Exception ignore) {
              }
              try {
                city = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("city");
              } catch (Exception ignore) {
              }
              try {
                cap = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("cap");
              } catch (Exception ignore) {
              }
              try {
                province = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("province");
              } catch (Exception ignore) {
              }
              try {
                address = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("address");
              } catch (Exception ignore) {
              }
              try {
                civic = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("civic");
              } catch (Exception ignore) {
              }
              try {
                phone = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("phone");
              } catch (Exception ignore) {
              }
              try {
                fax = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("fax");
              } catch (Exception ignore) {
              }
              try {
                website = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("website");
                URI uri = URI.create(website);
              } catch (Exception ignore) {
                website = null;
              }
              try {
                email = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("email");
              } catch (Exception ignore) {
              }
              try {
                note = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("note");
              } catch (Exception ignore) {
              }
              try {
                description = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("description");
              } catch (Exception ignore) {
              }
              try {
                linkDBpedia = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("linkDBpedia");
                URI uri = URI.create(linkDBpedia);
              } catch (Exception ignore) {
                linkDBpedia = null;
              }
              try {
                avgStars = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("avgStars");
              } catch (Exception ignore) {
              }
              try {
                starsCount = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getInt("starsCount");
              } catch (Exception ignore) {
              }
              if (serviceUri == null) {
                throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceUri");
              }
              if (serviceType == null) {
                throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceType");
              }

              /*
              String response = "<?xml version=\"1.0\" ?>\n"
                      + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd " + serverUrl + "?service=WFS&amp;version=" + serverVersion + "&amp;request=DescribeFeatureType&amp;typeName=km4c%3Service http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                      + "<km4c:IoTActuator gml:id=\"" + serviceUri + "\">\n"
                      + (serviceUri != null && (!"null".equals(serviceUri)) && !serviceUri.isEmpty() ? "   <km4c:serviceUri>" + serviceUri.replaceAll("&", "&amp;") + "</km4c:serviceUri>" : "")
                      + (name != null && (!"null".equals(name)) && !name.isEmpty() ? "   <km4c:name>" + name.replaceAll("&", "&amp;") + "</km4c:name>" : "")
                      + (typeLabel != null && (!"null".equals(typeLabel)) && !typeLabel.isEmpty() ? "   <km4c:typeLabel>" + typeLabel + "</km4c:typeLabel>" : "")
                      + (latitude != -1 && longitude != -1 ? "<km4c:geometry><gml:Point srsDimension=\"2\" srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\"><gml:pos>" + String.valueOf(longitude) + " " + String.valueOf(latitude) + "</gml:pos></gml:Point></km4c:geometry>" : "")
                      + (city != null && (!"null".equals(city)) && !city.isEmpty() ? "   <km4c:city>" + city + "</km4c:city>" : "")
                      + (cap != null && (!"null".equals(cap)) && !cap.isEmpty() ? "   <km4c:cap>" + cap + "</km4c:cap>" : "")
                      + (province != null && (!"null".equals(province)) && !province.isEmpty() ? "   <km4c:province>" + province + "</km4c:province>" : "")
                      + (address != null && (!"null".equals(address)) && !address.isEmpty() ? "   <km4c:address>" + address + "</km4c:address>" : "")
                      + (civic != null && (!"null".equals(civic)) && !civic.isEmpty() ? "   <km4c:civic>" + civic + "</km4c:civic>" : "")
                      + (phone != null && (!"null".equals(phone)) && !phone.isEmpty() ? "   <km4c:phone>" + phone + "</km4c:phone>" : "")
                      + (fax != null && (!"null".equals(fax)) && !fax.isEmpty() ? "   <km4c:fax>" + fax + "</km4c:fax>" : "")
                      + (website != null && (!"null".equals(website)) && !website.isEmpty() ? "   <km4c:website>" + website.replaceAll("&", "&amp;") + "</km4c:website>" : "")
                      + (email != null && (!"null".equals(email)) && !email.isEmpty() ? "   <km4c:email>" + email + "</km4c:email>" : "")
                      + (note != null && (!"null".equals(note)) && !note.isEmpty() ? "   <km4c:note>" + note + "</km4c:note>" : "")
                      + (linkDBpedia != null && (!"null".equals(linkDBpedia)) && !linkDBpedia.isEmpty() ? "   <km4c:linkDBpedia>" + linkDBpedia.replaceAll("&", "&amp;") + "</km4c:linkDBpedia>" : "")
                      + (avgStars > 0 ? "   <km4c:avgStars>" + String.valueOf(avgStars) + "</km4c:avgStars>" : "")
                      + (starsCount > 0 ? "   <km4c:starsCount>" + String.valueOf(starsCount) + "</km4c:starsCount>" : "")
                      + "</km4c:IoTActuator>"
                      + "</wfs:member></wfs:FeatureCollection>";*/
                            String response = bldResp( "IoTActuator",  nowAsISO,  serverUrl,  serverVersion,  serviceType,  serviceUri, name,  typeLabel,  latitude, 
           longitude,  city,  cap,  province,  address,  civic,  phone,  fax,  website,  email,  note,
           linkDBpedia,  avgStars,  starsCount );

              if ("hits".equals(pResultType)) {
                response = "<?xml version=\"1.0\" ?>\n"
                        + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                        + "</wfs:member></wfs:FeatureCollection>";
              }

              return Response.ok(response).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/gml+xml;version=3.2").status(r.getStatus()).header("Cache-Control", "no-cache").build();

            } else if (isIoTSensor) {
              String serviceUri; // uri
              String name = null;
              double latitude = -1;
              double longitude = -1;
              double distance = -1;
              String city = null;
              String cap = null;
              String province = null;
              String address = null;
              String civic = null;
              String phone = null;
              String fax = null;
              String website; // uri
              String email = null;
              String note = null;
              String description = null;
              String linkDBpedia; // uri
              double avgStars = -1;
              int starsCount = -1;
              String typeLabel = null;
              String serviceType = null;
              try {
                serviceUri = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceUri");
                URI uri = URI.create(serviceUri);
              } catch (Exception ignore) {
                serviceUri = null;
              }
              try {
                name = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("name");
              } catch (Exception ignore) {
              }
              try {
                typeLabel = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("typeLabel");
              } catch (Exception ignore) {
              }
              try {
                serviceType = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceType");
              } catch (Exception ignore) {
              }
              try {
                longitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(0);
              } catch (Exception ignore) {
              }
              try {
                latitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(1);
              } catch (Exception ignore) {
              }
              try {
                distance = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("distance");
              } catch (Exception ignore) {
              }
              try {
                city = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("city");
              } catch (Exception ignore) {
              }
              try {
                cap = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("cap");
              } catch (Exception ignore) {
              }
              try {
                province = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("province");
              } catch (Exception ignore) {
              }
              try {
                address = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("address");
              } catch (Exception ignore) {
              }
              try {
                civic = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("civic");
              } catch (Exception ignore) {
              }
              try {
                phone = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("phone");
              } catch (Exception ignore) {
              }
              try {
                fax = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("fax");
              } catch (Exception ignore) {
              }
              try {
                website = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("website");
                URI uri = URI.create(website);
              } catch (Exception ignore) {
                website = null;
              }
              try {
                email = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("email");
              } catch (Exception ignore) {
              }
              try {
                note = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("note");
              } catch (Exception ignore) {
              }
              try {
                description = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("description");
              } catch (Exception ignore) {
              }
              try {
                linkDBpedia = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("linkDBpedia");
                URI uri = URI.create(linkDBpedia);
              } catch (Exception ignore) {
                linkDBpedia = null;
              }
              try {
                avgStars = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("avgStars");
              } catch (Exception ignore) {
              }
              try {
                starsCount = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getInt("starsCount");
              } catch (Exception ignore) {
              }
              if (serviceUri == null) {
                throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceUri");
              }
              if (serviceType == null) {
                throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceType");
              }

              /*
              String response = "<?xml version=\"1.0\" ?>\n"
                      + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd " + serverUrl + "?service=WFS&amp;version=" + serverVersion + "&amp;request=DescribeFeatureType&amp;typeName=km4c%3Service http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                      + "<km4c:IoTSensor gml:id=\"" + serviceUri + "\">\n"
                      + (serviceUri != null && (!"null".equals(serviceUri)) && !serviceUri.isEmpty() ? "   <km4c:serviceUri>" + serviceUri.replaceAll("&", "&amp;") + "</km4c:serviceUri>" : "")
                      + (name != null && (!"null".equals(name)) && !name.isEmpty() ? "   <km4c:name>" + name.replaceAll("&", "&amp;") + "</km4c:name>" : "")
                      + (typeLabel != null && (!"null".equals(typeLabel)) && !typeLabel.isEmpty() ? "   <km4c:typeLabel>" + typeLabel + "</km4c:typeLabel>" : "")
                      + (latitude != -1 && longitude != -1 ? "<km4c:geometry><gml:Point srsDimension=\"2\" srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\"><gml:pos>" + String.valueOf(longitude) + " " + String.valueOf(latitude) + "</gml:pos></gml:Point></km4c:geometry>" : "")
                      + (city != null && (!"null".equals(city)) && !city.isEmpty() ? "   <km4c:city>" + city + "</km4c:city>" : "")
                      + (cap != null && (!"null".equals(cap)) && !cap.isEmpty() ? "   <km4c:cap>" + cap + "</km4c:cap>" : "")
                      + (province != null && (!"null".equals(province)) && !province.isEmpty() ? "   <km4c:province>" + province + "</km4c:province>" : "")
                      + (address != null && (!"null".equals(address)) && !address.isEmpty() ? "   <km4c:address>" + address + "</km4c:address>" : "")
                      + (civic != null && (!"null".equals(civic)) && !civic.isEmpty() ? "   <km4c:civic>" + civic + "</km4c:civic>" : "")
                      + (phone != null && (!"null".equals(phone)) && !phone.isEmpty() ? "   <km4c:phone>" + phone + "</km4c:phone>" : "")
                      + (fax != null && (!"null".equals(fax)) && !fax.isEmpty() ? "   <km4c:fax>" + fax + "</km4c:fax>" : "")
                      + (website != null && (!"null".equals(website)) && !website.isEmpty() ? "   <km4c:website>" + website.replaceAll("&", "&amp;") + "</km4c:website>" : "")
                      + (email != null && (!"null".equals(email)) && !email.isEmpty() ? "   <km4c:email>" + email + "</km4c:email>" : "")
                      + (note != null && (!"null".equals(note)) && !note.isEmpty() ? "   <km4c:note>" + note + "</km4c:note>" : "")
                      + (linkDBpedia != null && (!"null".equals(linkDBpedia)) && !linkDBpedia.isEmpty() ? "   <km4c:linkDBpedia>" + linkDBpedia.replaceAll("&", "&amp;") + "</km4c:linkDBpedia>" : "")
                      + (avgStars > 0 ? "   <km4c:avgStars>" + String.valueOf(avgStars) + "</km4c:avgStars>" : "")
                      + (starsCount > 0 ? "   <km4c:starsCount>" + String.valueOf(starsCount) + "</km4c:starsCount>" : "")
                      + "</km4c:IoTSensor>"
                      + "</wfs:member></wfs:FeatureCollection>";*/
                            String response = bldResp( "IoTSensor",  nowAsISO,  serverUrl,  serverVersion,  serviceType,  serviceUri, name,  typeLabel,  latitude, 
           longitude,  city,  cap,  province,  address,  civic,  phone,  fax,  website,  email,  note,
           linkDBpedia,  avgStars,  starsCount );

              if ("hits".equals(pResultType)) {
                response = "<?xml version=\"1.0\" ?>\n"
                        + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                        + "</wfs:member></wfs:FeatureCollection>";
              }

              return Response.ok(response).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/gml+xml;version=3.2").status(r.getStatus()).header("Cache-Control", "no-cache").build();

            } else if (isNoiseLevelSensor) {
                String response = "<?xml version=\"1.0\" ?>\n"
                    + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd " + serverUrl + "?service=WFS&amp;version=" + serverVersion + "&amp;request=DescribeFeatureType&amp;typeName=km4c%3Service http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\">";
              
                            int l = 0;
            try {
                l = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).length();
            } catch(Exception x) {}
            
              for(int b = 0; b < Math.max(1,l); b++) {
                String serviceUri; // uri
              String name = null;
              double latitude = -1;
              double longitude = -1;
              double distance = -1;
              String city = null;
              String cap = null;
              String province = null;
              String address = null;
              String civic = null;
              String phone = null;
              String fax = null;
              String website; // uri
              String email = null;
              String note = null;
              String description = null;
              String linkDBpedia; // uri
              double avgStars = -1;
              int starsCount = -1;
              String typeLabel = null;
              String serviceType = null;

              String LAeq = null;
              String LAmax = null;
              String dateObserved = null;
              String dateObservedFrom = null;
              String dateObservedTo = null;
              String location = null;
              String measurand = null;
              String noiseLAeqAvg2h = null;
              String sonometerClass = null;
              java.util.Date measuredTime = null;
              java.util.Date periodStartTime = null;
              java.util.Date nextMeasuredTime = null;

              try {
                serviceUri = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceUri");
                URI uri = URI.create(serviceUri);
              } catch (Exception ignore) {
                serviceUri = null;
              }
              try {
                name = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("name");
              } catch (Exception ignore) {
              }
              try {
                typeLabel = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("typeLabel");
              } catch (Exception ignore) {
              }
              try {
                serviceType = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceType");
              } catch (Exception ignore) {
              }
              try {
                longitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(0);
              } catch (Exception ignore) {
              }
              try {
                latitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(1);
              } catch (Exception ignore) {
              }
              try {
                distance = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("distance");
              } catch (Exception ignore) {
              }
              try {
                city = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("city");
              } catch (Exception ignore) {
              }
              try {
                cap = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("cap");
              } catch (Exception ignore) {
              }
              try {
                province = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("province");
              } catch (Exception ignore) {
              }
              try {
                address = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("address");
              } catch (Exception ignore) {
              }
              try {
                civic = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("civic");
              } catch (Exception ignore) {
              }
              try {
                phone = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("phone");
              } catch (Exception ignore) {
              }
              try {
                fax = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("fax");
              } catch (Exception ignore) {
              }
              try {
                website = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("website");
                URI uri = URI.create(website);
              } catch (Exception ignore) {
                website = null;
              }
              try {
                email = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("email");
              } catch (Exception ignore) {
              }
              try {
                note = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("note");
              } catch (Exception ignore) {
              }
              try {
                description = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("description");
              } catch (Exception ignore) {
              }
              try {
                linkDBpedia = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("linkDBpedia");
                URI uri = URI.create(linkDBpedia);
              } catch (Exception ignore) {
                linkDBpedia = null;
              }
              try {
                avgStars = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("avgStars");
              } catch (Exception ignore) {
              }
              try {
                starsCount = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getInt("starsCount");
              } catch (Exception ignore) {
              }

              try {
                LAeq = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("LAeq").getString("value");
              } catch (Exception ignore) {
              }
              try {
                LAmax = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("LAmax").getString("value");
              } catch (Exception ignore) {
              }
              try {
                dateObserved = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("dateObserved").getString("value");
              } catch (Exception ignore) {
              }
              try {
                dateObservedFrom = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("dateObservedFrom").getString("value");
              } catch (Exception ignore) {
              }
              try {
                dateObservedTo = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("dateObservedTo").getString("value");
              } catch (Exception ignore) {
              }
              try {
                location = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("location").getString("value");
              } catch (Exception ignore) {
              }
              try {
                measurand = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("measurand").getString("value");
              } catch (Exception ignore) {
              }
              try {
                noiseLAeqAvg2h = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("noiseLAeqAvg2h").getString("value");
              } catch (Exception ignore) {
              }
              try {
                sonometerClass = ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("sonometerClass").getString("value");
              } catch (Exception ignore) {
              }
              try { measuredTime = parseDate(((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("measuredTime").getString("value")); } catch(Exception ignore) {}
              try { periodStartTime = parseDate(requestContext.getParameter("fromTime")+ ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b).getJSONObject("measuredTime").getString("value").substring(19)); } catch(Exception ignore) { periodStartTime = parseDate(requestContext.getParameter("fromTime")+timezoneSuffix); }
              try {
                    if( b > 0) nextMeasuredTime = parseDate(((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(b-1).getJSONObject("measuredTime").getString("value"));
                    else nextMeasuredTime = parseDate(requestContext.getParameter("toTime")+ ((JSONArray) jsonObj.getJSONObject("realtime").getJSONObject("results").getJSONArray("bindings")).getJSONObject(l-1).getJSONObject("measuredTime").getString("value").substring(19));                
              } catch (Exception ignore) {nextMeasuredTime = parseDate(requestContext.getParameter("toTime")+timezoneSuffix); }       

              if (serviceUri == null) {
                throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceUri");
              }
              if (serviceType == null) {
                throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceType");
              }


              /*if(b == 0) {
                  response = response + "<wfs:member>"
                      + "<km4c:" + t + "Noise_level_sensor gml:id=\"" + serviceUri + "\">\n" // + "#" + ( measuredTime != null ? String.valueOf(measuredTime.getTime()) : String.valueOf(b)) + "\">\n"
                      + (serviceUri != null && (!"null".equals(serviceUri)) && !serviceUri.isEmpty() ? "   <km4c:serviceUri>" + serviceUri.replaceAll("&", "&amp;") + "</km4c:serviceUri>" : "")
                      + (name != null && (!"null".equals(name)) && !name.isEmpty() ? "   <km4c:name>" + name.replaceAll("&", "&amp;") + "</km4c:name>" : "")
                      + (typeLabel != null && (!"null".equals(typeLabel)) && !typeLabel.isEmpty() ? "   <km4c:typeLabel>" + typeLabel + "</km4c:typeLabel>" : "")
                      + (latitude != -1 && longitude != -1 ? "<km4c:geometry><gml:Point srsDimension=\"2\" srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\"><gml:pos>" + String.valueOf(longitude) + " " + String.valueOf(latitude) + "</gml:pos></gml:Point></km4c:geometry>" : "")
                      + ( city != null && (!"null".equals(city)) && !city.isEmpty() ? "   <km4c:city>" + city + "</km4c:city>" : "")
                      + ( cap != null && (!"null".equals(cap)) && !cap.isEmpty() ? "   <km4c:cap>" + cap + "</km4c:cap>" : "")
                      + ( province != null && (!"null".equals(province)) && !province.isEmpty() ? "   <km4c:province>" + province + "</km4c:province>" : "")
                      + ( address != null && (!"null".equals(address)) && !address.isEmpty() ? "   <km4c:address>" + address + "</km4c:address>" : "")
                      + ( civic != null && (!"null".equals(civic)) && !civic.isEmpty() ? "   <km4c:civic>" + civic + "</km4c:civic>" : "")
                      + ( phone != null && (!"null".equals(phone)) && !phone.isEmpty() ? "   <km4c:phone>" + phone + "</km4c:phone>" : "")
                      + ( fax != null && (!"null".equals(fax)) && !fax.isEmpty() ? "   <km4c:fax>" + fax + "</km4c:fax>" : "")
                      + ( website != null && (!"null".equals(website)) && !website.isEmpty() ? "   <km4c:website>" + website.replaceAll("&", "&amp;") + "</km4c:website>" : "")
                      + ( email != null && (!"null".equals(email)) && !email.isEmpty() ? "   <km4c:email>" + email + "</km4c:email>" : "")
                      + ( note != null && (!"null".equals(note)) && !note.isEmpty() ? "   <km4c:note>" + note + "</km4c:note>" : "")
                      + ( linkDBpedia != null && (!"null".equals(linkDBpedia)) && !linkDBpedia.isEmpty() ? "   <km4c:linkDBpedia>" + linkDBpedia.replaceAll("&", "&amp;") + "</km4c:linkDBpedia>" : "")
                      + ( avgStars > 0 ? "   <km4c:avgStars>" + String.valueOf(avgStars) + "</km4c:avgStars>" : "")
                      + ( starsCount > 0 ? "   <km4c:starsCount>" + String.valueOf(starsCount) + "</km4c:starsCount>" : "")
                      + (measuredTime != null ? "<km4c:RT_measuredTime>" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(startTime) + "</km4c:RT_measuredTime>" : "")
                          + (measuredTime != null ? "<km4c:RT_nextTime>" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(measuredTime) + "</km4c:RT_nextTime>" : "")
                           + "</km4c:" + t + "Noise_level_sensor>"
                      + "</wfs:member>";
              }*/
              
              if (l > 0) response = response + "<wfs:member>"
                      + "<km4c:Noise_level_sensor gml:id=\"" + serviceUri + "\">\n" // "#" + (measuredTime != null ? String.valueOf(measuredTime) : String.valueOf(b) ) + "\">\n"
                      + (serviceUri != null && (!"null".equals(serviceUri)) && !serviceUri.isEmpty() ? "   <km4c:serviceUri>" + serviceUri.replaceAll("&", "&amp;") + "</km4c:serviceUri>" : "")
                      + (name != null && (!"null".equals(name)) && !name.isEmpty() ? "   <km4c:name>" + name.replaceAll("&", "&amp;") + "</km4c:name>" : "")
                      + (typeLabel != null && (!"null".equals(typeLabel)) && !typeLabel.isEmpty() ? "   <km4c:typeLabel>" + typeLabel + "</km4c:typeLabel>" : "")
                      + (latitude != -1 && longitude != -1 ? "<km4c:geometry><gml:Point srsDimension=\"2\" srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\"><gml:pos>" + String.valueOf(longitude) + " " + String.valueOf(latitude) + "</gml:pos></gml:Point></km4c:geometry>" : "")
                      + (city != null && (!"null".equals(city)) && !city.isEmpty() ? "   <km4c:city>" + city + "</km4c:city>" : "")
                      + (cap != null && (!"null".equals(cap)) && !cap.isEmpty() ? "   <km4c:cap>" + cap + "</km4c:cap>" : "")
                      + (province != null && (!"null".equals(province)) && !province.isEmpty() ? "   <km4c:province>" + province + "</km4c:province>" : "")
                      + (address != null && (!"null".equals(address)) && !address.isEmpty() ? "   <km4c:address>" + address + "</km4c:address>" : "")
                      + (civic != null && (!"null".equals(civic)) && !civic.isEmpty() ? "   <km4c:civic>" + civic + "</km4c:civic>" : "")
                      + (phone != null && (!"null".equals(phone)) && !phone.isEmpty() ? "   <km4c:phone>" + phone + "</km4c:phone>" : "")
                      + (fax != null && (!"null".equals(fax)) && !fax.isEmpty() ? "   <km4c:fax>" + fax + "</km4c:fax>" : "")
                      + (website != null && (!"null".equals(website)) && !website.isEmpty() ? "   <km4c:website>" + website.replaceAll("&", "&amp;") + "</km4c:website>" : "")
                      + (email != null && (!"null".equals(email)) && !email.isEmpty() ? "   <km4c:email>" + email + "</km4c:email>" : "")
                      + (note != null && (!"null".equals(note)) && !note.isEmpty() ? "   <km4c:note>" + note + "</km4c:note>" : "")
                      + (linkDBpedia != null && (!"null".equals(linkDBpedia)) && !linkDBpedia.isEmpty() ? "   <km4c:linkDBpedia>" + linkDBpedia.replaceAll("&", "&amp;") + "</km4c:linkDBpedia>" : "")
                      + (avgStars > 0 ? "   <km4c:avgStars>" + String.valueOf(avgStars) + "</km4c:avgStars>" : "")
                      + (starsCount > 0 ? "   <km4c:starsCount>" + String.valueOf(starsCount) + "</km4c:starsCount>" : "")
                      + (measuredTime != null ? "<km4c:RT_measuredTime>" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(measuredTime) + "</km4c:RT_measuredTime>" : "")
                      + (nextMeasuredTime != null ? "<km4c:RT_nextTime>" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(nextMeasuredTime) + "</km4c:RT_nextTime>" : "")
                      + (LAeq != null && (!"null".equals(LAeq)) && !LAeq.isEmpty() ? "   <km4c:RT_LAeq>" + LAeq.replaceAll("&", "&amp;") + "</km4c:RT_LAeq>" : "")
                      + (LAmax != null && (!"null".equals(LAmax)) && !LAmax.isEmpty() ? "   <km4c:RT_LAmax>" + LAmax.replaceAll("&", "&amp;") + "</km4c:RT_LAmax>" : "")
                      + (dateObserved != null && (!"null".equals(dateObserved)) && !dateObserved.isEmpty() ? "   <km4c:RT_dateObserved>" + dateObserved.replaceAll("&", "&amp;") + "</km4c:RT_dateObserved>" : "")
                      + (dateObservedFrom != null && (!"null".equals(dateObservedFrom)) && !dateObservedFrom.isEmpty() ? "   <km4c:RT_dateObservedFrom>" + dateObservedFrom.replaceAll("&", "&amp;") + "</km4c:RT_dateObservedFrom>" : "")
                      + (dateObservedTo != null && (!"null".equals(dateObservedTo)) && !dateObservedTo.isEmpty() ? "   <km4c:RT_dateObservedTo>" + dateObservedTo.replaceAll("&", "&amp;") + "</km4c:RT_dateObservedTo>" : "")
                      + (location != null && (!"null".equals(location)) && !location.isEmpty() ? "   <km4c:RT_location>" + location.replaceAll("&", "&amp;") + "</km4c:RT_location>" : "")
                      + (measurand != null && (!"null".equals(measurand)) && !measurand.isEmpty() ? "   <km4c:RT_measurand>" + measurand.replaceAll("&", "&amp;") + "</km4c:RT_measurand>" : "")
                      + (noiseLAeqAvg2h != null && (!"null".equals(noiseLAeqAvg2h)) && !noiseLAeqAvg2h.isEmpty() ? "   <km4c:RT_noiseLAeqAvg2h>" + noiseLAeqAvg2h.replaceAll("&", "&amp;") + "</km4c:RT_noiseLAeqAvg2h>" : "")
                      + (sonometerClass != null && (!"null".equals(sonometerClass)) && !sonometerClass.isEmpty() ? "   <km4c:RT_sonometerClass>" + sonometerClass.replaceAll("&", "&amp;") + "</km4c:RT_sonometerClass>" : "")
                      + "</km4c:Noise_level_sensor>"
                      + "</wfs:member>";
              
                            if( ( l == 0 || periodStartTime != null ) && ( b == l-1 || l == 0) ) {
                                response = response + initFeat("Noise_level_sensor", serviceUri, name, typeLabel, latitude, longitude, city, cap, province, address,           civic, phone, fax, website, email, note, linkDBpedia, avgStars, starsCount, periodStartTime, measuredTime, timezoneSuffix, requestContext);
                            }  
                            
              }
              
              response = response + "</wfs:FeatureCollection>";

            if ("hits".equals(pResultType)) {
              response = "<?xml version=\"1.0\" ?>\n"
                      + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                      + "</wfs:member></wfs:FeatureCollection>";
            }

            return Response.ok(response).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/gml+xml;version=3.2").status(r.getStatus()).header("Cache-Control", "no-cache").build();

            } else if (isPeople_counter) {
              String serviceUri; // uri
              String name = null;
              double latitude = -1;
              double longitude = -1;
              double distance = -1;
              String city = null;
              String cap = null;
              String province = null;
              String address = null;
              String civic = null;
              String phone = null;
              String fax = null;
              String website; // uri
              String email = null;
              String note = null;
              String description = null;
              String linkDBpedia; // uri
              double avgStars = -1;
              int starsCount = -1;
              String typeLabel = null;
              String serviceType = null;
              try {
                serviceUri = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceUri");
                URI uri = URI.create(serviceUri);
              } catch (Exception ignore) {
                serviceUri = null;
              }
              try {
                name = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("name");
              } catch (Exception ignore) {
              }
              try {
                typeLabel = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("typeLabel");
              } catch (Exception ignore) {
              }
              try {
                serviceType = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceType");
              } catch (Exception ignore) {
              }
              try {
                longitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(0);
              } catch (Exception ignore) {
              }
              try {
                latitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(1);
              } catch (Exception ignore) {
              }
              try {
                distance = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("distance");
              } catch (Exception ignore) {
              }
              try {
                city = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("city");
              } catch (Exception ignore) {
              }
              try {
                cap = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("cap");
              } catch (Exception ignore) {
              }
              try {
                province = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("province");
              } catch (Exception ignore) {
              }
              try {
                address = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("address");
              } catch (Exception ignore) {
              }
              try {
                civic = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("civic");
              } catch (Exception ignore) {
              }
              try {
                phone = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("phone");
              } catch (Exception ignore) {
              }
              try {
                fax = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("fax");
              } catch (Exception ignore) {
              }
              try {
                website = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("website");
                URI uri = URI.create(website);
              } catch (Exception ignore) {
                website = null;
              }
              try {
                email = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("email");
              } catch (Exception ignore) {
              }
              try {
                note = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("note");
              } catch (Exception ignore) {
              }
              try {
                description = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("description");
              } catch (Exception ignore) {
              }
              try {
                linkDBpedia = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("linkDBpedia");
                URI uri = URI.create(linkDBpedia);
              } catch (Exception ignore) {
                linkDBpedia = null;
              }
              try {
                avgStars = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("avgStars");
              } catch (Exception ignore) {
              }
              try {
                starsCount = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getInt("starsCount");
              } catch (Exception ignore) {
              }
              if (serviceUri == null) {
                throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceUri");
              }
              if (serviceType == null) {
                throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceType");
              }

/*
              String response = "<?xml version=\"1.0\" ?>\n"
                      + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd " + serverUrl + "?service=WFS&amp;version=" + serverVersion + "&amp;request=DescribeFeatureType&amp;typeName=km4c%3Service http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                      + "<km4c:People_counter gml:id=\"" + serviceUri + "\">\n"
                      + (serviceUri != null && (!"null".equals(serviceUri)) && !serviceUri.isEmpty() ? "   <km4c:serviceUri>" + serviceUri.replaceAll("&", "&amp;") + "</km4c:serviceUri>" : "")
                      + (name != null && (!"null".equals(name)) && !name.isEmpty() ? "   <km4c:name>" + name.replaceAll("&", "&amp;") + "</km4c:name>" : "")
                      + (typeLabel != null && (!"null".equals(typeLabel)) && !typeLabel.isEmpty() ? "   <km4c:typeLabel>" + typeLabel + "</km4c:typeLabel>" : "")
                      + (latitude != -1 && longitude != -1 ? "<km4c:geometry><gml:Point srsDimension=\"2\" srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\"><gml:pos>" + String.valueOf(longitude) + " " + String.valueOf(latitude) + "</gml:pos></gml:Point></km4c:geometry>" : "")
                      + (city != null && (!"null".equals(city)) && !city.isEmpty() ? "   <km4c:city>" + city + "</km4c:city>" : "")
                      + (cap != null && (!"null".equals(cap)) && !cap.isEmpty() ? "   <km4c:cap>" + cap + "</km4c:cap>" : "")
                      + (province != null && (!"null".equals(province)) && !province.isEmpty() ? "   <km4c:province>" + province + "</km4c:province>" : "")
                      + (address != null && (!"null".equals(address)) && !address.isEmpty() ? "   <km4c:address>" + address + "</km4c:address>" : "")
                      + (civic != null && (!"null".equals(civic)) && !civic.isEmpty() ? "   <km4c:civic>" + civic + "</km4c:civic>" : "")
                      + (phone != null && (!"null".equals(phone)) && !phone.isEmpty() ? "   <km4c:phone>" + phone + "</km4c:phone>" : "")
                      + (fax != null && (!"null".equals(fax)) && !fax.isEmpty() ? "   <km4c:fax>" + fax + "</km4c:fax>" : "")
                      + (website != null && (!"null".equals(website)) && !website.isEmpty() ? "   <km4c:website>" + website.replaceAll("&", "&amp;") + "</km4c:website>" : "")
                      + (email != null && (!"null".equals(email)) && !email.isEmpty() ? "   <km4c:email>" + email + "</km4c:email>" : "")
                      + (note != null && (!"null".equals(note)) && !note.isEmpty() ? "   <km4c:note>" + note + "</km4c:note>" : "")
                      + (linkDBpedia != null && (!"null".equals(linkDBpedia)) && !linkDBpedia.isEmpty() ? "   <km4c:linkDBpedia>" + linkDBpedia.replaceAll("&", "&amp;") + "</km4c:linkDBpedia>" : "")
                      + (avgStars > 0 ? "   <km4c:avgStars>" + String.valueOf(avgStars) + "</km4c:avgStars>" : "")
                      + (starsCount > 0 ? "   <km4c:starsCount>" + String.valueOf(starsCount) + "</km4c:starsCount>" : "")
                      + "</km4c:People_counter>"
                      + "</wfs:member></wfs:FeatureCollection>";*/
              String response = bldResp( "People_counter",  nowAsISO,  serverUrl,  serverVersion,  serviceType,  serviceUri, name,  typeLabel,  latitude, 
           longitude,  city,  cap,  province,  address,  civic,  phone,  fax,  website,  email,  note,
           linkDBpedia,  avgStars,  starsCount );

              if ("hits".equals(pResultType)) {
                response = "<?xml version=\"1.0\" ?>\n"
                        + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                        + "</wfs:member></wfs:FeatureCollection>";
              }

              return Response.ok(response).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/gml+xml;version=3.2").status(r.getStatus()).header("Cache-Control", "no-cache").build();

            } else if (isSensorSite) {
              String serviceUri; // uri
              String name = null;
              double latitude = -1;
              double longitude = -1;
              double distance = -1;
              String city = null;
              String cap = null;
              String province = null;
              String address = null;
              String civic = null;
              String phone = null;
              String fax = null;
              String website; // uri
              String email = null;
              String note = null;
              String description = null;
              String linkDBpedia; // uri
              double avgStars = -1;
              int starsCount = -1;
              String typeLabel = null;
              String serviceType = null;
              try {
                serviceUri = ((JSONArray) jsonObj.getJSONObject("Sensor").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceUri");
                URI uri = URI.create(serviceUri);
              } catch (Exception ignore) {
                serviceUri = null;
              }
              try {
                name = ((JSONArray) jsonObj.getJSONObject("Sensor").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("name");
              } catch (Exception ignore) {
              }
              try {
                typeLabel = ((JSONArray) jsonObj.getJSONObject("Sensor").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("typeLabel");
              } catch (Exception ignore) {
              }
              try {
                serviceType = ((JSONArray) jsonObj.getJSONObject("Sensor").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceType");
              } catch (Exception ignore) {
              }
              try {
                longitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Sensor").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(0);
              } catch (Exception ignore) {
              }
              try {
                latitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Sensor").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(1);
              } catch (Exception ignore) {
              }
              try {
                distance = ((JSONArray) jsonObj.getJSONObject("Sensor").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("distance");
              } catch (Exception ignore) {
              }
              try {
                city = ((JSONArray) jsonObj.getJSONObject("Sensor").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("city");
              } catch (Exception ignore) {
              }
              try {
                cap = ((JSONArray) jsonObj.getJSONObject("Sensor").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("cap");
              } catch (Exception ignore) {
              }
              try {
                province = ((JSONArray) jsonObj.getJSONObject("Sensor").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("province");
              } catch (Exception ignore) {
              }
              try {
                address = ((JSONArray) jsonObj.getJSONObject("Sensor").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("address");
              } catch (Exception ignore) {
              }
              try {
                civic = ((JSONArray) jsonObj.getJSONObject("Sensor").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("civic");
              } catch (Exception ignore) {
              }
              try {
                phone = ((JSONArray) jsonObj.getJSONObject("Sensor").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("phone");
              } catch (Exception ignore) {
              }
              try {
                fax = ((JSONArray) jsonObj.getJSONObject("Sensor").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("fax");
              } catch (Exception ignore) {
              }
              try {
                website = ((JSONArray) jsonObj.getJSONObject("Sensor").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("website");
                URI uri = URI.create(website);
              } catch (Exception ignore) {
                website = null;
              }
              try {
                email = ((JSONArray) jsonObj.getJSONObject("Sensor").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("email");
              } catch (Exception ignore) {
              }
              try {
                note = ((JSONArray) jsonObj.getJSONObject("Sensor").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("note");
              } catch (Exception ignore) {
              }
              try {
                description = ((JSONArray) jsonObj.getJSONObject("Sensor").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("description");
              } catch (Exception ignore) {
              }
              try {
                linkDBpedia = ((JSONArray) jsonObj.getJSONObject("Sensor").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("linkDBpedia");
                URI uri = URI.create(linkDBpedia);
              } catch (Exception ignore) {
                linkDBpedia = null;
              }
              try {
                avgStars = ((JSONArray) jsonObj.getJSONObject("Sensor").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("avgStars");
              } catch (Exception ignore) {
              }
              try {
                starsCount = ((JSONArray) jsonObj.getJSONObject("Sensor").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getInt("starsCount");
              } catch (Exception ignore) {
              }
              if (serviceUri == null) {
                throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceUri");
              }
              if (serviceType == null) {
                throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceType");
              }

              /*String response = "<?xml version=\"1.0\" ?>\n"
                      + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd " + serverUrl + "?service=WFS&amp;version=" + serverVersion + "&amp;request=DescribeFeatureType&amp;typeName=km4c%3Service http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                      + "<km4c:SensorSite gml:id=\"" + serviceUri + "\">\n"
                      + (serviceUri != null && (!"null".equals(serviceUri)) && !serviceUri.isEmpty() ? "   <km4c:serviceUri>" + serviceUri.replaceAll("&", "&amp;") + "</km4c:serviceUri>" : "")
                      + (name != null && (!"null".equals(name)) && !name.isEmpty() ? "   <km4c:name>" + name.replaceAll("&", "&amp;") + "</km4c:name>" : "")
                      + (typeLabel != null && (!"null".equals(typeLabel)) && !typeLabel.isEmpty() ? "   <km4c:typeLabel>" + typeLabel + "</km4c:typeLabel>" : "")
                      + (latitude != -1 && longitude != -1 ? "<km4c:geometry><gml:Point srsDimension=\"2\" srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\"><gml:pos>" + String.valueOf(longitude) + " " + String.valueOf(latitude) + "</gml:pos></gml:Point></km4c:geometry>" : "")
                      + (city != null && (!"null".equals(city)) && !city.isEmpty() ? "   <km4c:city>" + city + "</km4c:city>" : "")
                      + (cap != null && (!"null".equals(cap)) && !cap.isEmpty() ? "   <km4c:cap>" + cap + "</km4c:cap>" : "")
                      + (province != null && (!"null".equals(province)) && !province.isEmpty() ? "   <km4c:province>" + province + "</km4c:province>" : "")
                      + (address != null && (!"null".equals(address)) && !address.isEmpty() ? "   <km4c:address>" + address + "</km4c:address>" : "")
                      + (civic != null && (!"null".equals(civic)) && !civic.isEmpty() ? "   <km4c:civic>" + civic + "</km4c:civic>" : "")
                      + (phone != null && (!"null".equals(phone)) && !phone.isEmpty() ? "   <km4c:phone>" + phone + "</km4c:phone>" : "")
                      + (fax != null && (!"null".equals(fax)) && !fax.isEmpty() ? "   <km4c:fax>" + fax + "</km4c:fax>" : "")
                      + (website != null && (!"null".equals(website)) && !website.isEmpty() ? "   <km4c:website>" + website.replaceAll("&", "&amp;") + "</km4c:website>" : "")
                      + (email != null && (!"null".equals(email)) && !email.isEmpty() ? "   <km4c:email>" + email + "</km4c:email>" : "")
                      + (note != null && (!"null".equals(note)) && !note.isEmpty() ? "   <km4c:note>" + note + "</km4c:note>" : "")
                      + (linkDBpedia != null && (!"null".equals(linkDBpedia)) && !linkDBpedia.isEmpty() ? "   <km4c:linkDBpedia>" + linkDBpedia.replaceAll("&", "&amp;") + "</km4c:linkDBpedia>" : "")
                      + (avgStars > 0 ? "   <km4c:avgStars>" + String.valueOf(avgStars) + "</km4c:avgStars>" : "")
                      + (starsCount > 0 ? "   <km4c:starsCount>" + String.valueOf(starsCount) + "</km4c:starsCount>" : "")
                      + "</km4c:SensorSite>"
                      + "</wfs:member></wfs:FeatureCollection>";*/
                            String response = bldResp( "SensorSite",  nowAsISO,  serverUrl,  serverVersion,  serviceType,  serviceUri, name,  typeLabel,  latitude, 
           longitude,  city,  cap,  province,  address,  civic,  phone,  fax,  website,  email,  note,
           linkDBpedia,  avgStars,  starsCount );

              if ("hits".equals(pResultType)) {
                response = "<?xml version=\"1.0\" ?>\n"
                        + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                        + "</wfs:member></wfs:FeatureCollection>";
              }

              return Response.ok(response).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/gml+xml;version=3.2").status(r.getStatus()).header("Cache-Control", "no-cache").build();

            } else if (isUnderpass) {
              String serviceUri; // uri
              String name = null;
              double latitude = -1;
              double longitude = -1;
              double distance = -1;
              String city = null;
              String cap = null;
              String province = null;
              String address = null;
              String civic = null;
              String phone = null;
              String fax = null;
              String website; // uri
              String email = null;
              String note = null;
              String description = null;
              String linkDBpedia; // uri
              double avgStars = -1;
              int starsCount = -1;
              String typeLabel = null;
              String serviceType = null;
              try {
                serviceUri = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceUri");
                URI uri = URI.create(serviceUri);
              } catch (Exception ignore) {
                serviceUri = null;
              }
              try {
                name = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("name");
              } catch (Exception ignore) {
              }
              try {
                typeLabel = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("typeLabel");
              } catch (Exception ignore) {
              }
              try {
                serviceType = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("serviceType");
              } catch (Exception ignore) {
              }
              try {
                longitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(0);
              } catch (Exception ignore) {
              }
              try {
                latitude = ((JSONArray) ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates")).getDouble(1);
              } catch (Exception ignore) {
              }
              try {
                distance = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("distance");
              } catch (Exception ignore) {
              }
              try {
                city = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("city");
              } catch (Exception ignore) {
              }
              try {
                cap = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("cap");
              } catch (Exception ignore) {
              }
              try {
                province = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("province");
              } catch (Exception ignore) {
              }
              try {
                address = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("address");
              } catch (Exception ignore) {
              }
              try {
                civic = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("civic");
              } catch (Exception ignore) {
              }
              try {
                phone = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("phone");
              } catch (Exception ignore) {
              }
              try {
                fax = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("fax");
              } catch (Exception ignore) {
              }
              try {
                website = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("website");
                URI uri = URI.create(website);
              } catch (Exception ignore) {
                website = null;
              }
              try {
                email = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("email");
              } catch (Exception ignore) {
              }
              try {
                note = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("note");
              } catch (Exception ignore) {
              }
              try {
                description = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("description");
              } catch (Exception ignore) {
              }
              try {
                linkDBpedia = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getString("linkDBpedia");
                URI uri = URI.create(linkDBpedia);
              } catch (Exception ignore) {
                linkDBpedia = null;
              }
              try {
                avgStars = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getDouble("avgStars");
              } catch (Exception ignore) {
              }
              try {
                starsCount = ((JSONArray) jsonObj.getJSONObject("Service").getJSONArray("features")).getJSONObject(0).getJSONObject("properties").getInt("starsCount");
              } catch (Exception ignore) {
              }
              if (serviceUri == null) {
                throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceUri");
              }
              if (serviceType == null) {
                throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceType");
              }

              /*
              String response = "<?xml version=\"1.0\" ?>\n"
                      + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd " + serverUrl + "?service=WFS&amp;version=" + serverVersion + "&amp;request=DescribeFeatureType&amp;typeName=km4c%3Service http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                      + "<km4c:Underpass gml:id=\"" + serviceUri + "\">\n"
                      + (serviceUri != null && (!"null".equals(serviceUri)) && !serviceUri.isEmpty() ? "   <km4c:serviceUri>" + serviceUri.replaceAll("&", "&amp;") + "</km4c:serviceUri>" : "")
                      + (name != null && (!"null".equals(name)) && !name.isEmpty() ? "   <km4c:name>" + name.replaceAll("&", "&amp;") + "</km4c:name>" : "")
                      + (typeLabel != null && (!"null".equals(typeLabel)) && !typeLabel.isEmpty() ? "   <km4c:typeLabel>" + typeLabel + "</km4c:typeLabel>" : "")
                      + (latitude != -1 && longitude != -1 ? "<km4c:geometry><gml:Point srsDimension=\"2\" srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\"><gml:pos>" + String.valueOf(longitude) + " " + String.valueOf(latitude) + "</gml:pos></gml:Point></km4c:geometry>" : "")
                      + (city != null && (!"null".equals(city)) && !city.isEmpty() ? "   <km4c:city>" + city + "</km4c:city>" : "")
                      + (cap != null && (!"null".equals(cap)) && !cap.isEmpty() ? "   <km4c:cap>" + cap + "</km4c:cap>" : "")
                      + (province != null && (!"null".equals(province)) && !province.isEmpty() ? "   <km4c:province>" + province + "</km4c:province>" : "")
                      + (address != null && (!"null".equals(address)) && !address.isEmpty() ? "   <km4c:address>" + address + "</km4c:address>" : "")
                      + (civic != null && (!"null".equals(civic)) && !civic.isEmpty() ? "   <km4c:civic>" + civic + "</km4c:civic>" : "")
                      + (phone != null && (!"null".equals(phone)) && !phone.isEmpty() ? "   <km4c:phone>" + phone + "</km4c:phone>" : "")
                      + (fax != null && (!"null".equals(fax)) && !fax.isEmpty() ? "   <km4c:fax>" + fax + "</km4c:fax>" : "")
                      + (website != null && (!"null".equals(website)) && !website.isEmpty() ? "   <km4c:website>" + website.replaceAll("&", "&amp;") + "</km4c:website>" : "")
                      + (email != null && (!"null".equals(email)) && !email.isEmpty() ? "   <km4c:email>" + email + "</km4c:email>" : "")
                      + (note != null && (!"null".equals(note)) && !note.isEmpty() ? "   <km4c:note>" + note + "</km4c:note>" : "")
                      + (linkDBpedia != null && (!"null".equals(linkDBpedia)) && !linkDBpedia.isEmpty() ? "   <km4c:linkDBpedia>" + linkDBpedia.replaceAll("&", "&amp;") + "</km4c:linkDBpedia>" : "")
                      + (avgStars > 0 ? "   <km4c:avgStars>" + String.valueOf(avgStars) + "</km4c:avgStars>" : "")
                      + (starsCount > 0 ? "   <km4c:starsCount>" + String.valueOf(starsCount) + "</km4c:starsCount>" : "")
                      + "</km4c:Underpass>"
                      + "</wfs:member></wfs:FeatureCollection>";*/
                            String response = bldResp( "Underpass",  nowAsISO,  serverUrl,  serverVersion,  serviceType,  serviceUri, name,  typeLabel,  latitude, 
           longitude,  city,  cap,  province,  address,  civic,  phone,  fax,  website,  email,  note,
           linkDBpedia,  avgStars,  starsCount );

              if ("hits".equals(pResultType)) {
                response = "<?xml version=\"1.0\" ?>\n"
                        + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                        + "</wfs:member></wfs:FeatureCollection>";
              }

              return Response.ok(response).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/gml+xml;version=3.2").status(r.getStatus()).header("Cache-Control", "no-cache").build();

            } else {
              throw new WfsException(WfsException.NOT_FOUND, URLEncoder.encode(pID, "UTF-8"));
            }
          } 
    
    public static boolean isRequested(String what, String list) {
        return list == null || Arrays.asList(list.split(",")).contains(what);
      }

    public static String bldResp( String className, String nowAsISO, String serverUrl, String serverVersion, String serviceType, String serviceUri, String name, String typeLabel, double latitude, 
            double longitude, String city, String cap, String province, String address, String civic, String phone, String fax, String website, String email, String note,
            String linkDBpedia, double avgStars, int starsCount ) {
        return "<?xml version=\"1.0\" ?>\n"
                        + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"1\" numberReturned=\"1\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd " + serverUrl + "?service=WFS&amp;version=" + serverVersion + "&amp;request=DescribeFeatureType&amp;typeName=km4c%3Service http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\"><wfs:member>"
                        + "<km4c:" + className + " gml:id=\"" + serviceUri + "\">\n"
                        + (serviceUri != null && (!"null".equals(serviceUri)) && !serviceUri.isEmpty() ? "   <km4c:serviceUri>" + serviceUri.replaceAll("&", "&amp;") + "</km4c:serviceUri>" : "")
                        + (name != null && (!"null".equals(name)) && !name.isEmpty() ? "   <km4c:name>" + name.replaceAll("&", "&amp;") + "</km4c:name>" : "")
                        + (typeLabel != null && (!"null".equals(typeLabel)) && !typeLabel.isEmpty() ? "   <km4c:typeLabel>" + typeLabel + "</km4c:typeLabel>" : "")
                        + (latitude != -1 && longitude != -1 ? "<km4c:geometry><gml:Point srsDimension=\"2\" srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\"><gml:pos>" + String.valueOf(longitude) + " " + String.valueOf(latitude) + "</gml:pos></gml:Point></km4c:geometry>" : "")
                        + (city != null && (!"null".equals(city)) && !city.isEmpty() ? "   <km4c:city>" + city + "</km4c:city>" : "")
                        + (cap != null && (!"null".equals(cap)) && !cap.isEmpty() ? "   <km4c:cap>" + cap + "</km4c:cap>" : "")
                        + (province != null && (!"null".equals(province)) && !province.isEmpty() ? "   <km4c:province>" + province + "</km4c:province>" : "")
                        + (address != null && (!"null".equals(address)) && !address.isEmpty() ? "   <km4c:address>" + address + "</km4c:address>" : "")
                        + (civic != null && (!"null".equals(civic)) && !civic.isEmpty() ? "   <km4c:civic>" + civic + "</km4c:civic>" : "")
                        + (phone != null && (!"null".equals(phone)) && !phone.isEmpty() ? "   <km4c:phone>" + phone + "</km4c:phone>" : "")
                        + (fax != null && (!"null".equals(fax)) && !fax.isEmpty() ? "   <km4c:fax>" + fax + "</km4c:fax>" : "")
                        + (website != null && (!"null".equals(website)) && !website.isEmpty() ? "   <km4c:website>" + website.replaceAll("&", "&amp;") + "</km4c:website>" : "")
                        + (email != null && (!"null".equals(email)) && !email.isEmpty() ? "   <km4c:email>" + email + "</km4c:email>" : "")
                        + (note != null && (!"null".equals(note)) && !note.isEmpty() ? "   <km4c:note>" + note + "</km4c:note>" : "")
                        + (linkDBpedia != null && (!"null".equals(linkDBpedia)) && !linkDBpedia.isEmpty() ? "   <km4c:linkDBpedia>" + linkDBpedia.replaceAll("&", "&amp;") + "</km4c:linkDBpedia>" : "")
                        + (avgStars > 0 ? "   <km4c:avgStars>" + String.valueOf(avgStars) + "</km4c:avgStars>" : "")
                        + (starsCount > 0 ? "   <km4c:starsCount>" + String.valueOf(starsCount) + "</km4c:starsCount>" : "")
                        + "</km4c:" + className + ">"
                        + "</wfs:member></wfs:FeatureCollection>";
    }       
    
    public static String initFeat(String className, String serviceUri, String name, String typeLabel, double latitude, double longitude, String city, String cap, String province, String address, 
          String civic, String phone, String fax, String website, String email, String note, String linkDBpedia, double avgStars, int starsCount, java.util.Date periodStartTime, java.util.Date measuredTime, String timezoneSuffix, HttpServletRequest requestContext) {
        return "<wfs:member>"
            + "<km4c:" + className + " gml:id=\"" + serviceUri + "\">\n" // + "#" + ( measuredTime != null ? String.valueOf(measuredTime.getTime()) : String.valueOf(b)) + "\">\n"
            + (serviceUri != null && (!"null".equals(serviceUri)) && !serviceUri.isEmpty() ? "   <km4c:serviceUri>" + serviceUri.replaceAll("&", "&amp;") + "</km4c:serviceUri>" : "")
            + (name != null && (!"null".equals(name)) && !name.isEmpty() ? "   <km4c:name>" + name.replaceAll("&", "&amp;") + "</km4c:name>" : "")
            + (typeLabel != null && (!"null".equals(typeLabel)) && !typeLabel.isEmpty() ? "   <km4c:typeLabel>" + typeLabel + "</km4c:typeLabel>" : "")
            + (latitude != -1 && longitude != -1 ? "<km4c:geometry><gml:Point srsDimension=\"2\" srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\"><gml:pos>" + String.valueOf(longitude) + " " + String.valueOf(latitude) + "</gml:pos></gml:Point></km4c:geometry>" : "")
            + ( city != null && (!"null".equals(city)) && !city.isEmpty() ? "   <km4c:city>" + city + "</km4c:city>" : "")
            + ( cap != null && (!"null".equals(cap)) && !cap.isEmpty() ? "   <km4c:cap>" + cap + "</km4c:cap>" : "")
            + ( province != null && (!"null".equals(province)) && !province.isEmpty() ? "   <km4c:province>" + province + "</km4c:province>" : "")
            + ( address != null && (!"null".equals(address)) && !address.isEmpty() ? "   <km4c:address>" + address + "</km4c:address>" : "")
            + ( civic != null && (!"null".equals(civic)) && !civic.isEmpty() ? "   <km4c:civic>" + civic + "</km4c:civic>" : "")
            + ( phone != null && (!"null".equals(phone)) && !phone.isEmpty() ? "   <km4c:phone>" + phone + "</km4c:phone>" : "")
            + ( fax != null && (!"null".equals(fax)) && !fax.isEmpty() ? "   <km4c:fax>" + fax + "</km4c:fax>" : "")
            + ( website != null && (!"null".equals(website)) && !website.isEmpty() ? "   <km4c:website>" + website.replaceAll("&", "&amp;") + "</km4c:website>" : "")
            + ( email != null && (!"null".equals(email)) && !email.isEmpty() ? "   <km4c:email>" + email + "</km4c:email>" : "")
            + ( note != null && (!"null".equals(note)) && !note.isEmpty() ? "   <km4c:note>" + note + "</km4c:note>" : "")
            + ( linkDBpedia != null && (!"null".equals(linkDBpedia)) && !linkDBpedia.isEmpty() ? "   <km4c:linkDBpedia>" + linkDBpedia.replaceAll("&", "&amp;") + "</km4c:linkDBpedia>" : "")
            + ( avgStars > 0 ? "   <km4c:avgStars>" + String.valueOf(avgStars) + "</km4c:avgStars>" : "")
            + ( starsCount > 0 ? "   <km4c:starsCount>" + String.valueOf(starsCount) + "</km4c:starsCount>" : "")
            + (periodStartTime != null ? "<km4c:RT_measuredTime>" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(periodStartTime) + "</km4c:RT_measuredTime>" : "")
                + (measuredTime != null ? "<km4c:RT_nextTime>" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(measuredTime) + "</km4c:RT_nextTime>" : ( requestContext.getParameter("toTime") != null ?  "<km4c:RT_nextTime>" + new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(parseDate(requestContext.getParameter("toTime")+timezoneSuffix)) + "</km4c:RT_nextTime>" : "") )              
                 + "</km4c:" + className + ">"
            + "</wfs:member>";
  }
}
