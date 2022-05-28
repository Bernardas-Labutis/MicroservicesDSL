package lt.vu.labutis.jsontomicroservicegenerator.factory.yml.generated;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "application",
        "cloud"
})
@Generated("jsonschema2pojo")
public class Spring {

    @JsonProperty("application")
    private Application application;
    @JsonProperty("cloud")
    private Cloud cloud;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("application")
    public Application getApplication() {
        return application;
    }

    @JsonProperty("application")
    public void setApplication(Application application) {
        this.application = application;
    }

    @JsonProperty("cloud")
    public Cloud getCloud() {
        return cloud;
    }

    @JsonProperty("cloud")
    public void setCloud(Cloud cloud) {
        this.cloud = cloud;
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
