package lt.vu.labutis.jsontomicroservicegenerator.factory.yml.generated;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "client",
        "instance"
})
@Generated("jsonschema2pojo")
public class Eureka {

    @JsonProperty("client")
    private Client client;
    @JsonProperty("instance")
    private Instance instance;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("client")
    public Client getClient() {
        return client;
    }

    @JsonProperty("client")
    public void setClient(Client client) {
        this.client = client;
    }

    @JsonProperty("instance")
    public Instance getInstance() {
        return instance;
    }

    @JsonProperty("instance")
    public void setInstance(Instance instance) {
        this.instance = instance;
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
