package lt.vu.labutis.jsontomicroservicegenerator.factory.yml.generated;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "defaultZone"
})
@Generated("jsonschema2pojo")
public class ServiceUrl {

    @JsonProperty("defaultZone")
    private String defaultZone;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("defaultZone")
    public String getDefaultZone() {
        return defaultZone;
    }

    @JsonProperty("defaultZone")
    public void setDefaultZone(String defaultZone) {
        this.defaultZone = defaultZone;
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
