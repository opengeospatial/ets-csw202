package org.opengis.cite.csw20;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Provides utility methods that assist with the handling of test run arguments.
 */
public class TestRunArguments {

    /**
     * Validates the given test run arguments. An
     * <code>IllegalArgumentException</code> is thrown if a required argument is
     * missing or invalid. The recognized arguments are listed below.
     * <ul>
     * <li>{@value org.opengis.cite.csw20.CSW20#CAPABILITIES_URL}
     * (required)</li>
     * </ul>
     * 
     * @param testRunArgs
     *            A Document containing a set of XML properties; each entry
     *            (key-value pair) is a test run argument.
     * @return A valid set of test run arguments.
     */
    public static Properties validateArguments(Document testRunArgs) {
        NodeList entries = testRunArgs.getDocumentElement().getElementsByTagName("entry");
        if (entries.getLength() == 0) {
            throw new IllegalArgumentException("No test run arguments.");
        }
        // load defaults?
        Properties args = new Properties();
        for (int i = 0; i < entries.getLength(); i++) {
            Element entry = (Element) entries.item(i);
            args.setProperty(entry.getAttribute("key"), entry.getTextContent().trim());
        }
        String capabilitiesUrl = args.getProperty(CSW20.CAPABILITIES_URL);
        try {
            URI uriRef = new URI(capabilitiesUrl);
            if (!uriRef.isAbsolute()) {
                throw new IllegalArgumentException(String.format("Not an absolute URI: %s", uriRef));
            }
        } catch (URISyntaxException e1) {
            throw new IllegalArgumentException(String.format("Invalid URI reference: %s", capabilitiesUrl));
        } catch (NullPointerException e2) {
            throw new IllegalArgumentException(String.format("Missing required argument: %s", CSW20.CAPABILITIES_URL));
        }
        return args;
    }

    /**
     * Creates a document that contains a simple XML representation of the given
     * set of properties.
     * 
     * @param props
     *            A set of properties (key-value pairs).
     * @return A Document node, wherein each property is represented by an
     *         entry[@key] element.
     */
    public static Document propertiesAsDocument(Properties props) {
        Document doc = null;
        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            props.storeToXML(outStream, "Test run arguments");
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = builder.parse(new ByteArrayInputStream(outStream.toByteArray()));
        } catch (Exception e) { // very unlikely
            Logger.getLogger(TestRunArguments.class.getName()).log(Level.WARNING, "Failed to create Document node. {0}",
                    e.getMessage());
        }
        return doc;
    }

}
