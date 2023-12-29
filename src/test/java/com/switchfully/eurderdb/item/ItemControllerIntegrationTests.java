package com.switchfully.eurderdb.item;

import com.switchfully.eurderdb.item.dto.CreateItemDto;
import com.switchfully.eurderdb.item.dto.ItemDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureTestDatabase
public class ItemControllerIntegrationTests {

    @LocalServerPort
    private int port;
    @Autowired
    private ItemService itemService;

    @Test
    void whenCreateItem_thenReturnItemDto() {
        CreateItemDto createItemDto = new CreateItemDto(
                "bowl",
                "stores food",
                12.00,
                6
        );

        //WHEN
        ItemDto itemDto =
                RestAssured
                        .given()
                        .body(createItemDto)
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .header("username", "adminTest")
                        .header("password", "adminPasswordTest")
                        .when()
                        .port(port)
                        .post("/item")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(ItemDto.class);

        assertThat(itemDto.getDescription()).isEqualTo("stores food");
        assertThat(itemDto.getName()).isEqualTo("bowl");
        assertThat(itemDto.getPrice()).isEqualTo(12.00);
        assertThat(itemDto.getStockAmount()).isEqualTo(6);
    }

    @Test
    void whenSaveItem_thenItemIsSavedInDatabase() {
        CreateItemDto createItemDto = new CreateItemDto(
                "bowl",
                "stores food",
                12.00,
                6
        );

        //WHEN
        ItemDto itemDto =
                RestAssured
                        .given()
                        .body(createItemDto)
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .header("username", "adminTest")
                        .header("password", "adminPasswordTest")
                        .when()
                        .port(port)
                        .post("/item")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(ItemDto.class);

        assertThat(itemService.findItemById(itemDto.getId()).getName()).isEqualTo(createItemDto.getName());
    }
}
