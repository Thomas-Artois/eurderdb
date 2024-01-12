package com.switchfully.eurderdb.item;

import com.switchfully.eurderdb.admin.AdminService;
import com.switchfully.eurderdb.item.dto.CreateItemDto;
import com.switchfully.eurderdb.item.dto.ItemDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {

    private final ItemService itemService;
    private final AdminService adminService;

    public ItemController(ItemService itemService, AdminService adminService) {
        this.itemService = itemService;
        this.adminService = adminService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto createItem(@RequestBody CreateItemDto createItemDto, @RequestHeader String username, @RequestHeader String password) {
        adminService.findAdminByUsername(username);
        adminService.checkIfAdminPasswordIsCorrect(username, password);

        return itemService.saveItem(createItemDto);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDto> getAllItems() {
        return itemService.findAllItems();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ItemDto getItemById(@PathVariable Long id) {
        return itemService.findItemById(id);
    }
}
