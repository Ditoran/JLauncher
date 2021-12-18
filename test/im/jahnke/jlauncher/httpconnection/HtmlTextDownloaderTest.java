package im.jahnke.jlauncher.httpconnection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class HtmlTextDownloaderTest {

	@Test
	public void testDownload() throws MalformedURLException {
		HtmlTextDownloader downloader = new HtmlTextDownloader(
				new URL("http://dominikjahnke.de/jlauncher/test/testHtmlTextDownloader.txt"));
		downloader.download();
		assertEquals("success", downloader.getText());
	}

}
