package com.switchfully.eurderdb.customer;

import com.switchfully.eurderdb.admin.AdminMapper;
import com.switchfully.eurderdb.admin.AdminService;
import com.switchfully.eurderdb.admin.domain.Admin;
import com.switchfully.eurderdb.admin.dto.AdminDto;
import com.switchfully.eurderdb.customer.domain.Address;
import com.switchfully.eurderdb.customer.domain.Customer;
import com.switchfully.eurderdb.customer.dto.CreateCustomerDto;
import com.switchfully.eurderdb.customer.dto.CustomerDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureTestDatabase
public class CustomerControllerIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminMapper adminMapper;

    @Test
    void whenSaveCustomer_thenCustomerIsSavedInDatabase() {
        CreateCustomerDto createCustomerDto = new CreateCustomerDto(
                "voornaam",
                "achternaam",
                "naam@gmail.com",
                "0456778899",
                new Address(
                        "straatnaam",
                        "nummer",
                        "locatie"
                ),
                "wachtwoord"
        );

        CustomerDto customerDto =
                RestAssured
                        .given()
                        .body(createCustomerDto)
                        .header("username", "adminTest")
                        .header("password", "adminPasswordTest")
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .when()
                        .port(port)
                        .post("/customer")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(CustomerDto.class);

        Customer customerEntity = customerMapper.mapCustomerDtoToCustomer(customerService.findCustomberById(customerDto.getId()));

        assertThat(customerDto.getFirstName()).isEqualTo(customerEntity.getFirstName());
        assertThat(customerDto.getPassword()).isEqualTo(customerEntity.getPassword());

    }

    @Test
    void whenAdminGetsSingleCustomerById_thenReturnThatCustomer() {
        AdminDto adminDto = adminService.findAdminByUsername("adminTest");

        CustomerDto customerDto = customerService.findCustomerByEmail("TestingEmail@gmail.com");
        Long customerId = customerDto.getId();

        Response response =
                RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                        .header("username", adminDto.getUsername())
                        .header("password", adminDto.getPassword())
                        .when()
                        .port(port)
                        .get("/customer/" + customerId)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .response();

        SoftAssertions testBundle = new SoftAssertions();

        CustomerDto returnedCustomerDto = response.as(CustomerDto.class);

        testBundle.assertThat(response.statusCode()).as("HttpStatus")
                .isEqualTo(HttpStatus.OK.value());

        testBundle.assertThat(returnedCustomerDto).as("CustomerDto")
                .isEqualTo(customerDto);

        testBundle.assertAll();

    }

    @Test
    void whenAdminGetsAllCustomers_thenReturnAllCustomers() {
        AdminDto adminDto = adminService.findAdminByUsername("adminTest");

        List<CustomerDto> customerDtoList =
                RestAssured
                        .given()
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .header("username", adminDto.getUsername())
                        .header("password", adminDto.getPassword())
                        .when()
                        .port(port)
                        .get("/customer")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .body()
                        .jsonPath()
                        .getList(".", CustomerDto.class);

        assertThat(customerDtoList).hasSize(customerService.findAllCustomers().size());
        assertThat(customerDtoList
                .stream()
                .filter(customerDto -> customerDto.getFirstName().equals("TestingFirstName"))
                .findAny()
                .get()
                .getPhoneNumber())

                .isEqualTo("0444444444");

    }
}
