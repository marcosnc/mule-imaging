package org.mule.modules.imaging.automation.functional;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mule.modules.imaging.ImagingConnector;
import org.mule.modules.imaging.ImagingException;
import org.mule.tools.devkit.ctf.junit.AbstractTestCase;

public class ReadTestCases extends AbstractTestCase<ImagingConnector> {

	public ReadTestCases() {
		super(ImagingConnector.class);
	}

	@Before
	public void setup() {
		// TODO
	}

	@After
	public void tearDown() {
		// TODO
	}

	@Test
	public void verify() throws ImagingException {
		java.awt.image.BufferedImage expected = null;
		java.io.InputStream source = null;
		assertEquals(getConnector().read(source), expected);
	}

}