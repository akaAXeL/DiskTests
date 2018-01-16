import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import models.ResourcesDeleteRequest;
import models.ResourcesGetRequest;
import models.ResourcesPatchRequest;
import models.UploadPostRequest;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by a.a.perfilyev on 16.01.2018.
 */
public class ApiClient {

    private static volatile ApiClient instance;

    public static ApiClient getInstance() {
        ApiClient localInstance = instance;
        if (localInstance == null) {
            synchronized (ApiClient.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ApiClient();
                }
            }
        }
        return localInstance;
    }

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final Map<String, String> headers;
    static {
        Map<String, String> aMap = new HashMap<>();
        aMap.put("authorization", Config.AUTH_TOKEN);
        aMap.put("cache-control", "no-cache");
        headers = Collections.unmodifiableMap(aMap);
    }
    ObjectMapper mapper = new ObjectMapper();


    HttpResponse<JsonNode> getResources(String path) throws UnirestException {
        ResourcesGetRequest request = new ResourcesGetRequest(path);
        mapper.registerModule(new Jdk8Module());
        Map<String, Object> params = mapper.convertValue(request, Map.class);
        return Unirest.get(Config.API_URL)
                .headers(headers)
                .queryString(params)
                .asJson();
    }

    HttpResponse<JsonNode> deleteResources(String path) throws UnirestException {
        ResourcesDeleteRequest request = new ResourcesDeleteRequest(path);
        mapper.registerModule(new Jdk8Module());
        Map<String, Object> params = mapper.convertValue(request, Map.class);
        return Unirest.delete(Config.API_URL)
                .headers(headers)
                .queryString(params)
                .asJson();
    }

    HttpResponse<JsonNode> operationStatus(String operationID) throws UnirestException {
        return Unirest.get(Config.API_OPERATIONS_URL + "/" + operationID)
                .headers(headers)
                .asJson();
    }

    HttpResponse<JsonNode> uploadResources(String path, String url) throws UnirestException {
        UploadPostRequest request = new UploadPostRequest(path, url);
        mapper.registerModule(new Jdk8Module());
        Map<String, Object> params = mapper.convertValue(request, Map.class);
        return Unirest.post(Config.API_URL + "/upload")
                .headers(headers)
                .queryString(params)
                .asJson();
    }

    HttpResponse<JsonNode> patchResources(String path, Map<String, String> customProperties) throws UnirestException {
        Map<String, Map<String,String>> body = new HashMap<>();
        body.put("custom_properties", customProperties);
        ResourcesPatchRequest request = new ResourcesPatchRequest(path);
        mapper.registerModule(new Jdk8Module());
        Map<String, Object> params = mapper.convertValue(request, Map.class);
        return Unirest.patch(Config.API_URL)
                .headers(headers)
                .queryString(params)
                .body(new JSONObject(body))
                .asJson();
    }
}
