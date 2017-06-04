# dir-scan-api
This REST API exposes a service that lists files and directories on the local file system in a hateoas format

## To build project and run:

Run:  **mvn package docker:build docker:run**

This will create the image in your local docker repo and run it.
Pressing ctr-C will kill the container and delete it. The image will still be available to run using docker run.

To run it from the Dockerfile, first run: **mvn package**

Then run the docker build: **docker build -t tvictor/dir-scan-api .** in the project root. This version will be tagged as latest.

Once build is complete to start container:  **docker run --name dir-scan-api -p 8080:8080 -t tvictor/dir-scan-api**

To run project on local filesystem outside of docker container: **mvn spring-boot:run**

## Volume mounting

The api lists files and directories for the local file system, but the host file system can be mounted to the running container

Once the image is built, start the container using: **docker run --name dir-scan -v /:/home/hostroot -p 8080:8080 -t tvictor/dir-scan-api:1.0-SNAPSHOT**

This mounts the local host file system root, to the container home directory as */home/hostroot*

## Testing the API

To see the container file system root : **http://localhost:8080/**
If you mounted the host file system you should be able to see your own systems files listed: **http://localhost:8080/home/hostroot/**

Because the API is hypermedia linked, you would browse file and folders as you would the directory structure in a linux terminal.
