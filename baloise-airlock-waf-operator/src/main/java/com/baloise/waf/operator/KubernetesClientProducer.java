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
                .withMasterUrl("https://192.168.99.100:8443")
                .withOauthToken("eyJhbGciOiJSUzI1NiIsImtpZCI6IiJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJ3YWYtbWFwcGluZy1vcGVyYXRvci1kZXYiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlY3JldC5uYW1lIjoid2FmLW1hcHBpbmctb3BlcmF0b3ItYWNjb3VudC10b2tlbi1qdjk2NyIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50Lm5hbWUiOiJ3YWYtbWFwcGluZy1vcGVyYXRvci1hY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQudWlkIjoiYzkyYWZkZTEtMDE1Ny0xMWViLThlYzctMDgwMDI3ZDM2YjM4Iiwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50OndhZi1tYXBwaW5nLW9wZXJhdG9yLWRldjp3YWYtbWFwcGluZy1vcGVyYXRvci1hY2NvdW50In0.Gk7loA1VTmFRbVYKohMc25ysdwWKC5is65uR0GkYwDjSSDQWnPWNtP39JpGelKOc2HxKD0ZdvnrkD-IksErqrX3nKrLbu3qImqpNLKOIO1AMKH-jiUTKFZCxD1O49uxkJhwiCCX0vbAyrLYHMGCp0_9E9QoKTkU9IEV5iklvK2pyK9eCwJqhV6VQjsMFYT9Lxg-lVk2gkLqT7Li4S52xiyN2EZ87eC1ItzB3bKLPzdLV70HhKlpv0-aQaiL--5JRhMC__3G545VgS2TjONPGRoLdkSBf_FxJblpSO-FSRBb8AkZf3pBP329bIGJ76ryJiDRmERb6saMcRQDuacXWXQ")
                .withNamespace("waf-mapping-operator-dev")
                .build();
        return new DefaultKubernetesClient(config);
    }
}
