package co.za.tvictor.dirscanrest.model;

import org.springframework.stereotype.Component;

@Component
public class DirectoryItem {
	
	private String name;
	private String fullPath;
	private String _self;
	private FileAttributes attributes;
	
	//Default Empty Contructor
	public DirectoryItem(){
		
	}
	
	public DirectoryItem(String name, String fullPath, boolean isDirectory, String fileSize,String _self,String owner, boolean canRead, boolean canWrite) {
		super();
		this.name = name;
		this.fullPath = fullPath;
		
		if(isDirectory){this._self = _self;}
		
		this.attributes = new FileAttributes(canRead, canWrite, owner, isDirectory,fileSize);
	
	}

	public String getName() {
		return name;
	}

	public String getFullPath() {
		return fullPath;
	}


	public String get_self() {
		return _self;
	}


	public FileAttributes getAttributes() {
		return attributes;
	}	
	

}
