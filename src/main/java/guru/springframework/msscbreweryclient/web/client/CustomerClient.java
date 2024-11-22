package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.UUID;


@Component
public class CustomerClient {
    public final String CUSTOMER_PATH_V1 = "/api/v1/customer/";

    @Value("${sfg.brewery.apihost}")
    private String apihost;

    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public CustomerDto getCustomerById(UUID uuid) {
        return restTemplate.getForObject( apihost + CUSTOMER_PATH_V1 + uuid.toString(), CustomerDto.class);
    }

}
