package org.opengis.cite.csw20;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.occamlab.te.Engine;
import com.occamlab.te.RuntimeOptions;
import com.occamlab.te.index.Index;

/**
 * Verifies the behavior of the the CSW20 test-suite.
 */
public class ITSuite {

    static Engine engine;
    static Index index;
    static RuntimeOptions runOpts;

    public static void setUpClass() {
    }

    @Test
    public void verifyTest() throws Exception {
        /**
         * later find the way to do this getting properties files for example:
         * http
         * ://stackoverflow.com/questions/11740618/is-there-a-way-to-use-maven
         * -property-in-java-class-during-compilation
         * 
         * **/
        final File f = new File(getClass().getProtectionDomain()
                .getCodeSource().getLocation().getPath());
        String target = f.getParent();
        // should be in the target folder
        System.out.println("path of class " + f.getParent());
        // String teb =
        // Misc.getResourceAsFile("target/csw-20-r10/TE_BASE").getCanonicalPath();
        String tebase = target + "/ets-csw-2.0.2-r11-SNAPSHOT/TE_BASE";
        System.out.println("TE_BASE is at " + tebase);
        System.setProperty("TE_BASE", tebase);
        String[] testRunArgs = new String[] { "-source=csw/2.0.2/ctl/main.xml" };
        com.occamlab.te.Test.main(testRunArgs);
        assertTrue(true);
    }
}
