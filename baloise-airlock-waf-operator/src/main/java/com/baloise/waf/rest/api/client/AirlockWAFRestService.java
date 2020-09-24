package com.baloise.waf.rest.api.client;

import com.baloise.waf.rest.api.client.beans.AirlockWAFConnectMappingBackend;
import com.baloise.waf.rest.api.client.beans.AirlockWAFConnectMappingVhost;
import com.baloise.waf.rest.api.client.beans.AirlockWAFSave;
import com.baloise.waf.rest.api.client.beans.backend.AirlockWAFBackend;
import com.baloise.waf.rest.api.client.beans.backend.BackendHost;
import com.baloise.waf.rest.api.client.beans.mapping.AirlockWAFMapping;
import com.baloise.waf.rest.api.client.beans.ConnectData;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

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
        AirlockWAFMapping wafMapping = airlockWAFRestAPI.createMapping(cookie.getValue(), buildAirlockWAFMappingBean());
        System.out.println("Mapping ID: " + wafMapping.data.id);
        AirlockWAFBackend wafBackend = airlockWAFRestAPI.createBackend(cookie.getValue(), buildAirlockWAFBackendBean());
        System.out.println("Backend ID: " + wafBackend.data.id);
        response = airlockWAFRestAPI.connectMappingBackend(cookie.getValue(), wafMapping.data.id, buildAirlockWAFConnectMappingBackend(wafBackend.data.id));
        System.out.println("connectMappingBackend: " + response.getStatus());
        response = airlockWAFRestAPI.connectMappingVhost(cookie.getValue(), wafMapping.data.id, buildAirlockWAFConnectMappingVhost());
        System.out.println("connectMappingVhost: " + response.getStatus());
        response = airlockWAFRestAPI.saveConfiguration(cookie.getValue(), buildAirlockWAFSaveBean());
 //       System.out.println("saveConfig " + response.getStatus());       
 //       response = airlockWAFRestAPI.terminateWAFSessions(cookie.getValue());
        System.out.println("terminateSession " + response.getStatus());
    }

    private AirlockWAFMapping buildAirlockWAFMappingBean() {
        com.baloise.waf.rest.api.client.beans.mapping.EntryPath entryPath = new com.baloise.waf.rest.api.client.beans.mapping.EntryPath();
        entryPath.value = "/";
        com.baloise.waf.rest.api.client.beans.mapping.Attributes mapAttributes = new com.baloise.waf.rest.api.client.beans.mapping.Attributes();
        mapAttributes.name = "CodeCampMapFromQuarkus";
        mapAttributes.entryPath = entryPath;
        mapAttributes.backendPath = "/";
        com.baloise.waf.rest.api.client.beans.mapping.Data mapData = new com.baloise.waf.rest.api.client.beans.mapping.Data();
        mapData.type = "mapping";
        mapData.attributes = mapAttributes;
        AirlockWAFMapping airlockWAFMapping = new AirlockWAFMapping();
        airlockWAFMapping.data = mapData;
        return airlockWAFMapping;
    }

    private AirlockWAFBackend buildAirlockWAFBackendBean() {
        com.baloise.waf.rest.api.client.beans.backend.Attributes backAttributes = new com.baloise.waf.rest.api.client.beans.backend.Attributes();
        backAttributes.name = "CodeCampBackFromQuarkus";
        backAttributes.backendHosts = buildBackendHosts();
        com.baloise.waf.rest.api.client.beans.backend.Data backData  = new com.baloise.waf.rest.api.client.beans.backend.Data ();
        backData.type = "back-end-group";
        backData.attributes = backAttributes;
        AirlockWAFBackend airlockWAFBackend = new AirlockWAFBackend();
        airlockWAFBackend.data  =  backData ;
        return airlockWAFBackend;
    }

    private List<BackendHost> buildBackendHosts() {

        List<BackendHost> backendHosts = new ArrayList<>();
        BackendHost backendHost = new BackendHost();
        backendHost.hostName = "example.com";
        backendHost.protocol = "HTTP";
        backendHost.port = 80;
        backendHosts.add(backendHost);
        return backendHosts;
    
    }

    private AirlockWAFSave buildAirlockWAFSaveBean() {
        AirlockWAFSave airlockWAFSave = new AirlockWAFSave();
        airlockWAFSave.comment = "CodeCamp Save From Quarkus";
        return airlockWAFSave;
    }

    private AirlockWAFConnectMappingBackend buildAirlockWAFConnectMappingBackend(String backendID) {
        ConnectData connectMapBackData = new ConnectData();
        connectMapBackData.type = "back-end-group";
        connectMapBackData.id = backendID;
        AirlockWAFConnectMappingBackend airlockWAFConnectMappingBackend = new AirlockWAFConnectMappingBackend();
        airlockWAFConnectMappingBackend.data = connectMapBackData;
        return airlockWAFConnectMappingBackend;
    }

    private AirlockWAFConnectMappingVhost buildAirlockWAFConnectMappingVhost() {
        AirlockWAFConnectMappingVhost airlockWAFConnectMappingVhost = new AirlockWAFConnectMappingVhost();
        airlockWAFConnectMappingVhost.data = buildConnectVhostData();
        return airlockWAFConnectMappingVhost;
    }

    private List<ConnectData> buildConnectVhostData() {

        List<ConnectData> connectDatas = new ArrayList<>();
        ConnectData connectData = new ConnectData();
        connectData.type = "virtual-host";
        connectData.id = "7";
        connectDatas.add(connectData);
        return connectDatas;
    
    }

}
