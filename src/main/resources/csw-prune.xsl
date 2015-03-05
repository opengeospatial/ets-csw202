<xsl:stylesheet version="2.0" 
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:ows="http://www.opengis.net/ows">

  <xsl:output method="xml" version="1.0" indent="yes"/>
  <xsl:strip-space elements="*"/>
  
  <xsl:template match="@* | node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
  </xsl:template>
  
  <xsl:template match="ows:ExtendedCapabilities" />

</xsl:stylesheet>
