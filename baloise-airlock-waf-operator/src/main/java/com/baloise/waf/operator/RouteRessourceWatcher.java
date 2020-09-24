package com.baloise.waf.operator;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.openshift.api.model.Route;
import io.quarkus.runtime.Quarkus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import static java.lang.String.format;

@ApplicationScoped
public class RouteRessourceWatcher implements Watcher<Route> {

    private final Logger log = LoggerFactory.getLogger(RouteRessourceWatcher.class);

    @Inject
    KubernetesClient kubernetesClient;


    @Override
    public void eventReceived(Action action, Route route) {
        log.debug(format("action: %s for route: %s", action.name(), route.getMetadata().getName()));
        if(this.isWafMappingOperatorEnabled(route)) {
            log.info(format("%s - Route: %s", action.name(), route.getMetadata().getName()));
        }
    }

    @Override
    public void onClose(KubernetesClientException e) {
        log.error(format("Application exception: %s", e));
        Quarkus.asyncExit();
    }

    private void handleRouteEvent(Action action, Route route) {
        try {
            switch (action.name()) {
                case "ADDED":
                    //ToDo handle added event
                    break;
                case "MODIFIED":
                    // ToDo handle modified event
                    break;
                case "DELETED":
                    // ToDo handle deleted event
                    break;
                default:
                    log.info(format("Unknown Action: %s", action.name()));
                    break;
            }
            log.info(format("%s - secret: %s", action.name(), route.getMetadata().getName()));
        } catch (KubernetesClientException e) {
            log.error(format("route: %s exception:", route.getMetadata().getName()));
            log.error(e.toString());
        }

    }

    private boolean isWafMappingOperatorEnabled(Route route) {
        if(route.getMetadata().getAnnotations() == null || route.getMetadata().getAnnotations().containsKey("baloise.com/waf-operator-enabled")) {
            return false;
        }
        return "true".equals(route.getMetadata().getAnnotations().get("baloise.com/waf-operator-enabled"));
    }
}
