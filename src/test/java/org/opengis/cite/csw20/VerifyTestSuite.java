package org.opengis.cite.csw20;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;

import com.occamlab.te.Engine;
import com.occamlab.te.Generator;
import com.occamlab.te.RuntimeOptions;
import com.occamlab.te.SetupOptions;
import com.occamlab.te.TEClassLoader;
import com.occamlab.te.TECore;
import com.occamlab.te.html.EarlToHtmlTransformation;
import com.occamlab.te.index.Index;
import com.occamlab.te.util.DocumentationHelper;

/**
 * Verifies the results of executing a CTL test suite. The test suite parameters are read
 * from a properties file located at ${user.home}/sut.properties; if this file does not
 * exist then load the default sut.properties file from the classpath
 * (src/test/resources).
 */
public class VerifyTestSuite {

	static final int VERDICT_PASS = 1;
	static final int VERDICT_SKIP = 3;
	static final int VERDICT_INHERITED_FAIL = 5;
	static final int VERDICT_FAIL = 6;

	private static Properties testProps;

	private static Properties sutProps;

	private static String previousTEBase;

	private static DocumentBuilder docBuilder;

	private static final String CSW202_NS = "http://www.opengis.net/cat/csw/2.0.2";

	private RuntimeOptions runOpts;

	private SetupOptions setupOpts;

	private File sessionDir;

	@BeforeClass
	public static void initFixture() throws IOException, ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		docBuilder = dbf.newDocumentBuilder();
		testProps = new Properties();
		try (InputStream propsInput = VerifyTestSuite.class.getResourceAsStream("/integration-test.properties")) {
			testProps.load(propsInput);
		}
		File targetDir = new File(testProps.getProperty("targetDir"));
		File teBaseDir = new File(targetDir, "te-base");
		teBaseDir.mkdir();
		for (String dir : new String[] { "users", "work" }) {
			new File(teBaseDir, dir).mkdir();
		}
		previousTEBase = System.getProperty("TE_BASE");
		System.setProperty("TE_BASE", teBaseDir.getAbsolutePath());
	}

	@BeforeClass
	public static void initTestSuiteParams() throws IOException {
		sutProps = new Properties();
		String homeDir = System.getProperty("user.home");
		File sutPropsFile = new File(homeDir, "sut.properties");
		if (sutPropsFile.exists()) {
			sutProps.load(new FileInputStream(sutPropsFile));
		}
		else {
			try (InputStream propsInput = VerifyTestSuite.class.getResourceAsStream("/sut.properties")) {
				sutProps.load(propsInput);
			}
		}
	}

	@AfterClass
	public static void restoreEnvironment() {
		if (null != previousTEBase) {
			System.setProperty("TE_BASE", previousTEBase);
		}
	}

	@Before
	public void initTestSession() {
		this.setupOpts = new SetupOptions();
		this.setupOpts.setValidate(false);
		this.runOpts = new RuntimeOptions();
		this.runOpts.setMode(com.occamlab.te.Test.TEST_MODE);
		this.runOpts.setWorkDir(setupOpts.getWorkDir());
		UUID sessionId = UUID.randomUUID();
		this.runOpts.setSessionId(sessionId.toString());
		File logDir = this.runOpts.getLogDir();
		this.sessionDir = new File(logDir, sessionId.toString());
	}

	@After
	public void generateReport() throws Exception {
		EarlToHtmlTransformation earlToHtmlTransformation = new EarlToHtmlTransformation();
		earlToHtmlTransformation.earlHtmlReport(this.sessionDir.getAbsolutePath());
	}

	@Test
	public void executeStartingTest() throws Exception {
		File scriptsDir = new File(testProps.getProperty("scriptsDir"));
		File ctlFile = new File(scriptsDir, "ctl" + System.getProperty("file.separator") + "main.xml");
		this.setupOpts.addSource(ctlFile);
		// run starting test directly to bypass input form
		QName startingTest = new QName(CSW202_NS, "Main", "csw");
		this.runOpts.setTestName(startingTest.toString());
		for (Object key : sutProps.keySet()) {
			String paramName = (String) key;
			StringBuilder kvp = new StringBuilder(paramName);
			kvp.append("=").append(sutProps.getProperty(paramName));
			this.runOpts.addParam(kvp.toString());
		}
		File indexFile = new File(this.sessionDir, "index.xml");
		Index mainIndex = Generator.generateXsl(this.setupOpts);
		mainIndex.persist(indexFile);
		File resourcesDir = new File(scriptsDir.getParentFile(), "resources");
		TEClassLoader teLoader = new TEClassLoader(resourcesDir);
		Engine engine = new Engine(mainIndex, this.setupOpts.getSourcesName(), teLoader);
		TECore core = new TECore(engine, mainIndex, this.runOpts);
		core.execute();
		Document testLog = docBuilder.parse(new File(this.sessionDir, "log.xml"));
		XPath xpath = XPathFactory.newInstance().newXPath();
		String result = xpath.evaluate("/log/endtest/@result", testLog);
		assertEquals("Unexpected result.", VERDICT_PASS, Integer.parseInt(result));
	}

}
