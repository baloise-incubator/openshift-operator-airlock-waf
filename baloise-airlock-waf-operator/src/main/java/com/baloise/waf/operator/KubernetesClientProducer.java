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
                .withOauthToken("eyJhbGciOiJSUzI1NiIsImtpZCI6IiJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJiYWxvaXNlLXdhZi1vcGVyYXRvci10ZXN0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImJhbG9pc2Utd2FmLW9wZXJhdG9yLWFjY291bnQtdG9rZW4temdnZDgiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiYmFsb2lzZS13YWYtb3BlcmF0b3ItYWNjb3VudCIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6ImJlOGZiNGUyLWZkZDUtMTFlYS05OGE5LTAwNTA1NmE2OTcyMSIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDpiYWxvaXNlLXdhZi1vcGVyYXRvci10ZXN0OmJhbG9pc2Utd2FmLW9wZXJhdG9yLWFjY291bnQifQ.BvAGDwi7iWWKnAoSiYhmzDncnbR5IPac1x1XxmBA51_5dxHhyCcnDIaU_il3RHc8PM849d0fKk18O0IGibZ4J4G7iHgJkPluwmQsLfcdDFGLKzPd8tVpsZg7KWwYmtt0ewy7MFOGuWcQFc-64zEq4lkYgpQNaEytuuASpBe0zvBzrloMU37FUDjBLGnE1wuB6LUvraiWtO-Lit14gn8FvExmm-eqvMSAPWFi_RlKxKX6EuROkpWvg72G7FXuXFsS6RcsWjjp6dHlYX5CAdQR5N6gqQHAKP4rcta2MhSTMiD7ALNqJaJX_Pb-x2G1_IyTCXKDOPNr3XRkm79SmwhWyA")
                .withNamespace("echoserver-test")
                .build();
        return new DefaultKubernetesClient(config);
    }
}
