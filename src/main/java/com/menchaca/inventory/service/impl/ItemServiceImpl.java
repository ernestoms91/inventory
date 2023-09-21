package com.menchaca.inventory.service.impl;

import com.menchaca.inventory.exception.ObjectNotFoundException;
import com.menchaca.inventory.exception.ObjectPropertyRepeatedException;
import com.menchaca.inventory.mapper.ItemMapper;
import com.menchaca.inventory.model.BusinessOffice;
import com.menchaca.inventory.model.Item;
import com.menchaca.inventory.model.Type;
import com.menchaca.inventory.model.dto.ItemDTO;
import com.menchaca.inventory.model.dto.UpdateItemDTO;
import com.menchaca.inventory.persistence.IBusinessOfficeDAO;
import com.menchaca.inventory.persistence.IItemDAO;
import com.menchaca.inventory.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    private IItemDAO itemDAO;
    @Autowired
    private IBusinessOfficeDAO businessOfficeDAO;
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public ItemDTO findById(Long id) throws ObjectNotFoundException {
        ItemDTO itemDTO = null;
        System.out.println(id);
        try {

            Optional<Item> itemOptional = itemDAO.findById(id);
            if (itemOptional.isEmpty()) {
                throw new ObjectNotFoundException("No existe un item con id: " + id);
            }

            Item item = itemOptional.get();
            itemDTO = itemMapper.ItemToItemDTO(item);

        } catch (Exception e) {
            throw e;
        }
        return itemDTO;
    }

    @Override
    public Page<Item> findAll(Pageable pageable) {
        Page<Item> itemPage = null;
        try {
            itemPage = itemDAO.findAll(pageable);
        } catch (Exception e) {
            throw e;
        }

        return itemPage;
    }

    @Override
    public Item save(ItemDTO itemDTO) {

        Item item = null;

        try {
            Optional<Item> itemOptional = itemDAO.findByStockNumber(Integer.parseInt(itemDTO.getStockNumber()));

            if (itemOptional.isPresent()) {
                throw new ObjectPropertyRepeatedException("Ya existe un objeto con este numero de inventario: " + itemDTO.getStockNumber());
            }

            Optional<BusinessOffice> businessOfficeOptional = businessOfficeDAO.findById((long) itemDTO.getId_department());

            if (businessOfficeOptional.isEmpty()) {
                throw new ObjectNotFoundException("No existe ningun departamento con el id: " + itemDTO.getId_department());
            }

            item = itemMapper.ItemDTOToItem(itemDTO);

            itemDAO.save(item);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    @Override
    public Item update(Long id, UpdateItemDTO updateItemDTO) {
        Item item = null;
        try {

            if (updateItemDTO.isEmpty()) {
                System.out.println("Esta vacia");
                throw new ObjectPropertyRepeatedException("Peticion vacía");
            }


            Optional<Item> itemOptional = itemDAO.findById(id);
            if (itemOptional.isEmpty()) {
                throw new ObjectNotFoundException("No existe un item con id: " + id);
            }

            item = itemOptional.get();


            if (updateItemDTO.getStockNumber() != null && !updateItemDTO.getStockNumber().isEmpty() && !updateItemDTO.getStockNumber().isBlank()) {
                Optional<Item> itemOptional1 = itemDAO.findByStockNumber(Integer.parseInt(updateItemDTO.getStockNumber()));

                if (itemOptional1.isPresent() && itemOptional1.get().getId() != id) {
                    throw new ObjectPropertyRepeatedException("Ya existe un objeto con este numero de inventario: " + updateItemDTO.getStockNumber());
                }

                item.setStockNumber(Integer.parseInt(updateItemDTO.getStockNumber()));
            }


            if (updateItemDTO.getElectric() != null && !updateItemDTO.getElectric().isEmpty() && !updateItemDTO.getElectric().isBlank()) {
                item.setElectric(Boolean.parseBoolean(updateItemDTO.getElectric()));
            }
            if (updateItemDTO.getType() != null && !updateItemDTO.getType().isEmpty() && !updateItemDTO.getType().isBlank()) {
                item.setType(Type.valueOf(updateItemDTO.getType()));
            }

            if (updateItemDTO.getDescription() != null && !updateItemDTO.getDescription().isEmpty() && !updateItemDTO.getDescription().isBlank()) {
                item.setDescription(updateItemDTO.getDescription());
            }

            if (updateItemDTO.getObservation() != null && !updateItemDTO.getObservation().isEmpty() && !updateItemDTO.getObservation().isBlank()) {
                item.setObservation(updateItemDTO.getObservation());
            }
            if (updateItemDTO.getPrice() != null) {
                item.setPrice(BigDecimal.valueOf(Long.parseLong(updateItemDTO.getPrice())));
            }
            if (updateItemDTO.getBroken() != null && !updateItemDTO.getBroken().isEmpty() && !updateItemDTO.getBroken().isBlank()) {
                item.setBroken(Boolean.parseBoolean(updateItemDTO.getBroken()));
            }
            if (updateItemDTO.getWithdrawn() != null && !updateItemDTO.getWithdrawn().isEmpty() && !updateItemDTO.getWithdrawn().isBlank()) {
                item.setWithdrawn(Boolean.parseBoolean(updateItemDTO.getWithdrawn()));
            }

            Optional<BusinessOffice> businessOfficeOptional = businessOfficeDAO.findById(Long.valueOf(updateItemDTO.getId_department()));

            if (businessOfficeOptional.isEmpty()) {
                throw new ObjectNotFoundException("No existe ningun departamento con el id: " + updateItemDTO.getId_department());
            }

            if (updateItemDTO.getId_department() != null && !updateItemDTO.getId_department().isEmpty() && !updateItemDTO.getId_department().isBlank()) {
                item.setBusinessOffice(BusinessOffice.builder()
                        .id(Long.valueOf(updateItemDTO.getId_department())).build());
            }

            itemDAO.save(item);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Item> itemOptional;
        Item item = null;
        try {
            itemOptional = itemDAO.findById(id);

            if (itemOptional.isEmpty()) {
                throw new ObjectNotFoundException("No existe un objeto con id: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        itemDAO.deleteById(id);
    }
}
