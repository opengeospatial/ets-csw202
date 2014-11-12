# OGC CSW 2.0 Test-Suite

GC CSW 2.0 Test-Suite provides the Executable Test Script (ETS) to test implementations   against the following specification(s):

- OGC Catalogue Services Specification 2.0.2, [OGC 07-006r1](http://portal.opengeospatial.org/files/?artifact_id=20555)

Detailed information about this test suite is available at src/site/markdown/index.md


## License

[Apache 2.0 License](LICENSE.md)

## Building

There are two options to build and run this test.

### 1. Configure a properties file and run using maven

This test is build using [Apache Maven](http://maven.apache.org/). Process:

Clone this repository:
```     
% git clone https://github.com/opengeospatial/$artifactId.git
```
Update information about the URL to be tested

open this file:

     ets-csw202/src/test/resources/integration-test.properties
     
write the correct URL:
```
capabilities.url = http:yourServer?request=GetCapabilities&service=CSW&version=2.0.2
```

run maven test

```
% mvn test
```

### 2. Run it within TEAM ENGINE
Follow the [TEAM ENGINE instructions](http://opengeospatial.github.io/teamengine/installation.html) to install and run the tests in TEAM ENGINE.     


## Bugs

Issue tracker is available at:
[github](https://github.com/opengeospatial/ets-csw202/issues).

## Mailing Lists

The [cite-forum](http://cite.opengeospatial.org/forum) is where software developers discuss issues and solutions related to OGC tests. 


## More Information

Visit the [CITE website](http://cite.opengeospatial.org/) to get more information about the CITE program and tools.


