package im.jahnke.jlauncher.httpconnection;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HtmlFileDownloader implements IHtmlDownloader {

	private Logger log = Logger.getLogger(HtmlFileDownloader.class.getName());

	private String fileName;
	private URL downloadUrl;

	public HtmlFileDownloader(URL downloadUrl, String fileName) {
		this.fileName = fileName;
		this.downloadUrl = downloadUrl;
	}

	public void download() {
		try (InputStream downloadInputStream = downloadUrl.openStream();
				ReadableByteChannel rbc = Channels.newChannel(downloadUrl.openStream());
				FileOutputStream fos = new FileOutputStream(fileName);) {
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public String getFileName() {
		return fileName;
	}

	public File getFile() {
		return new File(fileName);
	}

}
