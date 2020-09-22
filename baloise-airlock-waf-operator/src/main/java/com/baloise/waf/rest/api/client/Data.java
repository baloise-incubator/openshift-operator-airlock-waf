package com.baloise.waf.rest.api.client;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"type",
"attributes"
})
public class Data {

@JsonProperty("type")
private String type;
@JsonProperty("attributes")
private Attributes attributes;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("type")
public String getType() {
return type;
}

@JsonProperty("type")
public void setType(String type) {
this.type = type;
}

@JsonProperty("attributes")
public Attributes getAttributes() {
return attributes;
}

@JsonProperty("attributes")
public void setAttributes(Attributes attributes) {
this.attributes = attributes;
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