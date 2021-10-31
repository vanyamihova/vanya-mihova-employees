package eu.vanyamihova.employees.core.api;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Main structure of responses for this application
 *
 * @author Vanya Mihova - vanya.mihova89@gmail.com
 * @since 30.10.2021
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ResponseBodyData<T> {

    private final String status;

    private T data;

    private String error;

    public ResponseBodyData(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public ResponseBodyData(String status, String errorMessage) {
        this.status = status;
        this.error = errorMessage;
    }

    public String getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public T getData() {
        return data;
    }

}
