package im.jahnke.jlauncher;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JarLauncher {

	private Logger log = Logger.getLogger(JarLauncher.class.getName());

	public Process launchApp(String pathOfJar) {
		Process proc = null;
		try {
			proc = Runtime.getRuntime().exec("java -jar " + pathOfJar);
			if (proc.isAlive()) {
				return proc;
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
		return proc;
	}

}
