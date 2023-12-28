package com.switchfully.eurderdb.eurder;

import com.switchfully.eurderdb.customer.CustomerMapper;
import com.switchfully.eurderdb.customer.CustomerRepository;
import com.switchfully.eurderdb.customer.domain.Customer;
import com.switchfully.eurderdb.eurder.domain.Eurder;
import com.switchfully.eurderdb.eurder.dto.CreateEurderDto;
import com.switchfully.eurderdb.eurder.dto.EurderDto;
import com.switchfully.eurderdb.exceptions.CustomerDoesntExistException;
import com.switchfully.eurderdb.exceptions.ItemDoesntExistException;
import com.switchfully.eurderdb.item.ItemRepository;
import com.switchfully.eurderdb.item.ItemService;
import com.switchfully.eurderdb.item.domain.Item;
import com.switchfully.eurderdb.itemgroup.ItemGroupRepository;
import com.switchfully.eurderdb.itemgroup.domain.ItemGroup;
import com.switchfully.eurderdb.itemgroup.dto.CreateItemGroupDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EurderService {

    private final EurderRepository eurderRepository;
    private final ItemRepository itemRepository;
private final ItemGroupRepository itemGroupRepository;
    private final CustomerRepository customerRepository;

    public EurderService(EurderRepository eurderRepository, ItemRepository itemRepository, ItemGroupRepository itemGroupRepository, CustomerRepository customerRepository) {
        this.eurderRepository = eurderRepository;
        this.itemRepository = itemRepository;
        this.itemGroupRepository = itemGroupRepository;
        this.customerRepository = customerRepository;
    }

//    public void validateEurderForValidItem(CreateEurderDto createEurderDto, int index) throws ItemDoesntExistException {
//        Long itemId = createEurderDto.getItemGroupDtoList().get(index).getItem().getId();
//        ItemDto itemDto = itemService.findItemById(itemId);
//
//        if (itemDto == null) {
//            throw new ItemDoesntExistException();
//        }
//    }


    public EurderDto saveEurder(CreateEurderDto createEurderDto, String email) {
        Customer customer = customerRepository.findCustomerByEmail(email).stream().findFirst().orElseThrow(CustomerDoesntExistException::new).get(0);

        Eurder eurder = new Eurder(customer);
        eurderRepository.save(eurder);

        List<ItemGroup> itemGroupList= new ArrayList<>();
        double totalPrice = 0;

        for (CreateItemGroupDto itemGroupDto : createEurderDto.getCreateItemGroupDtoList()) {
            Item item = itemRepository.findById(itemGroupDto.getItemId())
                    .orElseThrow(ItemDoesntExistException::new);

            int amount = itemGroupDto.getAmount();
            double tempPrice = item.getPrice();
            tempPrice *= amount;
            LocalDate shippingDate = calculateShippingDate(item, amount);

            ItemGroup itemGroup = new ItemGroup(eurder, item, amount, tempPrice, shippingDate);

            itemGroupList.add(itemGroup);

            itemGroupRepository.save(itemGroup);
            totalPrice += itemGroup.getTotalPrice();
        }

        eurder.setTotalPrice(totalPrice);
        eurderRepository.save(eurder);

        return new EurderDto(
                eurder.getId(),
                eurder.getCustomer().getId(),
                eurder.getTotalPrice()
        );


    }

    private LocalDate calculateShippingDate(Item item, int amount) {
        if (item.getStockAmount() > 0 && item.getStockAmount() - amount >= 0) {
            return LocalDate.now().plusDays(1);
        } else {
            return LocalDate.now().plusDays(7);
        }

    }
}
