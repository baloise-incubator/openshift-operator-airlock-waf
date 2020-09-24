package com.baloise.waf.rest.api.client.beans;

public class MappingDTO {
    private String mappingName;
    private String entryPath;
    private String backendPath;
    private String backendGroupName;
    private String backendHostName;
    private String backendProtocol;
    private Integer backendPort;
    private String vhostName;

    public String getMappingName() {
        return mappingName;
    }

    public void setMappingName(String mappingName) {
        this.mappingName = mappingName;
    }

    public String getEntryPath() {
        return entryPath;
    }

    public void setEntryPath(String entryPath) {
        this.entryPath = entryPath;
    }

    public String getBackendPath() {
        return backendPath;
    }

    public void setBackendPath(String backendPath) {
        this.backendPath = backendPath;
    }

    public String getBackendGroupName() {
        return backendGroupName;
    }

    public void setBackendGroupName(String backendGroupName) {
        this.backendGroupName = backendGroupName;
    }

    public String getBackendHostName() {
        return backendHostName;
    }

    public void setBackendHostName(String backendHostName) {
        this.backendHostName = backendHostName;
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
}
