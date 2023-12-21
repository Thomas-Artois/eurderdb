package com.switchfully.eurderdb.customer;

import com.switchfully.eurderdb.admin.AdminService;
import com.switchfully.eurderdb.admin.dto.AdminDto;
import com.switchfully.eurderdb.customer.domain.Address;
import com.switchfully.eurderdb.customer.dto.CreateCustomerDto;
import com.switchfully.eurderdb.customer.dto.CustomerDto;
import com.switchfully.eurderdb.exceptions.CustomerDoesntExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class CustomerServiceTests {

    @Autowired
    private  CustomerService customerService;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void givenUser_whenCreateNewCustomer_thenNewCustomerIsSavedIntoDatabase() {
        //GIVEN
        CreateCustomerDto createCustomerDto =
                new CreateCustomerDto("Igor", "De Verschrikkelijke", "igor@gmail.com", "0111223344",
                        new Address("IgorStraat", "21B", "Charleroi"));

        //WHEN
        customerService.saveCustomer(createCustomerDto);

        //THEN
        assertThat(customerRepository.findById(2L).get().getFirstName()).isEqualTo(customerMapper.mapCreateCustomerDtoToCustomer(createCustomerDto).getFirstName());

    }

    @Test
    void givenCustomerInDatabase_whenGetCustomerByInvalidId_thenThrowCustomerDoesntExistException() {
        //WHEN & THEN
        assertThrows(CustomerDoesntExistException.class, () -> customerService.findCustomberById(2L));
    }

    @Test
    void whenGetAllCustomers_thenReturnListOfAllCustomers() {
        //WHEN
        List<CustomerDto> listOfCustomerDto = customerService.findAllCustomers();

        //THEN
        assertThat(listOfCustomerDto).hasSize(1);
        assertThat(listOfCustomerDto.stream().map(CustomerDto::getEmail)).containsExactly("TestingEmail@gmail.com");
    }

    @Test
    void whenGetCustomerByValidId_thenReturnCustomer() {
        //WHEN
        CustomerDto customerDto = customerService.findCustomberById(1L);

        //THEN
        assertThat(customerDto).isNotNull();
        assertThat(customerDto.getLastName()).isEqualTo("TestingLastName");
        assertThat(customerDto.getAddress().getLocation()).isEqualTo("TestingLocation");
        assertThat(customerDto.getAddress().getStreetName()).isEqualTo("TestingStreetName");
        assertThat(customerDto.getAddress().getStreetNumber()).isEqualTo("TestingStreetNumber");
    }
}
