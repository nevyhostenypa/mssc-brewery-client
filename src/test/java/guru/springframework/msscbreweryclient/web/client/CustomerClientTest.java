package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerClientTest {
    @Autowired
    CustomerClient client;

    @Test
    void getCustomerById() {
        CustomerDto dto = client.getCustomerById(UUID.randomUUID());
        assertNotNull(dto);
    }

    @Test
    void testSaveNewCustomer() {
        CustomerDto customer = CustomerDto.builder().name("Mark Erickson NEW").build();
        URI uri = client.saveNewCustomer(customer);
        assertNotNull(uri);
        System.out.println(uri.toString());
    }

    @Test
    void testUpdateCustomer() {
        CustomerDto customer = CustomerDto.builder().name("New customer").build();
        client.updateCustomer(UUID.randomUUID(), customer);
    }

    @Test
    void testDeleteCustomer() {
        client.deleteCustomer(UUID.randomUUID());
    }
}
