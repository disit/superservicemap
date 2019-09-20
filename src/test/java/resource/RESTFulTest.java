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

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class RESTFulTest {

	public static void main(String[] args) throws Exception {

		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);

		WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8080/servicemap").build());
		
		String response = target.path("rest").path("serviceMaps").request().accept(MediaType.APPLICATION_JSON).get(Response.class).toString();
		System.out.println(response);		

		// String xmlAnswer = target.path("rest").path("serviceMaps").request().accept(MediaType.APPLICATION_JSON).get(String.class);
		// System.out.println(xmlAnswer);
		
		/*
		// POST Graph
		Form form = new Form();
		form.param("uri", "http://www.disit.org/km4city/resource/GeolocatedObject/Taxi_kmz");
		target.path("rest").path("serviceMaps/test/graphs").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);
		*/

		// DELETE SM
		// target.path("rest").path("serviceMaps/prova").request().delete();

		// DELETE Graph
		// target.queryParam("uri", "http://www.disit.org/km4city/resource/GeolocatedObject/Taxi_kmz").path("rest").path("serviceMaps/test/graphs").request().delete();
		
		/*
		// POST
		ServiceMap sm = new ServiceMap();
		sm.setId("test");
		sm.setIp(InetAddress.getByName("127.0.0.1"));
		sm.setCompetenceArea(new WKTReader().read("POLYGON((11.168975830078125 43.811537018144904,11.35711669921875 43.811537018144904,11.35711669921875 43.72029585283302,11.168975830078125 43.72029585283302,11.168975830078125 43.811537018144904))"));
		List<Graph> graphs = new ArrayList<Graph>();
		Graph g1 = new Graph();
		g1.setUri(URI.create("http://www.disit.org/km4city/resource/GeolocatedObject/Fontanelli_kmz"));
		graphs.add(g1);
		Graph g2 = new Graph();
		g2.setUri(URI.create("http://www.disit.org/km4city/resource/GeolocatedObject/Aeroporti_dbf"));
		graphs.add(g2);
		sm.setGraphs(graphs);
		target.path("rest").path("serviceMaps/" + sm.getId()).request(MediaType.APPLICATION_XML).post(Entity.entity(sm, MediaType.APPLICATION_XML), Response.class);
		*/
	}
}
