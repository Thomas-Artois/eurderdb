package com.switchfully.eurderdb.customer;

import com.switchfully.eurderdb.customer.dto.CreateCustomerDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerDto saveCustomer(CreateCustomerDto createCustomerDto) {
        return customerMapper.mapCustomerToCustomerDto(
                customerRepository.save(
                        customerMapper.mapCreateCustomerDtoToCustomer(
                                createCustomerDto)));
    }

    public List<CustomerDto> findAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::mapCustomerToCustomerDto).collect(Collectors.toList());
    }
}
