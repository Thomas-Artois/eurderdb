package com.switchfully.eurderdb.admin;

import com.switchfully.eurderdb.admin.domain.Admin;
import com.switchfully.eurderdb.admin.dto.AdminDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByUsername(String username);
}
