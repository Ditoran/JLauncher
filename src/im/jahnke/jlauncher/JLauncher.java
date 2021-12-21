package im.jahnke.jlauncher;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import im.jahnke.jlauncher.gui.SplashScreenViewer;

public class JLauncher {

	private static Logger log = Logger.getLogger(JLauncher.class.getName());

	private static String configFile = "launch.properties";

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ConfigurationManager configManager = new ConfigurationManager(configFile);
			AppUpdater updater = new AppUpdater();
			if (updater.isUpdateAvailable(configManager)) {
				JOptionPane.showConfirmDialog(null, "Update available. Download now?", "Update",
						JOptionPane.YES_NO_OPTION);
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
