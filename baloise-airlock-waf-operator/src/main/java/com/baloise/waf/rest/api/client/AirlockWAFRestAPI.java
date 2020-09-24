package com.baloise.waf.rest.api.client;

import com.baloise.waf.rest.api.client.beans.AirlockWAFConnectMappingBackend;
import com.baloise.waf.rest.api.client.beans.AirlockWAFConnectMappingVhost;
import com.baloise.waf.rest.api.client.beans.AirlockWAFSave;
import com.baloise.waf.rest.api.client.beans.AirlockWAFSimpleBackend;
import com.baloise.waf.rest.api.client.beans.AirlockWAFSimpleMapping;
import com.baloise.waf.rest.api.client.beans.backend.AirlockWAFBackend;
import com.baloise.waf.rest.api.client.beans.mapping.AirlockWAFMapping;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@RegisterRestClient
public interface AirlockWAFRestAPI {

    public static String WAF_SESSION_COOKIE_NAME = "JSESSIONID";

    @POST
    @Path("/session/create")
    @ClientHeaderParam(name="Authorization", value = "{com.baloise.waf.rest.api.client.WAFSessionAuthorizationHeaderGenerator.generateWAFAuthorizationHeader}")
    Response createWAFSession();

    @POST
    @Path("/session/terminate")
    Response terminateWAFSessions(@CookieParam(WAF_SESSION_COOKIE_NAME) String wafSessionCookie);

    @POST
    @Path("/configuration/configurations/load-active")
    Response loadActiveConiguration(@CookieParam(WAF_SESSION_COOKIE_NAME) String wafSessionCookie);
    
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/configuration/configurations/save")
    Response saveConfiguration(
        @CookieParam(WAF_SESSION_COOKIE_NAME) String wafSessionCookie,
        AirlockWAFSave airlockWAFSave);

    @POST
    @Consumes("application/json")
    @Path("/configuration/configurations/activate")
    Response activateConfiguration(
        @CookieParam(WAF_SESSION_COOKIE_NAME) String wafSessionCookie,
        AirlockWAFSave airlockWAFSave);

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/configuration/mappings")
    AirlockWAFMapping createMapping(
        @CookieParam(WAF_SESSION_COOKIE_NAME) String wafSessionCookie,
        AirlockWAFSimpleMapping airlockWAFMapping);

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/configuration/back-end-groups")
    AirlockWAFBackend createBackend(
        @CookieParam(WAF_SESSION_COOKIE_NAME) String wafSessionCookie,
        AirlockWAFSimpleBackend airlockWAFBackend);

    @PATCH
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/configuration/mappings/{mappingid}/relationships/back-end-group")
    //ToDo replace hardcoded mapping id with PathParam
    Response connectMappingBackend(
        @CookieParam(WAF_SESSION_COOKIE_NAME) String wafSessionCookie,
        @PathParam("mappingid") String mappingId,
        AirlockWAFConnectMappingBackend airlockWAFConnectMappingBackend);
    
    @PATCH
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/configuration/mappings/{mappingid}/relationships/virtual-hosts")
    //ToDo replace hardcoded mapping id with PathParam
    Response connectMappingVhost(
        @CookieParam(WAF_SESSION_COOKIE_NAME) String wafSessionCookie,
        @PathParam("mappingid") String mappingId,
        AirlockWAFConnectMappingVhost airlockWAFConnectMappingVhost);
        
}
