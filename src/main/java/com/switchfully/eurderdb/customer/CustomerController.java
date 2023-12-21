package com.switchfully.eurderdb.customer;

import com.switchfully.eurderdb.admin.AdminService;
import com.switchfully.eurderdb.customer.dto.CreateCustomerDto;
import com.switchfully.eurderdb.customer.dto.CustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final AdminService adminService;

    public CustomerController(CustomerService customerService, AdminService adminService) {
        this.customerService = customerService;
        this.adminService = adminService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer(@RequestBody CreateCustomerDto createCustomerDto) {
        return customerService.saveCustomer(createCustomerDto);
    }

    @GetMapping(produces = "application/json")
    public List<CustomerDto> getAllCustomers(@RequestHeader String username, @RequestHeader String password) {
        adminService.findAdminByUsername(username);
        adminService.checkIfAdminPasswordIsCorrect(username, password);

        return customerService.findAllCustomers();
    }

    @GetMapping(path = "{id}", produces = "application/json")
    public CustomerDto getCustomerById(@RequestHeader String username, @RequestHeader String password, @PathVariable Long id) {
        adminService.findAdminByUsername(username);
        adminService.checkIfAdminPasswordIsCorrect(username, password);

        return customerService.findCustomberById(id);
    }

}
