<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <movies>
            <xsl:for-each select="//div[@class='lister-item-content']">
                <movies>
                    <title><xsl:value-of select="normalize-space(h3/a[1])"/></title>
                    <description><xsl:value-of select="normalize-space(p[@class='text-muted' and position() = 2])"/></description>
                </movies>
            </xsl:for-each>
        </movies>
    </xsl:template>

</xsl:stylesheet>