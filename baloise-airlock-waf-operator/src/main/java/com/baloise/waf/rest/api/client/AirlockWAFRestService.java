package com.baloise.waf.rest.api.client;

import com.baloise.waf.rest.api.client.beans.AirlockWAFConnectMappingBackend;
import com.baloise.waf.rest.api.client.beans.AirlockWAFConnectMappingVhost;
import com.baloise.waf.rest.api.client.beans.AirlockWAFSave;
import com.baloise.waf.rest.api.client.beans.AirlockWAFSimpleBackend;
import com.baloise.waf.rest.api.client.beans.AirlockWAFSimpleMapping;
import com.baloise.waf.rest.api.client.beans.MapAttributes;
import com.baloise.waf.rest.api.client.beans.BackAttributes;
import com.baloise.waf.rest.api.client.beans.MapData;
import com.baloise.waf.rest.api.client.beans.backend.AirlockWAFBackend;
import com.baloise.waf.rest.api.client.beans.mapping.AirlockWAFMapping;
import com.baloise.waf.rest.api.client.beans.BackData;
import com.baloise.waf.rest.api.client.beans.BackendHost;
import com.baloise.waf.rest.api.client.beans.ConnectData;
import com.baloise.waf.rest.api.client.beans.EntryPath;
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
        response = airlockWAFRestAPI.connectMappingBackend(cookie.getValue(), wafMapping.data.id, wafBackend.data.id, buildAirlockWAFConnectMappingBackend());
        System.out.println("connectMappingBackend: " + response.getStatus());
        response = airlockWAFRestAPI.connectMappingVhost(cookie.getValue(), wafMapping.data.id, buildAirlockWAFConnectMappingVhost());
        System.out.println("connectMappingVhost: " + response.getStatus());
        response = airlockWAFRestAPI.saveConfiguration(cookie.getValue(), buildAirlockWAFSaveBean());
 //       System.out.println("saveConfig " + response.getStatus());       
 //       response = airlockWAFRestAPI.terminateWAFSessions(cookie.getValue());
        System.out.println("terminateSession " + response.getStatus());
    }

    private AirlockWAFSimpleMapping buildAirlockWAFMappingBean() {
        EntryPath entryPath = new EntryPath();
        entryPath.value = "/";
        MapAttributes mapAttributes = new MapAttributes();
        mapAttributes.name = "CodeCampMapFromQuarkus";
        mapAttributes.entryPath = entryPath;
        mapAttributes.backendPath = "/";
        MapData mapData = new MapData();
        mapData.type = "mapping";
        mapData.attributes = mapAttributes;
        AirlockWAFSimpleMapping airlockWAFMapping = new AirlockWAFSimpleMapping();
        airlockWAFMapping.data = mapData;
        return airlockWAFMapping;
    }

    private AirlockWAFSimpleBackend buildAirlockWAFBackendBean() {
        BackAttributes backAttributes = new BackAttributes();
        backAttributes.name = "CodeCampBackFromQuarkus";
        backAttributes.backendHosts = buildBackendHosts();
        BackData backData  = new BackData ();
        backData.type = "back-end-group";
        backData.attributes = backAttributes;
        AirlockWAFSimpleBackend airlockWAFBackend = new AirlockWAFSimpleBackend();
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

    private AirlockWAFConnectMappingBackend buildAirlockWAFConnectMappingBackend() {
        ConnectData connectMapBackData = new ConnectData();
        connectMapBackData.type = "back-end-group";
        connectMapBackData.id = "5";
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
