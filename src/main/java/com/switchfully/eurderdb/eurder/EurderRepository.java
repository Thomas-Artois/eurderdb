package com.switchfully.eurderdb.eurder;

import com.switchfully.eurderdb.eurder.domain.Eurder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface EurderRepository extends JpaRepository<Eurder, Long> {
}
