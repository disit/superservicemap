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

import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.RepositoryConnection;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;

import virtuoso.rdf4j.driver.VirtuosoRepository;
@JsonSerialize
public class SPARQLManager {

	private static final String IPADDRESS = "192.168.0.113";
	private static final String PORT = "1111";
	private static final String USERNAME = "dba";
	private static final String PASSWORD = "dba";

	public static Polygon getGraphBoundingBox(String graph) {
		VirtuosoRepository repository = new VirtuosoRepository("jdbc:virtuoso://" + IPADDRESS + ":" + PORT + "/log_enable=0", USERNAME, PASSWORD);
		RepositoryConnection conn = repository.getConnection();

		String latQuery = "SELECT MIN(?lat) as ?minLat, MAX(?lat) as ?maxLat "
				+ "FROM <" + graph + "> WHERE { ?s geo:lat ?lat }";
		TupleQueryResult result = conn.prepareTupleQuery(latQuery).evaluate();
		BindingSet solution = result.next();
		double minLat = Double.parseDouble(solution.getValue("minLat").stringValue());
		double maxLat = Double.parseDouble(solution.getValue("maxLat").stringValue());

		String longQuery = "SELECT MIN(?long) as ?minLong, MAX(?long) as ?maxLong "
				+ "FROM <" + graph + "> WHERE { ?s geo:long ?long }";
		result = conn.prepareTupleQuery(longQuery).evaluate();
		solution = result.next();
		double minLong = Double.parseDouble(solution.getValue("minLong").stringValue());
		double maxLong = Double.parseDouble(solution.getValue("maxLong").stringValue());
		conn.close();

		GeometryFactory geometryFactory = new GeometryFactory();
		Coordinate[] coords = new Coordinate[] { 
				new Coordinate(minLong, minLat), new Coordinate(maxLong, minLat),
				new Coordinate(maxLong, maxLat), new Coordinate(minLong, maxLat), new Coordinate(minLong, minLat)
		};
		return geometryFactory.createPolygon(geometryFactory.createLinearRing(coords), null);
	}
	
}
