package com.baloise.waf.rest.api.client;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@RegisterRestClient
public interface AirlockWAFRestAPI {

    public static String WAF_SESSION_COOKIE_NAME = "JSESSIONID";

    @POST
    @Path("/session/create")
    @ClientHeaderParam(name="Authorization", value = "{com.baloise.waf.rest.api.client.WAFSessionAuthorizationHeaderGenerator.generateWAFAuthorizationHeader}")
    Response createWAFSession();

    @POST
    @Path("/configuration/configurations/load-active")
    Response loadActiveConiguration(@CookieParam(WAF_SESSION_COOKIE_NAME) String wafSessionCookie);




}
