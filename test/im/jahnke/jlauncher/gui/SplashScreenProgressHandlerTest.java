package im.jahnke.jlauncher.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JProgressBar;

import org.junit.Test;

public class SplashScreenProgressHandlerTest {

	@Test
	public void testSplashScreenProgressHandler() throws InterruptedException {
		JProgressBar progressBar = new JProgressBar();
		progressBar.setMaximum(150);
		SplashScreenProgressHandler handler = new SplashScreenProgressHandler(progressBar);
		handler.setValue(77);
		handler.setText("test123");

		// Wait for dispatch thread
		Thread.sleep(50);

		assertEquals(77, progressBar.getValue());
		assertEquals("test123", progressBar.getString());

		handler.setDone();

		// Wait for dispatch thread
		Thread.sleep(50);

		assertEquals(150, progressBar.getValue());
		assertEquals("Done", progressBar.getString());
	}

}
