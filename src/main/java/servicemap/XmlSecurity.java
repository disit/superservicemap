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

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

public final class XmlSecurity {

  private XmlSecurity() {
  }

  public static DocumentBuilderFactory newSecureDocumentBuilderFactory() throws ParserConfigurationException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    secureDocumentBuilderFactory(dbf);
    return dbf;
  }

  public static SAXParserFactory newSecureSaxParserFactory()
          throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
    SAXParserFactory spf = SAXParserFactory.newInstance();
    secureSaxParserFactory(spf);
    return spf;
  }

  private static void secureDocumentBuilderFactory(DocumentBuilderFactory dbf) throws ParserConfigurationException {
    dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
    dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
    dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
    dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
    dbf.setXIncludeAware(false);
    dbf.setExpandEntityReferences(false);
  }

  private static void secureSaxParserFactory(SAXParserFactory spf)
          throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
    spf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
    spf.setFeature("http://xml.org/sax/features/external-general-entities", false);
    spf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
    spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
    spf.setXIncludeAware(false);
  }
}
