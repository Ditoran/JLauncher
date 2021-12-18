package im.jahnke.jlauncher.util;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnzipTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		File file = new File("100mb.bin");
		file.delete();
	}

	@Test
	void testUnzipWithOneFile() throws MalformedURLException, URISyntaxException {

		File downloadedFile = new File("test-res\\one-file.zip");
		Unzip.unzipFileToFolder(downloadedFile, new File("."));
		File extractedFile = new File("100mb.bin");
		assertTrue("File does not exist", extractedFile.exists());
	}

}
