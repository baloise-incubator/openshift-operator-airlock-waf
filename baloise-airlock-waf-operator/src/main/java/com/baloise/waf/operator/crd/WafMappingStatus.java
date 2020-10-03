package com.baloise.waf.operator.crd;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.KubernetesResource;

@JsonDeserialize(
        using = JsonDeserializer.None.class
)
public class WafMappingStatus implements KubernetesResource {
    private Integer mappingId;
    private Integer backendId;

    public Integer getMappingId() {
        return mappingId;
    }

    public void setMappingId(Integer mappingId) {
        this.mappingId = mappingId;
    }

    public Integer getBackendId() {
        return backendId;
    }

    public void setBackendId(Integer backendId) {
        this.backendId = backendId;
    }

    @Override
    public String toString() {
        return "WafMappingStatus{" +
                "mappingId=" + mappingId +
                ", backendId=" + backendId +
                '}';
    }
}
