package com.switchfully.eurderdb.admin;

import com.switchfully.eurderdb.admin.domain.Admin;
import com.switchfully.eurderdb.admin.dto.AdminDto;
import com.switchfully.eurderdb.exceptions.AdminNotFoundException;
import com.switchfully.eurderdb.exceptions.AdminPasswordIncorrectException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    public AdminService(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    public AdminDto findAdminByUsername(String username) throws AdminNotFoundException{
        Optional<Admin> adminToCheck = adminRepository.findByUsername(username);

        if (!adminToCheck.isPresent()) {
            throw new AdminNotFoundException();
        }
        Admin admin = adminToCheck.get();

        return adminMapper.mapAdminToAdminDto(admin);
    }

    public void checkIfAdminPasswordIsCorrect(String username, String password) throws AdminPasswordIncorrectException{
        AdminDto adminDto = findAdminByUsername(username);

        String passwordToCheck = adminDto.getPassword();

        if (!passwordToCheck.equals(password)) {
            throw new AdminPasswordIncorrectException();
        }

    }
}
