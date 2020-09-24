package com.baloise.waf.rest.api.client;

import com.baloise.waf.rest.api.client.beans.AirlockWAFConnectMappingBackendGroup;
import com.baloise.waf.rest.api.client.beans.AirlockWAFConnectMappingVhost;
import com.baloise.waf.rest.api.client.beans.AirlockWAFSave;
import com.baloise.waf.rest.api.client.beans.backend.AirlockWAFBackendGroup;
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
        AirlockWAFMapping airlockWAFMapping);

    @PATCH
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/configuration/mappings/{mappingid}")
    Response updateMapping(
        @CookieParam(WAF_SESSION_COOKIE_NAME) String wafSessionCookie,
        @PathParam("mappingid") String mappingId,
        AirlockWAFMapping airlockWAFMapping);
    
    @DELETE
    @Path("/configuration/mappings/{mappingid}")
    Response deleteMapping(
        @CookieParam(WAF_SESSION_COOKIE_NAME) String wafSessionCookie,
        @PathParam("mappingid") String mappingId);

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/configuration/back-end-groups")
    AirlockWAFBackendGroup createBackendGroup(
        @CookieParam(WAF_SESSION_COOKIE_NAME) String wafSessionCookie,
        AirlockWAFBackendGroup airlockWAFBackendGroup);

    @PATCH
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/configuration/back-end-groups/{backendGroupid}")
    Response updateBackendGroup(
        @CookieParam(WAF_SESSION_COOKIE_NAME) String wafSessionCookie,
        @PathParam("backendGroupid") String backendGroupId,
        AirlockWAFBackendGroup airlockWAFBackendGroup);
    
    @DELETE
    @Path("/configuration/back-end-groups/{backendGroupid}")
    Response deleteBackendGroup(
        @CookieParam(WAF_SESSION_COOKIE_NAME) String wafSessionCookie,
        @PathParam("backendGroupid") String backendGroupId);

    @PATCH
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/configuration/mappings/{mappingid}/relationships/back-end-group")
    Response connectMappingBackendGroup(
        @CookieParam(WAF_SESSION_COOKIE_NAME) String wafSessionCookie,
        @PathParam("mappingid") String mappingId,
        AirlockWAFConnectMappingBackendGroup airlockWAFConnectMappingBackend);
    
    @PATCH
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/configuration/mappings/{mappingid}/relationships/virtual-hosts")
    Response connectMappingVhost(
        @CookieParam(WAF_SESSION_COOKIE_NAME) String wafSessionCookie,
        @PathParam("mappingid") String mappingId,
        AirlockWAFConnectMappingVhost airlockWAFConnectMappingVhost);
        
}
