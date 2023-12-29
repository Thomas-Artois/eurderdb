package com.switchfully.eurderdb.admin;

import com.switchfully.eurderdb.admin.domain.Admin;
import com.switchfully.eurderdb.admin.dto.AdminDto;
import com.switchfully.eurderdb.admin.dto.CreateAdminDto;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public AdminDto mapAdminToAdminDto(Admin admin) {
        return new AdminDto(
                admin.getUsername(),
                admin.getPassword()
        );
    }

    public Admin mapCreateAdminDtoToAdmin(CreateAdminDto createAdminDto) {
        return new Admin(
                createAdminDto.getUsername(),
                createAdminDto.getPassword()
        );
    }

}
