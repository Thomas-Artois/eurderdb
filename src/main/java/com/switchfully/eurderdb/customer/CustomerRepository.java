package com.switchfully.eurderdb.customer;

import com.switchfully.eurderdb.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

    Optional<Customer> findById(Long id);

    Optional<Customer> findCustomerByEmail(String email);
}
