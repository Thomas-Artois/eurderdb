package com.switchfully.eurderdb.customer;

import com.switchfully.eurderdb.admin.AdminRepository;
import com.switchfully.eurderdb.admin.domain.Admin;
import com.switchfully.eurderdb.customer.domain.Customer;
import com.switchfully.eurderdb.customer.dto.CreateCustomerDto;
import com.switchfully.eurderdb.customer.dto.CustomerDto;
import com.switchfully.eurderdb.exceptions.AdminNotFoundException;
import com.switchfully.eurderdb.exceptions.AdminPasswordIncorrectException;
import com.switchfully.eurderdb.exceptions.CustomerDoesntExistException;
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

    public CustomerDto findCustomberById(Long id) throws CustomerDoesntExistException{
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) {
            return customerMapper.mapCustomerToCustomerDto(customerOptional.get());
        } else {
            // Throw an exception or handle the case where customer is not found
            throw new CustomerDoesntExistException();
        }
    }


//    private void checkIfCustomerExists(Long id) throws CustomerDoesntExistException {
//        Optional<Customer> customerToCheck = customerRepository.findById(id);
//
//        if (customerToCheck.isEmpty()) {
//            throw new CustomerDoesntExistException();
//        }
//    }
}
