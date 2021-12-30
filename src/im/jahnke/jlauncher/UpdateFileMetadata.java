package im.jahnke.jlauncher;

public class UpdateFileMetadata {

	private String updateFileName;
	private String checksum;
	private String version;

	public void parseMetadata(String text) {
		for (String line : text.split(System.lineSeparator())) {
			String[] splitRow = line.split(":", 2);
			String parameter = splitRow[0];
			String value = splitRow[1];
			switch (parameter) {
			case "updatefile":
				updateFileName = value.trim();
				break;
			case "checksum":
				checksum = value.trim();
				break;
			case "version":
				version = value.trim();
				break;
			default:
				break;
			}
		}
	}

	public String getUpdateFileName() {
		return updateFileName;
	}

	public String getChecksum() {
		return checksum;
	}

	public String getVersion() {
		return version;
	}
}
