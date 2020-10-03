package com.baloise.waf.operator;

import com.baloise.waf.rest.api.client.AirlockWAFRestService;
import com.baloise.waf.rest.api.client.beans.MappingDTO;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.openshift.api.model.Route;
import io.quarkus.runtime.Quarkus;
import org.jboss.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.Map;

import static java.lang.String.format;

@ApplicationScoped
public class RouteRessourceWatcher implements Watcher<Route> {

    private final Logger log = Logger.getLogger(RouteRessourceWatcher.class);

    @Inject
    KubernetesClient kubernetesClient;

    @Inject
    AirlockWAFRestService airlockWAFRestService;


    @Override
    public void eventReceived(Action action, Route route) {
        System.out.println(format("action: %s for route: %s", action.name(), route.getMetadata().getName()));
        if(this.isWafMappingOperatorEnabled(route)) {
            System.out.println(format("%s - Route: %s", action.name(), route.getMetadata().getName()));
            this.handleRouteEvent(action, route);
        }
    }

    @Override
    public void onClose(KubernetesClientException e) {
        System.out.println(format("Application exception: %s", e));
        log.error(format("Application exception: %s", e));
        Quarkus.asyncExit();
    }

    private void handleRouteEvent(Action action, Route route) {
        try {
            switch (action.name()) {
                case "ADDED":
                    this.callCreateWafMapping(route);
                    break;
                case "MODIFIED":
                    // ToDo handle modified event
                    System.out.println("not implemented yet");
                    break;
                case "DELETED":
                    // ToDo handle deleted event
                    System.out.println("not implemented yet");
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

    /**
     *   metadata:
     *     annotations:
     *       baloise.com/waf-operator-enabled: "true"
     *       baloise.com/waf-mapping-name: "echoserver"
     *       baloise.com/waf-mapping-entry-path: "/"
     *       baloise.com/waf-mapping-backend-path: "/"
     *       baloise.com/waf-backendgroup-name: "echoserver-group"
     *       baloise.com/waf-backend-name: "echoserver-test.demodomain.com"
     *       baloise.com/waf-backend-protocol: "https"
     *       baloise.com/waf-backend-port: "443"
     *       baloise.com/waf-vhost-name: "CodeCamp WAF1"
     * @param route
     */
    private void callCreateWafMapping(Route route) {

        if(route.getMetadata().getAnnotations() != null) {
            Map<String, String> annotations = route.getMetadata().getAnnotations();

            MappingDTO mappingDTO = new MappingDTO();
            mappingDTO.setMappingName(annotations.get("baloise.com/waf-mapping-name"));
            mappingDTO.setBackendGroupName(annotations.get("baloise.com/waf-backendgroup-name"));
            mappingDTO.setBackendHostName(annotations.get("baloise.com/waf-backend-name"));
            mappingDTO.setBackendPath(annotations.get("baloise.com/waf-mapping-backend-path"));
            mappingDTO.setBackendProtocol(annotations.get("baloise.com/waf-backend-protocol"));
            Integer port = Integer.valueOf(annotations.get("baloise.com/waf-backend-port"));
            mappingDTO.setBackendPort(port);
            mappingDTO.setEntryPath(annotations.get("baloise.com/waf-mapping-entry-path"));
            mappingDTO.setVhostName(annotations.get("baloise.com/waf-vhost-name"));

            this.airlockWAFRestService.createMappig(mappingDTO);
        }
    }

    private boolean isWafMappingOperatorEnabled(Route route) {
        if(route.getMetadata().getAnnotations() == null || route.getMetadata().getAnnotations().containsKey("baloise.com/waf-operator-enabled")) {
            return false;
        }
        System.out.println("annotations detected");
        for(String annotation : route.getMetadata().getAnnotations().keySet()) {
            System.out.println(annotation + ": " +route.getMetadata().getAnnotations().get(annotation));
        }
        System.out.println("no annotations detected");
        return "true".equals(route.getMetadata().getAnnotations().get("baloise.com/waf-operator-enabled"));
    }
}
