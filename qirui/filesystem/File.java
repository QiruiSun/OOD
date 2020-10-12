package qirui.filesystem;

public class File extends Entry {
	private byte[] content;


	public File(String name, Dir parent) {
		super(name, parent);
	}

	public byte[] getContent() {
		return content;
	}

	@Override
	public boolean isFile() {
		return true;
	}


}