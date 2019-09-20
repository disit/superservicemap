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

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import servicemap.TripleUtility;
import servicemap.ZipUtility;

public class FileTest {

	public static void main(String[] args) throws Exception {
		String graph = "GrafoStradale/Grafo_stradale_Firenze";
		
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);

		WebTarget target = client.target(UriBuilder.fromUri("http://192.168.0.113:8080/servicemapdistr").build());
		Response fileResponce = target.queryParam("graph", graph).path("rest").path("file").request().get(Response.class);

		InputStream input = (InputStream) fileResponce.getEntity();
		File file = new File("/Users/Simone/Desktop/test.zip");
		FileOutputStream out = new FileOutputStream(file);
		int c;
		while ((c = input.read()) != -1) {
			out.write(c);
		}
		input.close();
		out.close();
		
		new File("/Users/Simone/Desktop/test").mkdir();
		ZipUtility.unzipDirectory(file, new File("/Users/Simone/Desktop/test"));
		file.delete();
		
		TripleUtility.loadTriples("192.168.0.113", graph, new File("/Users/Simone/Desktop/test"));
	}

}
