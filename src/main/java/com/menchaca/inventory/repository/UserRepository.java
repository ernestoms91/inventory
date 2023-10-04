package com.menchaca.inventory.repository;

import com.menchaca.inventory.model.Item;
import com.menchaca.inventory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);


    

}
