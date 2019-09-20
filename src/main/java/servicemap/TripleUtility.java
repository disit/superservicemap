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
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonSerialize
public class TripleUtility {

	private static final String SHFILE = Paths.get("").toAbsolutePath().toString() + "/senddata.sh";

	public static void addGraphsToVirtuoso(String smId, List<String> graphs) throws Exception {
		for (String g : graphs) {
			addGraphToVirtuoso(smId, g);
		}
	}

	public static void addGraphToVirtuoso(String smId, String graph) throws Exception {
		MySQLManager store = new MySQLManager();
		List<String> ips = store.getGraphOwner(graph);
		int i = 0;
		if (ips.get(i).equals(store.getById(smId).getIp().getHostAddress())) {
			i++;
		}
		addTriples(ips.get(i), store.getById(smId).getIp().toString(), graph);
	}

	private static void addTriples(String requestTo, String virtuoso, String graph) throws Exception {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);

		WebTarget target = client.target(UriBuilder.fromUri("http://" + requestTo + ":8080/servicemapdistr").build());
		Response fileResponce = target.queryParam("graph", graph.replace("http://www.disit.org/km4city/resource/", ""))
				.path("rest").path("file").request().get(Response.class);

		InputStream input = (InputStream) fileResponce.getEntity();
		File dir = new File( Paths.get("").toAbsolutePath().toString() +  graph.substring(graph.lastIndexOf("/")));
		File zippedDir = new File(
				 Paths.get("").toAbsolutePath().toString() + new Timestamp(System.currentTimeMillis()).getTime() + ".zip");
		FileOutputStream out = new FileOutputStream(zippedDir);
		int c;
		while ((c = input.read()) != -1) {
			out.write(c);
		}
		input.close();
		out.close();

		if (!dir.isDirectory()) {
			dir.mkdir();
		}
		ZipUtility.unzipDirectory(zippedDir, dir);
		zippedDir.delete();

		TripleUtility.loadTriples(virtuoso, graph, dir);
	}

	public static void loadTriples(String virtuoso, String graph, File n3Folder) throws Exception {
		ProcessBuilder builder = new ProcessBuilder(SHFILE, n3Folder.getPath(), virtuoso, graph);
		builder.start().waitFor();
	}

}
