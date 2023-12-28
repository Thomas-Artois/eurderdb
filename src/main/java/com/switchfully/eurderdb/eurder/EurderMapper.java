package com.switchfully.eurderdb.eurder;

import com.switchfully.eurderdb.eurder.domain.Eurder;
import com.switchfully.eurderdb.eurder.dto.CreateEurderDto;
import com.switchfully.eurderdb.eurder.dto.EurderDto;
import org.springframework.stereotype.Component;

@Component
public class EurderMapper {

    public Eurder mapCreateEurderDtoToEurder(CreateEurderDto createEurderDto) {
        return new Eurder(
                createEurderDto.getCustomer(),
                createEurderDto.getItemGroups(),
                createEurderDto.getTotalPrice()
        );
    }

    public EurderDto mapEurderToEurderDto(Eurder eurder) {
        return new EurderDto(
                eurder.getId(),
                eurder.getCustomer(),
                eurder.getItemGroups(),
                eurder.getTotalPrice()
        );
    }
}
