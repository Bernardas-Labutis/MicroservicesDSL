package lt.vu.labutis.jsontomicroservicegenerator.factory.yml.generated;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "gateway"
})
@Generated("jsonschema2pojo")
public class Cloud {

    @JsonProperty("gateway")
    private Gateway gateway;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("gateway")
    public Gateway getGateway() {
        return gateway;
    }

    @JsonProperty("gateway")
    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
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