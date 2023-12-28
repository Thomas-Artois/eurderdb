package com.switchfully.eurderdb.customer;

import com.switchfully.eurderdb.admin.AdminRepository;
import com.switchfully.eurderdb.admin.domain.Admin;
import com.switchfully.eurderdb.customer.domain.Customer;
import com.switchfully.eurderdb.customer.dto.CreateCustomerDto;
import com.switchfully.eurderdb.customer.dto.CustomerDto;
import com.switchfully.eurderdb.exceptions.AdminNotFoundException;
import com.switchfully.eurderdb.exceptions.AdminPasswordIncorrectException;
import com.switchfully.eurderdb.exceptions.CustomerDoesntExistException;
import com.switchfully.eurderdb.exceptions.CustomerPasswordIncorrectException;
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

    public CustomerDto findCustomberById(Long id) throws CustomerDoesntExistException{
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) {
            return customerMapper.mapCustomerToCustomerDto(customerOptional.get());
        } else {
            throw new CustomerDoesntExistException();
        }
    }

    public CustomerDto findCustomerByEmail(String email) throws CustomerDoesntExistException {
        Customer customer = customerRepository.findCustomerByEmail(email).stream().findFirst().orElseThrow(CustomerDoesntExistException::new).get(0);

        return customerMapper.mapCustomerToCustomerDto(customer);

    }


    public void checkIfPasswordIsCorrect(CustomerDto customerDto, String password) throws CustomerPasswordIncorrectException{
        if (!(customerDto.getPassword().equals(password))) {
            throw new CustomerPasswordIncorrectException();
        }
    }
}
