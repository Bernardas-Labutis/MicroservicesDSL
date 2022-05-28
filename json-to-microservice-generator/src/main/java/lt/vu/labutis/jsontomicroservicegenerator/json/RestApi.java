package lt.vu.labutis.jsontomicroservicegenerator.json;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "path",
        "methods"
})
@Generated("jsonschema2pojo")
public class RestApi {

    @JsonProperty("path")
    private String path;
    @JsonProperty("methods")
    private List<Method> methods = null;
    @JsonProperty("tests")
    private List<String> tests = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

    @JsonProperty("methods")
    public List<Method> getMethods() {
        return methods;
    }

    @JsonProperty("methods")
    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonProperty("tests")
    public List<String> getTests() {
        return tests;
    }

    @JsonProperty("tests")
    public void setTests(List<String> tests) {
        this.tests = tests;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
