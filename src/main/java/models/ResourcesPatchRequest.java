package models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Optional;

/**
 * Created by a.a.perfilyev on 16.01.2018.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResourcesPatchRequest {
    String path;
    Optional<String> fields;

    public ResourcesPatchRequest(String path, Optional<String> fields) {
        this.path = path;
        this.fields = fields;
    }

    public ResourcesPatchRequest(String path) {
        this.path = path;
        this.fields = Optional.empty();
    }
}
