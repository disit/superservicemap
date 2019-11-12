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
package resource;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.PrecisionModel;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.security.Principal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import servicemap.GeometryAdapter;
import servicemap.MySQLManager;
import servicemap.ServiceMap;
import servicemap.WfsException;
import servicemap.WfsServer;

@JsonSerialize
@Path("/api/v1")
public class ApiV1Resource {

  @Context
  private HttpServletRequest requestContext;

  // Manage feature (bus stops, sensors, and other services) searches, and requests for full details about a specific feature
  @SuppressWarnings("deprecation")
  @GET
  public Response getServices(
          @QueryParam("selection") String selection,
          @QueryParam("queryId") String queryId,
          @QueryParam("search") String search,
          @QueryParam("categories") String categories,
          @QueryParam("text") String text,
          @QueryParam("maxDists") String maxDists,
          @QueryParam("maxResults") String maxResults,
          @QueryParam("lang") String lang,
          @QueryParam("geometry") String geometry,
          @QueryParam("uid") String uid,
          @QueryParam("format") String format,
          @QueryParam("map") String map,
          @QueryParam("controls") String controls,
          @QueryParam("info") String info,
          @QueryParam("serviceUri") String serviceUri,
          @QueryParam("realtime") String realtime,
          @QueryParam("requestFrom") String requestFrom,
          @QueryParam("valueName") String valueName,
          @QueryParam("fromTime") String fromTime,
          @QueryParam("toTime") String toTime,
          @QueryParam("value_type") String valueType,
          @QueryParam("healthiness") String healthiness,
          @QueryParam("graphUri") String graphUri,
          @QueryParam("fullCount") String fullCount,
          @QueryParam("accessToken") String accessToken,
          @QueryParam("apikey") String apikey
  ) throws Exception {

    String authorization = requestContext.getHeader("Authorization");
    if (authorization == null && accessToken != null && !accessToken.isEmpty()) {
      authorization = "bearer " + accessToken;
    }

    String ipAddressRequestCameFrom = requestContext.getRemoteAddr();

    if (serviceUri == null) { // If it is a service search, and not a request for details about a specific service, do the following

                // Identify service maps that have to be queried based on the given position, sorted by priority4html, so that if HTML format is requested,
      // getting the first element of the list ignoring all the others is the right choice. JSON output is not affected since how elements are
      // sorted in the list does not affect any way the JSON output.
      List<String> competentServiceMapsPrefix = getCompetentServiceMaps(selection, "/api/v1", "html".equals(format) ? "html" : "json");

                // If just one service map must be queried, simply forward the query, return its result, and you are done
      if ("html".equals(format) || competentServiceMapsPrefix.size() == 1) {
        try {
          final String SMQUERY = competentServiceMapsPrefix.get(0) + "/api/v1/?ssm=yes"
                  + (selection == null || selection.isEmpty() ? "" : "&selection=" + URLEncoder.encode(selection, "UTF-8"))
                  + (queryId == null || queryId.isEmpty() ? "" : "&queryId=" + URLEncoder.encode(queryId, "UTF-8"))
                  + (search == null || search.isEmpty() ? "" : "&search=" + URLEncoder.encode(search, "UTF-8"))
                  + (categories == null || categories.isEmpty() ? "" : "&categories=" + URLEncoder.encode(categories, "UTF-8"))
                  + (text == null || text.isEmpty() ? "" : "&text=" + URLEncoder.encode(text, "UTF-8"))
                  + (maxDists == null || maxDists.isEmpty() ? "" : "&maxDists=" + URLEncoder.encode(maxDists, "UTF-8"))
                  + (maxResults == null || maxResults.isEmpty() ? "" : "&maxResults=" + URLEncoder.encode(maxResults, "UTF-8"))
                  + (lang == null || lang.isEmpty() ? "" : "&lang=" + URLEncoder.encode(lang, "UTF-8"))
                  + (geometry == null || geometry.isEmpty() ? "" : "&geometry=" + URLEncoder.encode(geometry, "UTF-8"))
                  + (uid == null || uid.isEmpty() ? "" : "&uid=" + URLEncoder.encode(uid, "UTF-8"))
                  + (format == null || format.isEmpty() ? "" : "&format=" + URLEncoder.encode(format, "UTF-8"))
                  + (map == null || map.isEmpty() ? "" : "&maps=" + URLEncoder.encode(map, "UTF-8"))
                  + (controls == null || controls.isEmpty() ? "" : "&controls=" + URLEncoder.encode(controls, "UTF-8"))
                  + (info == null || info.isEmpty() ? "" : "&info=" + URLEncoder.encode(info, "UTF-8"))
                  + (realtime == null || realtime.isEmpty() ? "" : "&realtime=" + URLEncoder.encode(realtime, "UTF-8"))
                  + (requestFrom == null || requestFrom.isEmpty() ? "" : "&requestFrom=" + URLEncoder.encode(requestFrom, "UTF-8"))
                  + (valueName == null || valueName.isEmpty() ? "" : "&valueName=" + URLEncoder.encode(valueName, "UTF-8"))
                  + (fromTime == null || fromTime.isEmpty() ? "" : "&fromTime=" + URLEncoder.encode(fromTime, "UTF-8"))
                  + (toTime == null || toTime.isEmpty() ? "" : "&toTime=" + URLEncoder.encode(toTime, "UTF-8"))
                  + (valueType == null || valueType.isEmpty() ? "" : "&value_type=" + URLEncoder.encode(valueType, "UTF-8"))
                  + (healthiness == null || healthiness.isEmpty() ? "" : "&healthiness=" + URLEncoder.encode(healthiness, "UTF-8"))
                  + (graphUri == null || graphUri.isEmpty() ? "" : "&graphUri=" + URLEncoder.encode(graphUri, "UTF-8"))
                  + (fullCount == null || fullCount.isEmpty() ? "" : "&fullCount=" + URLEncoder.encode(fullCount, "UTF-8"))
                  + (apikey == null || apikey.isEmpty() ? "" : "&apikey=" + URLEncoder.encode(apikey, "UTF-8"));
          ClientConfig config = new ClientConfig();
          Client client = ClientBuilder.newClient(config);
          WebTarget targetServiceMap = client.target(UriBuilder.fromUri(SMQUERY).build());
          String httpRequestForwardedFor = "";
          if (requestContext.getHeader("X-Forwarded-For") != null && !requestContext.getHeader("X-Forwarded-For").isEmpty()) {
            httpRequestForwardedFor += requestContext.getHeader("X-Forwarded-For") + ",";
          }
          httpRequestForwardedFor += ipAddressRequestCameFrom;
          Response r = null;
          if (requestContext.getHeader("Referer") == null) {
            if (authorization == null) {
              r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).get();
            } else {
              r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).header("Authorization", authorization).get();
            }
          } else {
            if (authorization == null) {
              r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).header("Referer", requestContext.getHeader("Referer")).get();
            } else {
              r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).header("Authorization", authorization).header("Referer", requestContext.getHeader("Referer")).get();
            }
          }
          String serviceMapResponse = r.readEntity(String.class);
          if ("html".equals(format)) {
            return Response.ok(goThere(competentServiceMapsPrefix.get(0), serviceMapResponse), MediaType.TEXT_HTML).header("Access-Control-Allow-Origin", "*").status(r.getStatus()).build();
          } else {
            return Response.ok(new JSONObject(serviceMapResponse).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").status(r.getStatus()).build();
          }
        } catch (Exception e) {
          e.printStackTrace();
          return Response.status(500).build();
        }
      } else {

                    // Otherwise, launch a separate thread for each of the service maps that must be queried, so that they could concurrently populate the lists of bus stops, sensors, and generic services
        HashSet<String> BusStopsFeaturedUniques = new HashSet<>();
        HashSet<String> SensorSitesFeaturedUniques = new HashSet<>();
        HashSet<String> ServicesFeaturedUniques = new HashSet<>();
        HashSet<String> GenericFeaturedUniques = new HashSet<>();
        HashSet<String> PlainResponses = new HashSet<>();
        HashSet<String> PlainResponseCodes = new HashSet<>();

        RequestMakingAndHashThread[] threads = new RequestMakingAndHashThread[competentServiceMapsPrefix.size()];

        for (int i = 0; i < competentServiceMapsPrefix.size(); i++) {

          final String SMQUERY = competentServiceMapsPrefix.get(i) + "/api/v1/?ssm=yes&maxResults=0&"
                  + (selection == null || selection.isEmpty() ? "" : "&selection=" + URLEncoder.encode(selection, "UTF-8"))
                  + (queryId == null || queryId.isEmpty() ? "" : "&queryId=" + URLEncoder.encode(queryId, "UTF-8"))
                  + (search == null || search.isEmpty() ? "" : "&search=" + URLEncoder.encode(search, "UTF-8"))
                  + (categories == null || categories.isEmpty() ? "" : "&categories=" + URLEncoder.encode(categories, "UTF-8"))
                  + (text == null || text.isEmpty() ? "" : "&text=" + URLEncoder.encode(text, "UTF-8"))
                  + (maxDists == null || maxDists.isEmpty() ? "" : "&maxDists=" + URLEncoder.encode(maxDists, "UTF-8"))
                  + (lang == null || lang.isEmpty() ? "" : "&lang=" + URLEncoder.encode(lang, "UTF-8"))
                  + (geometry == null || geometry.isEmpty() ? "" : "&geometry=" + URLEncoder.encode(geometry, "UTF-8"))
                  + (uid == null || uid.isEmpty() ? "" : "&uid=" + URLEncoder.encode(uid, "UTF-8"))
                  + (format == null || format.isEmpty() ? "" : "&format=" + URLEncoder.encode(format, "UTF-8"))
                  + (map == null || map.isEmpty() ? "" : "&maps=" + URLEncoder.encode(map, "UTF-8"))
                  + (controls == null || controls.isEmpty() ? "" : "&controls=" + URLEncoder.encode(controls, "UTF-8"))
                  + (info == null || info.isEmpty() ? "" : "&info=" + URLEncoder.encode(info, "UTF-8"))
                  + (realtime == null || realtime.isEmpty() ? "" : "&realtime=" + URLEncoder.encode(realtime, "UTF-8"))
                  + (requestFrom == null || requestFrom.isEmpty() ? "" : "&requestFrom=" + URLEncoder.encode(requestFrom, "UTF-8"))
                  + (valueName == null || valueName.isEmpty() ? "" : "&valueName=" + URLEncoder.encode(valueName, "UTF-8"))
                  + (fromTime == null || fromTime.isEmpty() ? "" : "&fromTime=" + URLEncoder.encode(fromTime, "UTF-8"))
                  + (toTime == null || toTime.isEmpty() ? "" : "&toTime=" + URLEncoder.encode(toTime, "UTF-8"))
                  + (valueType == null || valueType.isEmpty() ? "" : "&value_type=" + URLEncoder.encode(valueType, "UTF-8"))
                  + (healthiness == null || healthiness.isEmpty() ? "" : "&healthiness=" + URLEncoder.encode(healthiness, "UTF-8"))
                  + (graphUri == null || graphUri.isEmpty() ? "" : "&graphUri=" + URLEncoder.encode(graphUri, "UTF-8"))
                  + (fullCount == null || fullCount.isEmpty() ? "" : "&fullCount=" + URLEncoder.encode(fullCount, "UTF-8"))
                  + (apikey == null || apikey.isEmpty() ? "" : "&apikey=" + URLEncoder.encode(apikey, "UTF-8"));

          String httpRequestForwardedFor = "";
          if (requestContext.getHeader("X-Forwarded-For") != null && !requestContext.getHeader("X-Forwarded-For").isEmpty()) {
            httpRequestForwardedFor += requestContext.getHeader("X-Forwarded-For") + ",";
          }
          httpRequestForwardedFor += ipAddressRequestCameFrom;

          threads[i] = new RequestMakingAndHashThread(SMQUERY, i, BusStopsFeaturedUniques,
                  SensorSitesFeaturedUniques, ServicesFeaturedUniques, GenericFeaturedUniques, PlainResponses, PlainResponseCodes, httpRequestForwardedFor, authorization, requestContext.getHeader("Referer"));

          threads[i].start();

        }

        for (int i = 0; i < competentServiceMapsPrefix.size(); i++) {
          threads[i].join();
        }

        // If nothing was found querying all the service maps of interest, check to see if all service maps agree about a response, in which case give back that response, otherwise return an empty object. This way, errors due to invalid inputs and similar are forwarded without the need of duplicating the validation.
        if ((ServicesFeaturedUniques.isEmpty() && SensorSitesFeaturedUniques.isEmpty() && BusStopsFeaturedUniques.isEmpty() && GenericFeaturedUniques.isEmpty())) {
          if (PlainResponses.size() == 1 && PlainResponseCodes.size() == 1) {
            try {
              Iterator<String> PlainResponseCodesIterator = PlainResponseCodes.iterator();
              Iterator<String> PlainResponsesIterator = PlainResponses.iterator();
              String responseCode = PlainResponseCodesIterator.next();
              String plainResponse = PlainResponsesIterator.next();
              return Response.ok(new JSONObject(plainResponse).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").status(Integer.parseInt(responseCode)).build();
            } catch (Exception e) {
              e.printStackTrace();
              return Response.status(500).build();
            }
          } else {
            return Response.status(500).build();
          }
        }

                    // Put all features in a single set, and sort them by distance 
        class Feature {

          double dist;
          JSONObject obj;
        }
        class BusStop extends Feature {
        }
        class SensorSite extends Feature {
        }
        class Service extends Feature {
        }
        class Generic extends Feature {
        }
        ArrayList<Feature> allFeatures = new ArrayList<>();
        Iterator<String> busStopIterator = BusStopsFeaturedUniques.iterator();
        HashSet<String> uris = new HashSet<>();
        while (busStopIterator.hasNext()) {
          try {
            JSONObject tmpJSON = new JSONObject(busStopIterator.next());
            if (uris.add(tmpJSON.getJSONObject("properties").getString("serviceUri"))) {
              BusStop busStop = new BusStop();
              busStop.dist = Double.parseDouble(tmpJSON.getJSONObject("properties").getString("distance"));
              busStop.obj = tmpJSON;
              allFeatures.add(busStop);
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        uris.clear();
        Iterator<String> sensorSitesIterator = SensorSitesFeaturedUniques.iterator();
        while (sensorSitesIterator.hasNext()) {
          try {
            JSONObject tmpJSON = new JSONObject(sensorSitesIterator.next());
            if (uris.add(tmpJSON.getJSONObject("properties").getString("serviceUri"))) {
              SensorSite sensorSite = new SensorSite();
              sensorSite.dist = Double.parseDouble(tmpJSON.getJSONObject("properties").getString("distance"));
              sensorSite.obj = tmpJSON;
              allFeatures.add(sensorSite);
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        uris.clear();
        Iterator<String> servicesIterator = ServicesFeaturedUniques.iterator();
        while (servicesIterator.hasNext()) {
          try {
            JSONObject tmpJSON = new JSONObject(servicesIterator.next());
            if (uris.add(tmpJSON.getJSONObject("properties").getString("serviceUri"))) {
              Service service = new Service();
              service.dist = Double.parseDouble(tmpJSON.getJSONObject("properties").getString("distance"));
              service.obj = tmpJSON;
              allFeatures.add(service);
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        uris.clear();
        Iterator<String> genericIterator = GenericFeaturedUniques.iterator();
        while (genericIterator.hasNext()) {
          try {
            JSONObject tmpJSON = new JSONObject(genericIterator.next());
            if (uris.add(tmpJSON.getJSONObject("properties").getString("serviceUri"))) {
              Generic generic = new Generic();
              generic.dist = computeDistance(str2geo(selection), str2geo(getAddressPosition(tmpJSON)));
              generic.obj = tmpJSON;
              allFeatures.add(generic);
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        uris.clear();
        class FeatureComparator implements Comparator<Feature> {

          @Override
          public int compare(Feature f1, Feature f2) {
            try {
              return (int) Math.round(Math.signum(f1.dist - f2.dist));
            } catch (Exception e) {
              e.printStackTrace();
              return 0;
            }
          }
        }
        allFeatures.sort(new FeatureComparator());

                    // Initialize the response 
        JSONObject initialJSONresponse = new JSONObject();
        if (!GenericFeaturedUniques.isEmpty()) {
          initialJSONresponse.put("type", "FeatureCollection");
          initialJSONresponse.put("fullCount", GenericFeaturedUniques.size());
          initialJSONresponse.put("features", new JSONArray());
        }
        JSONObject iBusStops = new JSONObject();
        iBusStops.put("fullCount", BusStopsFeaturedUniques.size());
        iBusStops.put("type", "FeatureCollection");
        iBusStops.put("features", new JSONArray());
        initialJSONresponse.put("BusStops", iBusStops);
        JSONObject iSensorSites = new JSONObject();
        iSensorSites.put("fullCount", SensorSitesFeaturedUniques.size());
        iSensorSites.put("type", "FeatureCollection");
        iSensorSites.put("features", new JSONArray());
        initialJSONresponse.put("SensorSites", iSensorSites);
        JSONObject iServices = new JSONObject();
        iServices.put("fullCount", ServicesFeaturedUniques.size());
        iServices.put("type", "FeatureCollection");
        iServices.put("features", new JSONArray());
        initialJSONresponse.put("Services", iServices);

                    // Add the first maxResults features to the response
                    // ( retrieve the limit )
        int maxResultNumeric = 100;
        try {
          if (maxResults != null && (!maxResults.isEmpty()) && StringUtils.isNumeric(maxResults)) {
            maxResultNumeric = Integer.parseInt(maxResults);
          }
          if (maxResultNumeric == 0) {
            maxResultNumeric = BusStopsFeaturedUniques.size() + SensorSitesFeaturedUniques.size() + ServicesFeaturedUniques.size() + GenericFeaturedUniques.size();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

                    // ( reserve 20% to sensors, 30% to bus, 50% to service, redistributing vacancies )
        int howManySensors = 0;
        int howManyBusStops = 0;
        int howManyServices = 0;
        int howManyGeneric = Math.min(GenericFeaturedUniques.size(), maxResultNumeric);
        try {
          int[] queue = new int[]{1, 2, 3};
          for (int i = 0; i < maxResultNumeric; i++) {
            boolean assigned = false;
            int attempts = 0;
            while (!assigned && attempts < 6) {

              if (queue[0] == 1) {
                if (howManySensors < SensorSitesFeaturedUniques.size() && ((float) howManySensors < (int) Math.round(((float) maxResultNumeric) * 20.0 / 100.0) || attempts >= 3)) {
                  howManySensors++;
                  assigned = true;
                  continue;
                }
                int first = 0;
                int second = 0;
                for (int j = 0; j < 3; j++) {
                  if (queue[j] != 1) {
                    if (first == 0) {
                      first = queue[j];
                    } else {
                      second = queue[j];
                    }
                  }
                }
                queue = new int[]{first, second, 1};
              }
              if (queue[0] == 2) {
                if (howManyBusStops < BusStopsFeaturedUniques.size() && ((float) howManyBusStops < (int) Math.round(((float) maxResultNumeric) * 30.0 / 100.0) || attempts >= 3)) {
                  howManyBusStops++;
                  assigned = true;
                  continue;
                }
                int first = 0;
                int second = 0;
                for (int j = 0; j < 3; j++) {
                  if (queue[j] != 2) {
                    if (first == 0) {
                      first = queue[j];
                    } else {
                      second = queue[j];
                    }
                  }
                }
                queue = new int[]{first, second, 2};
              }
              if (queue[0] == 3) {
                if (howManyServices < ServicesFeaturedUniques.size() && ((float) howManyServices < (int) Math.round(((float) maxResultNumeric) * 50.0 / 100.0) || attempts >= 3)) {
                  howManyServices++;
                  assigned = true;
                  continue;
                }
                int first = 0;
                int second = 0;
                for (int j = 0; j < 3; j++) {
                  if (queue[j] != 3) {
                    if (first == 0) {
                      first = queue[j];
                    } else {
                      second = queue[j];
                    }
                  }
                }
                queue = new int[]{first, second, 3};
              }
              attempts++;
            }

          }
        } catch (Exception e) {
          e.printStackTrace();
          int howMany = (int) Math.round(maxResultNumeric / 3);
          howManySensors = howMany;
          howManyBusStops = howMany;
          howManyServices = howMany;
        }

        // ( append features to response respecting shares of different resources )
        int bsid = 0;
        int ssid = 0;
        int sid = 0;
        int gid = 0;
        for (Feature feature : allFeatures) {
          if (feature instanceof BusStop && howManyBusStops > 0) {
            try {
              initialJSONresponse.getJSONObject("BusStops").append("features", ((BusStop) feature).obj);
              bsid++;
              feature.obj.put("id", bsid);
              howManyBusStops--;
            } catch (Exception e) {
              e.printStackTrace();
            }
          } else if (feature instanceof SensorSite && howManySensors > 0) {
            try {
              initialJSONresponse.getJSONObject("SensorSites").append("features", ((SensorSite) feature).obj);
              ssid++;
              feature.obj.put("id", ssid);
              howManySensors--;
            } catch (Exception e) {
              e.printStackTrace();
            }
          } else if (feature instanceof Service && howManyServices > 0) {
            try {
              initialJSONresponse.getJSONObject("Services").append("features", ((Service) feature).obj);
              sid++;
              feature.obj.put("id", sid);
              howManyServices--;
            } catch (Exception e) {
              e.printStackTrace();
            }
          } else if (feature instanceof Generic && howManyGeneric > 0) {
            try {
              initialJSONresponse.append("features", ((Generic) feature).obj);
              gid++;
              feature.obj.put("id", gid);
              howManyGeneric--;
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        }

                    // Strip out empty categories
        try {
          JSONObject ser = initialJSONresponse.getJSONObject("Services");
          if (ser.getJSONArray("features").length() == 0) {
            initialJSONresponse.remove("Services");
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

        try {
          JSONObject sens = initialJSONresponse.getJSONObject("SensorSites");
          if (sens.getJSONArray("features").length() == 0) {
            initialJSONresponse.remove("SensorSites");
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

        try {
          JSONObject bus = initialJSONresponse.getJSONObject("BusStops");
          if (bus.getJSONArray("features").length() == 0) {
            initialJSONresponse.remove("BusStops");
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

                    // Return the response object
        return Response.ok(initialJSONresponse.toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();

      }

    } else { // If it is not a search for services, but it is instead a request for full details about a specific service, do the following...

      String completePathAndQuery = "/api/v1/?ssm=yes&serviceUri=" + URLEncoder.encode(serviceUri, "UTF-8")
              + (selection == null || selection.isEmpty() ? "" : "&selection=" + URLEncoder.encode(selection, "UTF-8"))
              + (queryId == null || queryId.isEmpty() ? "" : "&queryId=" + URLEncoder.encode(queryId, "UTF-8"))
              + (search == null || search.isEmpty() ? "" : "&search=" + URLEncoder.encode(search, "UTF-8"))
              + (categories == null || categories.isEmpty() ? "" : "&categories=" + URLEncoder.encode(categories, "UTF-8"))
              + (text == null || text.isEmpty() ? "" : "&text=" + URLEncoder.encode(text, "UTF-8"))
              + (maxDists == null || maxDists.isEmpty() ? "" : "&maxDists=" + URLEncoder.encode(maxDists, "UTF-8"))
              + (maxResults == null || maxResults.isEmpty() ? "" : "&maxResults=" + URLEncoder.encode(maxResults, "UTF-8"))
              + (lang == null || lang.isEmpty() ? "" : "&lang=" + URLEncoder.encode(lang, "UTF-8"))
              + (geometry == null || geometry.isEmpty() ? "" : "&geometry=" + URLEncoder.encode(geometry, "UTF-8"))
              + (uid == null || uid.isEmpty() ? "" : "&uid=" + URLEncoder.encode(uid, "UTF-8"))
              + (format == null || format.isEmpty() ? "" : "&format=" + URLEncoder.encode(format, "UTF-8"))
              + (map == null || map.isEmpty() ? "" : "&maps=" + URLEncoder.encode(map, "UTF-8"))
              + (controls == null || controls.isEmpty() ? "" : "&controls=" + URLEncoder.encode(controls, "UTF-8"))
              + (info == null || info.isEmpty() ? "" : "&info=" + URLEncoder.encode(info, "UTF-8"))
              + (realtime == null || realtime.isEmpty() ? "" : "&realtime=" + URLEncoder.encode(realtime, "UTF-8"))
              + (requestFrom == null || requestFrom.isEmpty() ? "" : "&requestFrom=" + URLEncoder.encode(requestFrom, "UTF-8")) + (valueName == null || valueName.isEmpty() ? "" : "&valueName=" + URLEncoder.encode(valueName, "UTF-8"))
              + (fromTime == null || fromTime.isEmpty() ? "" : "&fromTime=" + URLEncoder.encode(fromTime, "UTF-8"))
              + (toTime == null || toTime.isEmpty() ? "" : "&toTime=" + URLEncoder.encode(toTime, "UTF-8"))
              + (valueType == null || valueType.isEmpty() ? "" : "&value_type=" + URLEncoder.encode(valueType, "UTF-8"))
              + (healthiness == null || healthiness.isEmpty() ? "" : "&healthiness=" + URLEncoder.encode(healthiness, "UTF-8"))
              + (graphUri == null || graphUri.isEmpty() ? "" : "&graphUri=" + URLEncoder.encode(graphUri, "UTF-8"))
              + (fullCount == null || fullCount.isEmpty() ? "" : "&fullCount=" + URLEncoder.encode(fullCount, "UTF-8"))
              + (apikey == null || apikey.isEmpty() ? "" : "&apikey=" + URLEncoder.encode(apikey, "UTF-8"));

      String httpRequestForwardedFor = "";
      if (requestContext.getHeader("X-Forwarded-For") != null && !requestContext.getHeader("X-Forwarded-For").isEmpty()) {
        httpRequestForwardedFor += requestContext.getHeader("X-Forwarded-For") + ",";
      }
      httpRequestForwardedFor += ipAddressRequestCameFrom;

      return getServiceURI(serviceUri, format == null || format.isEmpty() ? "json" : format, "/api/v1", completePathAndQuery, httpRequestForwardedFor, authorization);

    }

  }

        // Manage requests for full details about a specific feature (bus stop, sensor, or other generic service) identifier through its URI.
  // It is a subcase of the /api/v1 RESTful Web Service. It is executed when the serviceUri input parameter is received in input.
  public Response getServiceURI(String serviceUri, String format, String api, String completePathAndQuery, String httpRequestForwardedFor, String authorization) throws Exception {

      /*System.out.println("serviceUri = "+serviceUri);
      System.out.println("format = "+format);
      System.out.println("api = "+api);
      System.out.println("completePathAndQuery = "+completePathAndQuery);
      System.out.println("httpRequestForwardedFor = "+httpRequestForwardedFor);
      System.out.println("authorization = "+authorization);*/
      
            // Check to see if the feature is cached, in which case retrieve the service map where it can be found is also cached and can be retrieved without polling all service maps
    try {

      MySQLManager store = new MySQLManager();
      String idOfSMwithUri = store.getSMIdFromServiceUriCache(serviceUri);

      if (idOfSMwithUri != null) { // If it is cached
        try {
          final String SMQUERY = store.getUrlPrefixFromSMid(idOfSMwithUri) + completePathAndQuery;
          ClientConfig config = new ClientConfig();
          Client client = ClientBuilder.newClient(config);
          WebTarget targetServiceMap = client.target(UriBuilder.fromUri(SMQUERY).build());
          Response r = null;
          if (requestContext.getHeader("Referer") == null) {
            if (authorization == null) { r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).get(); }
            else { r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).header("Authorization", authorization).get(); } 
          } else {
            if (authorization == null) {  r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).header("Referer", requestContext.getHeader("Referer")).get(); }
            else {  r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).header("Referer", requestContext.getHeader("Referer")).header("Authorization", authorization).get(); }
          }
          return Response.ok("html".equals(format) ? goThere(store.getUrlPrefixFromSMid(idOfSMwithUri), r.readEntity(String.class)) : new JSONObject(r.readEntity(String.class)).toString(4), "html".equals(format) ? MediaType.TEXT_HTML : MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").status(r.getStatus()).build();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      try {
        List<ServiceMap> sMs = store.getAll(api, format);
        HashSet<String> responses = new HashSet<>();
        HashSet<String> responseCodes = new HashSet<>();
        for (int i = 0; i < sMs.size(); i++) {
          final String SMQUERY = store.getUrlPrefixFromSMid(sMs.get(i).getId()) + completePathAndQuery;
          ClientConfig config = new ClientConfig();
          Client client = ClientBuilder.newClient(config);
          WebTarget targetServiceMap = client.target(UriBuilder.fromUri(SMQUERY).build());
          Response r = null;
          if (requestContext.getHeader("Referer") == null) {
            if (authorization == null) {  r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).get(); }
            else {  r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).header("Authorization", authorization).get(); } 
          } else {
              if (authorization == null) {  r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).header("Referer", requestContext.getHeader("Referer")).get(); }
              else {  r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).header("Referer", requestContext.getHeader("Referer")).header("Authorization", authorization).get(); }
          }
          String code = String.valueOf(r.getStatus());
          String response = r.readEntity(String.class);
          responseCodes.add(code);
          responses.add(response);
          if (r.getStatus() != 400) {
            store.insertCache(serviceUri, sMs.get(i).getId());
            return Response.ok("html".equals(format) ? goThere(store.getUrlPrefixFromSMid(sMs.get(i).getId()), response) : new JSONObject(response).toString(4), "html".equals(format) ? MediaType.TEXT_HTML : MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
          }
        }
        if (responses.size() == 1 && responseCodes.size() == 1) {
          store.insertCache(serviceUri, sMs.get(0).getId(), true);
          Iterator<String> PlainResponseCodesIterator = responseCodes.iterator();
          Iterator<String> PlainResponsesIterator = responses.iterator();
          String responseCode = PlainResponseCodesIterator.next();
          String plainResponse = PlainResponsesIterator.next();
          return Response.ok("html".equals(format) ? goThere(store.getUrlPrefixFromSMid(sMs.get(0).getId()), plainResponse) : new JSONObject(plainResponse).toString(4), "html".equals(format) ? MediaType.TEXT_HTML : MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").status(Integer.parseInt(responseCode)).build();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

      return Response.status(500).build();

    } catch (Exception e) {
      e.printStackTrace();
      return Response.status(500).build();

    }

  }

  // Manage address searches 
  @SuppressWarnings("deprecation")
  @Path("/location")
  @GET
  public Response getLocation(
          @QueryParam("position") String position,
          @QueryParam("search") String search,
          @QueryParam("searchMode") String searchMode,
          @QueryParam("maxDists") String maxDists,
          @QueryParam("excludePOI") String excludePOI,
          @QueryParam("maxResults") String maxResults,
          @QueryParam("intersectGeom") String intersectGeom,
          @QueryParam("uid") String uid,
          @QueryParam("requestFrom") String requestFrom
  ) throws Exception {

            // Identify service maps that have to be queried based on the given position.
    List<String> competentServiceMaps = getCompetentServiceMaps(position, "/api/v1/location", "json");

            // Perform a parallel polling among the service maps of interest to retrieve the street address(es)
    ArrayList<Thread> threads = new ArrayList<>();
    ArrayList<String> addresses = new ArrayList<>();
    HashSet<String> allResponses = new HashSet<>();
    HashSet<String> allResponseCodes = new HashSet<>();
    for (int i = 0; i < competentServiceMaps.size(); i++) {
      final String SMQUERY = competentServiceMaps.get(i) + "/api/v1/location/?ssm=yes"
              + (position == null || position.isEmpty() ? "" : "&position=" + URLEncoder.encode(position, "UTF-8"))
              + (search == null || search.isEmpty() ? "" : "&search=" + URLEncoder.encode(search, "UTF-8"))
              + (searchMode == null || searchMode.isEmpty() ? "" : "&searchMode=" + URLEncoder.encode(searchMode, "UTF-8"))
              + (maxDists == null || String.valueOf(maxDists).isEmpty() ? "" : "&maxDists=" + URLEncoder.encode(maxDists, "UTF-8"))
              + (excludePOI == null || excludePOI.isEmpty() ? "" : "&excludePOI=" + URLEncoder.encode(excludePOI, "UTF-8"))
              + (maxResults == null || maxResults.isEmpty() ? "" : "&maxResults=" + URLEncoder.encode(maxResults, "UTF-8"))
              + (intersectGeom == null || intersectGeom.isEmpty() ? "" : "&intersectGeom=" + URLEncoder.encode(intersectGeom, "UTF-8"))
              + (uid == null || uid.isEmpty() ? "" : "&uid=" + URLEncoder.encode(uid, "UTF-8"))
              + (requestFrom == null || requestFrom.isEmpty() ? "" : "&requestFrom=" + URLEncoder.encode(requestFrom, "UTF-8"));

      String ipAddressRequestCameFrom = requestContext.getRemoteAddr();
      String httpRequestForwardedFor = "";
      if (requestContext.getHeader("X-Forwarded-For") != null && !requestContext.getHeader("X-Forwarded-For").isEmpty()) {
        httpRequestForwardedFor += requestContext.getHeader("X-Forwarded-For") + ",";
      }
      httpRequestForwardedFor += ipAddressRequestCameFrom;

      ParallelQuery thread = new ParallelQuery(SMQUERY, addresses, allResponses, allResponseCodes, httpRequestForwardedFor, requestContext.getHeader("Referer"));
      threads.add(thread);
      thread.start();
    }

    for (Thread thread : threads) {
      thread.join();
    }

            // Produce the response
    if (addresses.isEmpty()) { // If no addresses could be found, simply return an empty object                
      if (allResponses.size() == 1 && allResponseCodes.size() == 1) {
        try {
          Iterator<String> PlainResponseCodesIterator = allResponseCodes.iterator();
          Iterator<String> PlainResponsesIterator = allResponses.iterator();
          String responseCode = PlainResponseCodesIterator.next();
          String plainResponse = PlainResponsesIterator.next();
          return Response.ok(new JSONObject(plainResponse).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").status(Integer.parseInt(responseCode)).build();
        } catch (Exception e) {
          e.printStackTrace();
          return Response.status(500).build();
        }
      } else {
        return Response.status(500).build();
      }
    } else if (1 == addresses.size()) { // If just one address could be found, simply return it
      try {
        return Response.ok(new JSONObject(addresses.get(0)).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
      } catch (Exception e) {
        e.printStackTrace();
        return Response.status(500).build();
      }
    } else { // If more addresses could be found, sort them by distance as it is done in the /real/ service maps, remove duplicates, and return as many as it is specified in the maxResults

      class Address {

        double dist;
        JSONObject obj;
      }
      ArrayList<Address> sortedAddresses = new ArrayList<>();
      boolean isFeatureCollection = false;
      for (String strAddress : addresses) {
        try {
          JSONObject obj = new JSONObject(strAddress);
          if (obj.has("type") && "FeatureCollection".equals(obj.getString("type"))) {
            isFeatureCollection = true;
            JSONArray jFeatures = obj.getJSONArray("features");
            for (int i = 0; i < jFeatures.length(); i++) {
              JSONObject feature = jFeatures.getJSONObject(i);
              Address aFeature = new Address();
              aFeature.obj = feature;
              aFeature.dist = computeDistance(str2geo(position), str2geo(getAddressPosition(feature)));
              sortedAddresses.add(aFeature);
            }
          } else {
            Address address = new Address();
            address.obj = obj;
            address.dist = Double.MAX_VALUE;
            try {
              address.dist = computeDistance(str2geo(position), str2geo(getAddressPosition(obj)));
            } catch (Exception ee) {
              address.dist = Double.MAX_VALUE;
            }
            sortedAddresses.add(address);
          }

        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      class AddressComparator implements Comparator<Address> {

        @Override
        public int compare(Address a1, Address a2) {
          try {
            return (int) Math.round(Math.signum(a1.dist - a2.dist));
          } catch (Exception e) {
            e.printStackTrace();
            return 0;
          }

        }
      }
      sortedAddresses.sort(new AddressComparator());

      HashSet<String> wrk = new HashSet<>();
      ArrayList<Address> cleanedAddresses = new ArrayList<>();
      for (int i = 0; i < sortedAddresses.size(); i++) {
        try {
          if (isFeatureCollection && wrk.add(sortedAddresses.get(i).obj.getJSONObject("properties").getString("serviceUri"))) {
            cleanedAddresses.add(sortedAddresses.get(i));
          }
          if ((!isFeatureCollection) && wrk.add(sortedAddresses.get(i).obj.toString(4))) {
            cleanedAddresses.add(sortedAddresses.get(i));
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      sortedAddresses = cleanedAddresses;

      int maxResultNumeric = 10;
      try {
        if (maxResults != null && (!maxResults.isEmpty()) && StringUtils.isNumeric(maxResults)) {
          maxResultNumeric = Integer.parseInt(maxResults);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

      JSONObject featCollResp = new JSONObject();
      featCollResp.put("type", "FeatureCollection");
      featCollResp.put("count", Math.min(maxResultNumeric, sortedAddresses.size()));
      JSONArray featsResp = new JSONArray();
      featCollResp.put("features", featsResp);

      if (sortedAddresses.size() == 1 || maxResultNumeric == 1) {
        if (!isFeatureCollection) {
          try {
            return Response.ok(sortedAddresses.get(0).obj.toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
          } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
          }
        } else {
          try {
            sortedAddresses.get(0).obj.getJSONObject("properties").put("id", 1);
            featsResp.put(sortedAddresses.get(0).obj);
            return Response.ok(featCollResp.toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
          } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
          }
        }
      } else {
        if (!isFeatureCollection) {
          JSONArray resp = new JSONArray();
          for (int i = 0; i < Math.min(maxResultNumeric, sortedAddresses.size()); i++) {
            try {
              resp.put(sortedAddresses.get(i).obj);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
          return Response.ok(resp.toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
        } else {
          for (int i = 0; i < Math.min(maxResultNumeric, sortedAddresses.size()); i++) {
            try {
              sortedAddresses.get(i).obj.getJSONObject("properties").put("id", i + 1);
              featsResp.put(sortedAddresses.get(i).obj);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
          return Response.ok(featCollResp.toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
        }
      }

    }

  }

  // Manage event searches
  @SuppressWarnings("deprecation")
  @Path("/events")
  @GET
  public Response getEvents(
          @QueryParam("range") String range,
          @QueryParam("selection") String selection,
          @QueryParam("maxDists") String maxDists,
          @QueryParam("maxResults") String maxResults,
          @QueryParam("uid") String uid,
          @QueryParam("requestFrom") String requestFrom
  ) throws Exception {

            // Identify service maps that have to be queried based on the given position.
    List<String> competentServiceMaps = getCompetentServiceMaps(selection, "/api/v1/events", "json");

            // Perform a parallel polling among the service maps of interest to retrieve the events
    ArrayList<Thread> threads = new ArrayList<>();
    ArrayList<String> collections = new ArrayList<>();
    HashSet<String> allResponses = new HashSet<>();
    HashSet<String> allResponseCodes = new HashSet<>();
    for (int i = 0; i < competentServiceMaps.size(); i++) {
      final String SMQUERY = competentServiceMaps.get(i) + "/api/v1/events/?ssm=yes&maxResults=0"
              + (range == null || range.isEmpty() ? "" : "&range=" + URLEncoder.encode(range, "UTF-8"))
              + (selection == null || selection.isEmpty() ? "" : "&selection=" + URLEncoder.encode(selection, "UTF-8"))
              + (maxDists == null || maxDists.isEmpty() ? "" : "&maxDists=" + URLEncoder.encode(maxDists, "UTF-8"))
              + (uid == null || uid.isEmpty() ? "" : "&uid=" + URLEncoder.encode(uid, "UTF-8"))
              + (requestFrom == null || requestFrom.isEmpty() ? "" : "&requestFrom=" + URLEncoder.encode(requestFrom, "UTF-8"));

      String ipAddressRequestCameFrom = requestContext.getRemoteAddr();
      String httpRequestForwardedFor = "";
      if (requestContext.getHeader("X-Forwarded-For") != null && !requestContext.getHeader("X-Forwarded-For").isEmpty()) {
        httpRequestForwardedFor += requestContext.getHeader("X-Forwarded-For") + ",";
      }
      httpRequestForwardedFor += ipAddressRequestCameFrom;

      ParallelQuery thread = new ParallelQuery(SMQUERY, collections, allResponses, allResponseCodes, httpRequestForwardedFor, requestContext.getHeader("Referer"));
      threads.add(thread);
      thread.start();
    }

    for (Thread thread : threads) {
      thread.join();
    }

            // If no valid responses arrived...
    if (collections.isEmpty()) {
      if (allResponses.size() == 1 && allResponseCodes.size() == 1) {
        try {
          Iterator<String> PlainResponseCodesIterator = allResponseCodes.iterator();
          Iterator<String> PlainResponsesIterator = allResponses.iterator();
          String responseCode = PlainResponseCodesIterator.next();
          String plainResponse = PlainResponsesIterator.next();
          return Response.ok(new JSONObject(plainResponse).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").status(Integer.parseInt(responseCode)).build();
        } catch (Exception e) {
          e.printStackTrace();
          return Response.status(500).build();
        }
      } else {
        return Response.status(500).build();
      }
    }

            // If just one valid response arrived...
    if (collections.size() == 1) {
      try {
        return Response.ok(new JSONObject(collections.get(0)).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
      } catch (Exception e) {
        e.printStackTrace();
        return Response.status(500).build();
      }
    }

            // In the most complex possible case, put all events in a single set, and sort them by ascending start date, as it is done by the /real/ service maps
    ArrayList<JSONObject> events = new ArrayList<>();
    for (String collection : collections) {
      try {
        JSONObject jCollection = new JSONObject(collection);
        JSONArray jEvents = jCollection.getJSONObject("Event").getJSONArray("features");
        for (int i = 0; i < jEvents.length(); i++) {
          events.add((JSONObject) jEvents.get(i));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

    }

    class EventComparator implements Comparator<JSONObject> {

      @Override
      public int compare(JSONObject obj1, JSONObject obj2) {
        try {
          return obj1.getJSONObject("properties").getString("startDate").compareTo(obj2.getJSONObject("properties").getString("startDate"));
        } catch (Exception e) {
          e.printStackTrace();
          return 0;
        }
      }
    }

    events.sort(new EventComparator());

            // Remove duplicates 
    HashSet<String> wrk = new HashSet<>();
    ArrayList<JSONObject> cleanedEvents = new ArrayList<>();
    for (int i = 0; i < events.size(); i++) {
      try {
        if (wrk.add(events.get(i).getJSONObject("properties").getString("serviceUri"))) {
          cleanedEvents.add(events.get(i));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    events = cleanedEvents;

            // Parse Max Results
    int maxResultNumeric = 100;
    try {
      if (maxResults != null && (!maxResults.isEmpty()) && StringUtils.isNumeric(maxResults)) {
        maxResultNumeric = Integer.parseInt(maxResults);
      }
      if (maxResultNumeric == 0) {
        maxResultNumeric = events.size();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

            // Initialize response
    JSONObject response = new JSONObject();
    JSONObject event = new JSONObject();
    response.put("Event", event);
    event.put("type", "FeatureCollection");
    event.put("fullCount", events.size());
    event.put("count", Math.min(events.size(), maxResultNumeric));
    JSONArray features = new JSONArray();
    event.put("features", features);

            // Append events to response
    for (int i = 0; i < Math.min(events.size(), maxResultNumeric); i++) {
      try {
        events.get(i).put("id", i + 1);
        features.put(events.get(i));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

            // Output response
    return Response.ok(response.toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();

  }

  // Give back the full list of the TPL agencies
  @SuppressWarnings("deprecation")
  @Path("/tpl/agencies")
  @GET
  public Response getTplAgencies(
          @QueryParam("uid") String uid,
          @QueryParam("requestFrom") String requestFrom,
          @QueryParam("selection") String selection
  ) throws Exception {

            // Retrieve the full list of the querable service maps
    List<String> competentServiceMaps = getCompetentServiceMaps(selection, "/api/v1/tpl/agencies", "json");

            // Query all service maps for TPL agencies
    ArrayList<Thread> threads = new ArrayList<>();
    ArrayList<String> collections = new ArrayList<>();
    HashSet<String> allResponses = new HashSet<>();
    HashSet<String> allResponseCodes = new HashSet<>();
    for (int i = 0; i < competentServiceMaps.size(); i++) {
      final String SMQUERY = competentServiceMaps.get(i) + "/api/v1/tpl/agencies/?ssm=yes"
              + (uid == null || uid.isEmpty() ? "" : "&uid=" + URLEncoder.encode(uid, "UTF-8"))
              + (requestFrom == null || requestFrom.isEmpty() ? "" : "&requestFrom=" + URLEncoder.encode(requestFrom, "UTF-8"))
              + (selection == null || selection.isEmpty() ? "" : "&selection=" + URLEncoder.encode(selection, "UTF-8"));

      String ipAddressRequestCameFrom = requestContext.getRemoteAddr();
      String httpRequestForwardedFor = "";
      if (requestContext.getHeader("X-Forwarded-For") != null && !requestContext.getHeader("X-Forwarded-For").isEmpty()) {
        httpRequestForwardedFor += requestContext.getHeader("X-Forwarded-For") + ",";
      }
      httpRequestForwardedFor += ipAddressRequestCameFrom;

      ParallelQuery thread = new ParallelQuery(SMQUERY, collections, allResponses, allResponseCodes, httpRequestForwardedFor, requestContext.getHeader("Referer"));
      threads.add(thread);
      thread.start();
    }

    for (Thread thread : threads) {
      thread.join();
    }

            // If no valid responses arrived...
    if (collections.isEmpty()) {
      if (allResponses.size() == 1 && allResponseCodes.size() == 1) {
        try {
          Iterator<String> PlainResponseCodesIterator = allResponseCodes.iterator();
          Iterator<String> PlainResponsesIterator = allResponses.iterator();
          String responseCode = PlainResponseCodesIterator.next();
          String plainResponse = PlainResponsesIterator.next();
          return Response.ok(new JSONObject(plainResponse).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").status(Integer.parseInt(responseCode)).build();
        } catch (Exception e) {
          e.printStackTrace();
          return Response.status(500).build();
        }
      } else {
        return Response.status(500).build();
      }
    }

            // If just one valid response arrived...
    if (collections.size() == 1) {
      try {
        return Response.ok(new JSONObject(collections.get(0)).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
      } catch (Exception e) {
        e.printStackTrace();
        return Response.status(500).build();
      }
    }

            // In the most complex possible case, retrieve agencies from collections and put all them in a single set
    ArrayList<JSONObject> agencies = new ArrayList<>();
    for (String collection : collections) {
      try {
        JSONObject jCollection = new JSONObject(collection);
        JSONArray jAgencies = jCollection.getJSONArray("Agencies");
        for (int i = 0; i < jAgencies.length(); i++) {
          agencies.add((JSONObject) jAgencies.get(i));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

            // Sort by name
    class AgencyComparator implements Comparator<JSONObject> {

      @Override
      public int compare(JSONObject obj1, JSONObject obj2) {
        try {
          return obj1.getString("name").compareTo(obj2.getString("name"));
        } catch (Exception e) {
          e.printStackTrace();
          return 0;
        }
      }
    }

    agencies.sort(new AgencyComparator());

            // Remove duplicates 
    HashSet<String> wrk = new HashSet<>();
    ArrayList<JSONObject> cleanedAgencies = new ArrayList<>();
    for (int i = 0; i < agencies.size(); i++) {
      try {
        if (wrk.add(agencies.get(i).toString(4))) {
          cleanedAgencies.add(agencies.get(i));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    agencies = cleanedAgencies;

            // Initialize response
    JSONObject response = new JSONObject();
    JSONArray jAgencies = new JSONArray();
    response.put("Agencies", jAgencies);

            // Append agencies to response
    for (int i = 0; i < agencies.size(); i++) {
      jAgencies.put(agencies.get(i));
    }

            // Output response
    return Response.ok(response.toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();

  }

  // Give back the full list of the bus lines
  @SuppressWarnings("deprecation")
  @Path("/tpl/bus-lines")
  @GET
  public Response getTplBusLines(
          @QueryParam("agency") String agency,
          @QueryParam("uid") String uid,
          @QueryParam("requestFrom") String requestFrom
  ) throws Exception {

            // Retrieve the full list of the querable service maps
    List<String> competentServiceMaps = getCompetentServiceMaps(null, "/api/v1/tpl/bus-lines", "json");

            // Query all service maps for TPL lines
    ArrayList<Thread> threads = new ArrayList<>();
    ArrayList<String> collections = new ArrayList<>();
    HashSet<String> allResponses = new HashSet<>();
    HashSet<String> allResponseCodes = new HashSet<>();
    for (int i = 0; i < competentServiceMaps.size(); i++) {
      final String SMQUERY = competentServiceMaps.get(i) + "/api/v1/tpl/bus-lines/?ssm=yes"
              + (agency == null || agency.isEmpty() ? "" : "&agency=" + URLEncoder.encode(agency, "UTF-8"))
              + (uid == null || uid.isEmpty() ? "" : "&uid=" + URLEncoder.encode(uid, "UTF-8"))
              + (requestFrom == null || requestFrom.isEmpty() ? "" : "&requestFrom=" + URLEncoder.encode(requestFrom, "UTF-8"));

      String ipAddressRequestCameFrom = requestContext.getRemoteAddr();
      String httpRequestForwardedFor = "";
      if (requestContext.getHeader("X-Forwarded-For") != null && !requestContext.getHeader("X-Forwarded-For").isEmpty()) {
        httpRequestForwardedFor += requestContext.getHeader("X-Forwarded-For") + ",";
      }
      httpRequestForwardedFor += ipAddressRequestCameFrom;

      ParallelQuery thread = new ParallelQuery(SMQUERY, collections, allResponses, allResponseCodes, httpRequestForwardedFor, requestContext.getHeader("Referer"));
      threads.add(thread);
      thread.start();
    }

    for (Thread thread : threads) {
      thread.join();
    }

            // If no valid responses arrived...
    if (collections.isEmpty()) {
      if (allResponses.size() == 1 && allResponseCodes.size() == 1) {
        try {
          Iterator<String> PlainResponseCodesIterator = allResponseCodes.iterator();
          Iterator<String> PlainResponsesIterator = allResponses.iterator();
          String responseCode = PlainResponseCodesIterator.next();
          String plainResponse = PlainResponsesIterator.next();
          return Response.ok(new JSONObject(plainResponse).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").status(Integer.parseInt(responseCode)).build();
        } catch (Exception e) {
          e.printStackTrace();
          return Response.status(500).build();
        }
      } else {
        return Response.status(500).build();
      }
    }

            // If just one valid response arrived...
    if (collections.size() == 1) {
      try {
        return Response.ok(new JSONObject(collections.get(0)).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
      } catch (Exception e) {
        e.printStackTrace();
        return Response.status(500).build();
      }
    }

            // In the most complex possible case, retrieve lines from collections and put all them in a single set
    ArrayList<JSONObject> lines = new ArrayList<>();
    for (String collection : collections) {
      try {
        JSONObject jCollection = new JSONObject(collection);
        JSONArray jLines = jCollection.getJSONArray("BusLines");
        for (int i = 0; i < jLines.length(); i++) {
          lines.add((JSONObject) jLines.get(i));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

            // Remove duplicates 
    HashSet<String> wrk = new HashSet<>();
    ArrayList<JSONObject> cleanedLines = new ArrayList<>();
    for (int i = 0; i < lines.size(); i++) {
      try {
        if (wrk.add(lines.get(i).toString(4))) {
          cleanedLines.add(lines.get(i));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    lines = cleanedLines;

            // Sort by short name
    class LineComparator implements Comparator<JSONObject> {

      @Override
      public int compare(JSONObject obj1, JSONObject obj2) {
        try {
          return Integer.parseInt(obj1.getString("shortName")) - Integer.parseInt(obj2.getString("shortName"));
        } catch (Exception e) {
          e.printStackTrace();
          return 0;
        }
      }
    }

    lines.sort(new LineComparator());

            // Initialize response
    JSONObject response = new JSONObject();
    JSONArray jLines = new JSONArray();
    response.put("BusLines", jLines);

            // Append lines to response
    for (int i = 0; i < lines.size(); i++) {
      jLines.put(lines.get(i));
    }

            // Output response
    return Response.ok(response.toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();

  }

  // Give back the full list of the bus routes
  @SuppressWarnings("deprecation")
  @Path("/tpl/bus-routes")
  @GET
  public Response getTplBusRoutes(
          @QueryParam("agency") String agency,
          @QueryParam("line") String line,
          @QueryParam("busStopName") String busStopName,
          @QueryParam("geometry") String geometry,
          @QueryParam("uid") String uid,
          @QueryParam("requestFrom") String requestFrom
  ) throws Exception {

            // Retrieve the full list of the querable service maps
    List<String> competentServiceMaps = getCompetentServiceMaps(null, "/api/v1/tpl/bus-routes", "json");

            // Query all service maps for routes            
    ArrayList<Thread> threads = new ArrayList<>();
    ArrayList<String> collections = new ArrayList<>();
    HashSet<String> allResponses = new HashSet<>();
    HashSet<String> allResponseCodes = new HashSet<>();
    for (int i = 0; i < competentServiceMaps.size(); i++) {
      final String SMQUERY = competentServiceMaps.get(i) + "/api/v1/tpl/bus-routes/?ssm=yes"
              + (agency == null || agency.isEmpty() ? "" : "&agency=" + URLEncoder.encode(agency, "UTF-8"))
              + (line == null || line.isEmpty() ? "" : "&line=" + URLEncoder.encode(line, "UTF-8"))
              + (busStopName == null || busStopName.isEmpty() ? "" : "&busStopName=" + URLEncoder.encode(busStopName, "UTF-8"))
              + (geometry == null || geometry.isEmpty() ? "" : "&geometry=" + URLEncoder.encode(geometry, "UTF-8"))
              + (uid == null || uid.isEmpty() ? "" : "&uid=" + URLEncoder.encode(uid, "UTF-8"))
              + (requestFrom == null || requestFrom.isEmpty() ? "" : "&requestFrom=" + URLEncoder.encode(requestFrom, "UTF-8"));

      String ipAddressRequestCameFrom = requestContext.getRemoteAddr();
      String httpRequestForwardedFor = "";
      if (requestContext.getHeader("X-Forwarded-For") != null && !requestContext.getHeader("X-Forwarded-For").isEmpty()) {
        httpRequestForwardedFor += requestContext.getHeader("X-Forwarded-For") + ",";
      }
      httpRequestForwardedFor += ipAddressRequestCameFrom;

      ParallelQuery thread = new ParallelQuery(SMQUERY, collections, allResponses, allResponseCodes, httpRequestForwardedFor, requestContext.getHeader("Referer"));
      threads.add(thread);
      thread.start();
    }

    for (Thread thread : threads) {
      thread.join();
    }

            // If no valid responses arrived...
    if (collections.isEmpty()) {
      if (allResponses.size() == 1 && allResponseCodes.size() == 1) {
        try {
          Iterator<String> PlainResponseCodesIterator = allResponseCodes.iterator();
          Iterator<String> PlainResponsesIterator = allResponses.iterator();
          String responseCode = PlainResponseCodesIterator.next();
          String plainResponse = PlainResponsesIterator.next();
          return Response.ok(new JSONObject(plainResponse).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").status(Integer.parseInt(responseCode)).build();
        } catch (Exception e) {
          e.printStackTrace();
          return Response.status(500).build();
        }
      } else {
        return Response.status(500).build();
      }
    }

            // If just one valid response arrived...
    if (collections.size() == 1) {
      try {
        return Response.ok(new JSONObject(collections.get(0)).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
      } catch (Exception e) {
        e.printStackTrace();
        return Response.status(500).build();
      }
    }

            // In the most complex possible case, retrieve routes from collections and put all them in a single set
    ArrayList<JSONObject> routes = new ArrayList<>();
    for (String collection : collections) {
      try {
        JSONObject jCollection = new JSONObject(collection);
        JSONArray jRoutes = jCollection.getJSONArray("BusRoutes");
        for (int i = 0; i < jRoutes.length(); i++) {
          routes.add((JSONObject) jRoutes.get(i));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

            // Sort by first/last stop name
    class RouteComparator implements Comparator<JSONObject> {

      @Override
      public int compare(JSONObject obj1, JSONObject obj2) {
        try {
          if (obj1.getString("firstBusStop").compareTo(obj2.getString("firstBusStop")) != 0) {
            return obj1.getString("firstBusStop").compareTo(obj2.getString("firstBusStop"));
          } else {
            return obj1.getString("lastBusStop").compareTo(obj2.getString("lastBusStop"));
          }
        } catch (Exception e) {
          e.printStackTrace();
          return 0;
        }
      }
    }

    routes.sort(new RouteComparator());

            // Remove duplicates 
    HashSet<String> wrk = new HashSet<>();
    ArrayList<JSONObject> cleanedRoutes = new ArrayList<>();
    for (int i = 0; i < routes.size(); i++) {
      try {
        if (wrk.add(routes.get(i).toString(4))) {
          cleanedRoutes.add(routes.get(i));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    routes = cleanedRoutes;

            // Initialize response
    JSONObject response = new JSONObject();
    JSONArray jRoutes = new JSONArray();
    response.put("BusRoutes", jRoutes);

            // Append routes to response
    for (int i = 0; i < routes.size(); i++) {
      jRoutes.put(routes.get(i));
    }

            // Output response
    return Response.ok(response.toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();

  }

  // Give back the full list of the bus routes
  @SuppressWarnings("deprecation")
  @Path("/tpl/bus-stops")
  @GET
  public Response getTplBusStops(
          @QueryParam("route") String route,
          @QueryParam("geometry") String geometry,
          @QueryParam("uid") String uid,
          @QueryParam("requestFrom") String requestFrom
  ) throws Exception {

            // Retrieve the full list of the querable service maps
    List<String> competentServiceMaps = getCompetentServiceMaps(null, "/api/v1/tpl/bus-stops", "json");

            // Query all service maps for bus stops            
    ArrayList<Thread> threads = new ArrayList<>();
    ArrayList<String> collections = new ArrayList<>();
    HashSet<String> allResponses = new HashSet<>();
    HashSet<String> allResponseCodes = new HashSet<>();
    for (int i = 0; i < competentServiceMaps.size(); i++) {
      final String SMQUERY = competentServiceMaps.get(i) + "/api/v1/tpl/bus-stops/?ssm=yes"
              + (route == null || route.isEmpty() ? "" : "&route=" + URLEncoder.encode(route, "UTF-8"))
              + (geometry == null || geometry.isEmpty() ? "" : "&geometry=" + URLEncoder.encode(geometry, "UTF-8"))
              + (uid == null || uid.isEmpty() ? "" : "&uid=" + URLEncoder.encode(uid, "UTF-8"))
              + (requestFrom == null || requestFrom.isEmpty() ? "" : "&requestFrom=" + URLEncoder.encode(requestFrom, "UTF-8"));

      String ipAddressRequestCameFrom = requestContext.getRemoteAddr();
      String httpRequestForwardedFor = "";
      if (requestContext.getHeader("X-Forwarded-For") != null && !requestContext.getHeader("X-Forwarded-For").isEmpty()) {
        httpRequestForwardedFor += requestContext.getHeader("X-Forwarded-For") + ",";
      }
      httpRequestForwardedFor += ipAddressRequestCameFrom;

      ParallelQuery thread = new ParallelQuery(SMQUERY, collections, allResponses, allResponseCodes, httpRequestForwardedFor, requestContext.getHeader("Referer"));
      threads.add(thread);
      thread.start();
    }

    for (Thread thread : threads) {
      thread.join();
    }

            // If no valid responses arrived...
    if (collections.isEmpty()) {
      if (allResponses.size() == 1 && allResponseCodes.size() == 1) {
        try {
          Iterator<String> PlainResponseCodesIterator = allResponseCodes.iterator();
          Iterator<String> PlainResponsesIterator = allResponses.iterator();
          String responseCode = PlainResponseCodesIterator.next();
          String plainResponse = PlainResponsesIterator.next();
          return Response.ok(new JSONObject(plainResponse).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").status(Integer.parseInt(responseCode)).build();
        } catch (Exception e) {
          e.printStackTrace();
          return Response.status(500).build();
        }
      } else {
        return Response.status(500).build();
      }
    }

            // If just one valid response arrived...
    if (collections.size() == 1) {
      try {
        return Response.ok(new JSONObject(collections.get(0)).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
      } catch (Exception e) {
        e.printStackTrace();
        return Response.status(500).build();
      }
    }

            // In the most complex possible case, retrieve bus stops from collections and put all them in a single set
    HashSet<String> routes = new HashSet<>();
    ArrayList<JSONObject> stops = new ArrayList<>();
    for (String collection : collections) {
      try {
        JSONObject jCollection = new JSONObject(collection);
        String lineName = jCollection.getJSONObject("Route").getString("lineName");
        String lineNumber = jCollection.getJSONObject("Route").getString("lineNumber");
        JSONObject jRoute = new JSONObject();
        jRoute.put("lineName", lineName);
        jRoute.put("lineNumber", lineNumber);
        if (!"false".equals(geometry)) {
          jRoute.put("wktGeometry", jCollection.getJSONObject("Route").getString("wktGeometry"));
        }
        routes.add(jRoute.toString(4));
        JSONArray jStops = jCollection.getJSONObject("BusStops").getJSONArray("features");
        for (int i = 0; i < jStops.length(); i++) {
          stops.add((JSONObject) jStops.get(i));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

            // Sort by id
    class StopComparator implements Comparator<JSONObject> {

      @Override
      public int compare(JSONObject obj1, JSONObject obj2) {
        try {
          return (int) Math.round(Math.signum(Integer.parseInt(obj1.getString("id")) - Integer.parseInt(obj2.getString("id"))));
        } catch (Exception e) {
          e.printStackTrace();
          return 0;
        }
      }
    }

    stops.sort(new StopComparator());

            // Remove duplicates 
    HashSet<String> wrk = new HashSet<>();
    ArrayList<JSONObject> cleanedStops = new ArrayList<>();
    for (int i = 0; i < stops.size(); i++) {
      try {
        if (wrk.add(stops.get(i).getJSONObject("properties").getString("serviceUri"))) {
          cleanedStops.add(stops.get(i));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    stops = cleanedStops;

            // Initialize response
    JSONObject response = new JSONObject();
    if (1 == routes.size()) {
      try {
        Iterator i = routes.iterator();
        response.put("Route", new JSONObject(i.next().toString()));
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      JSONArray jRoutes = new JSONArray();
      Iterator i = routes.iterator();
      while (i.hasNext()) {
        try {
          jRoutes.put(new JSONObject(i.next().toString()));
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      response.put("Route", jRoutes);
    }

    JSONObject jStops = new JSONObject();
    response.put("BusStops", jStops);
    jStops.put("type", "FeatureCollection");
    JSONArray features = new JSONArray();
    jStops.put("features", features);

            // Append stops to response
    for (int i = 0; i < stops.size(); i++) {
      try {
        stops.get(i).put("id", i + 1);
        features.put(stops.get(i));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

            // Output response
    return Response.ok(response.toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();

  }

        // Manage geographical searches for TPL routes
  @SuppressWarnings("deprecation")
  @Path("/tpl")
  @GET
  public Response getTpl(
          @QueryParam("selection") String selection,
          @QueryParam("maxDists") String maxDists,
          @QueryParam("maxResults") String maxResults,
          @QueryParam("agency") String agency,
          @QueryParam("geometry") String geometry,
          @QueryParam("uid") String uid,
          @QueryParam("requestFrom") String requestFrom
  ) throws Exception {

            // Identify service maps that have to be queried based on the given position.
    List<String> competentServiceMaps = getCompetentServiceMaps(selection, "/api/v1/tpl", "json");

            // Perform a parallel polling among the service maps of interest to retrieve the routes
    ArrayList<Thread> threads = new ArrayList<>();
    ArrayList<String> collections = new ArrayList<>();
    HashSet<String> allResponses = new HashSet<>();
    HashSet<String> allResponseCodes = new HashSet<>();
    for (int i = 0; i < competentServiceMaps.size(); i++) {
      final String SMQUERY = competentServiceMaps.get(i) + "/api/v1/tpl/?ssm=yes&maxResults=0"
              + (selection == null || selection.isEmpty() ? "" : "&selection=" + URLEncoder.encode(selection, "UTF-8"))
              + (maxDists == null || maxDists.isEmpty() ? "" : "&maxDists=" + URLEncoder.encode(maxDists, "UTF-8"))
              + (agency == null || agency.isEmpty() ? "" : "&agency=" + URLEncoder.encode(agency, "UTF-8"))
              + (geometry == null || geometry.isEmpty() ? "" : "&geometry=" + URLEncoder.encode(geometry, "UTF-8"))
              + (uid == null || uid.isEmpty() ? "" : "&uid=" + URLEncoder.encode(uid, "UTF-8"))
              + (requestFrom == null || requestFrom.isEmpty() ? "" : "&requestFrom=" + URLEncoder.encode(requestFrom, "UTF-8"));

      String ipAddressRequestCameFrom = requestContext.getRemoteAddr();
      String httpRequestForwardedFor = "";
      if (requestContext.getHeader("X-Forwarded-For") != null && !requestContext.getHeader("X-Forwarded-For").isEmpty()) {
        httpRequestForwardedFor += requestContext.getHeader("X-Forwarded-For") + ",";
      }
      httpRequestForwardedFor += ipAddressRequestCameFrom;

      ParallelQuery thread = new ParallelQuery(SMQUERY, collections, allResponses, allResponseCodes, httpRequestForwardedFor, requestContext.getHeader("Referer"));
      threads.add(thread);
      thread.start();
    }

    for (Thread thread : threads) {
      thread.join();
    }

            // If no valid responses arrived...
    if (collections.isEmpty()) {
      if (allResponses.size() == 1 && allResponseCodes.size() == 1) {
        try {
          Iterator<String> PlainResponseCodesIterator = allResponseCodes.iterator();
          Iterator<String> PlainResponsesIterator = allResponses.iterator();
          String responseCode = PlainResponseCodesIterator.next();
          String plainResponse = PlainResponsesIterator.next();
          return Response.ok(new JSONObject(plainResponse).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").status(Integer.parseInt(responseCode)).build();
        } catch (Exception e) {
          e.printStackTrace();
          return Response.status(500).build();
        }
      } else {
        return Response.status(500).build();
      }
    }

            // If just one valid response arrived...
    if (collections.size() == 1) {
      try {
        return Response.ok(new JSONObject(collections.get(0)).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
      } catch (Exception e) {
        e.printStackTrace();
        Response.status(500).build();
      }
    }

            // Put all routes in a single set, and sort them by ascending start date, as it is done by the /real/ service maps
    ArrayList<JSONObject> routes = new ArrayList<>();
    for (String collection : collections) {
      try {
        JSONObject jCollection = new JSONObject(collection);
        JSONArray jRoutes = jCollection.getJSONObject("PublicTransportLine").getJSONArray("features");
        for (int i = 0; i < jRoutes.length(); i++) {
          routes.add((JSONObject) jRoutes.get(i));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    class RouteComparator implements Comparator<JSONObject> {

      @Override
      public int compare(JSONObject obj1, JSONObject obj2) {
        try {
          if (obj1.getString("agency").compareTo(obj2.getString("agency")) != 0) {
            return obj1.getString("agency").compareTo(obj2.getString("agency"));
          } else {
            return obj1.getString("direction").compareTo(obj2.getString("direction"));
          }
        } catch (Exception e) {
          e.printStackTrace();
          return 0;
        }
      }
    }

    routes.sort(new RouteComparator());

            // Remove duplicates 
    HashSet<String> wrk = new HashSet<>();
    ArrayList<JSONObject> cleanedRoutes = new ArrayList<>();
    for (int i = 0; i < routes.size(); i++) {
      try {
        if (wrk.add(routes.get(i).getJSONObject("properties").getString("routeUri"))) {
          cleanedRoutes.add(routes.get(i));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    routes = cleanedRoutes;

            // Parse Max Results
    int maxResultNumeric = 100;
    try {
      if (maxResults != null && (!maxResults.isEmpty()) && StringUtils.isNumeric(maxResults)) {
        maxResultNumeric = Integer.parseInt(maxResults);
      }
      if (maxResultNumeric == 0) {
        maxResultNumeric = routes.size();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

            // Initialize response
    JSONObject response = new JSONObject();
    JSONObject ptl = new JSONObject();
    response.put("PublicTransportLine", ptl);
    ptl.put("type", "FeatureCollection");
    ptl.put("fullCount", routes.size());
    ptl.put("count", Math.min(routes.size(), maxResultNumeric));
    JSONArray features = new JSONArray();
    ptl.put("features", features);

            // Append events to response
    for (int i = 0; i < Math.min(routes.size(), maxResultNumeric); i++) {
      try {
        routes.get(i).put("id", i + 1);
        features.put(routes.get(i));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

            // Output response
    return Response.ok(response.toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();

  }

  // Give back the full list of the TPL agencies
  @SuppressWarnings("deprecation")
  @Path("/tpl/bus-position")
  @GET
  public Response getBusPosition(
          @QueryParam("uid") String uid,
          @QueryParam("format") String format,
          @QueryParam("requestFrom") String requestFrom,
          @QueryParam("agency") String agency,
          @QueryParam("line") String line
  ) throws Exception {

            // Retrieve the full list of the querable service maps
    List<String> competentServiceMaps = getCompetentServiceMaps(null, "/api/v1/tpl/bus-position", "html".equals(format) ? "html" : "json");

            // If the required format is HTML, simply forward the request to the first service map of the list and you are done. 
    // Indeed, service maps are returned sorted by priority for HTML output.
    if ("html".equals(format) || competentServiceMaps.size() == 1) {
      try {
        final String SMQUERY = competentServiceMaps.get(0) + "/api/v1/tpl/bus-position/?ssm=yes"
                + (format == null || format.isEmpty() ? "" : "&format=" + URLEncoder.encode(format, "UTF-8"))
                + (uid == null || uid.isEmpty() ? "" : "&uid=" + URLEncoder.encode(uid, "UTF-8"))
                + (requestFrom == null || requestFrom.isEmpty() ? "" : "&requestFrom=" + URLEncoder.encode(requestFrom, "UTF-8"))
                + (agency == null || agency.isEmpty() ? "" : "&agency=" + URLEncoder.encode(agency, "UTF-8"))
                + (line == null || line.isEmpty() ? "" : "&line=" + URLEncoder.encode(line, "UTF-8"));
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget targetServiceMap = client.target(UriBuilder.fromUri(SMQUERY).build());
        Response r = null;
        if (requestContext.getHeader("Referer") == null) {
          r = targetServiceMap.request().get();
        } else {
          r = targetServiceMap.request().header("Referer", requestContext.getHeader("Referer")).get();
        }
        String response = r.readEntity(String.class);
        if ("html".equals(format)) {
          return Response.ok(goThere(competentServiceMaps.get(0), response), MediaType.TEXT_HTML).status(r.getStatus()).header("Access-Control-Allow-Origin", "*").build();
        } else {
          return Response.ok(new JSONObject(response).toString(4), MediaType.APPLICATION_JSON).status(r.getStatus()).header("Access-Control-Allow-Origin", "*").build();
        }
      } catch (Exception e) {
        e.printStackTrace();
        return Response.status(500).build();
      }
    }

            // Query all service maps for TPL agencies
    ArrayList<Thread> threads = new ArrayList<>();
    ArrayList<String> collections = new ArrayList<>();
    HashSet<String> allResponses = new HashSet<>();
    HashSet<String> allResponseCodes = new HashSet<>();
    for (int i = 0; i < competentServiceMaps.size(); i++) {
      final String SMQUERY = competentServiceMaps.get(i) + "/api/v1/tpl/bus-position/?ssm=yes"
              + (format == null || format.isEmpty() ? "" : "&format=" + URLEncoder.encode(format, "UTF-8"))
              + (uid == null || uid.isEmpty() ? "" : "&uid=" + URLEncoder.encode(uid, "UTF-8"))
              + (requestFrom == null || requestFrom.isEmpty() ? "" : "&requestFrom=" + URLEncoder.encode(requestFrom, "UTF-8"))
              + (agency == null || agency.isEmpty() ? "" : "&agency=" + URLEncoder.encode(agency, "UTF-8"))
              + (line == null || line.isEmpty() ? "" : "&line=" + URLEncoder.encode(line, "UTF-8"));
      String ipAddressRequestCameFrom = requestContext.getRemoteAddr();
      String httpRequestForwardedFor = "";
      if (requestContext.getHeader("X-Forwarded-For") != null && !requestContext.getHeader("X-Forwarded-For").isEmpty()) {
        httpRequestForwardedFor += requestContext.getHeader("X-Forwarded-For") + ",";
      }
      httpRequestForwardedFor += ipAddressRequestCameFrom;

      ParallelQuery thread = new ParallelQuery(SMQUERY, collections, allResponses, allResponseCodes, httpRequestForwardedFor, requestContext.getHeader("Referer"));
      threads.add(thread);
      thread.start();
    }

    for (Thread thread : threads) {
      thread.join();
    }

            // If no valid responses arrived...
    if (collections.isEmpty()) {
      if (allResponses.size() == 1 && allResponseCodes.size() == 1) {
        try {
          Iterator<String> PlainResponseCodesIterator = allResponseCodes.iterator();
          Iterator<String> PlainResponsesIterator = allResponses.iterator();
          String responseCode = PlainResponseCodesIterator.next();
          String plainResponse = PlainResponsesIterator.next();
          return Response.ok(new JSONObject(plainResponse).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").status(Integer.parseInt(responseCode)).build();
        } catch (Exception e) {
          e.printStackTrace();
          return Response.status(500).build();
        }
      } else {
        return Response.status(500).build();
      }
    }

            // If just one valid response arrived...
    if (collections.size() == 1) {
      try {
        return Response.ok(new JSONObject(collections.get(0)).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
      } catch (Exception e) {
        e.printStackTrace();
        return Response.status(500).build();
      }
    }

            // In the most complex possible case, retrieve agencies from collections and put all them in a single set
    ArrayList<JSONObject> busPositions = new ArrayList<>();
    for (String collection : collections) {
      try {
        JSONObject jCollection = new JSONObject(collection);
        JSONArray jBusPositions = jCollection.getJSONArray("features");
        for (int i = 0; i < jBusPositions.length(); i++) {
          busPositions.add((JSONObject) jBusPositions.get(i));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

            // Sort by id
    class PositionComparator implements Comparator<JSONObject> {

      @Override
      public int compare(JSONObject obj1, JSONObject obj2) {
        try {
          return (int) Math.round(Math.signum(Integer.parseInt(obj1.getString("id")) - Integer.parseInt(obj2.getString("id"))));
        } catch (Exception e) {
          e.printStackTrace();
          return 0;
        }
      }
    }

    busPositions.sort(new PositionComparator());

            // Remove duplicates 
    HashSet<String> wrk = new HashSet<>();
    ArrayList<JSONObject> cleanedBusPositions = new ArrayList<>();
    for (int i = 0; i < busPositions.size(); i++) {
      try {
        if (wrk.add(busPositions.get(i).getJSONObject("properties").toString(4))) {
          cleanedBusPositions.add(busPositions.get(i));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    busPositions = cleanedBusPositions;

            // Initialize response
    JSONObject response = new JSONObject();
    JSONArray jFeatures = new JSONArray();
    response.put("type", "FeatureCollection");
    response.put("features", jFeatures);

            // Append agencies to response
    for (int i = 0; i < busPositions.size(); i++) {
      try {
        busPositions.get(i).put("id", i + 1);
        jFeatures.put(busPositions.get(i));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

            // Output response
    return Response.ok(response.toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();

  }

        // Submit ratings and comments about a given service
        /*
   @SuppressWarnings("deprecation")
   @Path("/photo")
   @POST
   @Consumes(MediaType.MULTIPART_FORM_DATA)
   public Response putPhoto(
   @QueryParam("serviceUri") String serviceUri,
   @QueryParam("uid") String uid,
   @FormDataParam("file") InputStream uploadedInputStream,
   @FormDataParam("file") FormDataContentDisposition fileDetail
   ) throws Exception {
            
   HashSet<String> responses = new HashSet<>();
   HashSet<String> responseCodes = new HashSet<>();
   MySQLManager store = new MySQLManager();
   String smid = store.getSMIdFromServiceUriCache(serviceUri);
   if(smid == null) {
   List<ServiceMap> sMs = store.getAll();
   for (int i = 0; i < sMs.size(); i++) {
   try {
   final String PSMQUERY = store.getUrlPrefixFromSMid(sMs.get(i).getId()) + "/api/v1/?ssm=yes&serviceUri=" + URLEncoder.encode(serviceUri,"UTF-8");
   ClientConfig config = new ClientConfig();
   Client client = ClientBuilder.newClient(config);
   WebTarget targetServiceMap = client.target(UriBuilder.fromUri(PSMQUERY).build());
   Response r = targetServiceMap.request().get();
   if(r.getStatus() == 200) {
   postPhoto(store.getUrlPrefixFromSMid(sMs.get(i).getId()) + "/api/v1/photo", serviceUri, file, uid, responses, responseCodes);
   store.insertCache(serviceUri, sMs.get(i).getId());
   break;
   }
   }
   catch(Exception e) {
   e.printStackTrace();
   }
   }
   }
   else {
   postPhoto(store.getUrlPrefixFromSMid(store.getUrlPrefixFromSMid(smid) + "/api/v1/photo", serviceUri, file, uid, responses, responseCodes);
   }
   return null;
   }
   */
  // Submit ratings and comments about a given service
  @SuppressWarnings("deprecation")
  @Path("/feedback")
  @GET
  public Response putFeedback(
          @QueryParam("serviceUri") String serviceUri,
          @QueryParam("stars") String stars,
          @QueryParam("comment") String comment,
          @QueryParam("lang") String lang,
          @QueryParam("uid") String uid
  ) throws Exception {

            // Get IP address of client
    String ipAddressRequestCameFrom = requestContext.getRemoteAddr();
    String httpRequestForwardedFor = "";
    if (requestContext.getHeader("X-Forwarded-For") != null && !requestContext.getHeader("X-Forwarded-For").isEmpty()) {
      httpRequestForwardedFor += requestContext.getHeader("X-Forwarded-For") + ",";
    }
    httpRequestForwardedFor += ipAddressRequestCameFrom;

            // Identify the service map(s) where the service having the given serviceUri can be found
    HashSet<String> responses = new HashSet<>();
    HashSet<String> responseCodes = new HashSet<>();
    MySQLManager store = new MySQLManager();

    /* matter of choice: efficiency vs consistency 
     String idOfSMwithUri = store.getSMIdFromServiceUriCache(serviceUri);
            
     if (idOfSMwithUri != null) { // If it is cached

     try {
     final String SMQUERY = store.getUrlPrefixFromSMid(idOfSMwithUri) + "/api/v1/feedback/?ssm=yes"
     + (serviceUri == null || serviceUri.isEmpty() ? "" : "&serviceUri=" + URLEncoder.encode(serviceUri, "UTF-8")) 
     + (stars == null || stars.isEmpty() ? "" : "&stars=" + URLEncoder.encode(stars, "UTF-8")) 
     + (comment == null || comment.isEmpty() ? "" : "&comment=" + URLEncoder.encode(comment, "UTF-8")) 
     + (lang == null || lang.isEmpty() ? "" : "&lang=" + URLEncoder.encode(lang, "UTF-8"))                                            
     + (uid == null || uid.isEmpty() ? "" : "&uid=" + URLEncoder.encode(uid, "UTF-8"));

     ClientConfig config = new ClientConfig();
     Client client = ClientBuilder.newClient(config);
     WebTarget targetServiceMap = client.target(UriBuilder.fromUri(SMQUERY).build());
     Response r = targetServiceMap.request().get();
     return Response.ok(new JSONObject(r.readEntity(String.class)).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").status(r.getStatus()).build();
     }
     catch(Exception e) {}
                
     } 

     List<ServiceMap> sMs = store.getAll("/feedback","json");
     */
    String smid = store.getSMIdFromServiceUriCache(serviceUri);
    if (smid == null) {
      List<ServiceMap> sMs = store.getAll();

      for (int i = 0; i < sMs.size(); i++) {
        try {
          final String PSMQUERY = store.getUrlPrefixFromSMid(sMs.get(i).getId()) + "/api/v1/?ssm=yes&serviceUri=" + URLEncoder.encode(serviceUri, "UTF-8");
          ClientConfig config = new ClientConfig();
          Client client = ClientBuilder.newClient(config);
          WebTarget targetServiceMap = client.target(UriBuilder.fromUri(PSMQUERY).build());

          Response r = null;
          if (requestContext.getHeader("Referer") == null) {
            r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).get();
          } else {
            r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).header("Referer", requestContext.getHeader("Referer")).get();
          }
          if (r.getStatus() == 200) {
            final String SMQUERY = store.getUrlPrefixFromSMid(sMs.get(i).getId()) + "/api/v1/feedback/?ssm=yes"
                    + (serviceUri == null || serviceUri.isEmpty() ? "" : "&serviceUri=" + URLEncoder.encode(serviceUri, "UTF-8"))
                    + (stars == null || stars.isEmpty() ? "" : "&stars=" + URLEncoder.encode(stars, "UTF-8"))
                    + (comment == null || comment.isEmpty() ? "" : "&comment=" + URLEncoder.encode(comment, "UTF-8"))
                    + (lang == null || lang.isEmpty() ? "" : "&lang=" + URLEncoder.encode(lang, "UTF-8"))
                    + (uid == null || uid.isEmpty() ? "" : "&uid=" + URLEncoder.encode(uid, "UTF-8"));
            config = new ClientConfig();
            client = ClientBuilder.newClient(config);
            targetServiceMap = client.target(UriBuilder.fromUri(SMQUERY).build());
            if (requestContext.getHeader("Referer") == null) {
              r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).get();
            } else {
              r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).header("Referer", requestContext.getHeader("Referer")).get();
            }
            responses.add(r.readEntity(String.class));
            responseCodes.add(String.valueOf(r.getStatus()));
            store.insertCache(serviceUri, sMs.get(i).getId());
            break;
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    } else {
      try {
        final String SMQUERY = store.getUrlPrefixFromSMid(smid) + "/api/v1/feedback/?ssm=yes"
                + (serviceUri == null || serviceUri.isEmpty() ? "" : "&serviceUri=" + URLEncoder.encode(serviceUri, "UTF-8"))
                + (stars == null || stars.isEmpty() ? "" : "&stars=" + URLEncoder.encode(stars, "UTF-8"))
                + (comment == null || comment.isEmpty() ? "" : "&comment=" + URLEncoder.encode(comment, "UTF-8"))
                + (lang == null || lang.isEmpty() ? "" : "&lang=" + URLEncoder.encode(lang, "UTF-8"))
                + (uid == null || uid.isEmpty() ? "" : "&uid=" + URLEncoder.encode(uid, "UTF-8"));
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget targetServiceMap = client.target(UriBuilder.fromUri(SMQUERY).build());
        Response r = null;
        if (requestContext.getHeader("Referer") == null) {
          r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).get();
        } else {
          r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).header("Referer", requestContext.getHeader("Referer")).get();
        }
        responses.add(r.readEntity(String.class));
        responseCodes.add(String.valueOf(r.getStatus()));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    if (responses.size() == 1 && responseCodes.size() == 1) {
      try {
        Iterator<String> PlainResponseCodesIterator = responseCodes.iterator();
        Iterator<String> PlainResponsesIterator = responses.iterator();
        String responseCode = PlainResponseCodesIterator.next();
        String plainResponse = PlainResponsesIterator.next();
        return Response.ok(plainResponse, MediaType.TEXT_PLAIN).header("Access-Control-Allow-Origin", "*").status(Integer.parseInt(responseCode)).build();
      } catch (Exception e) {
        e.printStackTrace();
        return Response.status(500).build();
      }
    } else {
      return Response.status(500).build();
    }

  }

  @SuppressWarnings("deprecation")
  @Path("/feedback/last")
  @GET
  public Response getLastFeedbacks(
          @QueryParam("lang") String lang,
          @QueryParam("uid") String uid,
          @QueryParam("requestFrom") String requestFrom
  ) throws Exception {

    List<String> competentServiceMapsPrefix = getCompetentServiceMaps(null, "/api/v1/feedback/last", "json");

            // Otherwise, launch a separate thread for each of the service maps that must be queried, so that they could concurrently populate the lists of bus stops, sensors, and generic services
    LinkedHashSet<String> lastPhotos = new LinkedHashSet<>();
    LinkedHashSet<String> lastComments = new LinkedHashSet<>();
    LinkedHashSet<String> lastStars = new LinkedHashSet<>();
    HashSet<String> plainResponses = new HashSet<>();
    HashSet<String> plainResponseCodes = new HashSet<>();
    ArrayList<Integer> limit = new ArrayList<>();

    RequestMakingAndHashThreadFeedbacks[] threads = new RequestMakingAndHashThreadFeedbacks[competentServiceMapsPrefix.size()];

    for (int i = 0; i < competentServiceMapsPrefix.size(); i++) {

      final String SMQUERY = competentServiceMapsPrefix.get(i) + "/api/v1/feedback/last?ssm=yes"
              + (lang == null || lang.isEmpty() ? "" : "&lang=" + URLEncoder.encode(lang, "UTF-8"))
              + (uid == null || uid.isEmpty() ? "" : "&uid=" + URLEncoder.encode(uid, "UTF-8"))
              + (requestFrom == null || requestFrom.isEmpty() ? "" : "&requestFrom=" + URLEncoder.encode(requestFrom, "UTF-8"));

      String ipAddressRequestCameFrom = requestContext.getRemoteAddr();
      String httpRequestForwardedFor = "";
      if (requestContext.getHeader("X-Forwarded-For") != null && !requestContext.getHeader("X-Forwarded-For").isEmpty()) {
        httpRequestForwardedFor += requestContext.getHeader("X-Forwarded-For") + ",";
      }
      httpRequestForwardedFor += ipAddressRequestCameFrom;

      threads[i] = new RequestMakingAndHashThreadFeedbacks(SMQUERY, i, lastPhotos, lastComments, lastStars, plainResponses, plainResponseCodes, limit, httpRequestForwardedFor, requestContext.getHeader("Referer"));

      threads[i].start();

    }

    for (int i = 0; i < competentServiceMapsPrefix.size(); i++) {
      threads[i].join();
    }

    // If nothing was found querying all the service maps of interest, check to see if all service maps agree about a response, in which case give back that response, otherwise return an empty object. This way, errors due to invalid inputs and similar are forwarded without the need of duplicating the validation.
    if ((lastPhotos.isEmpty() && lastComments.isEmpty() && lastStars.isEmpty())) {
      if (plainResponses.size() == 1 && plainResponseCodes.size() == 1) {
        try {
          Iterator<String> PlainResponseCodesIterator = plainResponses.iterator();
          Iterator<String> PlainResponsesIterator = plainResponseCodes.iterator();
          String responseCode = PlainResponseCodesIterator.next();
          String plainResponse = PlainResponsesIterator.next();
          return Response.ok(new JSONObject(plainResponse).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").status(Integer.parseInt(responseCode)).build();
        } catch (Exception e) {
          e.printStackTrace();
          return Response.status(500).build();
        }
      } else {
        return Response.status(500).build();
      }
    }

            // Initialize the response 
    JSONObject initialJSONresponse = new JSONObject();
    JSONArray jLastPhotos = new JSONArray();
    JSONArray jLastComments = new JSONArray();
    JSONArray jLastStars = new JSONArray();
    initialJSONresponse.put("LastPhotos", jLastPhotos);
    initialJSONresponse.put("LastComments", jLastComments);
    initialJSONresponse.put("LastStars", jLastStars);

            // Append features to response respecting shares of different resources
    class LastContributionComparator implements Comparator<String> {

      @Override
      public int compare(String str1, String str2) {
        try {
          JSONObject obj1 = new JSONObject(str1);
          JSONObject obj2 = new JSONObject(str2);
          return obj2.getString("timestamp").compareTo(obj1.getString("timestamp"));
        } catch (Exception e) {
          e.printStackTrace();
          return 0;
        }
      }
    }

    ArrayList<String> sortedLastPhotos = new ArrayList(lastPhotos);
    sortedLastPhotos.sort(new LastContributionComparator());
    int ctr = 0;
    while (ctr < sortedLastPhotos.size() && ctr < Collections.max(limit)) {
      try {
        JSONObject lastPhoto = new JSONObject(sortedLastPhotos.get(ctr));
        jLastPhotos.put(lastPhoto);
      } catch (Exception e) {
        e.printStackTrace();
      }
      ctr++;
    }

    ArrayList<String> sortedLastComments = new ArrayList(lastComments);
    sortedLastComments.sort(new LastContributionComparator());
    ctr = 0;
    while (ctr < sortedLastComments.size() && ctr < Collections.max(limit)) {
      try {
        JSONObject lastComment = new JSONObject(sortedLastComments.get(ctr));
        jLastComments.put(lastComment);
      } catch (Exception e) {
        e.printStackTrace();
      }
      ctr++;
    }

    ArrayList<String> sortedLastStars = new ArrayList(lastStars);
    sortedLastStars.sort(new LastContributionComparator());
    ctr = 0;
    while (ctr < sortedLastStars.size() && ctr < Collections.max(limit)) {
      try {
        JSONObject lastStar = new JSONObject(sortedLastStars.get(ctr));
        jLastStars.put(lastStar);
      } catch (Exception e) {
        e.printStackTrace();
      }
      ctr++;
    }

            // Return the response object
    return Response.ok(initialJSONresponse.toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();

  }

  @SuppressWarnings("deprecation")
  @Path("/shortestpath")
  @GET
  public Response getShortestPath(
          @QueryParam("source") String source,
          @QueryParam("destination") String destination,
          @QueryParam("routeType") String routeType,
          @QueryParam("startDatetime") String startDatetime,
          @QueryParam("format") String format,
          @QueryParam("uid") String uid,
          @QueryParam("requestFrom") String requestFrom
  ) throws Exception {
    try {
      List<String> competentServiceMaps = getCompetentServiceMaps(source, "/api/v1/shortestpath", "html".equals(format) ? "html" : "json");
      competentServiceMaps.retainAll(getCompetentServiceMaps(destination, "/api/v1/shortestpath", "html".equals(format) ? "html" : "json"));
      if (competentServiceMaps.isEmpty()) {
        return Response.status(400).build();
      }
      HashSet<String> allResponses = new HashSet<>();
      HashSet<String> allCodes = new HashSet<>();
      for (String competentServiceMap : competentServiceMaps) {
        try {
          final String SMQUERY = competentServiceMap + "/api/v1/shortestpath/?ssm=yes"
                  + (source == null || source.isEmpty() ? "" : "&source=" + URLEncoder.encode(source, "UTF-8"))
                  + (destination == null || destination.isEmpty() ? "" : "&destination=" + URLEncoder.encode(destination, "UTF-8"))
                  + (routeType == null || routeType.isEmpty() ? "" : "&routeType=" + URLEncoder.encode(routeType, "UTF-8"))
                  + (startDatetime == null || startDatetime.isEmpty() ? "" : "&startDatetime=" + URLEncoder.encode(startDatetime, "UTF-8"))
                  + (format == null || format.isEmpty() ? "" : "&format=" + URLEncoder.encode(format, "UTF-8"))
                  + (uid == null || uid.isEmpty() ? "" : "&uid=" + URLEncoder.encode(uid, "UTF-8"))
                  + (requestFrom == null || requestFrom.isEmpty() ? "" : "&requestFrom=" + URLEncoder.encode(requestFrom, "UTF-8"));
          ClientConfig config = new ClientConfig();
          Client client = ClientBuilder.newClient(config);
          WebTarget targetServiceMap = client.target(UriBuilder.fromUri(SMQUERY).build());
          String ipAddressRequestCameFrom = requestContext.getRemoteAddr();
          String httpRequestForwardedFor = "";
          if (requestContext.getHeader("X-Forwarded-For") != null && !requestContext.getHeader("X-Forwarded-For").isEmpty()) {
            httpRequestForwardedFor += requestContext.getHeader("X-Forwarded-For") + ",";
          }
          httpRequestForwardedFor += ipAddressRequestCameFrom;
          Response r = null;
          if (requestContext.getHeader("Referer") == null) {
            r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).get();
          } else {
            r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).header("Referer", requestContext.getHeader("Referer")).get();
          }
          String code = String.valueOf(r.getStatus());
          String response = r.readEntity(String.class);
          allCodes.add(code);
          allResponses.add(response);
          if ("200".equals(code)) {
            if ("html".equals(format)) {
              return Response.ok(goThere(competentServiceMap, response), MediaType.TEXT_HTML).status(r.getStatus()).header("Access-Control-Allow-Origin", "*").build();
            } else {
              return Response.ok(new JSONObject(response).toString(4), MediaType.APPLICATION_JSON).status(r.getStatus()).header("Access-Control-Allow-Origin", "*").build();
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

      }
      if (allResponses.size() == 1 && allCodes.size() == 1) {
        try {
          Iterator allResponsesIterator = allResponses.iterator();
          Iterator allCodesIterator = allCodes.iterator();
          return Response.ok("html".equals(format) ? goThere(competentServiceMaps.get(0), allResponsesIterator.next().toString()) : new JSONObject(allResponsesIterator.next().toString()).toString(4), "html".equals(format) ? MediaType.TEXT_HTML : MediaType.APPLICATION_JSON).status(Integer.parseInt(allCodesIterator.next().toString())).build();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      return Response.status(500).build();
    } catch (Exception e) {
      e.printStackTrace();
      return Response.status(500).build();
    }

  }

    @SuppressWarnings("deprecation")
    @Path("/{organization}/wfs")
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
    public Response wfsPost(@PathParam("organization") String organization, String requestXml) {
        long tt = 0, ts = System.currentTimeMillis();
        String t = "";   
        String fromTime = null;
        String toTime = null;
        String pFilter = "";
        if(requestContext.getParameter("fromTime") != null) {
            pFilter = pFilter + "<fes:After><fes:ValueReference>km4c:RT_measuredTime</fes:ValueReference><fes:Literal><gml:TimeInstant gml:id=\"fromTime\"><gml:timePosition>"+requestContext.getParameter("fromTime")+"</gml:timePosition></gml:TimeInstant></fes:Literal></fes:After>";
            fromTime = requestContext.getParameter("fromTime");
        }
        if(requestContext.getParameter("toTime") != null) {
            pFilter = pFilter + "<fes:Before><fes:ValueReference>km4c:RT_measuredTime</fes:ValueReference><fes:Literal><gml:TimeInstant gml:id=\"toTime\"><gml:timePosition>"+requestContext.getParameter("toTime")+"</gml:timePosition></gml:TimeInstant></fes:Literal></fes:Before>";
            toTime = requestContext.getParameter("toTime");
        }
        if(pFilter.isEmpty()) {
            pFilter = null;
        }
        else {
            pFilter = "<fes:Filter xmlns:fes=\"http://www.opengis.org/fes/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\">" + pFilter + "</fes:Filter>";
        }
        if(pFilter != null) {
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder(); 
                Document doc = db.parse(new InputSource(new StringReader(requestXml)));
                Element fesFilter = (Element)doc.getElementsByTagName("fes:Filter").item(0);            
                javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
                javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
                javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(fesFilter);
                javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(new StringWriter());
                transformer.transform(source, result);
                pFilter = result.getWriter().toString();
                if(doc.getDocumentElement().getElementsByTagName("fes:After").getLength() > 0) fromTime = ((Element)doc.getDocumentElement().getElementsByTagName("fes:After").item(0)).getElementsByTagName("gml:timePosition").item(0).getTextContent();
                if(doc.getDocumentElement().getElementsByTagName("fes:Before").getLength() > 0) toTime = ((Element)doc.getDocumentElement().getElementsByTagName("fes:Before").item(0)).getElementsByTagName("gml:timePosition").item(0).getTextContent();        
            } catch(Exception eeee) {}        
        }
        String serverUrl = requestContext.getRequestURL().toString();
        if (requestContext.getRequestURL().toString().indexOf("?") > -1) {
            serverUrl = serverUrl.substring(0, serverUrl.indexOf("?"));
        }
        serverUrl = "https://www.disit.org/superservicemap/api/v1/" + organization + "/wfs";
        String selection = "36.619987291;6.7499552751;47.1153931748;18.4802470232";
        String timezoneSuffix = "+01:00";
        if ("antwerp".equals(organization)) {
            selection = "51.103370011851325;4.163390527343722;51.3947705664635;4.634429345703097";
        }
        if ("helsinki".equals(organization)) {
            selection = "60.139889621242524;24.835881147460896;60.20239233681951;25.013722333984333";
            timezoneSuffix = "+02:00";
        }
        if (requestContext.getParameter("selection") != null) {
          selection = requestContext.getParameter("selection");
        }
        double lowerCornerLong = Double.parseDouble(selection.split(";")[1]);
        double lowerCornerLat = Double.parseDouble(selection.split(";")[0]);
        double upperCornerLong = Double.parseDouble(selection.split(";")[3]);
        double upperCornerLat = Double.parseDouble(selection.split(";")[2]);
        String serverVersion = "2.0.0";
        try {
            javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
            javax.xml.parsers.DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document document = builder.parse(new InputSource(new StringReader(requestXml)));
            Element root = document.getDocumentElement();
            String request = root.getTagName();
            switch (request) {
                case "getcapabilities":
                case "GetCapabilities":
                    return WfsServer.getCapabilities(serverVersion, serverUrl, lowerCornerLong, lowerCornerLat, upperCornerLong, upperCornerLat, t);
                case "DescribeFeatureType":
                    java.util.Vector<String> typenamesVector = new java.util.Vector<>();
                    NodeList typenamesNodelist = root.getElementsByTagName("TypeName");
                    String typeName = null;
                    if (typenamesNodelist.getLength() > 0) {
                        typeName = ((Element) typenamesNodelist.item(0)).getTextContent();
                    }
                    if (typeName == null) {
                        String xx = "";
                        return WfsServer.describeAllFeatureTypes(serverVersion, t, xx);
                    } else {
                        return WfsServer.describeFeatureType(typeName, t);
                    }
                case "ListStoredQueries":
                    return WfsServer.listStoredQueries(serverVersion, serverUrl);          
                case "DescribeStoredQueries":
                    String pStoredQuery_ID = root.getElementsByTagName("StoredQueryId").item(0).getTextContent();
                    if (pStoredQuery_ID != null && !"urn:ogc:def:query:OGC-WFS::GetFeatureById".equals(pStoredQuery_ID)) {
                        throw new WfsException(WfsException.NOT_FOUND, pStoredQuery_ID);
                    }
                    return WfsServer.describeStoredQueries(serverUrl, serverVersion, t);
                case "GetFeature":
                    double[] coords;
                    TimeZone tz = TimeZone.getDefault();
                    java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); 
                    df.setTimeZone(tz);
                    String nowAsISO = df.format(new java.util.Date());
                    NodeList storedQueries = root.getElementsByTagName("StoredQuery");
                    if (storedQueries.getLength() > 0 || requestContext.getParameter("serviceUri") != null) {
                        String pID = null;
                        String pResultType = null;
                        if (storedQueries.getLength() > 0) {
                            Element storedQuery = (Element) root.getElementsByTagName("StoredQuery").item(0);
                            String pStoredquery_ID = storedQuery.getAttribute("id");
                            if (!"urn:ogc:def:query:OGC-WFS::GetFeatureById".equals(pStoredquery_ID)) {
                                throw new WfsException(WfsException.NOT_FOUND, pStoredquery_ID);
                            }
                            pID = storedQuery.getElementsByTagName("Parameter").item(0).getTextContent();
                            if (root.getElementsByTagName("resultType").getLength() > 0) {
                                pResultType = root.getElementsByTagName("resultType").item(0).getTextContent();
                            }
                        } else {
                            pID = requestContext.getParameter("serviceUri");
                        }
                        String httpRequestForwardedFor = "";
                        if (requestContext.getHeader("X-Forwarded-For") != null && !requestContext.getHeader("X-Forwarded-For").isEmpty()) {
                            httpRequestForwardedFor += requestContext.getHeader("X-Forwarded-For") + ",";
                        }
                        httpRequestForwardedFor += requestContext.getRemoteAddr();
                        Response r = getServiceURI(pID, "json", "/api/v1", "/api/v1/?ssm=yes&realtime=true&serviceUri=" + URLEncoder.encode(pID, "UTF-8"), httpRequestForwardedFor, null);
                        String jsonStr = r.getEntity().toString();
                        WfsServer.getFeature(requestContext, serverUrl, serverVersion, t, timezoneSuffix, selection, organization, tt, pFilter, ts, jsonStr, nowAsISO, pResultType, r, pID);
                    } else {
                        NodeList wfsQueries = root.getElementsByTagName("wfs:Query");
                        if (wfsQueries.getLength() == 0) {
                            wfsQueries = root.getElementsByTagName("Query");
                        }
                        Element wfsQuery = (Element) wfsQueries.item(0);
                        typeName = wfsQuery.getAttribute("typeNames");
                        String pPropertyName = null;
                        NodeList propertyNames = wfsQuery.getElementsByTagName("wfs:PropertyName");
                        if (propertyNames.getLength() > 0) {
                            pPropertyName = "";
                            for (int p = 0; p < propertyNames.getLength(); p++) {
                                Element propertyName = (Element) propertyNames.item(p);
                                pPropertyName += propertyName.getTextContent() + ",";
                            }
                        }
                        if (requestContext.getParameter("propertyname") != null) {
                            pPropertyName = requestContext.getParameter("propertyname");
                        }
                        boolean areDetailsRequired = false;
                        if (pPropertyName == null) {
                            areDetailsRequired = true;
                        } else {
                            String[] propNameArray = pPropertyName.split(",");
                            for (int p = 0; p < propNameArray.length; p++) {
                                if (!("km4c:serviceUri".equals(propNameArray[p]) || "km4c:name".equals(propNameArray[p]) || "km4c:typeLabel".equals(propNameArray[p]) || "km4c:geometry".equals(propNameArray[p]))) {
                                    areDetailsRequired = true;
                                }
                            }
                        }
                        List<String> competentServiceMapsPrefix = getCompetentServiceMaps(selection, "/api/v1", "json");
                        String categories = null;
                        if (typeName != null) {
                            categories = typeName.substring(5);
                        }
                        if (requestContext.getAttribute("categories") != null) {
                            categories = requestContext.getParameter("categories");
                        }
                        String[] serviceTypeNames = {"km4c:Service", "km4c:Accommodation", "km4c:Advertising", "km4c:AgricultureAndLivestock", "km4c:CivilAndEdilEngineering",
                            "km4c:CulturalActivity", "km4c:EducationAndResearch", "km4c:Emergency", "km4c:Entertainment", "km4c:Environment",
                            "km4c:FinancialService", "km4c:GovernmentOffice", "km4c:HealthCare", "km4c:IndustryAndManufacturing", "km4c:IoTSensor", "km4c:MiningAndQuarring",
                            "km4c:ShoppingAndService", "km4c:TourismService", "km4c:TransferServiceAndRenting", "km4c:UtilitiesAndSupply", "km4c:Wholesale",
                            "km4c:WineAndFood", "km4c:BusStop", "km4c:CarParkSensor", "km4c:Noise_level_sensor", "km4c:SensorSite", "km4c:Weather_sensor",
                            "km4c:Bike_rack", "km4c:" + t + "Air_quality_monitoring_station", "km4c:First_aid", "km4c:Charging_stations", "km4c:Fuel_station", "km4c:Monitoring_camera", "km4c:People_counter", "km4c:Pollen_monitoring_station", "km4c:Irrigator", "km4c:Smart_waste_container",
                            "km4c:Ferry_stop", "km4c:IoTActuator", "km4c:Underpass"};
                        if (Arrays.asList(serviceTypeNames).contains(typeName)) {
                            String queryId = null; String search = null; String text = null; String maxDists = null;
                            String maxResults = null; String lang = null; String uid = null; String requestServiceUri = null; String accessToken = null;
                            if (requestContext.getParameter("queryId") != null) queryId = requestContext.getParameter("queryId");                            
                            if (requestContext.getParameter("search") != null) search = requestContext.getParameter("search");
                            if (requestContext.getParameter("text") != null) text = requestContext.getParameter("text");
                            if (requestContext.getParameter("maxDists") != null) maxDists = requestContext.getParameter("maxDists");
                            if (requestContext.getParameter("maxResults") != null) maxResults = requestContext.getParameter("maxResults");
                            if (requestContext.getParameter("lang") != null) lang = requestContext.getParameter("lang");
                            if (requestContext.getParameter("uid") != null) uid = requestContext.getParameter("uid");
                            if (requestContext.getParameter("accessToken") != null) accessToken = requestContext.getParameter("accessToken");
                            long tstart1 = System.currentTimeMillis();
                            Response services = getServices(selection, queryId, search, categories, text, maxDists, maxResults, lang, null, uid, "json", null, null, null, requestServiceUri, "true", "WFS", null, null, null, null, null, null, "true", accessToken, null);
                            String jsonStr = services.getEntity().toString();
                            long tend1 = System.currentTimeMillis();
                            JSONObject jsonObj = new JSONObject(jsonStr);
                            JSONObject targetObj = null;
                            if ("km4c:BusStop".equals(typeName)) targetObj = jsonObj.getJSONObject("BusStops");
                            else if ("km4c:SensorSite".equals(typeName)) targetObj = jsonObj.getJSONObject("SensorSites");
                            else targetObj = jsonObj.getJSONObject("Services");
                            JSONArray features = targetObj.getJSONArray("features");
                            int fullCount = features.length();
                            if (targetObj.has("fullCount")) fullCount = targetObj.getInt("fullCount");
                            String response = "<?xml version=\"1.0\" ?>\n" + "<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"" + String.valueOf(fullCount) + "\" numberReturned=\"" + String.valueOf(features.length()) + "\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd " + serverUrl + "?service=WFS&amp;version=" + serverVersion + "&amp;request=DescribeFeatureType&amp;typeName=" + typeName.replaceAll(":", "%3") + " http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\">";
                            for (int f = 0; f < features.length(); f++) {
                                JSONObject feature = features.getJSONObject(f);
                                String serviceUri; 
                                try {
                                    serviceUri = feature.getJSONObject("properties").getString("serviceUri");
                                    URI uri = URI.create(serviceUri);
                                } catch (Exception ignore) {
                                    serviceUri = null;
                                }
                                if (serviceUri == null) {
                                    throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceUri");
                                }
                                if (areDetailsRequired) {
                                    long ts1=System.currentTimeMillis();
                                    try {
                                        Response detailsResponse = wfs(organization, "GetFeature", null, null, "WFS", null, null, "2.0.0", null, null, null, null, null, null, null, null, null, "urn:ogc:def:query:OGC-WFS::GetFeatureById", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, serviceUri, null, null, "2.0.0", null, null, null, null, null, pPropertyName, null, null, pFilter, null, null);
                                        String detailsStringResponse = detailsResponse.getEntity().toString();
                                        long te1=System.currentTimeMillis();
                                        tt += te1-ts1;
                                        System.out.println("PERF WFS "+(te1-ts1)+"ms "+serviceUri);
                                        response = response + detailsStringResponse.substring(detailsStringResponse.indexOf("<wfs:member>"), detailsStringResponse.indexOf("</wfs:FeatureCollection>"));
                                    } catch(Exception g) {}
                                } else {
                                    String name = null;     double latitude = -1;   double longitude = -1;  double distance = -1;   String city = null;
                                    String cap = null;      String province = null; String address = null;  String civic = null;    String phone = null;
                                    String fax = null;      String website;         String email = null;    String note = null;     String description = null;
                                    String linkDBpedia;     double avgStars = -1;   int starsCount = -1;    String typeLabel = null;String serviceType = null;
                                    String agency = null;   String agencyUri = null;
                                    try {
                                        serviceUri = feature.getJSONObject("properties").getString("serviceUri");
                                        URI uri = URI.create(serviceUri);
                                    } catch (Exception ignore) {
                                        serviceUri = null;
                                    }
                                    try { name = feature.getJSONObject("properties").getString("name"); } catch (Exception ignore) {}
                                    try { typeLabel = feature.getJSONObject("properties").getString("typeLabel"); } catch (Exception ignore) {}
                                    try { serviceType = feature.getJSONObject("properties").getString("serviceType"); } catch (Exception ignore) {}
                                    try { longitude = feature.getJSONObject("geometry").getJSONArray("coordinates").getDouble(0); } catch (Exception ignore) {}
                                    try { latitude = feature.getJSONObject("geometry").getJSONArray("coordinates").getDouble(1); } catch (Exception ignore) {}
                                    try { distance = feature.getJSONObject("properties").getDouble("distance"); } catch (Exception ignore) {}
                                    try { city = feature.getJSONObject("properties").getString("city"); } catch (Exception ignore) {}
                                    try { cap = feature.getJSONObject("properties").getString("cap"); } catch (Exception ignore) {}
                                    try { province = feature.getJSONObject("properties").getString("province"); } catch (Exception ignore) {}
                                    try { address = feature.getJSONObject("properties").getString("address"); } catch (Exception ignore) {}
                                    try { civic = feature.getJSONObject("properties").getString("civic"); } catch (Exception ignore) {}
                                    try { phone = feature.getJSONObject("properties").getString("phone"); } catch (Exception ignore) {}
                                    try { fax = feature.getJSONObject("properties").getString("fax"); } catch (Exception ignore) {}
                                    try {
                                        website = feature.getJSONObject("properties").getString("website");
                                        URI uri = URI.create(website);
                                    } catch (Exception ignore) {
                                        website = null;
                                    }
                                    try { email = feature.getJSONObject("properties").getString("email"); } catch (Exception ignore) {}
                                    try { note = feature.getJSONObject("properties").getString("note"); } catch (Exception ignore) {}
                                    try { description = feature.getJSONObject("properties").getString("description"); } catch (Exception ignore) {}
                                    try {
                                        linkDBpedia = feature.getJSONObject("properties").getString("linkDBpedia");
                                        URI uri = URI.create(linkDBpedia);
                                    } catch (Exception ignore) {
                                        linkDBpedia = null;
                                    }
                                    try { avgStars = feature.getJSONObject("properties").getDouble("avgStars"); } catch (Exception ignore) {}
                                    try { starsCount = feature.getJSONObject("properties").getInt("starsCount"); } catch (Exception ignore) {}
                                    try { agency = feature.getJSONObject("properties").getString("agency"); } catch (Exception ignore) {}
                                    try {
                                        agencyUri = feature.getJSONObject("properties").getString("agencyUri");
                                        URI uri = URI.create(agencyUri);
                                    } catch (Exception ignore) {
                                        agencyUri = null;
                                    }
                                    if(serviceUri == null) throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceUri"); 
                                    if(serviceType == null) throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceType");
                                    response = response + "<wfs:member>"
                                        + "<" + typeName + " gml:id=\"" + serviceUri + "\">\n"
                                        + (serviceUri != null && (!"null".equals(serviceUri)) && !serviceUri.isEmpty() ? "   <km4c:serviceUri>" + serviceUri.replaceAll("&", "&amp;") + "</km4c:serviceUri>" : "")
                                        + (name != null && (!"null".equals(name)) && !name.isEmpty() ? "   <km4c:name>" + name.replaceAll("&", "&amp;") + "</km4c:name>" : "")
                                        + (typeLabel != null && (!"null".equals(typeLabel)) && !typeLabel.isEmpty() ? "   <km4c:typeLabel>" + typeLabel + "</km4c:typeLabel>" : "")
                                        + (latitude != -1 && longitude != -1 ? "<km4c:geometry><gml:Point srsDimension=\"2\" srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\"><gml:pos>" + String.valueOf(longitude) + " " + String.valueOf(latitude) + "</gml:pos></gml:Point></km4c:geometry>" : "")
                                        + (isRequested("km4c:city", pPropertyName) && city != null && (!"null".equals(city)) && !city.isEmpty() ? "   <km4c:city>" + city + "</km4c:city>" : "")
                                        + (isRequested("km4c:cap", pPropertyName) && cap != null && (!"null".equals(cap)) && !cap.isEmpty() ? "   <km4c:cap>" + cap + "</km4c:cap>" : "")
                                        + (isRequested("km4c:province", pPropertyName) && province != null && (!"null".equals(province)) && !province.isEmpty() ? "   <km4c:province>" + province + "</km4c:province>" : "")
                                        + (isRequested("km4c:address", pPropertyName) && address != null && (!"null".equals(address)) && !address.isEmpty() ? "   <km4c:address>" + address + "</km4c:address>" : "")
                                        + (isRequested("km4c:civic", pPropertyName) && civic != null && (!"null".equals(civic)) && !civic.isEmpty() ? "   <km4c:civic>" + civic + "</km4c:civic>" : "")
                                        + (isRequested("km4c:phone", pPropertyName) && phone != null && (!"null".equals(phone)) && !phone.isEmpty() ? "   <km4c:phone>" + phone + "</km4c:phone>" : "")
                                        + (isRequested("km4c:fax", pPropertyName) && fax != null && (!"null".equals(fax)) && !fax.isEmpty() ? "   <km4c:fax>" + fax + "</km4c:fax>" : "")
                                        + (isRequested("km4c:website", pPropertyName) && website != null && (!"null".equals(website)) && !website.isEmpty() ? "   <km4c:website>" + website.replaceAll("&", "&amp;") + "</km4c:website>" : "")
                                        + (isRequested("km4c:email", pPropertyName) && email != null && (!"null".equals(email)) && !email.isEmpty() ? "   <km4c:email>" + email + "</km4c:email>" : "")
                                        + (isRequested("km4c:note", pPropertyName) && note != null && (!"null".equals(note)) && !note.isEmpty() ? "   <km4c:note>" + note + "</km4c:note>" : "")
                                        + (isRequested("km4c:linkDBpedia", pPropertyName) && linkDBpedia != null && (!"null".equals(linkDBpedia)) && !linkDBpedia.isEmpty() ? "   <km4c:linkDBpedia>" + linkDBpedia.replaceAll("&", "&amp;") + "</km4c:linkDBpedia>" : "")
                                        + (isRequested("km4c:avgStars", pPropertyName) && avgStars > 0 ? "   <km4c:avgStars>" + String.valueOf(avgStars) + "</km4c:avgStars>" : "")
                                        + (isRequested("km4c:starsCount", pPropertyName) && starsCount > 0 ? "   <km4c:starsCount>" + String.valueOf(starsCount) + "</km4c:starsCount>" : "")
                                        + (isRequested("km4c:agency", pPropertyName) && agency != null && (!"null".equals(agency)) && !agency.isEmpty() ? "   <km4c:agency>" + agency + "</km4c:agency>" : "")
                                        + (isRequested("km4c:agencyUri", pPropertyName) && agencyUri != null && (!"null".equals(agencyUri)) && !agencyUri.isEmpty() ? "   <km4c:agencyUri>" + agencyUri.replaceAll("&", "&amp;") + "</km4c:agencyUri>" : "")
                                        + "</" + typeName + "></wfs:member>";
                                }
                            }
                            response = response + "</wfs:FeatureCollection>";
                            System.out.println("PERF WFS tot:"+(System.currentTimeMillis()-ts)+"ms search:"+(tend1-tstart1)+"ms details:"+tt+"ms");
                            return Response.ok(response).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/gml+xml;version=3.2").header("Cache-Control", "no-cache").status(200).build();
                        } else if ("km4c:Event".equals(typeName)) { 
                            String range = null;    String maxResults = null;   String uid = null;  String requestFrom = null;
                            if (requestContext.getParameter("range") != null) range = requestContext.getParameter("range");                            
                            if (requestContext.getParameter("maxResults") != null) maxResults = requestContext.getParameter("maxResults");                            
                            if (requestContext.getParameter("uid") != null) uid = requestContext.getParameter("uid");                            
                            if (requestContext.getParameter("requestFrom") != null) requestFrom = requestContext.getParameter("requestFrom");
                            Response services = getEvents(range, selection, null, maxResults, uid, requestFrom);
                            String jsonStr = services.getEntity().toString();
                            JSONObject jsonObj = new JSONObject(jsonStr);
                            JSONObject targetObj = jsonObj.getJSONObject("Event");
                            JSONArray features = targetObj.getJSONArray("features");
                            int fullCount = features.length();
                            if (targetObj.has("fullCount")) {
                                fullCount = targetObj.getInt("fullCount");
                            }
                            String response = "<?xml version=\"1.0\" ?>\n<wfs:FeatureCollection xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:km4c=\"http://www.disit.org/km4city/schema#\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:wfs=\"http://www.opengis.net/wfs/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" numberMatched=\"" + String.valueOf(fullCount) + "\" numberReturned=\"" + String.valueOf(features.length()) + "\" timeStamp=\"" + nowAsISO + "\" xsi:schemaLocation=\"http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd " + serverUrl + "?service=WFS&amp;version=" + serverVersion + "&amp;request=DescribeFeatureType&amp;typeName=km4c%3Event http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd\">";
                            for (int f = 0; f < features.length(); f++) {
                                JSONObject feature = features.getJSONObject(f);
                                String serviceUri;
                                try {
                                    serviceUri = feature.getJSONObject("properties").getString("serviceUri");
                                    URI uri = URI.create(serviceUri);
                                } catch (Exception ignore) {
                                    serviceUri = null;
                                }
                                if (areDetailsRequired) {
                                    try {
                                        Response detailsResponse = wfs(organization, "GetFeature", null, null, "WFS", null, null, "2.0.0", null, null, null, null, null, null, null, null, null, "urn:ogc:def:query:OGC-WFS::GetFeatureById", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, serviceUri, null, null, "2.0.0", null, null, null, null, null, pPropertyName, null, null, pFilter, null, null);
                                        String detailsStringResponse = detailsResponse.getEntity().toString();
                                        response = response + detailsStringResponse.substring(detailsStringResponse.indexOf("<wfs:member>"), detailsStringResponse.indexOf("</wfs:FeatureCollection>"));
                                    } catch(Exception g) {}
                                } else {
                                    String name = null;     double latitude = -1;   double longitude = -1;          double distance = -1;   String city = null;
                                    String cap = null;      String province = null; String address = null;          String civic = null;    String phone = null;
                                    String fax = null;      String website;         String email = null;            String note = null;     String description = null;
                                    String linkDBpedia;     double avgStars = -1;   int starsCount = -1;            String startDate = null;String startTime = null;
                                    String endDate = null;  String endTime = null;  String eventCategory = null;    String place = null;    String freeEvent = null;
                                    String price = null;
                                    try { name = feature.getJSONObject("properties").getString("name"); } catch (Exception ignore) {}
                                    try { longitude = feature.getJSONObject("geometry").getJSONArray("coordinates").getDouble(0); } catch (Exception ignore) {}
                                    try { latitude = feature.getJSONObject("geometry").getJSONArray("coordinates").getDouble(1); } catch (Exception ignore) {}
                                    try { distance = feature.getJSONObject("properties").getDouble("distance"); } catch (Exception ignore) {}
                                    try { city = feature.getJSONObject("properties").getString("city"); } catch (Exception ignore) {}
                                    try { cap = feature.getJSONObject("properties").getString("cap"); } catch (Exception ignore) {}
                                    try { province = feature.getJSONObject("properties").getString("province"); } catch (Exception ignore) {}
                                    try { address = feature.getJSONObject("properties").getString("address"); } catch (Exception ignore) {}
                                    try { civic = feature.getJSONObject("properties").getString("civic"); } catch (Exception ignore) {}
                                    try { phone = feature.getJSONObject("properties").getString("phone"); } catch (Exception ignore) {}
                                    try { fax = feature.getJSONObject("properties").getString("fax"); } catch (Exception ignore) {}
                                    try {
                                        website = feature.getJSONObject("properties").getString("website");
                                        URI uri = URI.create(website);
                                    } catch (Exception ignore) {
                                        website = null;
                                    }
                                    try { email = feature.getJSONObject("properties").getString("email"); } catch (Exception ignore) {}
                                    try { note = feature.getJSONObject("properties").getString("note"); } catch (Exception ignore) {}
                                    try { description = feature.getJSONObject("properties").getString("description"); } catch (Exception ignore) {}
                                    try {
                                        linkDBpedia = feature.getJSONObject("properties").getString("linkDBpedia");
                                        URI uri = URI.create(linkDBpedia);
                                    } catch (Exception ignore) {
                                        linkDBpedia = null;
                                    }
                                    try { avgStars = feature.getJSONObject("properties").getDouble("avgStars"); } catch (Exception ignore) {}
                                    try { starsCount = feature.getJSONObject("properties").getInt("starsCount"); } catch (Exception ignore) {}
                                    try { startDate = feature.getJSONObject("properties").getString("startDate"); } catch (Exception ignore) {}
                                    try { startTime = feature.getJSONObject("properties").getString("startTime"); } catch (Exception ignore) {}
                                    try { endDate = feature.getJSONObject("properties").getString("endDate"); } catch (Exception ignore) {}
                                    try { endTime = feature.getJSONObject("properties").getString("endTime"); } catch (Exception ignore) {}
                                    try { eventCategory = feature.getJSONObject("properties").getString("eventCategory"); } catch (Exception ignore) {}
                                    try { place = feature.getJSONObject("properties").getString("place"); } catch (Exception ignore) {}
                                    try { freeEvent = feature.getJSONObject("properties").getString("freeEvent"); } catch (Exception ignore) {}
                                    try { price = feature.getJSONObject("properties").getString("price"); } catch (Exception ignore) {}
                                    if (serviceUri == null) throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, "serviceUri");
                                    response = response + "<wfs:member>"
                                        + "<km4c:Event gml:id=\"" + serviceUri + "\" >\n"
                                        + (serviceUri != null && (!"null".equals(serviceUri)) && !serviceUri.isEmpty() ? "   <km4c:serviceUri>" + serviceUri.replaceAll("&", "&amp;") + "</km4c:serviceUri>" : "")
                                        + (name != null && (!"null".equals(name)) && !name.isEmpty() ? "   <km4c:name>" + name.replaceAll("&", "&amp;") + "</km4c:name>" : "")
                                        + (latitude != -1 && longitude != -1 ? "<km4c:geometry><gml:Point srsDimension=\"2\" srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\"><gml:pos>" + String.valueOf(longitude) + " " + String.valueOf(latitude) + "</gml:pos></gml:Point></km4c:geometry>" : "")
                                        + (isRequested("km4c:city", pPropertyName) && city != null && (!"null".equals(city)) && !city.isEmpty() ? "   <km4c:city>" + city + "</km4c:city>" : "")
                                        + (isRequested("km4c:cap", pPropertyName) && cap != null && (!"null".equals(cap)) && !cap.isEmpty() ? "   <km4c:cap>" + cap + "</km4c:cap>" : "")
                                        + (isRequested("km4c:province", pPropertyName) && province != null && (!"null".equals(province)) && !province.isEmpty() ? "   <km4c:province>" + province + "</km4c:province>" : "")
                                        + (isRequested("km4c:address", pPropertyName) && address != null && (!"null".equals(address)) && !address.isEmpty() ? "   <km4c:address>" + address + "</km4c:address>" : "")
                                        + (isRequested("km4c:civic", pPropertyName) && civic != null && (!"null".equals(civic)) && !civic.isEmpty() ? "   <km4c:civic>" + civic + "</km4c:civic>" : "")
                                        + (isRequested("km4c:phone", pPropertyName) && phone != null && (!"null".equals(phone)) && !phone.isEmpty() ? "   <km4c:phone>" + phone + "</km4c:phone>" : "")
                                        + (isRequested("km4c:fax", pPropertyName) && fax != null && (!"null".equals(fax)) && !fax.isEmpty() ? "   <km4c:fax>" + fax + "</km4c:fax>" : "")
                                        + (isRequested("km4c:website", pPropertyName) && website != null && (!"null".equals(website)) && !website.isEmpty() ? "   <km4c:website>" + website.replaceAll("&", "&amp;") + "</km4c:website>" : "")
                                        + (isRequested("km4c:email", pPropertyName) && email != null && (!"null".equals(email)) && !email.isEmpty() ? "   <km4c:email>" + email + "</km4c:email>" : "")
                                        + (isRequested("km4c:note", pPropertyName) && note != null && (!"null".equals(note)) && !note.isEmpty() ? "   <km4c:note>" + note + "</km4c:note>" : "")
                                        + (isRequested("km4c:linkDBpedia", pPropertyName) && linkDBpedia != null && (!"null".equals(linkDBpedia)) && !linkDBpedia.isEmpty() ? "   <km4c:linkDBpedia>" + linkDBpedia.replaceAll("&", "&amp;") + "</km4c:linkDBpedia>" : "")
                                        + (isRequested("km4c:avgStars", pPropertyName) && avgStars > 0 ? "   <km4c:avgStars>" + String.valueOf(avgStars) + "</km4c:avgStars>" : "")
                                        + (isRequested("km4c:starsCount", pPropertyName) && starsCount > 0 ? "   <km4c:starsCount>" + String.valueOf(starsCount) + "</km4c:starsCount>" : "")
                                        + (isRequested("km4c:startDate", pPropertyName) && startDate != null && (!"null".equals(startDate)) && !startDate.isEmpty() ? "   <km4c:startDate>" + String.valueOf(startDate) + "</km4c:startDate>" : "")
                                        + (isRequested("km4c:startTime", pPropertyName) && startTime != null && (!"null".equals(startTime)) && !startTime.isEmpty() ? "   <km4c:startTime>" + String.valueOf(startTime) + "</km4c:startTime>" : "")
                                        + (isRequested("km4c:endDate", pPropertyName) && endDate != null && (!"null".equals(endDate)) && !endDate.isEmpty() ? "   <km4c:endDate>" + String.valueOf(endDate) + "</km4c:endDate>" : "")
                                        + (isRequested("km4c:endTime", pPropertyName) && endTime != null && (!"null".equals(endTime)) && !endTime.isEmpty() ? "   <km4c:endTime>" + String.valueOf(endTime) + "</km4c:endTime>" : "")
                                        + (isRequested("km4c:eventCategory", pPropertyName) && eventCategory != null && (!"null".equals(eventCategory)) && !eventCategory.isEmpty() ? "   <km4c:eventCategory>" + String.valueOf(eventCategory) + "</km4c:eventCategory>" : "")
                                        + (isRequested("km4c:place", pPropertyName) && place != null && (!"null".equals(place)) && !place.isEmpty() ? "   <km4c:place>" + String.valueOf(place) + "</km4c:place>" : "")
                                        + (isRequested("km4c:freeEvent", pPropertyName) && freeEvent != null && (!"null".equals(freeEvent)) && !freeEvent.isEmpty() ? "   <km4c:freeEvent>" + String.valueOf(freeEvent) + "</km4c:freeEvent>" : "")
                                        + (isRequested("km4c:price", pPropertyName) && price != null && (!"null".equals(price)) && !price.isEmpty() ? "   <km4c:price>" + String.valueOf(price) + "</km4c:price>" : "")
                                        + "</km4c:Event>"
                                        + "</wfs:member>";
                                }
                            }
                            response = response + "</wfs:FeatureCollection>";
                            return Response.ok(response).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/gml+xml;version=3.2").header("Cache-Control", "no-cache").status(200).build();
                        } else {
                            throw new WfsException(WfsException.INVALID_PARAMETER_VALUE, "typeNames");
                        }
                    }
                default:
                    throw new WfsException(WfsException.OPERATION_NOT_SUPPORTED, request);
            }
        } catch (WfsException wfse) {
            String template = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<ExceptionReport xmlns=\"http://www.opengis.net/ows/1.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.opengis.net/ows/1.1 http://schemas.opengis.net/ows/1.1.0/owsAll.xsd\" version=\"" + serverVersion + "\">\n"
                + (wfse.getLocator() != null ? "<Exception exceptionCode=\"%s\" locator=\"%s\"/>\n" : "<Exception exceptionCode=\"%s\" />\n")
                + "</ExceptionReport>";
            String xmlResponse = wfse.getLocator() != null ? String.format(template, wfse.getCode(), wfse.getLocator().replaceAll("\"", "\\\"")) : String.format(template, wfse.getCode());
            if (WfsException.NOT_FOUND.equals(wfse.getCode())) {
                return Response.status(404).entity(xmlResponse).header("Content-Type", "application/gml+xml;version=3.2").header("Cache-Control", "no-cache").build();
            } else if (WfsException.OPERATION_PROCESSING_FAILED.equals(wfse.getCode())) {
                return Response.status(500).entity(xmlResponse).header("Content-Type", "application/gml+xml;version=3.2").header("Cache-Control", "no-cache").build();
            } else {
                return Response.status(400).entity(xmlResponse).header("Content-Type", "application/gml+xml;version=3.2").header("Cache-Control", "no-cache").build();
            }
        } catch (Exception e) {
            String xmlResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<ExceptionReport xmlns=\"http://www.opengis.net/ows/1.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.opengis.net/ows/1.1 http://schemas.opengis.net/ows/1.1.0/owsAll.xsd\" version=\"" + serverVersion + "\">\n"
                + "<Exception exceptionCode=\"NoApplicableCode\" locator=\"" + (e.getMessage() != null ? e.getMessage().replace("\"", "&quot;") : "") + "\" />"
                + "</ExceptionReport>";
            return Response.status(400).entity(xmlResponse).header("Content-Type", "application/gml+xml;version=3.2").header("Cache-Control", "no-cache").build();
        }
    }

    @SuppressWarnings("deprecation")
    @Path("/{organization}/wfs")
    @GET
    public Response wfs(    
        @PathParam("organization") String organization,     @QueryParam("request") String request,                  @QueryParam("Request") String Request,
        @QueryParam("REQUEST") String REQUEST,              @QueryParam("service") String service,                  @QueryParam("Service") String Service,
        @QueryParam("SERVICE") String SERVICE,              @QueryParam("AcceptVersions") String AcceptVersions,    @QueryParam("acceptversions") String acceptversions,
        @QueryParam("ACCEPTVERSIONS") String ACCEPTVERSIONS,@QueryParam("TypeName") String TypeName,                @QueryParam("typename") String typename,
        @QueryParam("TYPENAME") String TYPENAME,            @QueryParam("typeName") String typeName,                @QueryParam("OutputFormat") String OutputFormat,
        @QueryParam("outputformat") String outputformat,    @QueryParam("OUTPUTFORMAT") String OUTPUTFORMAT,        @QueryParam("storedquery_id") String storedquery_id,
        @QueryParam("StoredQuery_Id") String StoredQuery_Id,@QueryParam("StoredQuery_ID") String StoredQuery_ID,    @QueryParam("STOREDQUERY_ID") String STOREDQUERY_ID,
        @QueryParam("StartIndex") String StartIndex,        @QueryParam("startindex") String startindex,            @QueryParam("STARTINDEX") String STARTINDEX,
        @QueryParam("Count") String Count,                  @QueryParam("count") String count,                      @QueryParam("COUNT") String COUNT,
        @QueryParam("ResultType") String ResultType,        @QueryParam("resulttype") String resulttype,            @QueryParam("RESULTTYPE") String RESULTTYPE,
        @QueryParam("Resolve") String Resolve,              @QueryParam("resolve") String resolve,                  @QueryParam("RESOLVE") String RESOLVE,
        @QueryParam("ResolveDepth") String ResolveDepth,    @QueryParam("resolvedepth") String resolvedepth,        @QueryParam("RESOLVEDEPTH") String RESOLVEDEPTH,
        @QueryParam("ResolveTimeout") String ResolveTimeout,@QueryParam("resolvetimeout") String resolvetimeout,    @QueryParam("RESOLVETIMEOUT") String RESOLVETIMEOUT,
        @QueryParam("id") String id,                        @QueryParam("ID") String ID,                            @QueryParam("Id") String Id,
        @QueryParam("version") String pversion,             @QueryParam("VERSION") String pVERSION,                 @QueryParam("TypeNames") String TypeNames,
        @QueryParam("typenames") String typenames,          @QueryParam("TYPENAMES") String TYPENAMES,              @QueryParam("typeNames") String typeNames,
        @QueryParam("propertyname") String propertyname,    @QueryParam("PropertyName") String PropertyName,        @QueryParam("PROPERTYNAME") String PROPERTYNAME,
        @QueryParam("filter") String filter,                @QueryParam("Filter") String Filter,                    @QueryParam("FILTER") String FILTER          
    ) throws Exception {
        String pFilter = "";
        if(requestContext.getParameter("fromTime") != null) pFilter = pFilter + "<fes:After><fes:ValueReference>km4c:RT_measuredTime</fes:ValueReference><fes:Literal><gml:TimeInstant gml:id=\"fromTime\"><gml:timePosition>"+requestContext.getParameter("fromTime")+"</gml:timePosition></gml:TimeInstant></fes:Literal></fes:After>";    
        if(requestContext.getParameter("toTime") != null) pFilter = pFilter + "<fes:Before><fes:ValueReference>km4c:RT_measuredTime</fes:ValueReference><fes:Literal><gml:TimeInstant gml:id=\"fromTime\"><gml:timePosition>"+requestContext.getParameter("toTime")+"</gml:timePosition></gml:TimeInstant></fes:Literal></fes:Before>";
        if(pFilter.isEmpty()) pFilter = null;    
        else pFilter = "<fes:Filter xmlns:fes=\"http://www.opengis.org/fes/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\">" + pFilter + "</fes:Filter>";
        if(pFilter == null) pFilter = filter;
        if(pFilter == null) pFilter = Filter;
        if(pFilter == null) pFilter = FILTER;       
        String fromTime = null;
        String toTime = null;
        if(pFilter != null) {
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder(); 
                Document doc = db.parse(new InputSource(new StringReader(pFilter)));
                if(doc.getDocumentElement().getElementsByTagName("fes:After").getLength() > 0) fromTime = ((Element)doc.getDocumentElement().getElementsByTagName("fes:After").item(0)).getElementsByTagName("gml:timePosition").item(0).getTextContent();
                if(doc.getDocumentElement().getElementsByTagName("fes:Before").getLength() > 0) toTime = ((Element)doc.getDocumentElement().getElementsByTagName("fes:Before").item(0)).getElementsByTagName("gml:timePosition").item(0).getTextContent();        
            } catch(Exception eeee) {}        
        }    
        String t = ""; // String.valueOf(Calendar.getInstance().getTimeInMillis()/10000) + "_";
        String pPropertyName = propertyname;
        if (pPropertyName == null) pPropertyName = PropertyName;
        if (pPropertyName == null) pPropertyName = PROPERTYNAME;
        String selection = "36.619987291;6.7499552751;47.1153931748;18.4802470232";
        String timezoneSuffix = "+01:00";
        if ("antwerp".equals(organization)) selection = "51.103370011851325;4.163390527343722;51.3947705664635;4.634429345703097";
        if ("helsinki".equals(organization)) {
            selection = "60.139889621242524;24.835881147460896;60.20239233681951;25.013722333984333";
            timezoneSuffix = "+02:00";
        }
        if (requestContext.getParameter("selection") != null) selection = requestContext.getParameter("selection");
        double lowerCornerLong = Double.parseDouble(selection.split(";")[1]);
        double lowerCornerLat = Double.parseDouble(selection.split(";")[0]);
        double upperCornerLong = Double.parseDouble(selection.split(";")[3]);
        double upperCornerLat = Double.parseDouble(selection.split(";")[2]);
        String serverUrl = requestContext.getRequestURL().toString();
        if (requestContext.getRequestURL().toString().indexOf("?") > -1) serverUrl = serverUrl.substring(0, serverUrl.indexOf("?"));
        serverUrl = "https://www.disit.org/superservicemap/api/v1/" + organization + "/wfs";
        String serverVersion = "2.0.0";
        try {
            String pService = service;
            if (pService == null) pService = Service;
            if (pService == null) pService = SERVICE;
            if (pService == null) throw new WfsException(WfsException.MISSING_PARAMETER_VALUE, "SERVICE");
            if (!"WFS".equals(pService)) throw new WfsException(WfsException.INVALID_PARAMETER_VALUE, "SERVICE");
            String pAcceptVersions = AcceptVersions;
            if (pAcceptVersions == null) pAcceptVersions = acceptversions;
            if (pAcceptVersions == null) pAcceptVersions = ACCEPTVERSIONS;
            if (pAcceptVersions == null) pAcceptVersions = pversion;
            if (pAcceptVersions == null) pAcceptVersions = pVERSION;
            if (pAcceptVersions == null) pAcceptVersions = serverVersion;
            String[] pAcceptVersionsArray = pAcceptVersions.split(",");
            if (pAcceptVersionsArray.length == 0) throw new WfsException(WfsException.INVALID_PARAMETER_VALUE, "ACCEPTVERSIONS");
            boolean acceptVersionsOk = false;
            String version = null;
            for (int i = 0; i < pAcceptVersionsArray.length; i++) {
                if ("2.0.0".equals(pAcceptVersionsArray[i]) || "1.1.0".equals(pAcceptVersionsArray[i])) {
                    version = pAcceptVersionsArray[i];
                    acceptVersionsOk = true;
                    continue;
                }
                version = pAcceptVersionsArray[i];
                String[] splittedVersion = version.split("\\.");
                if (splittedVersion.length != 3) throw new WfsException(WfsException.INVALID_PARAMETER_VALUE, "ACCEPTVERSIONS");
                if (!isInteger(splittedVersion[0])) throw new WfsException(WfsException.INVALID_PARAMETER_VALUE, "ACCEPTVERSIONS");
                if (!isInteger(splittedVersion[1])) throw new WfsException(WfsException.INVALID_PARAMETER_VALUE, "ACCEPTVERSIONS");
                if (!isInteger(splittedVersion[2])) throw new WfsException(WfsException.INVALID_PARAMETER_VALUE, "ACCEPTVERSIONS");
                if (Integer.parseInt(splittedVersion[0]) < 0 || Integer.parseInt(splittedVersion[0]) > 99) throw new WfsException(WfsException.INVALID_PARAMETER_VALUE, "ACCEPTVERSIONS");
                if (Integer.parseInt(splittedVersion[1]) < 0 || Integer.parseInt(splittedVersion[1]) > 99) throw new WfsException(WfsException.INVALID_PARAMETER_VALUE, "ACCEPTVERSIONS");
                if (Integer.parseInt(splittedVersion[2]) < 0 || Integer.parseInt(splittedVersion[2]) > 99) throw new WfsException(WfsException.INVALID_PARAMETER_VALUE, "ACCEPTVERSIONS");
            }
            if (!acceptVersionsOk) throw new WfsException(WfsException.VERSION_NEGOTIATION_FAILED, null);
            String pRequest = request;
            if (pRequest == null) pRequest = Request;
            if (pRequest == null) pRequest = REQUEST;
            if (pRequest == null) throw new WfsException(WfsException.MISSING_PARAMETER_VALUE, "REQUEST");
            switch (pRequest) {
                case "getcapabilities":
                case "GetCapabilities":
                    return WfsServer.getCapabilities(serverVersion, serverUrl, lowerCornerLong, lowerCornerLat, upperCornerLong, upperCornerLat, t);
                case "DescribeFeatureType":
                    String pOutputFormat = outputformat;
                    if (pOutputFormat == null) pOutputFormat = OutputFormat;
                    if (pOutputFormat == null) pOutputFormat = OUTPUTFORMAT;
                    if (pOutputFormat == null) pOutputFormat = "application/xml";
                    if (!"application/xml".equals(pOutputFormat.replaceAll("\\\\s+", ""))) throw new WfsException(WfsException.INVALID_PARAMETER_VALUE, "OUTPUTFORMAT");
                    String pTypename = typename;
                    if (pTypename == null) pTypename = TypeName;
                    if (pTypename == null) pTypename = TYPENAME;
                    if (pTypename == null) pTypename = typeName;
                    if (pTypename == null) {
                        String xx = "";
                        return WfsServer.describeAllFeatureTypes(serverVersion, t, xx);
                    } 
                    else {
                        return WfsServer.describeFeatureType(typeName, t);
                    }
                case "ListStoredQueries":
                    return WfsServer.listStoredQueries(serverVersion, serverUrl);
                case "DescribeStoredQueries":
                    String pStoredQuery_ID = storedquery_id;
                    if (pStoredQuery_ID == null) pStoredQuery_ID = StoredQuery_Id;
                    if (pStoredQuery_ID == null) pStoredQuery_ID = StoredQuery_ID;
                    if (pStoredQuery_ID == null) pStoredQuery_ID = STOREDQUERY_ID;
                    if (pStoredQuery_ID != null && !"urn:ogc:def:query:OGC-WFS::GetFeatureById".equals(pStoredQuery_ID)) throw new WfsException(WfsException.NOT_FOUND, pStoredQuery_ID);
                    return WfsServer.describeStoredQueries(serverUrl, serverVersion, t);
                case "GetFeature":
                    double[] coords;
                    String pTypenames = typenames;
                    if (pTypenames == null) pTypenames = TypeNames;
                    if (pTypenames == null) pTypenames = TYPENAMES;
                    if (pTypenames == null) pTypenames = typeNames;
                    if (pTypenames != null) {
                        String xmlPropertyName = "";
                        if (pPropertyName != null) {
                            String[] propertyNameArray = pPropertyName.split(",");
                            for (int p = 0; p < propertyNameArray.length; p++) {
                                xmlPropertyName += "<wfs:PropertyName>" + propertyNameArray[p] + "</wfs:PropertyName>";
                            }
                        }

                        return wfsPost(organization, "<?xml version='1.0' encoding='utf-8'?>\n"
                            + "<GetFeature \n"
                            + " xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'\n"
                            + " xmlns:gml='http://www.opengis.net/gml'\n"
                            + " xmlns:ogc='http://www.opengis.net/ogc'\n"
                            + " xmlns:wfs='http://www.opengis.net/wfs'\n"
                            + " xmlns:fes='http://www.opengis.org/fes/2.0'\n"                    
                            + " xmlns='http://www.opengis.net/wfs/2.0'\n"
                            + " xmlns:km4c='http://www.disit.org/km4city/schema#'\n"
                            + " version='2.0.0' service='WFS' count='3000'>\n"
                            + " <Query typeNames='" + pTypenames + "' srsName='EPSG:3857'>\n"
                            + xmlPropertyName
                            + (pFilter != null ? pFilter.replaceAll("xmlns:fes=\"http://www.opengis.org/fes/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\"", "") : "")
                            + " </Query>\n"
                            + "</GetFeature>");
                    }
                    TimeZone tz = TimeZone.getDefault();
                    java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
                    df.setTimeZone(tz);
                    String nowAsISO = df.format(new java.util.Date());
                    String pStartIndex = StartIndex;
                    if (pStartIndex == null) pStartIndex = startindex;
                    if (pStartIndex == null) pStartIndex = STARTINDEX;
                    if (pStartIndex == null) pStartIndex = "0";
                    if (!"0".equals(pStartIndex)) throw new WfsException(WfsException.INVALID_PARAMETER_VALUE, "STARTINDEX");
                    String pCount = Count;
                    if (pCount == null) pCount = count;
                    if (pCount == null) pCount = COUNT;
                    if (pCount != null && !"1".equals(pCount)) throw new WfsException(WfsException.INVALID_PARAMETER_VALUE, "COUNT");
                    String pResultType = ResultType;
                    if (pResultType == null) pResultType = resulttype;
                    if (pResultType == null) pResultType = RESULTTYPE;
                    if (pResultType == null) pResultType = "results";
                    if (!("hits".equals(pResultType) || "results".equals(pResultType))) throw new WfsException(WfsException.INVALID_PARAMETER_VALUE, "RESULTTYPE");
                    String pOutputformat = outputformat;
                    if (pOutputformat == null) pOutputformat = OutputFormat;
                    if (pOutputformat == null) pOutputformat = OUTPUTFORMAT;
                    if (pOutputformat == null) pOutputformat = "application/gml+xml;version=3.2";
                    if (!"application/gml+xml;version=3.2".equals(pOutputformat.replaceAll("\\\\s+", ""))) throw new WfsException(WfsException.INVALID_PARAMETER_VALUE, "OUTPUTFORMAT");
                    String pResolve = Resolve;
                    if (pResolve == null) pResolve = resolve;
                    if (pResolve == null) pResolve = RESOLVE;
                    if (pResolve == null) pResolve = "none";
                    if (!("none".equals(pResolve) || "local".equals(pResolve) || "remote".equals(pResolve) || "all".equals(pResolve))) throw new WfsException(WfsException.INVALID_PARAMETER_VALUE, "RESOLVE");
                    if (!("none".equals(pResolve) || "local".equals(pResolve))) throw new WfsException(WfsException.OPTION_NOT_SUPPORTED, "RESOLVE");
                    String pResolveDepth = ResolveDepth;
                    if (pResolveDepth == null) pResolveDepth = resolvedepth;
                    if (pResolveDepth == null) pResolveDepth = RESOLVEDEPTH;
                    if (pResolveDepth != null && "none".equals(pResolve)) throw new WfsException(WfsException.INVALID_PARAMETER_VALUE, "RESOLVEDEPTH");
                    if (pResolveDepth == null) pResolveDepth = "*";
                    if ((!"*".equals(pResolveDepth)) && !pResolveDepth.matches("\\d+")) throw new WfsException(WfsException.INVALID_PARAMETER_VALUE, "RESOLVEDEPTH");
                    String pResolveTimeout = ResolveTimeout;
                    if (pResolveTimeout == null) pResolveTimeout = resolvetimeout;
                    if (pResolveTimeout == null) pResolveTimeout = RESOLVETIMEOUT;
                    if (pResolveTimeout != null && "none".equals(pResolve)) throw new WfsException(WfsException.INVALID_PARAMETER_VALUE, "RESOLVETIMEOUT");
                    String pStoredquery_ID = storedquery_id;
                    if (pStoredquery_ID == null) pStoredquery_ID = StoredQuery_Id;
                    if (pStoredquery_ID == null) pStoredquery_ID = StoredQuery_ID;
                    if (pStoredquery_ID == null) pStoredquery_ID = STOREDQUERY_ID;
                    if (pStoredquery_ID == null) throw new WfsException(WfsException.MISSING_PARAMETER_VALUE, "STOREDQUERY_ID");
                    if (!"urn:ogc:def:query:OGC-WFS::GetFeatureById".equals(pStoredquery_ID)) throw new WfsException(WfsException.NOT_FOUND, pStoredquery_ID);
                    String pID = id;
                    if (pID == null) pID = Id;
                    if (pID == null) pID = ID;
                    if (pID == null) throw new WfsException(WfsException.MISSING_PARAMETER_VALUE, "ID");
                    String httpRequestForwardedFor = "";
                    if (requestContext.getHeader("X-Forwarded-For") != null && !requestContext.getHeader("X-Forwarded-For").isEmpty()) {
                      httpRequestForwardedFor += requestContext.getHeader("X-Forwarded-For") + ",";
                    }
                    httpRequestForwardedFor += requestContext.getRemoteAddr();
                    Response r = getServiceURI(pID, "json", "/api/v1", "/api/v1/?ssm=yes&realtime=true&serviceUri=" + URLEncoder.encode(pID, "UTF-8") + (fromTime != null ? "&fromTime="+URLEncoder.encode(fromTime,"UTF-8") : "") + (toTime != null ? "&toTime="+URLEncoder.encode(toTime,"UTF-8") : ""), httpRequestForwardedFor,null);
                    if(r.getStatus() != 200) throw new WfsException(WfsException.OPERATION_PROCESSING_FAILED, null);
                    String jsonStr = r.getEntity().toString();
                    return WfsServer.getFeature(requestContext, serverUrl, serverVersion, t, timezoneSuffix, selection, organization, 0, pFilter, 0, jsonStr, nowAsISO, pResultType, r, pID);
                default:
                    throw new WfsException(WfsException.OPERATION_NOT_SUPPORTED, pRequest);
            }
        } catch (WfsException wfse) {
            String template = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<ExceptionReport xmlns=\"http://www.opengis.net/ows/1.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.opengis.net/ows/1.1 http://schemas.opengis.net/ows/1.1.0/owsAll.xsd\" version=\"" + serverVersion + "\">\n"
                + (wfse.getLocator() != null ? "<Exception exceptionCode=\"%s\" locator=\"%s\"/>\n" : "<Exception exceptionCode=\"%s\" />\n")
                + "</ExceptionReport>";
            String xmlResponse = wfse.getLocator() != null ? String.format(template, wfse.getCode(), wfse.getLocator().replaceAll("\"", "\\\"")) : String.format(template, wfse.getCode());
            if (WfsException.NOT_FOUND.equals(wfse.getCode())) {
                return Response.status(404).entity(xmlResponse).header("Content-Type", "application/gml+xml;version=3.2").header("Cache-Control", "no-cache").build();
            } else if (WfsException.OPERATION_PROCESSING_FAILED.equals(wfse.getCode())) {
                return Response.status(500).entity(xmlResponse).header("Content-Type", "application/gml+xml;version=3.2").header("Cache-Control", "no-cache").build();
            } else {
                return Response.status(400).entity(xmlResponse).header("Content-Type", "application/gml+xml;version=3.2").header("Cache-Control", "no-cache").build();
            }
        } catch (Exception e) {
            String xmlResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<ExceptionReport xmlns=\"http://www.opengis.net/ows/1.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.opengis.net/ows/1.1 http://schemas.opengis.net/ows/1.1.0/owsAll.xsd\" version=\"" + serverVersion + "\">\n"
                + "<Exception exceptionCode=\"NoApplicableCode\" locator=\"" + (e.getMessage() != null ? e.getMessage().replace("\"", "&quot;") : "") + "\" />"
                + "</ExceptionReport>";
            return Response.status(400).entity(xmlResponse).header("Content-Type", "application/gml+xml;version=3.2").header("Cache-Control", "no-cache").build();
        }
    }

  private String initFeat(String className, String serviceUri, String name, String typeLabel, double latitude, double longitude, String city, String cap, String province, String address, 
          String civic, String phone, String fax, String website, String email, String note, String linkDBpedia, double avgStars, int starsCount, java.util.Date periodStartTime, java.util.Date measuredTime, String timezoneSuffix) {
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
  
  private String bldResp( String className, String nowAsISO, String serverUrl, String serverVersion, String serviceType, String serviceUri, String name, String typeLabel, double latitude, 
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
  
  private boolean isRequested(String what, String list) {
    return list == null || Arrays.asList(list.split(",")).contains(what);
  }

  private boolean isInteger(String s) {
    return isInteger(s, 10);
  }

  private boolean isInteger(String s, int radix) {
    if (s.isEmpty()) {
      return false;
    }
    for (int i = 0; i < s.length(); i++) {
      if (i == 0 && s.charAt(i) == '-') {
        if (s.length() == 1) {
          return false;
        } else {
          continue;
        }
      }
      if (Character.digit(s.charAt(i), radix) < 0) {
        return false;
      }
    }
    return true;
  }

  private String goThere(String where, String html) {
    try {
      String newHtml = html;
      newHtml = newHtml.replace("\"//", "\"" + new URI(where).getScheme() + "://");
      newHtml = newHtml.replace("'//", "'" + new URI(where).getScheme() + "://");
      newHtml = newHtml.replace("href=\"/", "href=\"" + new URI(where).getScheme() + "://" + new URI(where).getHost() + "/");
      newHtml = newHtml.replace("src=\"/", "src=\"" + new URI(where).getScheme() + "://" + new URI(where).getHost() + "/");
      newHtml = newHtml.replace("source: \"/", "source: \"" + new URI(where).getScheme() + "://" + new URI(where).getHost() + "/");
      newHtml = newHtml.replace("ctx = \"/", "ctx = \"" + new URI(where).getScheme() + "://" + new URI(where).getHost() + "/");
      newHtml = newHtml.replace("url: \"/", "url: \"" + new URI(where).getScheme() + "://" + new URI(where).getHost() + "/");
      newHtml = newHtml.replace("url : \"/", "url : \"" + new URI(where).getScheme() + "://" + new URI(where).getHost() + "/");
      newHtml = newHtml.replace("href='/", "href='" + new URI(where).getScheme() + "://" + new URI(where).getHost() + "/");
      newHtml = newHtml.replace("src='/", "src='" + new URI(where).getScheme() + "://" + new URI(where).getHost() + "/");
      newHtml = newHtml.replace("source: '/", "source: '" + new URI(where).getScheme() + "://" + new URI(where).getHost() + "/");
      newHtml = newHtml.replace("ctx = '/", "ctr = '" + new URI(where).getScheme() + "://" + new URI(where).getHost() + "/");
      newHtml = newHtml.replace("url: '/", "url: '" + new URI(where).getScheme() + "://" + new URI(where).getHost() + "/");
      newHtml = newHtml.replace("url : '/", "url : '" + new URI(where).getScheme() + "://" + new URI(where).getHost() + "/");
      return newHtml;
    } catch (Exception e) {
      e.printStackTrace();
      return html;
    }
  }

  public static class RequestMakingAndHashThread extends Thread {

    String request;
    int id;
    HashSet<String> BusStopsFeaturedUniques;
    HashSet<String> SensorSitesFeaturedUniques;
    HashSet<String> ServicesFeaturedUniques;
    HashSet<String> GenericFeaturedUniques;
    HashSet<String> PlainResponses;
    HashSet<String> PlainResponseCodes;
    String httpRequestForwardedFor;
    String authorization;
    String referer;

    public RequestMakingAndHashThread(String r, int i, HashSet<String> BStopsFeaturedUniques, HashSet<String> SSitesFeaturedUniques, HashSet<String> SFeaturedUniques, HashSet<String> genericFeatures, HashSet<String> SPlainResponses, HashSet<String> SPlainResponseCodes, String httpRequestForwardedFor, String authorization, String referer) {
      request = r;
      id = i;
      BusStopsFeaturedUniques = BStopsFeaturedUniques;
      SensorSitesFeaturedUniques = SSitesFeaturedUniques;
      ServicesFeaturedUniques = SFeaturedUniques;
      GenericFeaturedUniques = genericFeatures;
      PlainResponses = SPlainResponses;
      PlainResponseCodes = SPlainResponseCodes;
      this.httpRequestForwardedFor = httpRequestForwardedFor;
      this.authorization = authorization;
      this.referer = referer;
    }

    @Override
    public void run() {

      try {

        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget targetServiceMap = client.target(UriBuilder.fromUri(request).build());
        Response r = null;
        if (this.referer == null) {
          if (this.authorization == null) {
            r = targetServiceMap.request().header("X-Forwarded-For", this.httpRequestForwardedFor).get();
          } else {
            r = targetServiceMap.request().header("X-Forwarded-For", this.httpRequestForwardedFor).header("Authorization", this.authorization).get();
          }
        } else {
          if (this.authorization == null) {
            r = targetServiceMap.request().header("X-Forwarded-For", this.httpRequestForwardedFor).header("Referer", this.referer).get();
          } else {
            r = targetServiceMap.request().header("X-Forwarded-For", this.httpRequestForwardedFor).header("Authorization", this.authorization).header("Referer", this.referer).get();
          }
        }
        String serviceMapResponse = r.readEntity(String.class);
        PlainResponses.add(serviceMapResponse);
        PlainResponseCodes.add(String.valueOf(r.getStatus()));
        if (r.getStatus() != 200) {
          return;
        }

        JSONObject newRensponse = new JSONObject(serviceMapResponse);

        try {
          if (newRensponse.has("BusStops")) {
            JSONObject BusStops = newRensponse.getJSONObject("BusStops");
            if ((!BusStops.has("fullCount")) || BusStops.getInt("fullCount") != 0) {
              for (int m = 0; m < BusStops.getJSONArray("features").length(); m++) {
                addToBusStopsMap(BusStopsFeaturedUniques, ((JSONObject) BusStops.getJSONArray("features").get(m)).toString(4));
              }
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

        try {
          if (newRensponse.has("SensorSites")) {
            JSONObject SensorSites = newRensponse.getJSONObject("SensorSites");
            if ((!SensorSites.has("fullCount")) || SensorSites.getInt("fullCount") != 0) {
              for (int m = 0; m < SensorSites.getJSONArray("features").length(); m++) {
                addToSensorSitesMap(SensorSitesFeaturedUniques, ((JSONObject) SensorSites.getJSONArray("features").get(m)).toString(4));
              }
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

        try {
          if (newRensponse.has("Services")) {
            JSONObject Services = newRensponse.getJSONObject("Services");
            if ((!Services.has("fullCount")) || Services.getInt("fullCount") != 0) {
              for (int m = 0; m < Services.getJSONArray("features").length(); m++) {
                addToServicesMap(ServicesFeaturedUniques, ((JSONObject) Services.getJSONArray("features").get(m)).toString(4));
              }
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

        try {
          if (newRensponse.has("features")) {
            JSONArray features = newRensponse.getJSONArray("features");
            if ((!newRensponse.has("fullCount")) || newRensponse.getInt("fullCount") != 0) {
              for (int m = 0; m < features.length(); m++) {
                addToGenericFeaturesMap(GenericFeaturedUniques, ((JSONObject) features.get(m)).toString(4));
              }
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

      } catch (JSONException e) {
        e.printStackTrace();
      }

    }

  }

  public static class RequestMakingAndHashThreadFeedbacks extends Thread {

    String request;
    int id;
    HashSet<String> commentsUniques;
    HashSet<String> photosUniques;
    HashSet<String> starsUniques;
    HashSet<String> plainResponses;
    HashSet<String> plainResponseCodes;
    ArrayList<Integer> limit;
    String httpRequestForwardedFor;
    String referer;

    public RequestMakingAndHashThreadFeedbacks(String r, int i, HashSet<String> photosUniques, HashSet<String> commentsUniques, HashSet<String> starsUniques, HashSet<String> plainResponses, HashSet<String> plainResponseCodes, ArrayList<Integer> limit, String httpRequestForwardedFor, String referer) {
      request = r;
      id = i;
      this.photosUniques = photosUniques;
      this.commentsUniques = commentsUniques;
      this.starsUniques = starsUniques;
      this.plainResponses = plainResponses;
      this.plainResponseCodes = plainResponseCodes;
      this.limit = limit;
      this.httpRequestForwardedFor = httpRequestForwardedFor;
      this.referer = referer;
    }

    @Override
    public void run() {

      try {

        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget targetServiceMap = client.target(UriBuilder.fromUri(request).build());
        Response r = null;
        if (this.referer == null) {
          r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).get();
        } else {
          r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).header("Referer", this.referer).get();
        }
        String serviceMapResponse = r.readEntity(String.class);
        plainResponses.add(serviceMapResponse);
        plainResponseCodes.add(String.valueOf(r.getStatus()));

        JSONObject newResponse = new JSONObject(serviceMapResponse);

        try {
          if (newResponse.has("LastPhotos")) {
            JSONArray lastPhotos = newResponse.getJSONArray("LastPhotos");
            limit.add(lastPhotos.length());
            for (int m = 0; m < lastPhotos.length(); m++) {
              addToUniquesSet(photosUniques, ((JSONObject) lastPhotos.get(m)).toString(4));
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

        try {
          if (newResponse.has("LastComments")) {
            JSONArray lastComments = newResponse.getJSONArray("LastComments");
            limit.add(lastComments.length());
            for (int m = 0; m < lastComments.length(); m++) {
              addToUniquesSet(commentsUniques, ((JSONObject) lastComments.get(m)).toString(4));
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

        try {
          if (newResponse.has("LastStars")) {
            JSONArray lastStars = newResponse.getJSONArray("LastStars");
            limit.add(lastStars.length());
            for (int m = 0; m < lastStars.length(); m++) {
              addToUniquesSet(starsUniques, ((JSONObject) lastStars.get(m)).toString(4));
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

      } catch (JSONException e) {
        e.printStackTrace();
      }

    }

  }

  public static synchronized void addToUniquesSet(HashSet<String> set, String val) {
    set.add(val);
  }

  public static synchronized void goodResponseReceived(boolean resp) {
    resp = false;
  }

  public static synchronized void addToLastPhotos(HashSet<String> map, String val) {
    map.add(val);
  }

  public static synchronized void addToSensorSitesMap(HashSet<String> map, String val) {
    map.add(val);
  }

  public static synchronized void addToBusStopsMap(HashSet<String> map, String val) {
    map.add(val);
  }

  public static synchronized void addToServicesMap(HashSet<String> map, String val) {
    map.add(val);
  }

  public static synchronized void addToGenericFeaturesMap(HashSet<String> map, String val) {
    map.add(val);
  }

  // Builds a Geometry from a well-formed string that describes a point, ring, area, and other possible shapes that could come to be of interest in the future
  private Geometry str2geo(String str) {
    if (str == null || str.isEmpty()) {
      return null;
    }
    Geometry sel = null;
    try {
      GeometryAdapter ga = new GeometryAdapter();
      String[] elements = str.split(";");
      GeometryFactory gf = new GeometryFactory();

      switch (elements.length) {
        case 2: // Point
          sel = new Point(new Coordinate(Double.parseDouble(elements[1]), Double.parseDouble(elements[0])),
                  new PrecisionModel(), 0);
          break;
        case 4: // Ring points
          Coordinate[] ringPoints = {
            (new Coordinate(Double.parseDouble(elements[1]), Double.parseDouble(elements[0]))),
            (new Coordinate(Double.parseDouble(elements[1]), Double.parseDouble(elements[2]))),
            (new Coordinate(Double.parseDouble(elements[3]), Double.parseDouble(elements[2]))),
            (new Coordinate(Double.parseDouble(elements[3]), Double.parseDouble(elements[0]))),
            (new Coordinate(Double.parseDouble(elements[1]), Double.parseDouble(elements[0])))};
          LinearRing lr = new LinearRing(ringPoints, new PrecisionModel(), 0);
          sel = new Polygon(lr, null, gf);
          break;
        default: // Area
          sel = ga.unmarshal(str);

      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sel;
  }

        // Provides a sorted list of service maps based on the given position. Service maps are sorted by their HTML priority. This way, if HTML format
  // is requested, one can simply get the first service map in the list ignoring the others. Sorting does not affect any way JSON responses.
  private List<String> getCompetentServiceMaps(String position, String api, String format) throws Exception {

    try {

                // Selection string -> Geometry instance, for that it could be possible to efficiently determine intersections with geometries associated to service maps
      Geometry sel = str2geo(position);

                // Computation of intersections --> Identification of those service maps that have to be queried based on the selection input parameter
      // If something goes wrong, all service maps are considered to be of interest.
      MySQLManager msm = new MySQLManager();
      List<String> competentServiceMaps = msm.getResponsiblesUrlPrefix(sel, api, format);

                // Strip out duplicates from the list of the service maps that have to be queried 
      LinkedHashSet<String> h = new LinkedHashSet<>(competentServiceMaps);
      competentServiceMaps.clear();
      competentServiceMaps.addAll(h);

      return competentServiceMaps;

    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }

  }

  // Retrieve coordinates from location object
  private String getAddressPosition(JSONObject obj) throws Exception {
    if (obj.has("geometry")) {
      JSONArray coordinates = obj.getJSONObject("geometry").getJSONArray("coordinates");
      return coordinates.get(1).toString() + ";" + coordinates.get(0).toString();
    }
    String addressUri = obj.getString("addressUri");
    URL url = new URL(addressUri);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestProperty("Accept", "application/rdf+xml");
    InputStream is = conn.getInputStream();
    Scanner s = new Scanner(is).useDelimiter("\\A");
    String addressRdf = s.hasNext() ? s.next() : "";
    class AddressRdfParser extends DefaultHandler {

      String accessUri = null;

      @Override
      public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("km4c:hasExternalAccess".equals(qName)) {
          if (accessUri == null) {
            accessUri = attributes.getValue(0);
          }
        }
      }
    }
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser saxParser = factory.newSAXParser();
    AddressRdfParser handler = new AddressRdfParser();
    saxParser.parse(new InputSource(new StringReader(addressRdf)), handler);
    String accessUri = handler.accessUri;

    url = new URL(accessUri);
    conn = (HttpURLConnection) url.openConnection();
    conn.setRequestProperty("Accept", "application/rdf+xml");
    is = conn.getInputStream();
    s = new Scanner(is).useDelimiter("\\A");
    String accessRdf = s.hasNext() ? s.next() : "";
    class AccessRdfParser extends DefaultHandler {

      String lat = null;
      String lng = null;
      boolean isLat = false;
      boolean isLong = false;

      @Override
      public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("geo:lat".equals(qName)) {
          isLat = true;
        }
        if ("geo:long".equals(qName)) {
          isLong = true;
        }
      }

      @Override
      public void characters(char ch[], int start, int length) throws SAXException {
        if (isLat) {
          if (lat == null) {
            lat = new String(ch, start, length);
          }
        }
        if (isLong) {
          if (lng == null) {
            lng = new String(ch, start, length);
          }
        }
      }

    }
    factory = SAXParserFactory.newInstance();
    saxParser = factory.newSAXParser();
    AccessRdfParser ahandler = new AccessRdfParser();
    saxParser.parse(new InputSource(new StringReader(accessRdf)), ahandler);
    return ahandler.lat + ";" + ahandler.lng;
  }

  // Compute distance between two geometries
  private double computeDistance(Geometry g1, Geometry g2) {
    if (g1 == null || g2 == null) {
      return -1.0;
    } else {
      return g1.distance(g2);
    }
  }

  class ParallelQuery extends Thread {

    String url;
    ArrayList<String> allResponses;
    HashSet<String> allAllResponses;
    HashSet<String> allAllResponseCodes;
    String httpRequestForwardedFor;
    String referer;

    private ParallelQuery(String url, ArrayList<String> allResponses, HashSet<String> allAllResponses, HashSet<String> allAllResponseCodes, String httpRequestForwardedFor, String referer) {
      this.url = url;
      this.allResponses = allResponses;
      this.allAllResponses = allAllResponses;
      this.allAllResponseCodes = allAllResponseCodes;
      this.httpRequestForwardedFor = httpRequestForwardedFor;
      this.referer = referer;
    }

    @Override
    public void run() {
      try {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget targetServiceMap = client.target(UriBuilder.fromUri(url).build());
        Response r = null;
        if (this.referer == null) {
          r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).get();
        } else {
          r = targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).header("Referer", this.referer).get();
        }
        String responseBody = r.readEntity(String.class);
        String responseCode = String.valueOf(r.getStatus());
        allAllResponses.add(responseBody);
        allAllResponseCodes.add(responseCode);
        if ("200".equals(responseCode)) {
          allResponses.add(responseBody);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }

  private String getFirstStop(String route) throws Exception {
    Response response = getTplBusStops(route, "false", "", null);
    String txtResponse = response.readEntity(String.class);
    JSONObject jResponse = new JSONObject(txtResponse);
    JSONArray features = jResponse.getJSONObject("BusStops").getJSONArray("features");
    String tmp = null;
    for (int i = 0; i < features.length(); i++) {
      if (tmp == null || ((JSONObject) features.get(i)).getString("serviceUri").compareTo(tmp) < 0) {
        tmp = ((JSONObject) features.get(i)).getString("serviceUri");
      }
    }
    return tmp;
  }

  private String getLastStop(String route) throws Exception {
    Response response = getTplBusStops(route, "false", "", null);
    String txtResponse = response.readEntity(String.class);
    JSONObject jResponse = new JSONObject(txtResponse);
    JSONArray features = jResponse.getJSONObject("BusStops").getJSONArray("features");
    String tmp = null;
    for (int i = 0; i < features.length(); i++) {
      if (tmp == null || ((JSONObject) features.get(i)).getString("serviceUri").compareTo(tmp) > 0) {
        tmp = ((JSONObject) features.get(i)).getString("serviceUri");
      }
    }
    return tmp;
  }

  @SuppressWarnings("deprecation")
  @Path("/nextPOS")
  @GET
  public Response getNextPos(
          @QueryParam("range") String range,
          @QueryParam("categories") String categories,
          @QueryParam("selection") String selection,
          @QueryParam("maxDists") String maxDists,
          @QueryParam("uid") String uid,
          @QueryParam("text") String text,
          @QueryParam("requestFrom") String requestFrom
  ) throws Exception {

            // Identify service maps that have to be queried based on the given position.
    List<String> competentServiceMaps = getCompetentServiceMaps(selection, "/api/v1/nextPOS", "json");

            // Perform a parallel polling among the service maps of interest to retrieve the events
    ArrayList<Thread> threads = new ArrayList<>();
    ArrayList<String> collections = new ArrayList<>();
    HashSet<String> allResponses = new HashSet<>();
    HashSet<String> allResponseCodes = new HashSet<>();
    for (int i = 0; i < competentServiceMaps.size(); i++) {
      final String SMQUERY = competentServiceMaps.get(i) + "/api/v1/nextPOS/?ssm=yes"
              + (range == null || range.isEmpty() ? "" : "&range=" + URLEncoder.encode(range, "UTF-8"))
              + (categories == null || categories.isEmpty() ? "" : "&categories=" + URLEncoder.encode(categories, "UTF-8"))
              + (selection == null || selection.isEmpty() ? "" : "&selection=" + URLEncoder.encode(selection, "UTF-8"))
              + (maxDists == null || maxDists.isEmpty() ? "" : "&maxDists=" + URLEncoder.encode(maxDists, "UTF-8"))
              + (uid == null || uid.isEmpty() ? "" : "&uid=" + URLEncoder.encode(uid, "UTF-8"))
              + (text == null || text.isEmpty() ? "" : "&text=" + URLEncoder.encode(text, "UTF-8"))
              + (requestFrom == null || requestFrom.isEmpty() ? "" : "&requestFrom=" + URLEncoder.encode(requestFrom, "UTF-8"));
      String ipAddressRequestCameFrom = requestContext.getRemoteAddr();
      String httpRequestForwardedFor = "";
      if (requestContext.getHeader("X-Forwarded-For") != null && !requestContext.getHeader("X-Forwarded-For").isEmpty()) {
        httpRequestForwardedFor += requestContext.getHeader("X-Forwarded-For") + ",";
      }
      httpRequestForwardedFor += ipAddressRequestCameFrom;
      ParallelQuery thread = new ParallelQuery(SMQUERY, collections, allResponses, allResponseCodes, httpRequestForwardedFor, requestContext.getHeader("Referer"));
      threads.add(thread);
      thread.start();
    }

    for (Thread thread : threads) {
      thread.join();
    }

            // If no valid responses arrived...
    if (collections.isEmpty()) {
      if (allResponses.size() == 1 && allResponseCodes.size() == 1) {
        try {
          Iterator<String> PlainResponseCodesIterator = allResponseCodes.iterator();
          Iterator<String> PlainResponsesIterator = allResponses.iterator();
          String responseCode = PlainResponseCodesIterator.next();
          String plainResponse = PlainResponsesIterator.next();
          return Response.ok(new JSONObject(plainResponse).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").status(Integer.parseInt(responseCode)).build();
        } catch (Exception e) {
          e.printStackTrace();
          return Response.status(500).build();
        }
      } else {
        return Response.status(500).build();
      }
    }

            // If just one valid response arrived...
    if (collections.size() == 1) {
      try {
        return Response.ok(new JSONObject(collections.get(0)).toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
      } catch (Exception e) {
        e.printStackTrace();
        return Response.status(500).build();
      }
    }

            // In the most complex possible case, put all events in a single set, and sort them by ascending start date, as it is done by the /real/ service maps
    ArrayList<JSONObject> events = new ArrayList<>();
    for (String collection : collections) {
      try {
        JSONObject jCollection = new JSONObject(collection);
        JSONArray jEvents = jCollection.getJSONArray("features");
        for (int i = 0; i < jEvents.length(); i++) {
          events.add((JSONObject) jEvents.get(i));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

    }

    class EventComparator implements Comparator<JSONObject> {

      @Override
      public int compare(JSONObject obj1, JSONObject obj2) {
        try {
          return obj1.getJSONObject("properties").getString("startDate").compareTo(obj2.getJSONObject("properties").getString("startDate"));
        } catch (Exception e) {
          e.printStackTrace();
          return 0;
        }
      }
    }

    events.sort(new EventComparator());

            // Remove duplicates 
    HashSet<String> wrk = new HashSet<>();
    ArrayList<JSONObject> cleanedEvents = new ArrayList<>();
    for (int i = 0; i < events.size(); i++) {
      try {
        if (wrk.add(events.get(i).toString(4))) {
          cleanedEvents.add(events.get(i));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    events = cleanedEvents;

            // Initialize response
    JSONObject response = new JSONObject();
    JSONArray features = new JSONArray();
    response.put("features", features);

            // Append events to response
    for (int i = 0; i < events.size(); i++) {
      try {
        events.get(i).put("id", i + 1);
        features.put(events.get(i));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

            // Output response
    return Response.ok(response.toString(4), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();

  }

  // imgCache, utility API for automatic image editing, always forwarded to the main service map service ()
  @SuppressWarnings("deprecation")
  @Path("/imgcache")
  @GET
  public Response imgCache(
          @QueryParam("imageUrl") String imageUrl,
          @QueryParam("size") String size,
          @QueryParam("requestFrom") String requestFrom
  ) throws Exception {

    final String SMQUERY = "http://servicemap.disit.org/WebAppGrafo/api/v1/imgcache?ssm=yes"
            + (imageUrl == null || imageUrl.isEmpty() ? "" : "&imageUrl=" + URLEncoder.encode(imageUrl, "UTF-8"))
            + (size == null || size.isEmpty() ? "" : "&size=" + URLEncoder.encode(size, "UTF-8"))
            + (requestFrom == null || requestFrom.isEmpty() ? "" : "&requestFrom=" + URLEncoder.encode(requestFrom, "UTF-8"));

    String ipAddressRequestCameFrom = requestContext.getRemoteAddr();
    String httpRequestForwardedFor = "";
    if (requestContext.getHeader("X-Forwarded-For") != null && !requestContext.getHeader("X-Forwarded-For").isEmpty()) {
      httpRequestForwardedFor += requestContext.getHeader("X-Forwarded-For") + ",";
    }
    httpRequestForwardedFor += ipAddressRequestCameFrom;

    ClientConfig config = new ClientConfig();
    Client client = ClientBuilder.newClient(config);
    WebTarget targetServiceMap = client.target(UriBuilder.fromUri(SMQUERY).build());
    if (requestContext.getHeader("Referer") == null) {
      return targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).get();
    } else {
      return targetServiceMap.request().header("X-Forwarded-For", httpRequestForwardedFor).header("Referer", requestContext.getHeader("Referer")).get();
    }

  }

  public static java.util.Date parseDate(String date) {
    java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    java.util.Date r = null;
    try {
      r = df1.parse(date);
    } catch (Exception e) {
      java.text.SimpleDateFormat df2 = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
      try {
        r = df2.parse(date);
      } catch (Exception ignore) {
      }
    }
    return r;
  }
}
