
package com.baloise.waf.rest.api.types.mapping;

import java.util.List;

public class Attributes {

    public String name;
    public List<Object> labels = null;
    public String tenant;
    public EntryPath entryPath;
    public String backendPath;
    public String threatHandling;
    public String operationalMode;
    public Boolean enableMaintenancePage;
    public Access access;
    public IpRules ipRules;
    public BotManagement botManagement;
    public Timeouts timeouts;
    public Limits limits;
    public Application application;
    public ApiSecurity apiSecurity;
    public DosAttackPrevention dosAttackPrevention;
    public RequestBodyStreaming requestBodyStreaming;
    public HttpParameterPollutionDetection httpParameterPollutionDetection;
    public ExpertSettings expertSettings;
    public Locking locking;

}
