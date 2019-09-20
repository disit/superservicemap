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

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import servicemap.Graph;
import servicemap.MySQLManager;
import servicemap.SPARQLManager;
import servicemap.ServiceMap;
import servicemap.TripleUtility;

@JsonSerialize
public class ServiceMapResource {

	@Context private UriInfo uriInfo;
	@Context private Request request;
	private String id;

	public ServiceMapResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ServiceMap get() throws Exception {
		MySQLManager store = new MySQLManager();
		return store.getById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public void post(@Context HttpServletRequest r, JAXBElement<ServiceMap> input) throws Exception {
		ServiceMap sm = input.getValue();
		if (sm.getIp().getHostAddress().equals(r.getRemoteAddr())) {
			MySQLManager store = new MySQLManager();
			store.insert(sm);
			List<String> smGraphs = new ArrayList<String>();
			if (!sm.getGraphs().isEmpty()) {
				for (Graph g : sm.getGraphs()) {
					smGraphs.add(g.getUri().toString());
					TripleUtility.addGraphToVirtuoso(id, g.getUri().toString());
					store.insertBoundingBox(g.getUri(), 
							SPARQLManager.getGraphBoundingBox(g.getUri().toString()));
				}
			}
			List<String> graphs = store.getCompetenceGraphs(sm.getCompetenceArea());
			if (!graphs.isEmpty()) {
				graphs.removeAll(smGraphs);
				for (String s : graphs) {
					store.insertGraph(sm.getId(), URI.create(s));
					TripleUtility.addGraphToVirtuoso(id, s);
				}
			}
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void put(@Context HttpServletRequest r, @FormParam("newId") String newId) throws Exception {
		MySQLManager store = new MySQLManager();
		String ip = store.getById(id).getIp().getHostAddress();
		if (ip.equals(r.getRemoteAddr())) {
			store.updateId(id, ip, newId);
		}
	}

	@DELETE
	public void delete(@Context HttpServletRequest r) throws Exception {
		MySQLManager store = new MySQLManager();
		if (store.getById(id).getIp().getHostAddress().equals(r.getRemoteAddr())) {
			store.remove(id);
		}
	}

}
