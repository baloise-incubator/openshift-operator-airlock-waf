package com.baloise.waf.rest.api;

import com.baloise.waf.rest.api.types.backend.AirlockWAFBackendGroup;
import com.baloise.waf.rest.api.types.backend.BackendHost;
import com.baloise.waf.rest.api.types.mapping.AirlockWAFMapping;
import com.baloise.waf.rest.api.types.mapping.Attributes;
import com.baloise.waf.rest.api.types.mapping.Data;
import com.baloise.waf.rest.api.types.mapping.EntryPath;
import com.baloise.waf.rest.api.types.*;
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

    public void createMappig(MappingDTO mappingDTO) {
        Response response = airlockWAFRestAPI.createWAFSession();
        Cookie cookie = response.getCookies().get(AirlockWAFRestAPI.WAF_SESSION_COOKIE_NAME);
        System.out.println(cookie.getName() + " : " + cookie.getValue());
        response = airlockWAFRestAPI.loadActiveConiguration(cookie.getValue());
        System.out.println("loadeConfig: " + response.getStatus());
        AirlockWAFMapping wafMapping = airlockWAFRestAPI.createMapping(cookie.getValue(), buildAirlockWAFMappingBean(mappingDTO));
        System.out.println("Mapping ID: " + wafMapping.data.id);
        AirlockWAFBackendGroup wafBackend = airlockWAFRestAPI.createBackendGroup(cookie.getValue(), buildAirlockWAFBackendGroupBean(mappingDTO));
        System.out.println("Backend ID: " + wafBackend.data.id);
        response = airlockWAFRestAPI.connectMappingBackendGroup(cookie.getValue(), wafMapping.data.id, buildAirlockWAFConnectMappingBackendgroup(wafBackend.data.id));
        System.out.println("connectMappingBackend: " + response.getStatus());
        response = airlockWAFRestAPI.connectMappingVhost(cookie.getValue(), wafMapping.data.id, buildAirlockWAFConnectMappingVhost());
        System.out.println("connectMappingVhost: " + response.getStatus());
        response = airlockWAFRestAPI.saveConfiguration(cookie.getValue(), buildAirlockWAFSaveBean());
        System.out.println("saveConfig " + response.getStatus());       
        response = airlockWAFRestAPI.terminateWAFSessions(cookie.getValue());
        System.out.println("terminateSession " + response.getStatus());
    }

    public void updateMappig(MappingDTO mappingDTO) {
        Response response = airlockWAFRestAPI.createWAFSession();
        Cookie cookie = response.getCookies().get(AirlockWAFRestAPI.WAF_SESSION_COOKIE_NAME);
        System.out.println(cookie.getName() + " : " + cookie.getValue());
        response = airlockWAFRestAPI.loadActiveConiguration(cookie.getValue());
        System.out.println("loadeConfig: " + response.getStatus());
        response = airlockWAFRestAPI.updateMapping(cookie.getValue(), "4", buildAirlockWAFMappingBean(mappingDTO));
        System.out.println("Maping Update " + response.getStatus());
        response = airlockWAFRestAPI.updateBackendGroup(cookie.getValue(), "5", buildAirlockWAFBackendGroupBean(mappingDTO));
        System.out.println("Backend Update " + response.getStatus());
        response = airlockWAFRestAPI.saveConfiguration(cookie.getValue(), buildAirlockWAFSaveBean());
        System.out.println("saveConfig " + response.getStatus());       
        response = airlockWAFRestAPI.terminateWAFSessions(cookie.getValue());
        System.out.println("terminateSession " + response.getStatus());
    }

    public void deleteMappig() {
        Response response = airlockWAFRestAPI.createWAFSession();
        Cookie cookie = response.getCookies().get(AirlockWAFRestAPI.WAF_SESSION_COOKIE_NAME);
        System.out.println(cookie.getName() + " : " + cookie.getValue());
        response = airlockWAFRestAPI.loadActiveConiguration(cookie.getValue());
        System.out.println("loadeConfig: " + response.getStatus());
        response = airlockWAFRestAPI.deleteMapping(cookie.getValue(), "4");
        System.out.println("Maping Delete " + response.getStatus());
        response = airlockWAFRestAPI.deleteBackendGroup(cookie.getValue(), "5");
        System.out.println("Update Delete " + response.getStatus());
        response = airlockWAFRestAPI.saveConfiguration(cookie.getValue(), buildAirlockWAFSaveBean());
        System.out.println("saveConfig " + response.getStatus());       
        response = airlockWAFRestAPI.terminateWAFSessions(cookie.getValue());
        System.out.println("terminateSession " + response.getStatus());
    }

    private AirlockWAFMapping buildAirlockWAFMappingBean(MappingDTO mappingDTO) {
        EntryPath entryPath = new EntryPath();
        entryPath.value = mappingDTO.getEntryPath();
        Attributes mapAttributes = new Attributes();
        mapAttributes.name = mappingDTO.getMappingName();
        mapAttributes.entryPath = entryPath;
        mapAttributes.backendPath = mappingDTO.getBackendPath();
        Data mapData = new Data();
        mapData.type = "mapping";
        mapData.attributes = mapAttributes;
        AirlockWAFMapping airlockWAFMapping = new AirlockWAFMapping();
        airlockWAFMapping.data = mapData;
        return airlockWAFMapping;
    }

    private AirlockWAFBackendGroup buildAirlockWAFBackendGroupBean(MappingDTO mappingDTO) {
        com.baloise.waf.rest.api.types.backend.Attributes backAttributes = new com.baloise.waf.rest.api.types.backend.Attributes();
        backAttributes.name = mappingDTO.getBackendGroupName();
        backAttributes.backendHosts = buildBackendHosts(mappingDTO);
        com.baloise.waf.rest.api.types.backend.Data backData  = new com.baloise.waf.rest.api.types.backend.Data();
        backData.type = "back-end-group";
        backData.attributes = backAttributes;
        AirlockWAFBackendGroup airlockWAFBackend = new AirlockWAFBackendGroup();
        airlockWAFBackend.data  =  backData ;
        return airlockWAFBackend;
    }

    private List<BackendHost> buildBackendHosts(MappingDTO mappingDTO) {

        List<BackendHost> backendHosts = new ArrayList<>();
        BackendHost backendHost = new BackendHost();
        backendHost.hostName = mappingDTO.getBackendHostName().toLowerCase();
        backendHost.protocol = mappingDTO.getBackendProtocol().toUpperCase();
        backendHost.port = mappingDTO.getBackendPort();
        backendHosts.add(backendHost);
        return backendHosts;
    
    }

    private AirlockWAFSave buildAirlockWAFSaveBean() {
        AirlockWAFSave airlockWAFSave = new AirlockWAFSave();
        airlockWAFSave.comment = "CodeCamp Save from Stephan";
        return airlockWAFSave;
    }

    private AirlockWAFConnectMappingBackendGroup buildAirlockWAFConnectMappingBackendgroup(String backendID) {
        ConnectData connectMapBackData = new ConnectData();
        connectMapBackData.type = "back-end-group";
        connectMapBackData.id = backendID;
        AirlockWAFConnectMappingBackendGroup airlockWAFConnectMappingBackend = new AirlockWAFConnectMappingBackendGroup();
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
