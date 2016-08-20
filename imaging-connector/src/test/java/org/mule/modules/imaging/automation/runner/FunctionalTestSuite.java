package org.mule.modules.imaging.automation.runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.mule.modules.imaging.automation.functional.ReadTestCases;
import org.mule.modules.imaging.ImagingConnector;
import org.mule.tools.devkit.ctf.mockup.ConnectorTestContext;

@RunWith(Suite.class)
@SuiteClasses({ ReadTestCases.class })
public class FunctionalTestSuite {

	@BeforeClass
	public static void initialiseSuite() {
		ConnectorTestContext.initialize(ImagingConnector.class);
	}

	@AfterClass
	public static void shutdownSuite() {
		ConnectorTestContext.shutDown();
	}

}