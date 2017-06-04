package co.za.tvictor.dirscanrest.model;

import java.util.ArrayList;

public class DirectoryList{

	private String currentDir;
	private String _self;
	private String _parent;
	private ArrayList<DirectoryItem> directoryListing;
	
    public DirectoryList(String currentDir,String _self, String _parent){
    	
    	ArrayList<DirectoryItem> dirItem = new ArrayList<DirectoryItem>();

    	this.currentDir = currentDir;
    	this._self = _self;    	
    	this._parent = _parent;
    	this.directoryListing = dirItem;
    }

	public String getCurrentDir() {
		return currentDir;
	}

	public String get_self() {
		return _self;
	}

	public ArrayList<DirectoryItem> getDirectoryListing() {
		return directoryListing;
	}
	
	public void addItem(DirectoryItem item){
		this.directoryListing.add(item);
	}
	
	public String get_parent() {
		return _parent;
	}


}