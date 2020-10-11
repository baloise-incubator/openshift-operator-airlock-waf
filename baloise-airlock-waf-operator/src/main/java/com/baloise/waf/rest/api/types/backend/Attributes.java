
package com.baloise.waf.rest.api.types.backend;

import java.util.List;

public class Attributes {

    public String name;
    public String tenant;
    public List<BackendHost> backendHosts = null;
    public InBandChecks inBandChecks;
    public OutOfBandChecks outOfBandChecks;
    public ExpertSettings expertSettings;

}
