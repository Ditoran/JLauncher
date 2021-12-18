package im.jahnke.jlauncher.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;

import im.jahnke.jlauncher.IConfigurationManager;
import im.jahnke.jlauncher.JarLauncher;
import im.jahnke.jlauncher.helper.PathHelper;

public class SplashScreenViewer {

	private Logger log = Logger.getLogger(SplashScreenViewer.class.getName());

	private JFrame splashScreenFrame;
	private JPanel splashScreenPanel;
	private JProgressBar progressBar;

	private IConfigurationManager configManager;

	public SplashScreenViewer(IConfigurationManager configManager) {
		this.configManager = configManager;
	}

	public void showSplashScreen() throws MalformedURLException {

		this.splashScreenFrame = new JFrame();
		this.splashScreenPanel = new JPanel(new BorderLayout());
		this.progressBar = new JProgressBar();

		this.splashScreenFrame.setSize(400, 250);
		this.splashScreenFrame.setAlwaysOnTop(true);
		this.splashScreenFrame.setLocationRelativeTo(null);
		this.splashScreenFrame.setUndecorated(true);
		this.splashScreenFrame.setLayout(new BorderLayout());

		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setStringPainted(true);

		String splashImagePath = PathHelper.getAbsoluteFilePathInWorkingDirectory(configManager.getProperty("splash"));
		ImageIcon icon = new ImageIcon(splashImagePath);
		JLabel image = new JLabel();
		image.setIcon(icon);
		splashScreenPanel.add(image, BorderLayout.CENTER);

		int lineThickness = ((LineBorder) progressBar.getBorder()).getThickness();
		Color lineColor = ((LineBorder) progressBar.getBorder()).getLineColor();
		splashScreenPanel
				.setBorder(BorderFactory.createMatteBorder(lineThickness, lineThickness, 0, lineThickness, lineColor));

		splashScreenFrame.add(splashScreenPanel, BorderLayout.CENTER);
		splashScreenFrame.add(progressBar, BorderLayout.AFTER_LAST_LINE);

		SplashScreenProgressHandler handler = new SplashScreenProgressHandler(progressBar);

		this.splashScreenFrame.setVisible(true);

		Thread t = new Thread(() -> {
			initSplashFrame(handler);
			splashScreenFrame.setVisible(false);
			splashScreenFrame.dispose();
		});
		t.start();
	}

	private void initSplashFrame(SplashScreenProgressHandler handler) {
		try {
			handler.setText("Updating...");
			Thread.sleep(1000);
			handler.setValue(25);
			Thread.sleep(1000);
			handler.setValue(50);
			Thread.sleep(1000);
			handler.setValue(75);
			Thread.sleep(1000);
			handler.setDone();
			new JarLauncher()
					.launchApp(PathHelper.getAbsoluteFilePathInWorkingDirectory(configManager.getProperty("startup")));
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}
}
