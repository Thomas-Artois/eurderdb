package com.switchfully.eurderdb.customer;

import com.switchfully.eurderdb.customer.dto.CreateCustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer(@RequestBody CreateCustomerDto createCustomerDto) {
        return customerService.saveCustomer(createCustomerDto);
    }

    @GetMapping(produces = "application/json")
    public List<CustomerDto> getAllCustomers() {
        return customerService.findAllCustomers();
    }

}
