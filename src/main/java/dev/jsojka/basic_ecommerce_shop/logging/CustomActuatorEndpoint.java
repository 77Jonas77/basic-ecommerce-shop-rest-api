package dev.jsojka.basic_ecommerce_shop.logging;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "customEndpoint")
public class CustomActuatorEndpoint {

    @ReadOperation
    public String getCustomActuatorData() {
        return "Hello there general!";
    }
}
