package it.frigir.msscbeerservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;

@Configuration
public class FeignClientConfig {

    public BasicAuthenticationInterceptor basicAuthenticationInterceptor(@Value("${sfg.brewery.inventory-user}") String inventoryUser,
                                                                         @Value("${sfg.brewery.inventory-user}") String inventoryPassword) {
        return new BasicAuthenticationInterceptor(inventoryUser, inventoryPassword);
    }

}
