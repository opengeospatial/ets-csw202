# CSW 2.0.2 Conformance Test Suite Release Notes

## 1.17 (2017-11-16)
- [#20](https://github.com/opengeospatial/ets-csw202/issues/20) - Added isConformanceClass and isBasic attributes to qualify the Main test

## 1.16 (2016-05-26)
- [#19](https://github.com/opengeospatial/ets-csw202/pull/19) - Removed '/' at the beginning of filter property names, to fix the intended XPath syntax

## 1.15 (2016-01-21)
- [#7](https://github.com/opengeospatial/ets-csw202/issues/7) - Test 19.1 PropertyIsLike should match case by default in GetRecords filter
- [#4](https://github.com/opengeospatial/ets-csw202/issues/4) - Clarified assertion in csw:csw-2.0.2-GetCapabilities-tc7.2

## 1.14 (2015-08-18)
- [#17](https://github.com/opengeospatial/ets-csw202/issues/17) - Remove relnotes from index.md
- [#16](https://github.com/opengeospatial/ets-csw202/issues/16) - CSW-2.0.2-GetCapabilities-tc1.3 shall accept an unknown response for backward compatibility

## 1.13 (2015-07-22)

- [#6](https://github.com/opengeospatial/ets-csw202/issues/6) - Timezoned-date literals are not recommended for dc:date
- [#11](https://github.com/opengeospatial/ets-csw202/issues/11) - Generate test data bundle on build
- lots of GetCapabilities tests restored (mistakenly gone lost in past commits)

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
