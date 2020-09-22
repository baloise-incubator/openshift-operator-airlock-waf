package com.baloise.waf.rest.api.client;

import com.baloise.waf.rest.api.client.beans.AirlockWAFMapping;
import com.baloise.waf.rest.api.client.beans.Attributes;
import com.baloise.waf.rest.api.client.beans.Data;
import com.baloise.waf.rest.api.client.beans.EntryPath;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class AirlockWAFRestService {



    @Inject
    @RestClient
    AirlockWAFRestAPI airlockWAFRestAPI;

    public void createMappig() {
        Response response = airlockWAFRestAPI.createWAFSession();
        Cookie cookie = response.getCookies().get(AirlockWAFRestAPI.WAF_SESSION_COOKIE_NAME);
        System.out.println(cookie.getName() + " : " + cookie.getValue());
        response = airlockWAFRestAPI.loadActiveConiguration(cookie.getValue());
        System.out.println("loadeConfig: " + response.getStatus());
        response = airlockWAFRestAPI.createMapping(cookie.getValue(), buildAirlockWAfMappingBean());
        System.out.println("createMapping: " + response.getStatus());
        response = airlockWAFRestAPI.saveConfiguration(cookie.getValue());
        System.out.println("saveConfig " + response.getStatus());       
        response = airlockWAFRestAPI.terminateWAFSessions(cookie.getValue());
        System.out.println("terminateSession " + response.getStatus());
    }

    private AirlockWAFMapping buildAirlockWAfMappingBean() {
        EntryPath entryPath = new EntryPath();
        entryPath.value = "/";
        Attributes attributes = new Attributes();
        attributes.name = "CodeCampMapFromQuarkus";
        attributes.entryPath = entryPath;
        attributes.backendPath = "/";
        Data data = new Data();
        data.type = "mapping";
        data.attributes = attributes;
        AirlockWAFMapping airlockWAFMapping = new AirlockWAFMapping();
        airlockWAFMapping.data =  data;
        return airlockWAFMapping;
    }
}
