package lt.vu.labutis.jsontomicroservicegenerator.factory.yml.generated;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "register-with-eureka",
        "fetch-registry",
        "service-url"
})
@Generated("jsonschema2pojo")
public class Client {

    @JsonProperty("register-with-eureka")
    private Boolean registerWithEureka;
    @JsonProperty("fetch-registry")
    private Boolean fetchRegistry;
    @JsonProperty("service-url")
    private ServiceUrl serviceUrl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("register-with-eureka")
    public Boolean getRegisterWithEureka() {
        return registerWithEureka;
    }

    @JsonProperty("register-with-eureka")
    public void setRegisterWithEureka(Boolean registerWithEureka) {
        this.registerWithEureka = registerWithEureka;
    }

    @JsonProperty("fetch-registry")
    public Boolean getFetchRegistry() {
        return fetchRegistry;
    }

    @JsonProperty("fetch-registry")
    public void setFetchRegistry(Boolean fetchRegistry) {
        this.fetchRegistry = fetchRegistry;
    }

    @JsonProperty("service-url")
    public ServiceUrl getServiceUrl() {
        return serviceUrl;
    }

    @JsonProperty("service-url")
    public void setServiceUrl(ServiceUrl serviceUrl) {
        this.serviceUrl = serviceUrl;
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
