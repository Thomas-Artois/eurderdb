package com.switchfully.eurderdb.eurder;

import com.switchfully.eurderdb.customer.domain.Address;
import com.switchfully.eurderdb.customer.domain.Customer;
import com.switchfully.eurderdb.eurder.domain.Eurder;
import com.switchfully.eurderdb.eurder.dto.CreateEurderDto;
import com.switchfully.eurderdb.eurder.dto.EurderDto;
import com.switchfully.eurderdb.exceptions.EurderDoesntExistException;
import com.switchfully.eurderdb.exceptions.ItemDoesntExistException;
import com.switchfully.eurderdb.itemgroup.ItemGroupRepository;
import com.switchfully.eurderdb.itemgroup.domain.ItemGroup;
import com.switchfully.eurderdb.itemgroup.dto.CreateItemGroupDto;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;
import static org.assertj.core.api.InstanceOfAssertFactories.LOCAL_DATE_TIME;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class EurderServiceTests {

    @Autowired
    private EurderService eurderService;
    @Autowired
    private EurderRepository eurderRepository;
    @Autowired
    private ItemGroupRepository itemGroupRepository;
    @Autowired
    private EurderMapper eurderMapper;


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



    @Test
    void givenCreateEurderDto_whenSaveEurder_thenEurderIsSavedInDatabase() {
        //GIVEN
        CreateEurderDto createEurderDto = new CreateEurderDto(
                List.of(
                        new CreateItemGroupDto(1L, 2),
                        new CreateItemGroupDto(2L, 1)
                )
        );

        //WHEN
        EurderDto eurderDto = eurderService.saveEurder(createEurderDto, "TestingEmail@gmail.com");

        //THEN
        assertThat(eurderRepository.findEurderById(eurderDto.getId())).isPresent();
        assertThat(eurderRepository.findEurderById(eurderDto.getId()).orElseThrow(EurderDoesntExistException::new))
                .isInstanceOf(Eurder.class);
    }

    @Test
    void givenCreateEurderDto_whenSaveEurder_thenEurderIsSavedInDatabaseWithCorrectFields() {
        //GIVEN
        CreateEurderDto createEurderDto = new CreateEurderDto(
                List.of(
                        new CreateItemGroupDto(1L, 1),
                        new CreateItemGroupDto(2L, 1)
                )
        );

        //WHEN
        EurderDto eurderDto = eurderService.saveEurder(createEurderDto, "TestingEmail@gmail.com");

        //THEN
        assertThat(eurderRepository.findEurderById(eurderDto.getId()).get().getTotalPrice()).isEqualTo(62.42);
        assertThat(eurderRepository.findEurderById(eurderDto.getId()).get().getCustomer().getFirstName()).isEqualTo("TestingFirstName");
    }

    @Test
    void whenFindEurderByInvalidId_thenThrowEurderDoesntExistException() {
        //WHEN & THEN
        assertThrows(EurderDoesntExistException.class, () -> eurderService.findEurderById(2L));
    }

    @Test
    void whenCreateEurderWithInvalidItemId_thenThrowException() {
        //GIVEN
        CreateEurderDto createEurderDto = new CreateEurderDto(
                List.of(
                        new CreateItemGroupDto(400000L, 1),
                        new CreateItemGroupDto(2L, 1)
                )
        );

        //WHEN & THEN
        assertThrows(ItemDoesntExistException.class, () -> eurderService.saveEurder(createEurderDto, "TestingEmail@gmail.com"));
    }

    @Test
    void whenMappingEurderToEurderDto_thenEurderDtoHasAllTheSameFields() {
        Eurder eurder = new Eurder(
                new Customer(
                        "voornaam",
                        "achternaam",
                        "voornaamachternaam@gmail.com",
                        "0454667788",
                        new Address(
                                "straatnaam",
                                "nummer",
                                "locatie"
                        ),
                        "wachtwoord"
                )
        );

        //WHEN
        EurderDto eurderDto = eurderMapper.mapEurderToEurderDto(eurder);

        assertThat(eurderDto.getTotalPrice()).isEqualTo(eurder.getTotalPrice());
    }



}
