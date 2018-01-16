package models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Optional;

/**
 * Created by a.a.perfilyev on 16.01.2018.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResourcesDeleteRequest {
    String path;
    Optional<String> fields;
    Optional<Boolean> force_async;
    Optional<Boolean> permanently;

    public String getPath() {
        return path;
    }

    public Optional<String> getFields() {
        return fields;
    }

    public Optional<Boolean> getForce_async() {
        return force_async;
    }

    public Optional<Boolean> getPermanently() {
        return permanently;
    }

    public ResourcesDeleteRequest(String path, Optional<String> fields,
                                  Optional<Boolean> force_async, Optional<Boolean> permanently) {
        this.path = path;
        this.fields = fields;
        this.force_async = force_async;
        this.permanently = permanently;
    }

    public ResourcesDeleteRequest(String path) {
        this.path = path;
        this.fields = Optional.empty();
        this.force_async = Optional.of(false);
        this.permanently = Optional.of(true);
    }
}
