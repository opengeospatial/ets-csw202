package org.opengis.cite.csw20;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.occamlab.te.Engine;
import com.occamlab.te.RuntimeOptions;
import com.occamlab.te.index.Index;
import com.occamlab.te.util.Misc;


/**
 * Verifies the behavior of the the CSW20 test-suite.
 */
public class VerifyTest {

 	static Engine engine;
    static Index index; 
    static RuntimeOptions runOpts;

    public static void setUpClass() {
    }


    @Test
	public void verifyTest() throws Exception{
    	/** later find the way to do this getting properties files 
    	 * for example: http://stackoverflow.com/questions/11740618/is-there-a-way-to-use-maven-property-in-java-class-during-compilation
    	 *  
    	 *  **/
    	final File f = new File(VerifyTest.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    	String target = f.getParent();
    	//should be in the target folder
    	System.out.println("path of class "+f.getParent()); 
    	//String teb = Misc.getResourceAsFile("target/csw-20-r10/TE_BASE").getCanonicalPath();
    	String tebase=target+"/csw-2.0-r10/TE_BASE/";
    	System.out.println("TE_BASE is at "+tebase);
    	System.setProperty("TE_BASE", tebase);
	    com.occamlab.te.Test teamengineTest = new com.occamlab.te.Test();
	    teamengineTest.executeTest("csw-2.0/ctl/main.xml");
	    Assert.assertTrue(true);
	}
}
