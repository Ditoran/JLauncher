package im.jahnke.jlauncher.httpconnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HtmlTextDownloader implements IHtmlDownloader {

	private Logger log = Logger.getLogger(HtmlTextDownloader.class.getName());

	private URL downloadUrl;
	private String text;

	public HtmlTextDownloader(URL downloadUrl) {
		this.downloadUrl = downloadUrl;
		this.text = null;
	}

	public void download() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(downloadUrl.openStream()))) {
			StringBuilder sb = new StringBuilder();
			String s = null;
			while ((s = reader.readLine()) != null) {
				sb.append(s);
				sb.append(System.lineSeparator());
			}
			this.text = sb.toString().trim();
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public String getText() {
		return this.text;
	}
}
