package models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Optional;

/**
 * Created by a.a.perfilyev on 16.01.2018.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UploadPostRequest {
    String path;
    String url;
    Optional<String> fields;
    Optional<Boolean> disable_redirects;

    public String getPath() {
        return path;
    }

    public String getUrl() {
        return url;
    }

    public Optional<String> getFields() {
        return fields;
    }

    public Optional<Boolean> getDisable_redirects() {
        return disable_redirects;
    }


    public UploadPostRequest(String path, String url, Optional<String> fields,
                             Optional<Boolean> disable_redirects) {
        this.path = path;
        this.url = url;
        this.fields = fields;
        this.disable_redirects = disable_redirects;
    }

    public UploadPostRequest(String path, String url) {
        this.path = path;
        this.url = url;
        this.fields = Optional.empty();
        this.disable_redirects = Optional.empty();
    }
}
