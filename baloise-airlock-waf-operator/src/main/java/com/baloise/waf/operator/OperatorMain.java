package com.baloise.waf.operator;

import com.baloise.waf.rest.api.client.AirlockWAFRestService;
import com.baloise.waf.rest.api.client.beans.MappingDTO;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import javax.inject.Inject;


@QuarkusMain
public class OperatorMain implements QuarkusApplication {

    @Inject
    AirlockWAFRestService airlockWAFRestService;


    @Override
    public int run(String... args) throws Exception {
        System.out.println("Create WAF Session");
        MappingDTO mappingDTO = new MappingDTO();
        mappingDTO.setMappingName("CodeCampFromQuarkusMapping");
        mappingDTO.setBackendGroupName("CodeCampBackFromQuarkus");
        mappingDTO.setBackendHostName("example.com");
        mappingDTO.setBackendPath("/");
        mappingDTO.setBackendProtocol("HTTP");
        mappingDTO.setBackendPort(80);
        mappingDTO.setEntryPath("/");
        this.airlockWAFRestService.createMappig(mappingDTO);
        return 0;
    }
}
