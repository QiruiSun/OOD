package qirui.filesystem;

public abstract class Entry {
	protected Dir parent;
	protected String name;
	protected long createdAt;
	protected long updatedAt;
	

	public abstract boolean isFile();

	public Entry(String name, Dir parent) {
		this.name = name;
		this.parent= parent;
		this.createdAt = System.currentTimeMillis();
	}

	public String info() {
		return name + ", " + "created_at: " + createdAt + ", " + "updated_at: " + updatedAt;
	}

	public void rename(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
}