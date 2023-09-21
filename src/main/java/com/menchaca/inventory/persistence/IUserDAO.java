package com.menchaca.inventory.persistence;

import com.menchaca.inventory.model.Item;
import com.menchaca.inventory.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserDAO {
    Optional<User> findById(Long id);
    Page<User> findAll(Pageable pageable);
    void save (User user);
    void deleteById(Long id);

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
