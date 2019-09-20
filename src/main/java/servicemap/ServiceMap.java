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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;

import resource.GraphsResource;
@JsonSerialize
@XmlRootElement
public class ServiceMap {

	private String id;
	private InetAddress ip;
	private List<Graph> graphs;
	private Geometry competenceArea;

	private int maxID = 100000;

	public ServiceMap(Geometry competenceArea) throws Exception {
		// SET competence area
		setCompetenceArea(competenceArea);
		MySQLManager msm = new MySQLManager();

		// set InetAddress (IP addr)
		InetAddressAdapter ipAdapt = new InetAddressAdapter();
		String ip = InetAddress.getLocalHost().getHostAddress();
		setIp(ipAdapt.unmarshal(ip));

		// set id usando la data precisa, in funzione degli id presenti nel db controlla se va
		// bene o meno
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss.SSSS");
		Date date = new Date();
		setId(dateFormat.format(date));
		while (msm.getById(getId()) != null) {
			Random rand = new Random();
			int n = rand.nextInt(maxID);
			setId(dateFormat.format(date));
			setId(getId() + "-" + n);
			System.out.println(n + "");
		}

		// richiedere ai sm che hanno tali grafi i grafi stessi, scaricarli sotto
		// forma di .n3 ed aggiungerli con lo script
		graphs = msm.getIntersectingGraphs(competenceArea); //prendo grafi intersecanti
		for (int i = 0; i < graphs.size(); i++) { 

			List<String> owners = msm.getGraphOwner(graphs.get(i).getUri().toString()); //trovo la lista di possessori di un determinato grafo
			if (!owners.isEmpty()) { //se non è vuota provo a chidere al possessore il grafo

				// String graph = "GrafoStradale/Grafo_stradale_Firenze";
				String graph = graphs.get(i).getUri().toString();// FIXME .. qui serve solo la parte finale della uri o tutto?

				//connessione
				ClientConfig config = new ClientConfig();
				Client client = ClientBuilder.newClient(config);
				WebTarget target = client
						.target(UriBuilder.fromUri("http://" + owners.get(0) + ":8080/servicemapdistr").build()); // get0...
				Response fileResponce = target.queryParam("graph", graph).path("rest").path("file").request()
						.get(Response.class);
				InputStream input = (InputStream) fileResponce.getEntity();
				File file = new File("/Users/Davide/Desktop/test.zip"); 
				FileOutputStream out = new FileOutputStream(file);
				int c;
				while ((c = input.read()) != -1) {
					out.write(c);
				}
				input.close();
				out.close();
				
				//dezippo il file
				new File("/Users/Davide/Desktop/test").mkdir();
				ZipUtility.unzipDirectory(file, new File("/Users/Davide/Desktop/test"));
				file.delete();

				//carico le triple
				TripleUtility.loadTriples(owners.get(0), graph, new File("/Users/Davide/Desktop/test"));
			}

		}

		// updating database of servicempas with this new serviceMap
		msm.insert(this);
		// TODO check se è presente con le caratteristiche date

	}

	public ServiceMap() {
		// FIXME è bene avere un costruttore così vuoto?
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlJavaTypeAdapter(InetAddressAdapter.class)
	public InetAddress getIp() {
		return ip;
	}

	public void setIp(InetAddress ip) {
		this.ip = ip;
	}

	@XmlElementWrapper
	@XmlElement(name = "graph")
	public List<Graph> getGraphs() {
		return graphs;
	}

	public void setGraphs(List<Graph> uris) {
		this.graphs = uris;
	}

	@XmlJavaTypeAdapter(GeometryAdapter.class)
	public Geometry getCompetenceArea() {
		return competenceArea;
	}

	public void setCompetenceArea(Geometry competenceArea) {
		this.competenceArea = competenceArea;

	}

}
