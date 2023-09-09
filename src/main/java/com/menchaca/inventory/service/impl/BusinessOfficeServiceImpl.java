package com.menchaca.inventory.service.impl;

import com.menchaca.inventory.exception.ObjectPropertyRepeatedException;
import com.menchaca.inventory.exception.ObjectNotFoundException;
import com.menchaca.inventory.mapper.BusinessOfficeMapper;
import com.menchaca.inventory.model.BusinessOffice;
import com.menchaca.inventory.model.DepartmentName;
import com.menchaca.inventory.model.Item;
import com.menchaca.inventory.model.dto.BusinessOfficeDTO;
import com.menchaca.inventory.model.dto.UpdateBusinessOfficeDTO;
import com.menchaca.inventory.persistence.IBusinessOfficeDAO;
import com.menchaca.inventory.service.IBusinessOfficeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class BusinessOfficeServiceImpl implements IBusinessOfficeService {

    @Autowired
    private IBusinessOfficeDAO businessOfficeDAO;

    @Autowired
    private BusinessOfficeMapper businessOfficeMapper;

    @Override
    public BusinessOfficeDTO findById(Long id) throws ObjectNotFoundException {

        Optional<BusinessOffice> optionalBusinessOffice;
        BusinessOffice businessOffice = null;
        try {
            optionalBusinessOffice = businessOfficeDAO.findById(id);

            if (optionalBusinessOffice.isEmpty()) {
                throw new ObjectNotFoundException("No existe un departmento con id: " + id);
            }

            // If department is present  and  has more than one item , order items DESC by inventory number
            businessOffice = optionalBusinessOffice.get();
            if (businessOffice.getItemsList().size() > 1) {
                List<Item> itemsList = businessOffice.getItemsList();
                itemsList.sort(Comparator.comparingInt(Item::getStockNumber));

            }

        } catch (Exception e) {
            throw e;
//            log.error(e.getMessage());
        }
        return businessOfficeMapper.BusinessOfficeToBusinessOfficeDTO(businessOffice);
    }

    @Override
    public List<BusinessOfficeDTO> findAll() {
        List<BusinessOfficeDTO> businessOfficeDTOList = null;
        try {
            businessOfficeDTOList = businessOfficeDAO.findAll().stream().map(businessOffice -> {
                return businessOfficeMapper.BusinessOfficeToBusinessOfficeDTO(businessOffice);
            }).toList();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return businessOfficeDTOList;
    }

    @Override
    public BusinessOffice save(BusinessOfficeDTO businessOfficeDTO) {
        BusinessOffice businessOffice1 = null;

        try {
            Optional<BusinessOffice> businessOffice = businessOfficeDAO.findByDepartmentName(DepartmentName.valueOf(businessOfficeDTO.getDepartmentName()));

            if (businessOffice.isPresent()) {
                throw new ObjectPropertyRepeatedException("Ya existe un departamento con este nombre: " + businessOfficeDTO.getDepartmentName());
            }

            businessOffice1 = businessOfficeMapper.BusinessOfficeDTOToBusinessOffice(businessOfficeDTO);
            businessOfficeDAO.save(businessOffice1);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return businessOffice1;
    }

    @Override
    public BusinessOffice update(Long id, UpdateBusinessOfficeDTO updateBusinessOfficeDTO) {
        BusinessOffice businessOffice = null;


        try {
            if (updateBusinessOfficeDTO.isEmpty()) {
                throw new ObjectPropertyRepeatedException("Peticion vacía");
            }

            Optional<BusinessOffice> businessOfficeOptional = businessOfficeDAO.findById(id);

            if (businessOfficeOptional.isEmpty()) {
                throw new ObjectNotFoundException("No existe un departmento con id: " + id);
            }

            businessOffice = businessOfficeOptional.get();

            if (updateBusinessOfficeDTO.getDepartmentName() != null && !updateBusinessOfficeDTO.getDepartmentName().isEmpty() && !updateBusinessOfficeDTO.getDepartmentName().isBlank()) {
                Optional<BusinessOffice> businessOffice2 = businessOfficeDAO.findByDepartmentName(DepartmentName.valueOf(updateBusinessOfficeDTO.getDepartmentName()));

                if (businessOffice2.isPresent() && businessOffice2.get().getId() != id) {
                    throw new ObjectPropertyRepeatedException("Ya existe un departamento con este nombre: " + updateBusinessOfficeDTO.getDepartmentName());
                }

                businessOffice.setDepartmentName(DepartmentName.valueOf(updateBusinessOfficeDTO.getDepartmentName()));

            }

            if (updateBusinessOfficeDTO.getManager() != null && !updateBusinessOfficeDTO.getManager().isBlank() && !updateBusinessOfficeDTO.getManager().isEmpty()) {
                businessOffice.setManager(updateBusinessOfficeDTO.getManager().toUpperCase());
            }
            businessOfficeDAO.save(businessOffice);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return businessOffice;
    }

    @Override
    public void deleteById(Long id) {
        Optional<BusinessOffice> optionalBusinessOffice;
        BusinessOffice businessOffice = null;
        try {
            optionalBusinessOffice = businessOfficeDAO.findById(id);

            if (optionalBusinessOffice.isEmpty()) {
                throw new ObjectNotFoundException("No existe un departmento con id: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        businessOfficeDAO.deleteById(id);
    }
}
