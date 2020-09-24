
package com.baloise.waf.rest.api.client.beans.mapping;


public class Application_ {

    public Boolean sessionHandling;
    public Boolean controlApiAllowed;
    public Boolean environmentCookiesEnabled;
    public EncryptedCookies_ encryptedCookies;
    public PassthroughCookies_ passthroughCookies;
    public Boolean loadBalancingCookieEnabled;
    public Boolean webSocketsAllowed;
    public Boolean redirectForErrorPageEnabled;
    public Request_ request;
    public Response_ response;

}
