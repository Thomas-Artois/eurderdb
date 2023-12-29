package com.switchfully.eurderdb.eurder;

import com.switchfully.eurderdb.eurder.dto.CreateEurderDto;
import com.switchfully.eurderdb.eurder.dto.EurderDto;
import com.switchfully.eurderdb.itemgroup.dto.CreateItemGroupDto;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import io.restassured.RestAssured;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureTestDatabase
public class EurderControllerIntegrationTests {

    @LocalServerPort
    private int port;

    @Test
    void givenCreateEurderDto_whenSaveEurder_thenReturnEurderDto() {
        CreateEurderDto createEurderDto = new CreateEurderDto(
                List.of(
                        new CreateItemGroupDto(2L, 1),
                        new CreateItemGroupDto(1L, 1)
                )
        );

        //WHEN
        EurderDto eurderDto =
                RestAssured
                        .given()
                        .body(createEurderDto)
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .header("email", "TestingEmail@gmail.com")
                        .header("password", "testingPassword")
                        .when()
                        .port(port)
                        .post("/order")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(EurderDto.class);

        assertThat(eurderDto.getCustomerId()).isEqualTo(1L);
        assertThat(eurderDto.getTotalPrice()).isEqualTo(62.42);
    }

    @Test
    void whenSaveEurder_thenEurderIsSavedInDatabase() {

    }



}
