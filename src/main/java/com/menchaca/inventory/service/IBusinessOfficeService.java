package com.menchaca.inventory.service;

import com.menchaca.inventory.advice.ApiExceptionHandler;
import com.menchaca.inventory.exception.ObjectNotFoundException;
import com.menchaca.inventory.model.BusinessOffice;
import com.menchaca.inventory.model.dto.BusinessOfficeDTO;
import com.menchaca.inventory.model.dto.UpdateBusinessOfficeDTO;

import java.util.List;

public interface IBusinessOfficeService {
    BusinessOfficeDTO findById(Long id) throws ObjectNotFoundException, ApiExceptionHandler;
    List<BusinessOfficeDTO> findAll();
    BusinessOffice save (BusinessOfficeDTO businessOfficeDTO);
    BusinessOffice update ( Long id, UpdateBusinessOfficeDTO updateBusinessOfficeDTO);
    void deleteById(Long id);
}
