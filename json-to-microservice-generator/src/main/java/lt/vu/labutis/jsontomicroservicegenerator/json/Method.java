package lt.vu.labutis.jsontomicroservicegenerator.json;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "path",
        "name",
        "request",
        "response"
})
@Generated("jsonschema2pojo")
public class Method {

    @JsonProperty("type")
    private String type;
    @JsonProperty("path")
    private String path;
    @JsonProperty("name")
    private String name;
    @JsonProperty("request")
    private JsonNode request = null;
    @JsonProperty("response")
    private JsonNode response = null;
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

    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("request")
    public JsonNode getRequest() {
        return request;
    }

    @JsonProperty("request")
    public void setRequest(JsonNode request) {
        this.request = request;
    }

    @JsonProperty("response")
    public JsonNode getResponse() {
        return response;
    }

    @JsonProperty("response")
    public void setResponse(JsonNode response) {
        this.response = response;
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
