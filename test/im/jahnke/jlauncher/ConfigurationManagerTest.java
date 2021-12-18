package im.jahnke.jlauncher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class ConfigurationManagerTest {

	@Test
	public void testGetProperty() {
		ConfigurationManager manager = new ConfigurationManager("test-res\\test.properties");
		assertEquals("test123", manager.getProperty("testparam"));
	}
}
