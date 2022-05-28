package lt.vu.labutis.jsontomicroservicegenerator.json;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "port",
        "rest_api",
        "database",
        "externalCalls"
})
@Generated("jsonschema2pojo")
public class Service {

    @JsonProperty("name")
    private String name;
    @JsonProperty("port")
    private Integer port;
    @JsonProperty("rest_api")
    private RestApi restApi;
    @JsonProperty("database")
    private Database database;
    @JsonProperty("externalCalls")
    private List<ExternalCall> externalCalls = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("port")
    public Integer getPort() {
        return port;
    }

    @JsonProperty("port")
    public void setPort(Integer port) {
        this.port = port;
    }

    @JsonProperty("rest_api")
    public RestApi getRestApi() {
        return restApi;
    }

    @JsonProperty("rest_api")
    public void setRestApi(RestApi restApi) {
        this.restApi = restApi;
    }

    @JsonProperty("database")
    public Database getDatabase() {
        return database;
    }

    @JsonProperty("database")
    public void setDatabase(Database database) {
        this.database = database;
    }

    @JsonProperty("externalCalls")
    public List<ExternalCall> getExternalCalls() {
        return externalCalls;
    }

    @JsonProperty("externalCalls")
    public void setExternalCalls(List<ExternalCall> externalCalls) {
        this.externalCalls = externalCalls;
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
