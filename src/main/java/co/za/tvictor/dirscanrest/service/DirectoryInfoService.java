package co.za.tvictor.dirscanrest.service;

import java.io.File;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import co.za.tvictor.dirscanrest.model.DirectoryItem;
import co.za.tvictor.dirscanrest.model.DirectoryList;

@Component
@Slf4j
public class DirectoryInfoService implements DirectoryInfoServiceInterface {

	private String hostedUrl;

	@Override
	public DirectoryList generateDirectoryModel(String urlPath) {

		log.info("Retrieving directory listing at: " + urlPath);

		//function to set the parent link.
		String[] selfPieces = urlPath.split("/");
		String parent = "";

		for (int i = 0; i < selfPieces.length - 1; i++) {
			if (selfPieces[i].length() > 0) {
				parent += "/" + selfPieces[i];
			}
		}

		//create root model
		DirectoryList dirList = new DirectoryList(urlPath, hostedUrl + urlPath, hostedUrl + parent);


		//list files by path, correlated with path of rest api call from DirectoryScanController
		File file = new File(urlPath);

		boolean ownershipFail = false;

		if (file.exists()) {

			File[] files = file.listFiles();


			for (File f : files) {

				String owner = "";

				try {
					owner = java.nio.file.Files.getOwner(f.toPath()).getName();
				} catch (IOException e) {
					ownershipFail = true;
				}
				// This is needed to suppress the error trace of trying to get the owner of a file
				// when the local filesystem is mounted in the docker container
				// This will at least print that it tried
				if (ownershipFail) {
					log.info("mounted file ownership could not be retrieved: " + f.getName());
					ownershipFail = false;
				}

				DirectoryItem item = new DirectoryItem(f.getName(), f.getAbsolutePath(), f.isDirectory()
						, FileUtils.byteCountToDisplaySize(f.length()), hostedUrl + f.getAbsolutePath(), owner, f.canRead(), f.canWrite());


				dirList.addItem(item);

			}

			return dirList;

		} else {
			return null;
		}


	}

	public void setHostedUrl(String hostedUrl) {
		this.hostedUrl = hostedUrl;
	}

}
