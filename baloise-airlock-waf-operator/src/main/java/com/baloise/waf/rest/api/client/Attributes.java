package com.baloise.waf.rest.api.client;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"name",
"entryPath",
"backendPath"
})
public class Attributes {

@JsonProperty("name")
private String name;
@JsonProperty("entryPath")
private EntryPath entryPath;
@JsonProperty("backendPath")
private String backendPath;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("entryPath")
public EntryPath getEntryPath() {
return entryPath;
}

@JsonProperty("entryPath")
public void setEntryPath(EntryPath entryPath) {
this.entryPath = entryPath;
}

@JsonProperty("backendPath")
public String getBackendPath() {
return backendPath;
}

@JsonProperty("backendPath")
public void setBackendPath(String backendPath) {
this.backendPath = backendPath;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}