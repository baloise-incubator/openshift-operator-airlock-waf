package com.baloise.waf.operator.crd;

import io.fabric8.kubernetes.api.builder.Function;
import io.fabric8.kubernetes.client.CustomResourceDoneable;

public class DoneableWafMapping extends CustomResourceDoneable<WafMapping> {
    public DoneableWafMapping(WafMapping resource, Function<WafMapping, WafMapping> function) {
        super(resource, function);
    }
}
