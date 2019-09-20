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

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import servicemap.MySQLManager;
import servicemap.ServiceMap;

@Path("/serviceMaps")
@JsonSerialize
public class ServiceMapsResource {

	@Context private UriInfo uriInfo;
	@Context private Request request;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<ServiceMap> get() throws Exception {
		MySQLManager store = new MySQLManager();
		return store.getAll();
	}

	@Path("{serviceMap}")
	public ServiceMapResource get(@PathParam("serviceMap") String id) {
		return new ServiceMapResource(uriInfo, request, id);
	}

}
