package com.baloise.waf.operator;

import com.baloise.waf.rest.api.client.AirlockWAFRestAPI;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.core.Response;


@QuarkusMain
public class OperatorMain implements QuarkusApplication {

    @Inject
    @RestClient
    AirlockWAFRestAPI airlockWAFRestAPI;


    @Override
    public int run(String... args) throws Exception {
        System.out.println("Create WAF Session");
        Response response = airlockWAFRestAPI.createWAFSession();
        System.out.println(response.getStatus());
        return 0;
    }
}
