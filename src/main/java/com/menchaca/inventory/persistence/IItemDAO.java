package com.menchaca.inventory.persistence;

import com.menchaca.inventory.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IItemDAO {

    Optional<Item> findById(Long id);
    Page<Item> findAll(Pageable pageable);
    Optional<Item> findByStockNumber(int stockNumber);
    void save (Item item);
    void deleteById(Long id);

}
