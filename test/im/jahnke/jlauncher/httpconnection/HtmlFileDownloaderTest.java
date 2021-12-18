
/**
 * 
 */
package im.jahnke.jlauncher.httpconnection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HtmlFileDownloaderTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testFileDownload() throws IOException {
		HtmlFileDownloader download = new HtmlFileDownloader(
				new URL("file:\\\\\\C:\\Users\\Dominik\\workspace\\JLauncher\\test-res\\one-file.zip"), "test.zip");
		download.download();
		File downloadedFile = download.getFile();
		assertEquals(103754, Files.size(downloadedFile.toPath()), "Filesize does not match");
	}

	@AfterEach
	void tearDown() {
		File file = new File("test.zip");
		if (file.exists()) {
			file.delete();
		}
	}

}
