
package com.baloise.waf.rest.api.client.beans.backend;


public class InBandChecks {

    public Boolean enabled;
    public StatusPattern statusPattern;
    public Boolean checkResponseContentEnabled;
    public ContentTypePattern contentTypePattern;
    public Integer maxContentSize;
    public ContentPattern contentPattern;

}
