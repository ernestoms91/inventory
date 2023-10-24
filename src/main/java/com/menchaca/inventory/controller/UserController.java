package com.menchaca.inventory.controller;

import com.menchaca.inventory.exception.ObjectNotFoundException;
import com.menchaca.inventory.model.User;
import com.menchaca.inventory.model.dto.*;
import com.menchaca.inventory.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
@RestController
@RequestMapping("api/v1/user")
public class UserController {
        @Autowired
        private IUserService userService;

        @GetMapping("/find/{id}")
        @PreAuthorize("hasAuthority('ADMIN')")
        public ResponseEntity<?> findById(@PathVariable Long id) throws ObjectNotFoundException {
            UserDetailDTO userDetailDTO = userService.findById(id);
            return ResponseEntity.ok(userDetailDTO);
        }

        @GetMapping("/all")
        @PreAuthorize("hasAuthority('ADMIN')")
        public ResponseEntity<?> findAll(Pageable pageable) {

            Page<User> userPage = userService.findAll(pageable);
            return ResponseEntity.ok(userPage);
        }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDTO userDTO) throws URISyntaxException {

        Map<String, String> user = userService.register(userDTO);

        return ResponseEntity.created(new URI("api/v1/department/new")).body(HttpCreatedDTO.builder()
                .status(HttpStatus.CREATED.value())
                .msg("Usuario regustrado existosamente")
                .content(user).build());
    }


        @PutMapping("/update/{id}")
        @PreAuthorize("hasAuthority('ADMIN')")
        public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserDTO updateUserDTO) throws URISyntaxException {
            User user = userService.update(id, updateUserDTO);
            return  ResponseEntity.ok(HttpCreatedDTO.builder()
                    .status(HttpStatus.OK.value())
                    .msg("Item actualizado existosamente")
                    .content(user).build());
        }


        @DeleteMapping("/delete/{id}")
        @PreAuthorize("hasAuthority('ADMIN')")
        public ResponseEntity<?> deleteById(@PathVariable Long id) {
            userService.deleteById(id);
            return ResponseEntity.ok(HttpCreatedDTO.builder()
                    .status(HttpStatus.OK.value())
                    .msg("Usuario deshabilitado correctamente")
                    .build());
        }

}
