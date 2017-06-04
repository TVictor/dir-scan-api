package co.za.tvictor.dirscanrest.model;

public class FileAttributes {
	
	private boolean canRead;
	private boolean canWrite;
	private String owner;
	private boolean isDirectory;
	private String fileSize;
	
	public FileAttributes() {
		super();
	}

	public FileAttributes(boolean canRead, boolean canWrite, String owner, boolean isDirectory, String fileSize) {
		super();
		this.canRead = canRead;
		this.canWrite = canWrite;
		this.owner = owner;
		this.isDirectory = isDirectory;
		this.fileSize = fileSize;
	}

	public boolean isCanRead() {
		return canRead;
	}

	public boolean isCanWrite() {
		return canWrite;
	}

	public String getOwner() {
		return owner;
	}

	public boolean isDirectory() {
		return isDirectory;
	}
	
	public String  getFileSize() {
		return fileSize;
	}

}
