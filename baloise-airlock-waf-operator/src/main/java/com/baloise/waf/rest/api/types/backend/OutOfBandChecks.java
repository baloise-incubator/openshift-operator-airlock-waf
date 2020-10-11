
package com.baloise.waf.rest.api.types.backend;


public class OutOfBandChecks {

    public Boolean enabled;
    public String url;
    public ChecksWhenGood checksWhenGood;
    public ChecksWhenBad checksWhenBad;
    public Integer timeout;
    public StatusPattern_ statusPattern;
    public ContentPattern_ contentPattern;

}
