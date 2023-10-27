package com.menchaca.inventory.controller;

import com.menchaca.inventory.model.Role;
import com.menchaca.inventory.model.dto.HttpCreatedDTO;
import com.menchaca.inventory.model.dto.LoginDTO;
import com.menchaca.inventory.model.dto.UserDTO;
import com.menchaca.inventory.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDTO userDTO) throws URISyntaxException {
        userDTO.setRole(Role.valueOf("USER"));
        Map<String, String> user = userService.register(userDTO);

        return ResponseEntity.created(new URI("api/v1/department/new")).body(HttpCreatedDTO.builder()
                .status(HttpStatus.CREATED.value())
                .msg("Usuario regustrado exitosamente")
                .content(user).build());
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginDTO loginDTO) throws URISyntaxException {
        Map<String, Object> user = userService.login(loginDTO);

        return ResponseEntity.ok(HttpCreatedDTO.builder()
                .status(HttpStatus.OK.value())
                .msg("Usuario autenticado exitosamente")
                .content(user).build());
    }

}
