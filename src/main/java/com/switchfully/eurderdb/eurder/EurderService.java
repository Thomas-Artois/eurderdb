package com.switchfully.eurderdb.eurder;

import com.switchfully.eurderdb.customer.CustomerMapper;
import com.switchfully.eurderdb.customer.domain.Customer;
import com.switchfully.eurderdb.customer.dto.CustomerDto;
import com.switchfully.eurderdb.eurder.dto.CreateEurderDto;
import com.switchfully.eurderdb.eurder.dto.EurderDto;
import com.switchfully.eurderdb.exceptions.ItemDoesntExistException;
import com.switchfully.eurderdb.item.ItemRepository;
import com.switchfully.eurderdb.item.ItemService;
import com.switchfully.eurderdb.item.dto.ItemDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EurderService {

    private final EurderRepository eurderRepository;
    private final EurderMapper eurderMapper;
    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final CustomerMapper customerMapper;

    public EurderService(EurderRepository eurderRepository, EurderMapper eurderMapper, ItemRepository itemRepository, ItemService itemService, CustomerMapper customerMapper) {
        this.eurderRepository = eurderRepository;
        this.eurderMapper = eurderMapper;
        this.itemRepository = itemRepository;
        this.itemService = itemService;
        this.customerMapper = customerMapper;
    }

    public void validateEurderForValidItem(CreateEurderDto createEurderDto, int index) throws ItemDoesntExistException {
        Long itemId = createEurderDto.getItemGroups().get(index).getItem().getId();
        ItemDto itemDto = itemService.findItemById(itemId);

        if (itemDto == null) {
            throw new ItemDoesntExistException();
        }
    }


    public EurderDto saveEurder(CreateEurderDto createEurderDto, CustomerDto customerDto) {
        Customer customer = customerMapper.mapCustomerDtoToCustomer(customerDto);
        Long customerId = customer.getId();

        return eurderMapper.mapEurderToEurderDto(eurderRepository.save(eurderMapper.mapCreateEurderDtoToEurder(createEurderDto)));


    }
}
