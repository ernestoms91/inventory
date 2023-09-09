package com.menchaca.inventory.controller;

import com.menchaca.inventory.advice.ApiExceptionHandler;
import com.menchaca.inventory.exception.ObjectNotFoundException;
import com.menchaca.inventory.mapper.BusinessOfficeMapper;
import com.menchaca.inventory.model.dto.BusinessOfficeDTO;
import com.menchaca.inventory.model.BusinessOffice;
import com.menchaca.inventory.model.dto.HttpCreatedDTO;
import com.menchaca.inventory.model.dto.UpdateBusinessOfficeDTO;
import com.menchaca.inventory.service.IBusinessOfficeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("api/v1/department")
public class BusinessOfficeController {
    @Autowired
    private IBusinessOfficeService businessOfficeService;
    @Autowired
    private BusinessOfficeMapper businessOfficeMapper;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws ObjectNotFoundException, ApiExceptionHandler {
        BusinessOfficeDTO businessOfficeDTO = businessOfficeService.findById(id);
        return ResponseEntity.ok(businessOfficeDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        List<BusinessOfficeDTO> businessOfficeDTOList = businessOfficeService.findAll();
        return ResponseEntity.ok(businessOfficeDTOList);
    }

    @PostMapping("/new")
    public ResponseEntity<?> saveBusinessOfficeDTOList(@RequestBody @Valid BusinessOfficeDTO businessOfficeDTO) throws URISyntaxException {
        BusinessOffice businessOffice = businessOfficeService.save(businessOfficeDTO);
        return ResponseEntity.created(new URI("api/v1/department/new")).body(HttpCreatedDTO.builder()
                .status(HttpStatus.CREATED.value()).msg("Departamento creado existosamente").content(businessOffice).build());
    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<?> updateBusinessOfficeDTOList(@PathVariable Long id, @RequestBody @Valid UpdateBusinessOfficeDTO updateBusinessOfficeDTO) throws URISyntaxException {
        BusinessOffice businessOffice = businessOfficeService.update(id, updateBusinessOfficeDTO);
        return ResponseEntity.ok(HttpCreatedDTO.builder()
                .status(HttpStatus.OK.value()).msg("Departamento actualizado existosamente").content(businessOffice).build());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        businessOfficeService.deleteById(id);
        return ResponseEntity.ok(HttpCreatedDTO.builder()
                .status(HttpStatus.OK.value())
                .msg("Departamento eliminado correctamente")
                .content(null)
                .build());
    }
}

