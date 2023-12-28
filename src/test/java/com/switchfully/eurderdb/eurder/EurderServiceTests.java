package com.switchfully.eurderdb.eurder;

import com.switchfully.eurderdb.customer.domain.Address;
import com.switchfully.eurderdb.customer.domain.Customer;
import com.switchfully.eurderdb.eurder.domain.Eurder;
import com.switchfully.eurderdb.exceptions.EurderDoesntExistException;
import com.switchfully.eurderdb.itemgroup.ItemGroupRepository;
import com.switchfully.eurderdb.itemgroup.domain.ItemGroup;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;
import static org.assertj.core.api.InstanceOfAssertFactories.LOCAL_DATE_TIME;

@SpringBootTest
@Transactional
public class EurderServiceTests {

    @Autowired
    private EurderService eurderService;
    @Autowired
    private EurderRepository eurderRepository;
    @Autowired
    private ItemGroupRepository itemGroupRepository;

    @Test
    void whenFindByValidId_thenReturnCorrectEurder() {
        //WHEN
        Eurder eurder = eurderRepository.findEurderById(1L).orElseThrow(EurderDoesntExistException::new);

        //THEN
        assertThat(eurder.getCustomer().getFirstName()).isEqualTo("TestingFirstName");
    }

    @Test
    void whenFindByValidId_thenReturnCorrectEurderTotalPrice() {
        //WHEN
        Eurder eurder = eurderRepository.findEurderById(1L).orElseThrow(EurderDoesntExistException::new);

        //THEN
        assertThat(eurder.getTotalPrice()).isEqualTo(124.84);
    }

    @Test
    void whenFindByValidId_ThenReturnCorrectEurderDtoWithAllFields() {
        //WHEN
        Eurder eurder = eurderRepository.findEurderById(1L).orElseThrow(EurderDoesntExistException::new);

        //THEN
        assertThat(eurder.getCustomer().getLastName()).isEqualTo("TestingLastName");
        assertThat(eurder.getCustomer().getEmail()).isEqualTo("TestingEmail@gmail.com");
        assertThat(eurder.getCustomer().getPhoneNumber()).isEqualTo("0444444444");

        assertThat(eurder.getCustomer().getAddress().getStreetName()).isEqualTo("TestingStreetName");
        assertThat(eurder.getCustomer().getAddress().getStreetNumber()).isEqualTo("TestingStreetNumber");
        assertThat(eurder.getCustomer().getAddress().getLocation()).isEqualTo("TestingLocation");

        assertThat(eurder.getItemGroups().get(0).getShippingDate()).isEqualTo(LocalDate.now());
        assertThat(eurder.getItemGroups().get(0).getAmount()).isEqualTo(2);
        assertThat(eurder.getItemGroups().get(0).getItem().getName()).isEqualTo("TestingName");
    }



//    @Test
//    void givenCreateEurderDto_whenSaveEurder_thenEurderIsSavedInDatabase() {
//
//    }



}
