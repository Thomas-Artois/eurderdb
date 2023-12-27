package com.switchfully.eurderdb.eurder;

import com.switchfully.eurderdb.customer.CustomerService;
import com.switchfully.eurderdb.customer.dto.CustomerDto;
import com.switchfully.eurderdb.eurder.dto.CreateEurderDto;
import com.switchfully.eurderdb.eurder.dto.EurderDto;
import com.switchfully.eurderdb.item.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class EurderController {
    private final EurderService eurderService;
    private final ItemService itemService;
    private final CustomerService customerService;

    public EurderController(EurderService eurderService, ItemService itemService, CustomerService customerService) {
        this.eurderService = eurderService;
        this.itemService = itemService;
        this.customerService = customerService;
    }

//    @PostMapping(consumes = "application/json", produces = "application/json")
//    @ResponseStatus(HttpStatus.CREATED)
//    public EurderDto createEurder(@RequestBody CreateEurderDto createEurderDto, @RequestHeader String email, @RequestHeader String password) {
//        CustomerDto customerDto = customerService.findCustomerByEmail(email);
//        customerService.checkIfPasswordIsCorrect(customerDto, password);
//
//        return eurderService.saveEurder(createEurderDto);
//    }


}
