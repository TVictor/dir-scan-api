package co.za.tvictor.dirscanrest.service;

import co.za.tvictor.dirscanrest.model.DirectoryList;

public interface DirectoryInfoServiceInterface {
	
	public DirectoryList generateDirectoryModel(String urlPath);

}
