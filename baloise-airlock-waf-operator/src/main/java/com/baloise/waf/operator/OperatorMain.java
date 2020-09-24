package com.baloise.waf.operator;

import com.baloise.waf.rest.api.client.AirlockWAFRestService;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.openshift.client.OpenShiftClient;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;


@QuarkusMain
public class OperatorMain implements QuarkusApplication {

    private final Logger log = LoggerFactory.getLogger(OperatorMain.class);

    //@Inject
    //AirlockWAFRestService airlockWAFRestService;

    @Inject
    RouteRessourceWatcher routeRessourceWatcher;

    @Inject
    KubernetesClient kubernetesClient;


    @Override
    public int run(String... args) throws Exception {
        log.debug("Start Operator");
        //log.debug("UserName:" + kubernetesClient.getConfiguration().getUsername());
        //log.debug("OAuthToken:" + kubernetesClient.getConfiguration().getOauthToken());

        OpenShiftClient openShiftClient = kubernetesClient.adapt(OpenShiftClient.class);
        log.debug("UserName:" + openShiftClient.getConfiguration().getUsername());
        openShiftClient.routes().watch(this.routeRessourceWatcher);

        //System.out.println("Create WAF Session");
        //this.airlockWAFRestService.createMappig();
        Quarkus.waitForExit();
        return 0;
    }
}
