package com.switchfully.eurderdb.eurder;

import com.switchfully.eurderdb.eurder.domain.Eurder;
import com.switchfully.eurderdb.eurder.dto.EurderDto;
import org.springframework.stereotype.Component;

@Component
public class EurderMapper {

    public EurderDto mapEurderToEurderDto(Eurder eurder) {
        return new EurderDto(
                eurder.getId(),
                eurder.getCustomer(),
                eurder.getItemGroups(),
                eurder.getTotalPrice()
        );

    }
}
