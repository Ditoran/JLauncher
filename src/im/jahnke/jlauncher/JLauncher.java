package im.jahnke.jlauncher;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import im.jahnke.jlauncher.gui.SplashScreenViewer;
import im.jahnke.jlauncher.util.ChecksumException;

public class JLauncher {

	private static Logger log = Logger.getLogger(JLauncher.class.getName());

	private static String configFile = "launch.properties";

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ConfigurationManager configManager = new ConfigurationManager(configFile);
			String version = configManager.getProperty("version");
			String updateFile = configManager.getProperty("updatefile");
			AppUpdater updater = new AppUpdater(version, updateFile);
			if (updater.isUpdateAvailable()) {
				if (JOptionPane.showConfirmDialog(null, "Update available. Download now?", "Update",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					try {
						updater.downloadUpdate();
						updater.installUpdate();
					} catch (MalformedURLException | ChecksumException e) {
						log.log(Level.SEVERE, e.getMessage(), e);
					}
				}
			}
			SplashScreenViewer splashScreenViewer = new SplashScreenViewer(configManager);
			try {
				splashScreenViewer.showSplashScreen();
			} catch (MalformedURLException e) {
				log.log(Level.SEVERE, e.getMessage(), e);
			}
		});
	}

}
