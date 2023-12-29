package com.switchfully.eurderdb.itemgroup;

import com.switchfully.eurderdb.itemgroup.domain.ItemGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemGroupRepository extends JpaRepository<ItemGroup, Long> {
}
