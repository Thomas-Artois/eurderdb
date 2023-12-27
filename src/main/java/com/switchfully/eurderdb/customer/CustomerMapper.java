package com.switchfully.eurderdb.customer;

import com.switchfully.eurderdb.customer.domain.Customer;
import com.switchfully.eurderdb.customer.dto.CreateCustomerDto;
import com.switchfully.eurderdb.customer.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerDto mapCustomerToCustomerDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                customer.getPassword()
        );
    }

    public Customer mapCreateCustomerDtoToCustomer(CreateCustomerDto createCustomerDto) {
        return new Customer(
                createCustomerDto.getFirstName(),
                createCustomerDto.getLastName(),
                createCustomerDto.getEmail(),
                createCustomerDto.getPhoneNumber(),
                createCustomerDto.getAddress(),
                createCustomerDto.getPassword()
        );
    }

}
