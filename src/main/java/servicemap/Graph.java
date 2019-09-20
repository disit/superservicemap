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

import java.net.URI;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Polygon;

@XmlRootElement
@JsonSerialize
public class Graph {
	
	private URI uri;
	private Polygon boundingBox;
	private List<URI> dependencies;
	
	public URI getUri() {
		return uri;
	}
	public void setUri(URI uri) {
		this.uri = uri;
	}
	
	@XmlJavaTypeAdapter(PolygonAdapter.class)
	public Polygon getBoundingBox() {
		return boundingBox;
	}
	public void setBoundingBox(Polygon boundingBox) {
		this.boundingBox = boundingBox;
	}
	
	@XmlElementWrapper
	@XmlElement(name="dependency")
	public List<URI> getDependencies() {
		return dependencies;
	}
	public void setDependencies(List<URI> dependency) {
		this.dependencies = dependency;
	}

}
