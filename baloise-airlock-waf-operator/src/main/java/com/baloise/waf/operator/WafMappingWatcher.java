package com.baloise.waf.operator;

import com.baloise.waf.operator.crd.WafMapping;
import com.baloise.waf.rest.api.client.AirlockWAFRestService;
import com.baloise.waf.rest.api.client.beans.MappingDTO;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import io.quarkus.runtime.Quarkus;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.Map;

import static java.lang.String.format;

@ApplicationScoped
public class WafMappingWatcher implements Watcher<WafMapping> {

    private final Logger log = Logger.getLogger(WafMappingWatcher.class);

    @Inject
    AirlockWAFRestService airlockWAFRestService;

    @Override
    public void eventReceived(Action action, WafMapping wafMapping) {
        System.out.println(format("action: %s for wafmapping: %s", action.name(), wafMapping.getMetadata().getName()));
        System.out.println(wafMapping.toString());
        log.debug(format("action: %s for wafmapping: %s", action.name(), wafMapping.getMetadata().getName()));
    }

    @Override
    public void onClose(KubernetesClientException e) {
        System.out.println(format("Application exception: %s", e));
        log.error(format("Application exception: %s", e));
        Quarkus.asyncExit();
    }

    private void handleWafMappingEvent(Action action, WafMapping wafMapping) {
        try {
            switch (action.name()) {
                case "ADDED":
                    this.callCreateWafMapping(wafMapping);
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
            log.info(format("%s - wafmapping: %s", action.name(), wafMapping.getMetadata().getName()));
        } catch (KubernetesClientException e) {
            log.error(format("wafmapping: %s exception:", wafMapping.getMetadata().getName()));
            log.error(e.toString());
        }
    }

    private void callCreateWafMapping(WafMapping wafMapping){
        if(wafMapping.getSpec().getMappingId() == null) {
            MappingDTO mappingDTO = new MappingDTO();
            mappingDTO.setMappingName(wafMapping.getSpec().getMappingName());
            mappingDTO.setBackendGroupName(wafMapping.getSpec().getBackendGroupName());
            mappingDTO.setBackendHostName(wafMapping.getSpec().getBackendName());
            mappingDTO.setBackendPath(wafMapping.getSpec().getMappingBackendPath());
            mappingDTO.setBackendProtocol(wafMapping.getSpec().getBackendProtocol());
            mappingDTO.setBackendPort(wafMapping.getSpec().getBackendPort());
            mappingDTO.setEntryPath(wafMapping.getSpec().getMappingEntryPath());
            mappingDTO.setVhostName(wafMapping.getSpec().getVhostName());
            this.airlockWAFRestService.createMappig(mappingDTO);
        } else {
            log.warn(format("WafMapping with name %s and id %d already exists, call update instead.", wafMapping.getSpec().getMappingName(), wafMapping.getSpec().getMappingId()));
        }
    }
}
