package im.jahnke.jlauncher;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import im.jahnke.jlauncher.helper.PathHelper;

public class ConfigurationManager implements IConfigurationManager {

	private Logger log = Logger.getLogger(ConfigurationManager.class.getName());

	String rootPath;
	String appConfigPath;
	Properties properties;

	public ConfigurationManager(String configFile) {
		rootPath = PathHelper.getWorkingDir();
		appConfigPath = PathHelper.getAbsoluteFilePathInWorkingDirectory(configFile);
		try {
			loadProperties();
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private void loadProperties() throws FileNotFoundException, IOException {
		properties = new Properties();
		properties.load(new FileInputStream(appConfigPath));
	}

	public String getProperty(String propertyName) {
		return properties.getProperty(propertyName);
	}
}
