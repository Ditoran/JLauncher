package im.jahnke.jlauncher;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.net.MalformedURLException;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import im.jahnke.jlauncher.util.ChecksumException;

public class AppUpdaterTest {

	@AfterEach
	public void tearDown() {
		String[] filesToDelete = { "update.upd", "file1.txt", "file2.txt", "file3.txt" };
		for (String fileName : filesToDelete) {
			File file = new File(fileName);
			if (file.exists()) {
				file.delete();
			}
		}
	}

	@Test
	public void testIsUpdateAvailable() {
		ConfigurationManager configManager = new ConfigurationManager("test-res\\test.properties");
		String version = configManager.getProperty("version");
		String updateFile = configManager.getProperty("updatefile");

		AppUpdater updater = new AppUpdater(version, updateFile);

		assertTrue(updater.isUpdateAvailable());
	}

	@Test
	public void testIsUpdateAvailableMalformedParameters() {
		ConfigurationManager configManager = new ConfigurationManager("test-res\\malformed.properties");
		String version = configManager.getProperty("version");
		String updateFile = configManager.getProperty("updatefile");

		AppUpdater updater = new AppUpdater(version, updateFile);

		assertFalse(updater.isUpdateAvailable());
	}

	@Test
	public void testDownloadUpdateAndInstall() throws MalformedURLException, ChecksumException {
		AppUpdater updater = new AppUpdater("1.0",
				"https://dominikjahnke.de/jlauncher/test/version_testappupdater.txt");
		File downloadFile = updater.downloadUpdate();
		assertTrue(downloadFile.exists());
		updater.installUpdate();
		assertTrue(new File("file1.txt").exists());
		assertTrue(new File("file2.txt").exists());
		assertTrue(new File("file3.txt").exists());
	}
}
