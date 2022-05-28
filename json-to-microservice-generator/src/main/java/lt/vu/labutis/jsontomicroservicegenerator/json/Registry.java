package lt.vu.labutis.jsontomicroservicegenerator.json;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "port",
        "services",
        "gateway"
})
@Generated("jsonschema2pojo")
public class Registry {

    @JsonProperty("port")
    private Integer port;
    @JsonProperty("services")
    private List<Service> services = null;
    @JsonProperty("gateway")
    private Gateway gateway;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("port")
    public Integer getPort() {
        return port;
    }

    @JsonProperty("port")
    public void setPort(Integer port) {
        this.port = port;
    }

    @JsonProperty("services")
    public List<Service> getServices() {
        return services;
    }

    @JsonProperty("services")
    public void setServices(List<Service> services) {
        this.services = services;
    }

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