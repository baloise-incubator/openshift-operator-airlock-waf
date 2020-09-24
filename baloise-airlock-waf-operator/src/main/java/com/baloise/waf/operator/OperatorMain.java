package com.baloise.waf.operator;

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

    @Inject
    RouteRessourceWatcher routeRessourceWatcher;

    @Inject
    KubernetesClient kubernetesClient;


    @Override
    public int run(String... args) throws Exception {
        System.out.println("Start Operator");
        System.out.println("UserName:" + kubernetesClient.getConfiguration().getUsername());
        System.out.println("OAuthToken:" + kubernetesClient.getConfiguration().getOauthToken());
        log.debug("Start Operator");

        OpenShiftClient openShiftClient = kubernetesClient.adapt(OpenShiftClient.class);
        System.out.println("UserName:" + openShiftClient.getConfiguration().getUsername());
        openShiftClient.routes().watch(this.routeRessourceWatcher);
        Quarkus.waitForExit();
        return 0;
    }
}
