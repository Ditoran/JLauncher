package im.jahnke.jlauncher.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Checksum {

	private Logger log = Logger.getLogger(Checksum.class.getName());

	private File file;

	public Checksum(File file) {
		this.file = file;
	}

	public String getMD5() {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
		return getChecksum(md);
	}

	public String getSHA256() {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
		return getChecksum(md);
	}

	private String getChecksum(MessageDigest md) {
		byte[] fileBytes;
		try {
			fileBytes = Files.readAllBytes(Paths.get(this.file.toURI()));
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
		md.update(fileBytes);
		byte[] digest = md.digest();
		String checksum = bytesToHex(digest);
		return checksum;
	}

	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = HEX_ARRAY[v >>> 4];
			hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
		}
		return new String(hexChars);
	}

}
