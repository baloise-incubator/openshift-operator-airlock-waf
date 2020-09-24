
package com.baloise.waf.rest.api.client.beans.backend;


public class OutOfBandChecks {

    public Boolean enabled;
    public String url;
    public ChecksWhenGood checksWhenGood;
    public ChecksWhenBad checksWhenBad;
    public Integer timeout;
    public StatusPattern_ statusPattern;
    public ContentPattern_ contentPattern;

}
