package com.menchaca.inventory.service;

import com.menchaca.inventory.exception.ObjectNotFoundException;
import com.menchaca.inventory.model.Item;
import com.menchaca.inventory.model.User;
import com.menchaca.inventory.model.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IUserService {
    UserDetailDTO findById(Long id) throws ObjectNotFoundException;
    Page<User> findAll(Pageable pageable);
    User save (UserDTO userDTO);

    User update (Long id, UpdateUserDTO updateUserDTO);
    void deleteById(Long id);

    Map<String, String> register(UserDTO userDTO);
    Map<String, Object> login(LoginDTO loginDTO);
}
