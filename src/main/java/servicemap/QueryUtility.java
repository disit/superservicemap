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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.util.GeometricShapeFactory;
@JsonSerialize
public class QueryUtility {

	public static Polygon getPolygon(String query) {
		String tmp = query.substring(query.indexOf("?selection="));
		tmp = tmp.substring(0, tmp.indexOf("&")).replace("?selection=", "");

		List<Double> lats = new ArrayList<Double>();
		List<Double> longs = new ArrayList<Double>();
		while (tmp.indexOf(";") != -1) {
			lats.add(Double.parseDouble(tmp.substring(0, tmp.indexOf(";"))));
			tmp = tmp.substring(tmp.indexOf(";") + 1);
			if (tmp.indexOf(";") != -1) {
				longs.add(Double.parseDouble(tmp.substring(0, tmp.indexOf(";"))));
				tmp = tmp.substring(tmp.indexOf(";") + 1);
			}
			else {
				longs.add(Double.parseDouble(tmp));
			}
		}

		Comparator<Double> comp = new Comparator<Double>() {
			@Override
			public int compare(Double d1, Double d2)
			{
				return d1.compareTo(d2);
			}
		};
		lats.sort(comp);
		longs.sort(comp);

		double minLat = lats.get(0);
		double maxLat = lats.get(1);
		double minLong = longs.get(0);
		double maxLong = longs.get(1);

		GeometryFactory geometryFactory = new GeometryFactory();
		Coordinate[] coords = new Coordinate[] { 
				new Coordinate(minLong, minLat), new Coordinate(maxLong, minLat),
				new Coordinate(maxLong, maxLat), new Coordinate(minLong, maxLat), new Coordinate(minLong, minLat)
		};
		return geometryFactory.createPolygon(geometryFactory.createLinearRing(coords), null);
	}

	public static Geometry createWktCircle(double x, double y, double radius) {
		GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
		shapeFactory.setNumPoints(32);
		shapeFactory.setCentre(new Coordinate(x, y));
		shapeFactory.setSize(radius * 2);
		return shapeFactory.createCircle();
	}
	
	

}
