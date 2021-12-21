package im.jahnke.jlauncher;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import im.jahnke.jlauncher.httpconnection.HtmlTextDownloader;

public class AppUpdater {

	private Logger log = Logger.getLogger(AppUpdater.class.getName());

	public boolean isUpdateAvailable(ConfigurationManager configManager) {
		String version = configManager.getProperty("version");
		URL metaDataUrl = null;
		try {
			metaDataUrl = new URL(configManager.getProperty("updatefile"));
		} catch (MalformedURLException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return false;
		}
		HtmlTextDownloader fileDownloader = new HtmlTextDownloader(metaDataUrl);
		fileDownloader.download();
		UpdateFileMetadata metaData = new UpdateFileMetadata();
		metaData.parseMetadata(fileDownloader.getText());
		return !version.equals(metaData.getVersion());
	}

}
