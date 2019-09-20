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

import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryResult;

import virtuoso.rdf4j.driver.VirtuosoRepository;

public class VirtuosoTest {
	
	public static void main(String[] args) throws Exception {
		String graph = "http://www.disit.org/km4city/resource/Eventi/Eventi_a_Firenze";
		
		VirtuosoRepository repository = new VirtuosoRepository("jdbc:virtuoso://192.168.0.207:1111/log_enable=0", "dba", "dba");
		RepositoryConnection conn207 = repository.getConnection();
		RepositoryResult<Statement> result = conn207.getStatements(null, null, null, SimpleValueFactory.getInstance().createIRI(graph));
		
		RepositoryConnection conn113 = new VirtuosoRepository("jdbc:virtuoso://192.168.0.113:1111/log_enable=0", "dba", "dba").getConnection();
		conn113.add(result, SimpleValueFactory.getInstance().createIRI(graph));
		
		conn207.close();
		conn113.close();
	}

	/*
	public static void main(String[] args) throws Exception {
		String graph = "http://www.disit.org/km4city/resource/GeolocatedObject/Fontanelli_kmz";

		RepositoryConnection conn207 = new VirtuosoRepository("jdbc:virtuoso://192.168.0.207:1111/log_enable=0", "dba", "dba").getConnection();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		RDFWriter writer = Rio.createWriter(RDFFormat.RDFXML, output);
		conn207.exportStatements(null, null, null, false, writer, SimpleValueFactory.getInstance().createIRI(graph));
		
		ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
		RepositoryConnection conn113 = new VirtuosoRepository("jdbc:virtuoso://192.168.0.113:1111/log_enable=0", "dba", "dba").getConnection();
		conn113.add(input, "", RDFFormat.RDFXML, SimpleValueFactory.getInstance().createIRI(graph));
		
		IOUtils.closeQuietly(output);
		IOUtils.closeQuietly(input);
		conn207.close();
		conn113.close();
	}
	*/
	/*
	public static void main(String[] args) {

		VirtuosoRepository repository = new VirtuosoRepository("jdbc:virtuoso://192.168.0.207:1111/log_enable=0", "dba", "dba");
		RepositoryConnection conn = repository.getConnection();

		String graph = "http://www.disit.org/km4city/resource/GrafoStradale/Grafo_stradale_Firenze";
		String query = "select * from <" + graph + "> where { ?s ?p ?o }";
		GraphQueryResult result = conn.prepareGraphQuery(query).evaluate();
		Model resultModel = QueryResults.asModel(result);
		conn.close();

		System.out.println(resultModel);

		conn = new VirtuosoRepository("jdbc:virtuoso://192.168.0.113:1111/log_enable=0", "dba", "dba").getConnection();
		conn.add(resultModel, SimpleValueFactory.getInstance().createIRI(graph));
		result = conn.prepareGraphQuery(query).evaluate();
		resultModel = QueryResults.asModel(result);
		conn.close();

		System.out.println(resultModel);
	}
	 */
}
