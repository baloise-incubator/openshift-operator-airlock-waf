package com.baloise.waf.operator;

import io.fabric8.kubernetes.client.KubernetesClient;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

@Readiness
@ApplicationScoped
public class ReadinessProbe implements HealthCheck {

    private final KubernetesClient kubernetesClient;

    public ReadinessProbe(KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    @Override
    public HealthCheckResponse call() {
        if(this.kubernetesClient.getConfiguration().getOauthToken() != null) {
            return HealthCheckResponse.up("baloise-airlock-waf-operator is ready");
        } else {
            return HealthCheckResponse.down("baloise-airlock-waf-operator is not ready. Oauth token is missing. Have you configured an ServiceAccount?.");
        }
    }

}
