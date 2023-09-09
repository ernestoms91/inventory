package com.menchaca.inventory.service;

import com.menchaca.inventory.exception.ObjectNotFoundException;
import com.menchaca.inventory.model.Item;
import com.menchaca.inventory.model.dto.ItemDTO;
import com.menchaca.inventory.model.dto.UpdateItemDTO;

import java.util.List;
import java.util.Optional;

public interface IItemService {

    ItemDTO findById(Long id) throws ObjectNotFoundException;
    List<ItemDTO> findAll();
    Item save (ItemDTO itemDTO);

    Item update (Long id, UpdateItemDTO updateItemDTO);
    void deleteById(Long id);
}
