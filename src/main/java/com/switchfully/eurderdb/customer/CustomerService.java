package com.switchfully.eurderdb.customer;

import com.switchfully.eurderdb.admin.AdminRepository;
import com.switchfully.eurderdb.admin.domain.Admin;
import com.switchfully.eurderdb.customer.dto.CreateCustomerDto;
import com.switchfully.eurderdb.customer.dto.CustomerDto;
import com.switchfully.eurderdb.exceptions.AdminNotFoundException;
import com.switchfully.eurderdb.exceptions.AdminPasswordIncorrectException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AdminRepository adminRepository;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper, AdminRepository adminRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.adminRepository = adminRepository;
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
