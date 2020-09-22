package com.baloise.waf.rest.api.client;

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
        entryPath.setValue("/");
        Attributes attributes = new Attributes();
        attributes.setName("CodeCampMapFromQuarkus");
        attributes.setEntryPath(entryPath);
        attributes.setBackendPath("/");
        Data data = new Data();
        data.setType("mapping");
        data.setAttributes(attributes);
        AirlockWAFMapping airlockWAFMapping = new AirlockWAFMapping();
        airlockWAFMapping.setData(data);
        return airlockWAFMapping;
    }
}
