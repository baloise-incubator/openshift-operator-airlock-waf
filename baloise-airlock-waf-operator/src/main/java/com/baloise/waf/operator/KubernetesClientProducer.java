package com.baloise.waf.operator;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class KubernetesClientProducer {

    @Produces
    public KubernetesClient kubernetesClient() {
        Config config = new ConfigBuilder()
                .withMasterUrl("https://console.os2.balgroupit.com:443")
                .withOauthToken("<service-account-token>")
                .withNamespace("echoserver-test")
                .build();
        return new DefaultKubernetesClient(config);
    }
}
