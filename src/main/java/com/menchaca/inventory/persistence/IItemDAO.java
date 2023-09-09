package com.menchaca.inventory.persistence;

import com.menchaca.inventory.model.Item;

import java.util.List;
import java.util.Optional;

public interface IItemDAO {

    Optional<Item> findById(Long id);
    List<Item> findAll();
    Optional<Item> findByStockNumber(int stockNumber);
    void save (Item item);
    void deleteById(Long id);

}
