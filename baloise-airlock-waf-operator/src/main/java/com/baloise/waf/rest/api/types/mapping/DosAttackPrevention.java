
package com.baloise.waf.rest.api.types.mapping;


public class DosAttackPrevention {

    public Boolean enabled;
    public Integer maxRequestsPerInterval;
    public Integer interval;
    public WhitelistIpPattern whitelistIpPattern;

}
