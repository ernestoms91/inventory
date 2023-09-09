package com.menchaca.inventory.repository;

import com.menchaca.inventory.model.BusinessOffice;
import com.menchaca.inventory.model.DepartmentName;
import com.menchaca.inventory.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Long> {

    Optional<Item> findByStockNumber(int stockNumber);

}
