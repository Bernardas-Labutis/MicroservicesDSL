package lt.vu.labutis.jsontomicroservicegenerator.factory.yml.generated;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "server",
        "spring",
        "eureka"
})
@Generated("jsonschema2pojo")
public class ServiceYml {

    @JsonProperty("server")
    private Server server;
    @JsonProperty("spring")
    private Spring spring;
    @JsonProperty("eureka")
    private Eureka eureka;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("server")
    public Server getServer() {
        return server;
    }

    @JsonProperty("server")
    public void setServer(Server server) {
        this.server = server;
    }

    @JsonProperty("spring")
    public Spring getSpring() {
        return spring;
    }

    @JsonProperty("spring")
    public void setSpring(Spring spring) {
        this.spring = spring;
    }

    @JsonProperty("eureka")
    public Eureka getEureka() {
        return eureka;
    }

    @JsonProperty("eureka")
    public void setEureka(Eureka eureka) {
        this.eureka = eureka;
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
