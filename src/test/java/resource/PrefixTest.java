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

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.WKTReader;

import servicemap.GeometryAdapter;
import servicemap.MySQLManager;

public class PrefixTest {

	public static void main(String[] args) throws Exception {

		System.out.println(
				"http://192.168.0.207:8080/superservicemap/rest/api/v1/?selection=44.4985;11.3342&maxDists=0.5");

		String selection = "44.4985;11.3342";
		GeometryAdapter ga = new GeometryAdapter();
		String[] elements = selection.split(";");
		Geometry sel = null;
		GeometryFactory gf = new GeometryFactory();

		if (elements.length == 2) {
			sel = new Point(new Coordinate(Double.parseDouble(elements[0]), Double.parseDouble(elements[1])),
					new PrecisionModel(), 0);
		} else if (elements.length == 4) {
			Coordinate[] ringPoints = {
					(new Coordinate(Double.parseDouble(elements[1]), Double.parseDouble(elements[0]))),
					(new Coordinate(Double.parseDouble(elements[1]), Double.parseDouble(elements[3]))),
					(new Coordinate(Double.parseDouble(elements[3]), Double.parseDouble(elements[2]))),
					(new Coordinate(Double.parseDouble(elements[3]), Double.parseDouble(elements[0]))) };
			LinearRing lr = new LinearRing(ringPoints, new PrecisionModel(), 0);
			sel = new Polygon(lr, null, gf);
		} else {

			sel = ga.unmarshal(selection);

		}

		MySQLManager msm = new MySQLManager();
		List<String> competentServiceMaps = msm.getIPIntersectingServicempas(sel);
		for (int i = 0; i < competentServiceMaps.size(); i++) {
			System.out.println(competentServiceMaps.get(i));
		}

	}

}
