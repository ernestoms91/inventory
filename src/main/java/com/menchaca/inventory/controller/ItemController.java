package com.menchaca.inventory.controller;

import com.menchaca.inventory.exception.InvalidFileTypeException;
import com.menchaca.inventory.exception.ObjectNotFoundException;
import com.menchaca.inventory.model.BusinessOffice;
import com.menchaca.inventory.model.Item;
import com.menchaca.inventory.model.dto.BusinessOfficeDTO;
import com.menchaca.inventory.model.dto.HttpCreatedDTO;
import com.menchaca.inventory.model.dto.ItemDTO;
import com.menchaca.inventory.model.dto.UpdateItemDTO;
import com.menchaca.inventory.service.IBusinessOfficeService;
import com.menchaca.inventory.service.IItemService;
import com.menchaca.inventory.util.ResponseMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/item")
public class ItemController {
    @Autowired
    private IItemService itemService;

    @GetMapping("/find/{id}")
    //@PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> findById(@PathVariable Long id) throws ObjectNotFoundException {
        ItemDTO itemDTO = itemService.findById(id);
        return ResponseEntity.ok(itemDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(Pageable pageable) {

        Page<Item>  itemPage = itemService.findAll(pageable);
        return ResponseEntity.ok(itemPage);
    }

    @PostMapping("/new")
    public ResponseEntity<?> saveItem(@RequestBody @Valid ItemDTO itemDTO) throws URISyntaxException {
        Item item = itemService.save(itemDTO);

        return ResponseEntity.created(new URI("api/v1/department/new")).body(HttpCreatedDTO.builder()
                .status(HttpStatus.CREATED.value())
                .msg("Item creado exitosamente")
                .content(item).build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id, @RequestBody @Valid UpdateItemDTO itemDTO) throws URISyntaxException {
       Item item = itemService.update(id, itemDTO);
        return  ResponseEntity.ok(HttpCreatedDTO.builder()
                .status(HttpStatus.OK.value())
                .msg("Item actualizado exitosamente")
                .content(item).build());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        itemService.deleteById(id);
        return ResponseEntity.ok(HttpCreatedDTO.builder()
                .status(HttpStatus.OK.value())
                .msg("Objeto eliminado correctamente")
                .content(null)
                .build());
    }

    @PostMapping("/image/upload/{id}")
    public ResponseEntity<?> uploadImage(@PathVariable Long id, @RequestParam("image") MultipartFile file) throws  ObjectNotFoundException, IOException, InvalidFileTypeException {
        String msg = itemService.uploadImage(id, file);
        return ResponseEntity.ok(HttpCreatedDTO.builder()
                .status(HttpStatus.OK.value())
                .msg(msg)
                .content(null)
                .build());
    }
    @GetMapping("/image/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) throws  ObjectNotFoundException, IOException {
        byte[] imageData=itemService.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png@CrossOrigin"))
                .body(imageData);
    }


}
