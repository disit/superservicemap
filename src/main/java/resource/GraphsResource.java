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
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import servicemap.Graph;
import servicemap.MySQLManager;
import servicemap.SPARQLManager;
import servicemap.TripleUtility;

@Path("/serviceMaps/{serviceMap}/graphs") // FIXME
@JsonSerialize
public class GraphsResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Graph> get(@PathParam("serviceMap") String smId) throws Exception {
		MySQLManager store = new MySQLManager();
		return store.getGraphs(smId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void post(@Context HttpServletRequest r, @PathParam("serviceMap") String smId, @FormParam("uri") String uri) throws Exception {
		MySQLManager store = new MySQLManager();
		if (store.getById(smId).getIp().getHostAddress().equals(r.getRemoteAddr()) &&
				!store.getGraphs(smId).contains(URI.create(uri))) {
			store.insertGraph(smId, URI.create(uri));
			TripleUtility.addGraphToVirtuoso(smId, uri);
			store.insertBoundingBox(URI.create(uri), SPARQLManager.getGraphBoundingBox(uri));
		}
	}
	
	// FIXME: meglio toglierlo? Da aggiungere side effects su Virtuoso
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void put(@Context HttpServletRequest r, @PathParam("serviceMap") String smId, 
			@FormParam("actualUri") String actualUri, @FormParam("newUri") String newUri) throws Exception {
		MySQLManager store = new MySQLManager();
		if (store.getById(smId).getIp().getHostAddress().equals(r.getRemoteAddr()) &&
				!store.getGraphs(smId).contains(newUri)) {
			store.updateGraph(smId, URI.create(actualUri), URI.create(newUri));
		}
	}
	
	@DELETE
	public void delete(@Context HttpServletRequest r, @PathParam("serviceMap") String smId, @QueryParam("uri") String uri) throws Exception {
		MySQLManager store = new MySQLManager();
		if (store.getById(smId).getIp().getHostAddress().equals(r.getRemoteAddr())) {
			store.removeGraph(smId, URI.create(uri));
		}
	}
}
