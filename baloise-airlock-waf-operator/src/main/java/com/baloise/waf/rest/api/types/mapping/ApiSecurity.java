
package com.baloise.waf.rest.api.types.mapping;


public class ApiSecurity {

    public Boolean treatPathSegmentsAsParamValues;
    public JsonParser jsonParser;
    public Boolean openApiEnforced;
    public Boolean logOnly;
    public Boolean openApiPublishSpecificationEnabled;
    public String openApiPublishSpecificationPath;
    public Boolean apiPolicyServiceEnabled;
    public Integer apiPolicyServiceId;
    public ApiPolicyKeyExtractionHeader apiPolicyKeyExtractionHeader;
    public ApiPolicyKeyExtractionQueryParameter apiPolicyKeyExtractionQueryParameter;
    public ApiPolicyKeyExtractionCookie apiPolicyKeyExtractionCookie;

}
