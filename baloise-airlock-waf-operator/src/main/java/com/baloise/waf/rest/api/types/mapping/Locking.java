
package com.baloise.waf.rest.api.types.mapping;


public class Locking {

    public Boolean enabled;
    public Boolean labels;
    public Access_ access;
    public EntryPath_ entryPath;
    public Boolean backendPath;
    public Boolean threatHandling;
    public Boolean operationalMode;
    public Boolean enableMaintenancePage;
    public IpRules_ ipRules;
    public BotManagement_ botManagement;
    public Timeouts_ timeouts;
    public Limits_ limits;
    public Application_ application;
    public ApiSecurity_ apiSecurity;
    public DosAttackPrevention_ dosAttackPrevention;
    public RequestBodyStreaming_ requestBodyStreaming;
    public HttpParameterPollutionDetection_ httpParameterPollutionDetection;
    public Icap icap;

}
