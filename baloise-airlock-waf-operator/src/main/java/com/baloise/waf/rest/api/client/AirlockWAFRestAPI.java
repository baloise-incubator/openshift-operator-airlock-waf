package com.baloise.waf.rest.api.client;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@RegisterRestClient
public interface AirlockWAFRestAPI {

    @POST
    @Path("/session/create")
    @ClientHeaderParam(name="Authorization", value = "{com.baloise.waf.rest.api.client.WAFSessionAuthorizationHeaderGenerator.generateWAFAuthorizationHeader}")
    Response createWAFSession();
}
