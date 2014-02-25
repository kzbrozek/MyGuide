
package com.blstream.myguide.test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParserException;

import android.test.AndroidTestCase;

import com.blstream.myguide.R;
import com.blstream.myguide.zoolocations.ZooLocationsData;
import com.blstream.myguide.zoolocations.ZooLocationsDataParser;

/** Class containing tests for ZooLocationsDataParser. */
public class ZooLocationsDataParserTestCase extends AndroidTestCase {

	private static final String ENCODING = "UTF-8";

	/** Amount of animals in xml file from userstory */
	private static final int ANIMALS = 55;
	/** Amount of ways in xml file from userstory */
	private static final int WAYS = 82;
	/** Amount of junctions in xml file from userstory */
	private static final int JUNCTIONS = 20;

	/** Checks if tag <animals> is well parsed. */
	public void testParsingAnimals()  {
		// given
		String xmlText = "<root><animals>"
				+ "<animal lat=\"51.1052072\" lon=\"17.0754498\">Żyrafa</animal>"
				+ "<animal lat=\"51.1050185\" lon=\"17.0759970\">Struś</animal>"
				+ "</animals></root>";
		ZooLocationsDataParser parser = new ZooLocationsDataParser();
		InputStream is = null;
		ZooLocationsData data = null;
		Exception e = null;
		
		// when
		try {
			is = new ByteArrayInputStream(xmlText.getBytes(ENCODING));
			data = parser.parse(is);
		} catch (Exception e1) {
			e = e1;
		} finally {
			try {
				is.close();
			} catch (Exception e2) {
				e = e2;
			}
		}
		
		// then
		assertNull(e);
		assertEquals(2, data.getAnimals().size());
		assertEquals(0, data.getWays().size());
		assertEquals(0, data.getJunctions().size());
		assertEquals("Żyrafa", data.getAnimals().get(0).getName());
		assertEquals(51.1052072, data.getAnimals().get(0).getNode().getLatitude());
		assertEquals(17.0754498, data.getAnimals().get(0).getNode().getLongitude());
		assertEquals("Struś", data.getAnimals().get(1).getName());
		assertEquals(51.1050185, data.getAnimals().get(1).getNode().getLatitude());
		assertEquals(17.0759970, data.getAnimals().get(1).getNode().getLongitude());
	}

	/** Checks if tag <ways> is well parsed. */
	public void testParsingWays() {
		// given
		String xmlText = "<root><ways>"
				+ "<way id=\"32997558\">"
				+ "<node lat=\"51.1054430\" lon=\"17.0773945\" />"
				+ "<node lat=\"51.1054595\" lon=\"17.0774086\" />"
				+ "</way>"
				+ "<way id=\"35948032\">"
				+ "<node lat=\"51.1057838\" lon=\"17.0751055\" />"
				+ "<node lat=\"51.1051115\" lon=\"17.0745007\" />"
				+ "<node lat=\"51.1048329\" lon=\"17.0742639\" />"
				+ "</way>"
				+ "</ways></root>";
		ZooLocationsDataParser parser = new ZooLocationsDataParser();
		InputStream is = null;
		ZooLocationsData data = null;
		Exception e = null;
		
		// when
		try {
			is = new ByteArrayInputStream(xmlText.getBytes(ENCODING));
			data = parser.parse(is);
		} catch (Exception e1) {
			e = e1;
		} finally {
			try {
				is.close();
			} catch (Exception e2) {
				e = e2;
			}
		}

		// then
		assertNull(e);
		assertEquals(0, data.getAnimals().size());
		assertEquals(2, data.getWays().size());
		assertEquals(0, data.getJunctions().size());
		assertEquals(32997558, data.getWays().get(0).getId());
		assertEquals(51.1054430, data.getWays().get(0).getNodes().get(0).getLatitude());
		assertEquals(17.0773945, data.getWays().get(0).getNodes().get(0).getLongitude());
		assertEquals(51.1054595, data.getWays().get(0).getNodes().get(1).getLatitude());
		assertEquals(17.0774086, data.getWays().get(0).getNodes().get(1).getLongitude());

		assertEquals(35948032, data.getWays().get(1).getId());
		assertEquals(51.1057838, data.getWays().get(1).getNodes().get(0).getLatitude());
		assertEquals(17.0751055, data.getWays().get(1).getNodes().get(0).getLongitude());
		assertEquals(51.1051115, data.getWays().get(1).getNodes().get(1).getLatitude());
		assertEquals(17.0745007, data.getWays().get(1).getNodes().get(1).getLongitude());
		assertEquals(51.1048329, data.getWays().get(1).getNodes().get(2).getLatitude());
		assertEquals(17.0742639, data.getWays().get(1).getNodes().get(2).getLongitude());
	}

	/** Checks if tag <junctions> is well parsed. */
	public void testParsingJunctions() {
		// given
		String xmlText = "<root><ways>"
				+ "<way id=\"32997558\">"
				+ "<node lat=\"51.1054430\" lon=\"17.0773945\" />"
				+ "<node lat=\"51.1054595\" lon=\"17.0774086\" />"
				+ "</way>"
				+ "<way id=\"35948032\">"
				+ "<node lat=\"51.1057838\" lon=\"17.0751055\" />"
				+ "<node lat=\"51.1051115\" lon=\"17.0745007\" />"
				+ "</way>"
				+ "<way id=\"35948033\">"
				+ "<node lat=\"51.1054430\" lon=\"17.0773945\" />"
				+ "<node lat=\"51.1054595\" lon=\"17.0774086\" />"
				+ "</way>"
				+ "</ways>"

				+ "<junctions>"
				+ "<junction lat=\"51.1051115\" lon=\"17.0745007\">"
				+ "<way id=\"32997558\" />"
				+ "<way id=\"35948032\" />"
				+ "</junction>"
				+ "<junction lat=\"51.1069836\" lon=\"17.0729754\">"
				+ "<way id=\"32997558\" />"
				+ "<way id=\"35948033\" />"
				+ "<way id=\"35948032\" />"
				+ "</junction>"
				+ "</junctions></root>";

		ZooLocationsDataParser parser = new ZooLocationsDataParser();
		InputStream is = null;
		ZooLocationsData data = null;
		Exception e = null;
		
		// when
		try {
			is = new ByteArrayInputStream(xmlText.getBytes(ENCODING));
			data = parser.parse(is);
		} catch (Exception e1) {
			e = e1;
		} finally {
			try {
				is.close();
			} catch (Exception e2) {
				e = e2;
			}
		}

		//then
		assertNull(e);
		assertEquals(0, data.getAnimals().size());
		assertEquals(3, data.getWays().size());
		assertEquals(2, data.getJunctions().size());
		assertEquals(51.1051115, data.getJunctions().get(0).getNode().getLatitude());
		assertEquals(17.0745007, data.getJunctions().get(0).getNode().getLongitude());
		assertEquals(32997558, data.getJunctions().get(0).getWays().get(0).getId());
		assertEquals(35948032, data.getJunctions().get(0).getWays().get(1).getId());

		assertEquals(51.1069836, data.getJunctions().get(1).getNode().getLatitude());
		assertEquals(17.0729754, data.getJunctions().get(1).getNode().getLongitude());
		assertEquals(32997558, data.getJunctions().get(1).getWays().get(0).getId());
		assertEquals(35948033, data.getJunctions().get(1).getWays().get(1).getId());
		assertEquals(35948032, data.getJunctions().get(1).getWays().get(2).getId());
	}

	/**
	 * Checks if xml from userstory is well parsed (check if amount of animals,
	 * ways and junctions are good).
	 */
	public void testParsingXmlFromUserStory() {
		// given
		ZooLocationsDataParser parser = new ZooLocationsDataParser();
		InputStream is = null;
		ZooLocationsData data = null;
		Exception e = null;
		
		// when
		try {
			is = this.getContext().getResources().openRawResource(R.raw.data);
			data = parser.parse(is);
		} catch (Exception e1) {
			e = e1;
		} finally {
			try {
				is.close();
			} catch (Exception e2) {
				e = e2;
			}
		}
		
		// then
		assertNull(e);
		assertEquals(ANIMALS, data.getAnimals().size());
		assertEquals(WAYS, data.getWays().size());
		assertEquals(JUNCTIONS, data.getJunctions().size());
	}

	/**
	 * Chceck if parser throws XmlPullParserException with adequate message in
	 * case of lack of lat attribute where it is needed.
	 */
	public void testMissingLatitude() {
		// given
		String xmlText = "<root><animals>"
				+ "<animal lon=\"17.0754498\">Żyrafa</animal>"
				+ "<animal lat=\"51.1050185\" lon=\"17.0759970\">Struś</animal>"
				+ "</animals></root>";
		ZooLocationsDataParser parser = new ZooLocationsDataParser();
		InputStream is = null;
		XmlPullParserException exception = null;
		Exception e = null;
		
		// when
		try {
			is = new ByteArrayInputStream(xmlText.getBytes(ENCODING));
			parser.parse(is);
		} catch (XmlPullParserException exc) {
			exception = exc;
		} catch (Exception e1) {
			e = e1;
		} finally {
			try {
				is.close();
			} catch (Exception e2) {
				e = e2;
			}
		}
		
		//then
		assertNull(e);
		assertNotNull(exception);
		assertEquals(ZooLocationsDataParser.EXCEPTION_COORDINATE_MISSING, exception.getMessage());
	}

	/**
	 * Chceck if parser throws XmlPullParserException with adequate message in
	 * case of lack of lon attribute where it is needed.
	 */
	public void testMissingLongitude() {
		//given
		String xmlText = "<root><animals>"
				+ "<animal lat=\"51.1052072\" lon=\"17.0754498\">Żyrafa</animal>"
				+ "<animal lat=\"51.1050185\">Struś</animal>"
				+ "</animals></root>";
		ZooLocationsDataParser parser = new ZooLocationsDataParser();
		InputStream is = null;
		XmlPullParserException exception = null;
		Exception e = null;

		//when
		try {
			is = new ByteArrayInputStream(xmlText.getBytes(ENCODING));
			parser.parse(is);
		} catch (XmlPullParserException exc) {
			exception = exc;
		} catch (Exception e1) {
			e = e1;
		} finally {
			try {
				is.close();
			} catch (Exception e2) {
				e = e2;
			}
		}
		
		//then
		assertNull(e);
		assertNotNull(exception);
		assertEquals(ZooLocationsDataParser.EXCEPTION_COORDINATE_MISSING, exception.getMessage());
	}

	/**
	 * Chceck if parser throws XmlPullParserException with adequate message in
	 * case of bad format of lat attribute (it is not a double).
	 */
	public void testLatitudeBadFormat() {
		// given
		String xmlText = "<root><animals>"
				+ "<animal lat=\"ala123\" lon=\"17.0754498\">Żyrafa</animal>"
				+ "<animal lat=\"51.1050185\" lon=\"17.0759970\">Struś</animal>"
				+ "</animals></root>";

		ZooLocationsDataParser parser = new ZooLocationsDataParser();
		InputStream is = null;
		XmlPullParserException exception = null;
		Exception e = null;
		
		// when
		try {
			is = new ByteArrayInputStream(xmlText.getBytes(ENCODING));
			parser.parse(is);
		} catch (XmlPullParserException exc) {
			exception = exc;
		} catch (Exception e1) {
			e = e1;
		} finally {
			try {
				is.close();
			} catch (Exception e2) {
				e = e2;
			}
		}
		
		// then
		assertNull(e);
		assertNotNull(exception);
		assertEquals(ZooLocationsDataParser.EXCEPTION_LATITUDE_FORMAT, exception.getMessage());
	}

	/**
	 * Chceck if parser throws XmlPullParserException with adequate message in
	 * case of bad format of lon attribute (it is not a double).
	 */
	public void testLongitudeBadFormat() {
		// given
		String xmlText = "<root><animals>"
				+ "<animal lat=\"51.1052072\" lon=\"kot098\">Żyrafa</animal>"
				+ "<animal lat=\"51.1050185\" lon=\"17.0759970\">Struś</animal>"
				+ "</animals></root>";
		ZooLocationsDataParser parser = new ZooLocationsDataParser();
		InputStream is = null;
		XmlPullParserException exception = null;
		Exception e = null;
		
		// when
		try {
			is = new ByteArrayInputStream(xmlText.getBytes(ENCODING));
			parser.parse(is);
		} catch (XmlPullParserException exc) {
			exception = exc;
		} catch (Exception e1) {
			e = e1;
		} finally {
			try {
				is.close();
			} catch (Exception e2) {
				e = e2;
			}
		}
		// then
		assertNull(e);
		assertNotNull(exception);
		assertEquals(ZooLocationsDataParser.EXCEPTION_LONGITUDE_FORMAT, exception.getMessage());
	}

	/**
	 * Chceck if parser throws XmlPullParserException with adequate message in
	 * case of lack of id attribute where it is needed.
	 */
	public void testMissingId() {
		// given
		String xmlText = "<root><ways>"
				+ "<way>"
				+ "<node lat=\"51.1054430\" lon=\"17.0773945\" />"
				+ "<node lat=\"51.1054595\" lon=\"17.0774086\" />"
				+ "</way>"
				+ "</ways></root>";
		ZooLocationsDataParser parser = new ZooLocationsDataParser();
		InputStream is = null;
		XmlPullParserException exception = null;
		Exception e = null;
		
		// when
		try {
			is = new ByteArrayInputStream(xmlText.getBytes(ENCODING));
			parser.parse(is);
		} catch (XmlPullParserException exc) {
			exception = exc;
		} catch (Exception e1) {
			e = e1;
		} finally {
			try {
				is.close();
			} catch (Exception e2) {
				e = e2;
			}
		}
		
		// then
		assertNull(e);
		assertNotNull(exception);
		assertEquals(ZooLocationsDataParser.EXCEPTION_ID_MISSING, exception.getMessage());
	}

	/**
	 * Chceck if parser throws XmlPullParserException with adequate message in
	 * case of bad format of id attribute (it is not an integer).
	 */
	public void testIdBadFormat() {
		// given
		String xmlText = "<root><ways>"
				+ "<way id=\"123.456\">"
				+ "<node lat=\"51.1054430\" lon=\"17.0773945\" />"
				+ "<node lat=\"51.1054595\" lon=\"17.0774086\" />"
				+ "</way>"
				+ "</ways></root>";
		ZooLocationsDataParser parser = new ZooLocationsDataParser();
		InputStream is = null;
		XmlPullParserException exception = null;
		Exception e = null;
		
		// when
		try {
			is = new ByteArrayInputStream(xmlText.getBytes(ENCODING));
			parser.parse(is);
		} catch (XmlPullParserException exc) {
			exception = exc;
		} catch (Exception e1) {
			e = e1;
		} finally {
			try {
				is.close();
			} catch (Exception e2) {
				e = e2;
			}
		}
		
		// then
		assertNull(e);
		assertNotNull(exception);
		assertEquals(ZooLocationsDataParser.EXCEPTION_ID_FORMAT, exception.getMessage());
	}

}