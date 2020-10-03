package com.baloise.waf.operator.crd;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.CustomResource;

public class WafMapping extends CustomResource implements Namespaced {
    private WafMappingSpec spec;
    private WafMappingStatus status;

    @Override
    public ObjectMeta getMetadata() {
        return super.getMetadata();
    }

    @Override
    public String getApiVersion() {
        return super.getApiVersion();
    }

    public WafMappingSpec getSpec() {
        return spec;
    }

    public void setSpec(WafMappingSpec spec) {
        this.spec = spec;
    }

    public WafMappingStatus getStatus() {
        return status;
    }

    public void setStatus(WafMappingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WafMapping{" +
                "spec=" + spec +
                ", status=" + status +
                '}';
    }
}
