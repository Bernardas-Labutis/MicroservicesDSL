package lt.vu.labutis.jsontomicroservicegenerator.json;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "registry"
})
@Generated("jsonschema2pojo")
public class Root {

    @JsonProperty("registry")
    private Registry registry;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("registry")
    public Registry getRegistry() {
        return registry;
    }

    @JsonProperty("registry")
    public void setRegistry(Registry registry) {
        this.registry = registry;
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
