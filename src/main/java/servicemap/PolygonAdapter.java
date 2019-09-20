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

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.WKTReader;
@JsonSerialize
public class PolygonAdapter extends XmlAdapter<String, Polygon> {

	@Override
	public Polygon unmarshal(String wkt) throws Exception {
		return wkt != null ? (Polygon) new WKTReader().read(wkt) : null;
	}

	@Override
	public String marshal(Polygon g) {
		return g != null ? g.toText() : null;
	}

}