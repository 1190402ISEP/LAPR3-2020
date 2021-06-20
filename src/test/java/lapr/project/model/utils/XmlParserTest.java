package lapr.project.model.utils;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class XmlParserTest {

	@Test
	void getInvalidDocumentTest () {

		assertThrows( NullPointerException.class, () -> XmlParser.getDocument( null ) );
	}

	@Test
	void normalizeDocumentTest () {

		Document doc = XmlParser.getDocument( "LAPR3-20202021.xml" );
		Document ex = doc;
		doc.normalize();

		assertEquals( doc, XmlParser.normalizeDocument( ex ) );
	}
}
