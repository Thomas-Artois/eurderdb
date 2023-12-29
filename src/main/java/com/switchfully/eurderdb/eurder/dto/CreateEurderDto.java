package com.switchfully.eurderdb.eurder.dto;

import com.switchfully.eurderdb.customer.domain.Customer;
import com.switchfully.eurderdb.itemgroup.domain.ItemGroup;
import com.switchfully.eurderdb.itemgroup.dto.CreateItemGroupDto;
import com.switchfully.eurderdb.itemgroup.dto.ItemGroupDto;

import java.util.List;

public class CreateEurderDto {
    private List<CreateItemGroupDto> createItemGroupDtoList;

    public CreateEurderDto() {
    }

    public CreateEurderDto(List<CreateItemGroupDto> createItemGroupDtoList) {
        this.createItemGroupDtoList = createItemGroupDtoList;
    }

    public List<CreateItemGroupDto> getCreateItemGroupDtoList() {
        return createItemGroupDtoList;
    }
}
