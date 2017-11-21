# Directory Scan API

[![Build Status](https://travis-ci.org/victeck/dir-scan-api.svg?branch=master)](https://travis-ci.org/victeck/dir-scan-api)

This REST API exposes a service that lists files and directories on the local file system in a hateoas format

## To build project and run:

Run:  **mvn package docker:build docker:run**

This will create the image in your local docker repo and run it.
Pressing ctr-C will kill the container and delete it. The image will still be available to run using docker run.

To run it from the Dockerfile, first run: **mvn package**

Then run the docker build: **docker build -t tvictor/dir-scan-api .** in the project root. This version will be tagged as latest.

Once build is complete, to start container:  **docker run --name dir-scan-api -p 8080:8080 -t tvictor/dir-scan-api**

To run project on local filesystem outside of docker container: **mvn spring-boot:run**

## Volume mounting

The api lists files and directories for the local file system, but the host file system can be mounted to the running container

Once the image is built, start the container using: **docker run --name dir-scan -v /:/home/hostroot -p 8080:8080 -t tvictor/dir-scan-api:1.0-SNAPSHOT**

This mounts the local host file system root, to the container home directory as */home/hostroot*

## Testing the API

To see the container file system root : **http://localhost:8080/**

If you mounted the host file system you should be able to see your own systems files listed: 

**http://localhost:8080/home/hostroot/**

Because the API is hypermedia linked, you would browse files and folders as you would the directory structure in a linux terminal.

## Setup behind reverse proxy or hosted url entry:
To change the hypermedia uri entries for _self and _parent you will need to pass in an environment variable into the container on startup:

**docker run --name dir-scan-api -e hosted_url=mydomain.co.za -p 8080:8080 -t tvictor/dir-scan-api**

Links will then contain: http://mydomain.co.za:8080/

## Base Image

The base docker image is based on Fabric8's alpine openjdk image : [fabric8/java-alpine-openjdk8-jdk](https://hub.docker.com/r/fabric8/java-alpine-openjdk8-jdk/)

1. It is leightweight at only 103.5 MB
2. It is very easy to load an application into, by only dropping artefacts into /deployments/ and starting run-java.sh
3. It comes with 2 JMX exporters, one for Jolokia and one for Prometheus.

The Jolokia exporter is a JMX over HTTP exporter and exposes JVM information as REST services.
For instance by using the assinged internal docker IP, you could retrieve the Heap memory usage of the container.

http://172.17.0.2:8778/jolokia/read/java.lang:type=Memory/HeapMemoryUsage

```json
{
  "request": {
      "mbean": "java.lang:type=Memory",
      "attribute": "HeapMemoryUsage",
      "type": "read"
  },
      "value": {
          "init": 262144000,
          "committed": 265814016,
          "max": 3719299072,
          "used": 21745192
      },
    "timestamp": 1496637283,
    "status": 200
}
```

