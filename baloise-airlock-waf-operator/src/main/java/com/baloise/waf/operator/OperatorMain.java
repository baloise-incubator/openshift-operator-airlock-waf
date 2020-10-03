package com.baloise.waf.operator;

import com.baloise.waf.operator.crd.DoneableWafMapping;
import com.baloise.waf.operator.crd.WafMapping;
import com.baloise.waf.operator.crd.WafMappingList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.dsl.base.CustomResourceDefinitionContext;
import io.fabric8.kubernetes.internal.KubernetesDeserializer;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.jboss.logging.Logger;

import javax.inject.Inject;


@QuarkusMain
public class OperatorMain implements QuarkusApplication {

    private final Logger log = Logger.getLogger(OperatorMain.class);

    @Inject
    WafMappingWatcher wafMappingWatcher;

    @Inject
    KubernetesClient kubernetesClient;


    @Override
    public int run(String... args) throws Exception {
        System.out.println("Start Operator");
        System.out.println("UserName:" + kubernetesClient.getConfiguration().getUsername());
        System.out.println("OAuthToken:" + kubernetesClient.getConfiguration().getOauthToken());
        log.debug("Start Operator");

        //OpenShiftClient openShiftClient = kubernetesClient.adapt(OpenShiftClient.class);
        //System.out.println("UserName:" + openShiftClient.getConfiguration().getUsername());
        //openShiftClient.routes().watch(this.routeRessourceWatcher);

        CustomResourceDefinitionContext crdContext = new CustomResourceDefinitionContext.Builder()
                .withGroup("stable.baloise.ch")
                .withVersion("v1beta")
                .withScope("Namespaced")
                .withName("wafmappings.stable.baloise.ch")
                .withPlural("wafmappings")
                .withKind("wafMapping")
                .build();
        MixedOperation<WafMapping, WafMappingList, DoneableWafMapping, Resource<WafMapping, DoneableWafMapping>> wafMappingclient =
                kubernetesClient.customResources(crdContext, WafMapping.class, WafMappingList.class, DoneableWafMapping.class);
        // Register WafMapping CRD to KubernetesDeserializer
        KubernetesDeserializer.registerCustomKind("stable.baloise.ch/v1beta", "WafMapping", WafMapping.class);

        //wafMappingclient.inAnyNamespace().watch(this.wafMappingWatcher);
        wafMappingclient.inNamespace("waf-mapping-operator-dev").watch(this.wafMappingWatcher);
        Quarkus.waitForExit();
        return 0;
    }
}
