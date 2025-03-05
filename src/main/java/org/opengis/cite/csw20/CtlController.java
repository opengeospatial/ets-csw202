package org.opengis.cite.csw20;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;

import org.w3c.dom.Document;

import com.occamlab.te.SetupOptions;
import com.occamlab.te.spi.ctl.CtlExecutor;
import com.occamlab.te.spi.executors.TestRunExecutor;
import com.occamlab.te.spi.jaxrs.TestSuiteController;

public class CtlController implements TestSuiteController {

	private TestRunExecutor executor;

	private Properties etsProperties = new Properties();

	/**
	 * Constructs a controller object for this test suite. The location of the main CTL
	 * script is read from the "main-script" property in the ets.properties file (a
	 * classpath resource).
	 */
	public CtlController() {
		SetupOptions setupOpts = new SetupOptions();
		try (InputStream is = getClass().getResourceAsStream("ets.properties")) {
			this.etsProperties.load(is);
			String mainScriptPath = etsProperties.getProperty("main-script");
			File ctlFile = findScriptFile(URI.create(mainScriptPath));
			setupOpts.addSource(ctlFile);
			this.executor = new CtlExecutor(setupOpts);
		}
		catch (IOException ex) {
			Logger.getLogger(getClass().getName())
				.log(Level.SEVERE, "Failed to initialize CtlController. {0}", ex.getMessage());
		}
	}

	/**
	 * A convenience method for running the test suite using a command-line interface.
	 * @param args Test run arguments (optional). The first argument must refer to a local
	 * XML properties file containing the expected set of test run arguments. If no
	 * argument is supplied, the file located at ${user.home}/test-run-props.xml will be
	 * used.
	 * @throws Exception If an error errors during the test run.
	 */
	public static void main(String[] args) throws Exception {
		File propsFile;
		if (args.length == 0) {
			propsFile = new File(System.getProperty("user.home"), "test-run-props.xml");
		}
		else {
			String xmlProps = args[0];
			propsFile = (xmlProps.startsWith("file:")) ? new File(URI.create(xmlProps)) : new File(xmlProps);
		}
		if (!propsFile.isFile()) {
			throw new IllegalArgumentException("Test run arguments not found at " + propsFile);
		}
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document testRunArgs = db.parse(propsFile);
		TestSuiteController controller = new CtlController();
		Source results = controller.doTestRun(testRunArgs);
		Logger.getLogger(CtlController.class.getName()).log(Level.INFO, "Test results: {0}", results.getSystemId());
	}

	public String getCode() {
		return etsProperties.getProperty("ets-code");
	}

	public String getVersion() {
		return etsProperties.getProperty("ets-version");
	}

	public String getTitle() {
		return etsProperties.getProperty("ets-title");
	}

	public Source doTestRun(Document testRunArgs) throws Exception {
		Properties validArgs = TestRunArguments.validateArguments(testRunArgs);

		return executor.execute(TestRunArguments.propertiesAsDocument(validArgs));
	}

	/**
	 * Creates a File from the given URI reference. If the URI is not absolute, is is
	 * resolved against the location of the TE_BASE/scripts directory; if a file does not
	 * exist at this location the URI is assumed to be a classpath reference.
	 * @param uri An absolute or relative URI.
	 * @return A File object, or null if one could not be created.
	 */
	final File findScriptFile(URI uri) {
		File ctlFile = null;
		File baseDir = SetupOptions.getBaseConfigDirectory();
		if (!uri.isAbsolute()) {
			File scriptsDir = new File(baseDir, "scripts");
			ctlFile = new File(scriptsDir, uri.getPath());
		}
		if (null == ctlFile || !ctlFile.isFile()) {
			URL resource = getClass().getResource(uri.getPath());
			try {
				ctlFile = new File(resource.toURI());
			}
			catch (URISyntaxException ex) {
				Logger.getLogger(getClass().getName()).log(Level.INFO, "Invalid URI: {0}", ex);
			}
		}
		Logger.getLogger(getClass().getName()).log(Level.CONFIG, "Created File object: {0}", ctlFile);
		return ctlFile;
	}

}
