package co.za.tvictor.dirscanrest.controller;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import co.za.tvictor.dirscanrest.service.DirectoryInfoService;
import co.za.tvictor.dirscanrest.model.DirectoryList;

@RestController
@Slf4j
public class DirectoryScanController {

	@Autowired
	DirectoryInfoService directoryListService;

	@RequestMapping(value = "/**")
	public DirectoryList list(HttpServletRequest request) {

		//Retrieves path after root context
		String urlPath = (String) request.getAttribute(
				HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

    	/*
		    Retrieve the hosted_url environment variable
    	    and set it as the hateoas link for _self and _parent
    	    This allows the hosted container to be behind a proxy
    	    and still contain the correct links.
    	    Ideally this should be a global configuration, but for now will be in the controller
    	    as this is a simple rest service with only one operation.
    	 */
		String hosted_Url = System.getenv("hosted_url");

		//if no ENV set in assume localhost

		if (hosted_Url == null) {

			directoryListService.setHostedUrl("http://localhost:" + request.getServerPort());

		} else {

			directoryListService.setHostedUrl(hosted_Url);

		}

		DirectoryList listedFiles = directoryListService.generateDirectoryModel(urlPath);

		if (listedFiles == null) {
			throw new ResourceNotFoundException();
		}

		return listedFiles;
	}


	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "You are trying to locate a directory that does not exist")
	public class ResourceNotFoundException extends RuntimeException {

		/**
		 * For now will not do anything but return a 404 Not Found Error
		 */
		private static final long serialVersionUID = 1L;


	}


}