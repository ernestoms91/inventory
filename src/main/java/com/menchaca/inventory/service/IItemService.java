package com.menchaca.inventory.service;

import com.menchaca.inventory.exception.InvalidFileTypeException;
import com.menchaca.inventory.exception.ObjectNotFoundException;
import com.menchaca.inventory.model.Item;
import com.menchaca.inventory.model.dto.ItemDTO;
import com.menchaca.inventory.model.dto.UpdateItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IItemService {

    ItemDTO findById(Long id) throws ObjectNotFoundException;
    Page<Item> findAll(Pageable pageable);
    Item save (ItemDTO itemDTO);

    Item update (Long id, UpdateItemDTO updateItemDTO);
    void deleteById(Long id);

    String uploadImage( Long id, MultipartFile file) throws ObjectNotFoundException, IOException, InvalidFileTypeException;

    byte[] downloadImageFromFileSystem(String fileName) throws ObjectNotFoundException, IOException;
}
