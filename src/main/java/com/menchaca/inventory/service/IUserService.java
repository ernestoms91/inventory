package com.menchaca.inventory.service;

import com.menchaca.inventory.exception.ObjectNotFoundException;
import com.menchaca.inventory.model.Item;
import com.menchaca.inventory.model.User;
import com.menchaca.inventory.model.dto.ItemDTO;
import com.menchaca.inventory.model.dto.LoginDTO;
import com.menchaca.inventory.model.dto.UpdateItemDTO;
import com.menchaca.inventory.model.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IUserService {
    UserDTO findById(Long id) throws ObjectNotFoundException;
    Page<User> findAll(Pageable pageable);
    User save (UserDTO userDTO);

    User update (Long id, UpdateItemDTO updateItemDTO);
    void deleteById(Long id);

    Map<String, String> register(UserDTO userDTO);
    Map<String, String> login(LoginDTO loginDTO);
}
