package models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Optional;

/**
 * Created by a.a.perfilyev on 16.01.2018.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResourcesGetRequest {
    String path;
    Optional<String> fields;
    Optional<Integer> limit;
    Optional<Integer> offset;
    Optional<Boolean> preview_crop;
    Optional<String> preview_size;
    Optional<String> sort;

    public String getPath() {
        return path;
    }

    public Optional<String> getFields() {
        return fields;
    }

    public Optional<Integer> getLimit() {
        return limit;
    }

    public Optional<Integer> getOffset() {
        return offset;
    }

    public Optional<Boolean> getPreview_crop() {
        return preview_crop;
    }

    public Optional<String> getPreview_size() {
        return preview_size;
    }

    public Optional<String> getSort() {
        return sort;
    }

    public ResourcesGetRequest(String path, Optional<String> fields,
                               Optional<Integer> limit, Optional<Integer> offset,
                               Optional<Boolean> preview_crop, Optional<String> preview_size,
                               Optional<String> sort) {
        this.path = path;
        this.fields = fields;
        this.limit = limit;
        this.offset = offset;
        this.preview_crop = preview_crop;
        this.preview_size = preview_size;
        this.sort = sort;
    }

    public ResourcesGetRequest(String path) {
        this.path = path;
        this.fields = Optional.empty();
        this.limit = Optional.empty();
        this.offset = Optional.empty();
        this.preview_crop = Optional.empty();
        this.preview_size = Optional.empty();
        this.sort = Optional.empty();
    }
}
