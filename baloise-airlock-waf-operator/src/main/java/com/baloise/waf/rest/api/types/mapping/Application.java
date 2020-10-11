
package com.baloise.waf.rest.api.types.mapping;


public class Application {

    public String sessionHandling;
    public Boolean controlApiAllowed;
    public Boolean environmentCookiesEnabled;
    public EncryptedCookies encryptedCookies;
    public PassthroughCookies passthroughCookies;
    public Boolean loadBalancingCookieEnabled;
    public Boolean webSocketsAllowed;
    public Boolean redirectForErrorPageEnabled;
    public Request request;
    public Response response;

}
