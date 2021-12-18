package im.jahnke.jlauncher.gui;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class SplashScreenProgressHandler {

	private JProgressBar progressBar;

	public SplashScreenProgressHandler(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public void setValue(int value) {
		SwingUtilities.invokeLater(() -> {
			this.progressBar.setValue(value);
			this.progressBar.setStringPainted(true);
		});
	}

	public void setDone() {
		SwingUtilities.invokeLater(() -> {
			this.progressBar.setValue(this.progressBar.getMaximum());
			this.progressBar.setString("Done");
		});
	}

	public void setText(String text) {
		SwingUtilities.invokeLater(() -> {
			this.progressBar.setString(text);
		});
	}
}
