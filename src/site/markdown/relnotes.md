# CSW 2.0.1 Compliance Test Suite Release Notes

## 1.12 (2015-06-07)

- [#9](https://github.com/opengeospatial/ets-csw202/issues/9) - DescribeRecord invalid type name 
- [#8](https://github.com/opengeospatial/ets-csw202/issues/8) - Allow for higher versions than CSW 2.0.2  


## 1.11 (2015-03-06)

- [#2](https://github.com/opengeospatial/ets-csw202/issues/2) - Added functionality to parse extended Capabilities
- Updated [versioning mechanism](https://github.com/opengeospatial/cite/wiki/OGC-Compliance-Testing-Tools)

## r10 (2014-04-11)

- CITE-901: amended describeRecord schemalocation check in Capabilities.sch

## r9 (2013-12-02) 

- Clarified documentation of csw:csw-2.0.2-GetCapabilities-tc1.1
- CITE-582: removed the "not-matching" id from the test

##  r8 (2013-09-19) 
- Removed default namespace binding in DescribeRecord request entities (CSW-DescribeRecord-POST.xml) 

##  r7 (2013-07-04) 

- clarified assertion in csw:csw-2.0.2-DescribeRecord-tc7.1 (lbigagli)
- In common.xml, Schematron classpath reference was made absolute.
- CITE-580: the schema reported mandatory "nextRecord" attribute should be optional (according to 
  the published CSW schema, but there is no official CR or revision note regarding this).

TODO: new ISO Schematron validator was introduced in OWS-9. It might be worth considering
migrating the Schematron 1.5 schemas to standard Schematron (ISO 19757-3).

## r6 (2013-05-06)

- CITE-793 (see [CITE Issue Tracker](http://cite.opengeospatial.org/issues "Public Issue Tracker"))

## r5 (2013-03-22)

- added missing namespace declaration for 'xsd' prefix [functions.xml]
- allow for multiple POST endpoints in capabilities document [CITE-780]
- fixed a couple of test comments

## r4 (2013-01-24)

- updated config for TEAM Engine v4
- removed unneeded build.xml, xipr.xsl, etc.
- fixed Schematron 1.5 validation
- CITE-583: removed test csw-2.0.2-GetRecords-tc11.2
- updated config.xml to version r4-beta

## r3 (2011-12-01)

- fixed minor typo in all.xml (lbigagli)
- created tag csw-2.0.2 r3
