package com.baloise.waf.operator.crd;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.KubernetesResource;

@JsonDeserialize(
        using = JsonDeserializer.None.class
)
public class WafMappingSpec implements KubernetesResource {

    private String mappingName;
    private Integer mappingId;
    private String mappingEntryPath;
    private String mappingBackendPath;
    private String backendGroupName;
    private Integer backendGroupId;
    private String backendName;
    private String backendProtocol;
    private Integer backendPort;
    private String vhostName;

    public String getMappingName() {
        return mappingName;
    }

    public void setMappingName(String mappingName) {
        this.mappingName = mappingName;
    }

    public Integer getMappingId() {
        return mappingId;
    }

    public void setMappingId(Integer mappingId) {
        this.mappingId = mappingId;
    }

    public String getMappingEntryPath() {
        return mappingEntryPath;
    }

    public void setMappingEntryPath(String mappingEntryPath) {
        this.mappingEntryPath = mappingEntryPath;
    }

    public String getMappingBackendPath() {
        return mappingBackendPath;
    }

    public void setMappingBackendPath(String mappingBackendPath) {
        this.mappingBackendPath = mappingBackendPath;
    }

    public String getBackendGroupName() {
        return backendGroupName;
    }

    public void setBackendGroupName(String backendGroupName) {
        this.backendGroupName = backendGroupName;
    }

    public Integer getBackendGroupId() {
        return backendGroupId;
    }

    public void setBackendGroupId(Integer backendGroupId) {
        this.backendGroupId = backendGroupId;
    }

    public String getBackendName() {
        return backendName;
    }

    public void setBackendName(String backendName) {
        this.backendName = backendName;
    }

    public String getBackendProtocol() {
        return backendProtocol;
    }

    public void setBackendProtocol(String backendProtocol) {
        this.backendProtocol = backendProtocol;
    }

    public Integer getBackendPort() {
        return backendPort;
    }

    public void setBackendPort(Integer backendPort) {
        this.backendPort = backendPort;
    }

    public String getVhostName() {
        return vhostName;
    }

    public void setVhostName(String vhostName) {
        this.vhostName = vhostName;
    }

    @Override
    public String toString() {
        return "WafMappingSpec{" +
                "mappingName='" + mappingName + '\'' +
                ", mappingId=" + mappingId +
                ", mappingEntryPath='" + mappingEntryPath + '\'' +
                ", mappingBackendPath='" + mappingBackendPath + '\'' +
                ", backendGroupName='" + backendGroupName + '\'' +
                ", backendGroupId=" + backendGroupId +
                ", backendName='" + backendName + '\'' +
                ", backendProtocol='" + backendProtocol + '\'' +
                ", backendPort=" + backendPort +
                ", vhostName='" + vhostName + '\'' +
                '}';
    }
}
