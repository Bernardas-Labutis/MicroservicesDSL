package lt.vu.labutis.jsontomicroservicegenerator.factory.yml.generated;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "uri",
        "predicates"
})
@Generated("jsonschema2pojo")
public class Route {

    @JsonProperty("id")
    private String id;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("predicates")
    private List<String> predicates = null;
    @JsonProperty("filters")
    private List<String> filters = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }

    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
    }

    @JsonProperty("predicates")
    public List<String> getPredicates() {
        return predicates;
    }

    @JsonProperty("predicates")
    public void setPredicates(List<String> predicates) {
        this.predicates = predicates;
    }

    @JsonProperty("filters")
    public List<String> getFilters() {
        return filters;
    }

    @JsonProperty("filters")
    public void setFilters(List<String> filters) {
        this.filters = filters;
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
