package com.switchfully.eurderdb.customer;

import com.switchfully.eurderdb.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
}
