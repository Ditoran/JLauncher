package im.jahnke.jlauncher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class JarLauncherTest {

	@Test
	public void testLauncher() throws IOException {
		JarLauncher launcher = new JarLauncher();
		Process process = launcher.launchApp("test-res\\\\JLauncherTestApp.jar");
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String s = null;
		while ((s = stdInput.readLine()) != null) {
			sb.append(s);
		}

		assertEquals("Test progess output", sb.toString());
	}
}
