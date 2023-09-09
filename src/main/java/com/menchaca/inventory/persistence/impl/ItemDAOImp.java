package com.menchaca.inventory.persistence.impl;

import com.menchaca.inventory.model.Item;
import com.menchaca.inventory.persistence.IItemDAO;
import com.menchaca.inventory.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ItemDAOImp implements IItemDAO {
    @Autowired
    private ItemRepository itemRepository;
    @Override
    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public List<Item> findAll() {
        return (List<Item>) itemRepository.findAll();
    }

    @Override
    public Optional<Item> findByStockNumber(int stockNumber) {
        return itemRepository.findByStockNumber(stockNumber);
    }

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }
}
