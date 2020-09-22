package com.baloise.waf.operator;

import com.baloise.waf.rest.api.client.AirlockWAFRestService;
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
        this.airlockWAFRestService.createMappig();
        return 0;
    }
}
