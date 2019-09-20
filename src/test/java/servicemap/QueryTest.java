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

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vividsolutions.jts.io.WKTReader;

public class QueryTest {

	public static void main(String[] args) {

		final String SMQUERY = "http://192.168.0.113:8080/ServiceMap/api/v1/?selection=43.0741;10.8453;43.7768;11.2515&categories=Taxi_park&lang=it&format=json";
		System.out.println(QueryUtility.getPolygon(SMQUERY));

		System.out.println(QueryUtility.createWktCircle(0.d, 0.d, 1.d));

		try {
			MySQLManager store = new MySQLManager();
			List<String> ips = store.getResponsibles(
					new WKTReader().read("POLYGON ((11.2453 43.7741, 11.2515 43.7741, 11.2515 43.7768, 11.2453 43.7768, 11.2453 43.7741))"));
			System.out.println(ips);
		}
		catch (Exception e ) {
			e.printStackTrace();
		}

		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target113 = client.target(UriBuilder.fromUri(SMQUERY).build());
		String r113 = target113.request().get(String.class);

		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode j113 = mapper.readTree(r113);

			WebTarget target207 = client.target(UriBuilder.fromUri(SMQUERY.replace("113", "207")).build());
			String r207 = target207.request().get(String.class);
			JsonNode j207 = mapper.readTree(r207);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
