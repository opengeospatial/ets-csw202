# CSW 2.0.1 Compliance Test Suite

## Overview

This test suite is based on the following OGC specifications:

- OGC Catalogue Services Specification - 2.0.1 [OGC 04-021r3](http://portal.opengeospatial.org/files/?artifact_id=5929&amp;version=2) 
- OpenGIS Web Services Common Specification - 1.0.0 [OGC 05-008c1](https://portal.opengeospatial.org/files/?artifact_id=8798)

The conformance tests provided here are **not** intended to be used in a stand-alone 
manner, but to be incorporated into profile-specific test suites; they apply to all 
CSW-based catalogue implementations.

## What is tested

- GetCapabilities, GET method
- DescribeRecord, POST method
- GetRecordById, GET method
- GetRecords, POST method

## What is not tested

- GetCapabilities with updateSequence parameter
- SOAP protocol bindings
- Spatial predicates Beyond, DWithin (broken filter schema)
- Arithmetic operators
- Distributed search
- Asynchronous processing
- Persistent result sets
- Transaction (profile-specific)
- Harvest (profile-specific)


##  Test data

The [CSW-2.0.2 test data](data-csw-2.0.2.zip) are available in 
the common CSW record format. Loading the records into the catalogue is a 
precondition for compliance testing. While the test data are represented 
as a collection of csw:Record instances, no assumption is made about which 
application profile is supported by the implementation. It is expected that 
each profile defines a suitable mapping for csw:Record items.

##  Notes

The test engine can only execute the whole test suite, it cannot be used to 
run single tests or test groups directly. See the TEAM engine documentation 
for details on how to run scripts locally, or re-execute single tests.

##  Contributors

   - Andreas Schmitz 
   - Lorenzo Bigagli
   - Chuck Morris
   - Kevin Stegemoller
   - Luis Bermudez

##  License

[Apache License, Version 2.0](http://opensource.org/licenses/Apache-2.0 "Apache License")


##  Release Notes

### r10 (2014-04-11)

- CITE-901: amended describeRecord schemalocation check in Capabilities.sch

### r9 (2013-12-02) 

- clarified documentation of csw:csw-2.0.2-GetCapabilities-tc1.1
- CITE-582: removed the "not-matching" id from the test

###  r8 (2013-09-19) 
- Removed default namespace binding in DescribeRecord request entities (CSW-DescribeRecord-POST.xml) 

###  r7 (2013-07-04) 

- clarified assertion in csw:csw-2.0.2-DescribeRecord-tc7.1 (lbigagli)
- In common.xml, Schematron classpath reference was made absolute.
- CITE-580: the schema reported mandatory "nextRecord" attribute should be optional (according to 
  the published CSW schema, but there is no official CR or revision note regarding this).

TODO: new ISO Schematron validator was introduced in OWS-9. It might be worth considering
migrating the Schematron 1.5 schemas to standard Schematron (ISO 19757-3).

### r6 (2013-05-06)

- CITE-793 (see [CITE Issue Tracker](http://cite.opengeospatial.org/issues "Public Issue Tracker"))

### r5 (2013-03-22)

- added missing namespace declaration for 'xsd' prefix [functions.xml]
- allow for multiple POST endpoints in capabilities document [CITE-780]
- fixed a couple of test comments

### r4 (2013-01-24)

- updated config for TEAM Engine v4
- removed unneeded build.xml, xipr.xsl, etc.
- fixed Schematron 1.5 validation
- CITE-583: removed test csw-2.0.2-GetRecords-tc11.2
- updated config.xml to version r4-beta

### r3 (2011-12-01)

- fixed minor typo in all.xml (lbigagli)
- created tag csw-2.0.2 r3
