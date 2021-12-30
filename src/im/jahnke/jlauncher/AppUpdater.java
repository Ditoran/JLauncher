package im.jahnke.jlauncher;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import im.jahnke.jlauncher.helper.PathHelper;
import im.jahnke.jlauncher.httpconnection.HtmlFileDownloader;
import im.jahnke.jlauncher.httpconnection.HtmlTextDownloader;
import im.jahnke.jlauncher.util.Checksum;
import im.jahnke.jlauncher.util.ChecksumException;
import im.jahnke.jlauncher.util.Unzip;

public class AppUpdater {

	private Logger log = Logger.getLogger(AppUpdater.class.getName());

	private UpdateFileMetadata updateMetadata;
	private String currentVersion;
	private String updateServerPath;
	private File downloadedUpdateFile;

	public AppUpdater(String currentVersion, String updateServerPath) {
		this.currentVersion = currentVersion;
		this.updateServerPath = updateServerPath;
	}

	public boolean isUpdateAvailable() {
		if (!catchMetadata()) {
			return false;
		}
		return VersionComparer.compare(currentVersion, updateMetadata.getVersion()) < 0;
	}

	public File downloadUpdate() throws ChecksumException, MalformedURLException {
		if (this.updateMetadata == null && !catchMetadata()) {
			this.downloadedUpdateFile = null;
			return null;
		}
		HtmlFileDownloader downloader = new HtmlFileDownloader(new URL(this.updateMetadata.getUpdateFileName()),
				"update.upd");
		downloader.download();
		File file = new File("update.upd");
		if (!verifyUpdateFile(file)) {
			throw new ChecksumException("Checksum of the downloaded file does not match");
		}
		this.downloadedUpdateFile = file;
		return this.downloadedUpdateFile;
	}

	public void installUpdate() {
		Unzip.unzipFileToFolder(this.downloadedUpdateFile, new File(PathHelper.getWorkingDir()));
		if (this.downloadedUpdateFile.exists()) {
			this.downloadedUpdateFile.delete();
		}
	}

	private boolean catchMetadata() {
		URL metaDataUrl = null;
		try {
			metaDataUrl = new URL(this.updateServerPath);
		} catch (MalformedURLException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return false;
		}
		HtmlTextDownloader downloader = new HtmlTextDownloader(metaDataUrl);
		downloader.download();
		updateMetadata = new UpdateFileMetadata();
		updateMetadata.parseMetadata(downloader.getText());
		return true;
	}

	private boolean verifyUpdateFile(File updateFile) {
		Checksum checkSum = new Checksum(updateFile);
		return this.updateMetadata.getChecksum().equalsIgnoreCase(checkSum.getSHA256());
	}
}
