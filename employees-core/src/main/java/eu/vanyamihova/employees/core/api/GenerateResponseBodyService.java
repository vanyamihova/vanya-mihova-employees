package eu.vanyamihova.employees.core.api;

import org.springframework.stereotype.Component;

/**
 * Main structure of services, which are generating
 * responses for this application
 *
 * @author Vanya Mihova - vanya.mihova89@gmail.com
 * @since 30.10.2021
 */
@Component
public abstract class GenerateResponseBodyService<T> {

    protected GenerateResponseBodyService() {
    }

    protected ResponseBodyData<T> getOkResponseBody(T data) {
        return new ResponseBodyData<>("OK", data);
    }

    protected ResponseBodyData<T> getErrorResponseBody(String message) {
        return new ResponseBodyData<>("Error", message);
    }

}
