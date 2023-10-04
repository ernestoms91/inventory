package com.menchaca.inventory.service.impl;

import com.menchaca.inventory.exception.ObjectNotFoundException;
import com.menchaca.inventory.exception.ObjectPropertyRepeatedException;
import com.menchaca.inventory.mapper.UserMapper;
import com.menchaca.inventory.model.Role;
import com.menchaca.inventory.model.User;
import com.menchaca.inventory.model.dto.*;
import com.menchaca.inventory.persistence.IUserDAO;
import com.menchaca.inventory.repository.UserRepository;
import com.menchaca.inventory.security.JwtService;
import com.menchaca.inventory.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;


    @Override
    public UserDetailDTO findById(Long id) throws ObjectNotFoundException {
        UserDetailDTO userDetailDTO = null;
        try {

            Optional<User> itemOptional = userDAO.findById(id);
            if (itemOptional.isEmpty()) {
                throw new ObjectNotFoundException("No existe un usuario con id: " + id);
            }

            User user = itemOptional.get();
            userDetailDTO = userMapper.UserToUserDetailDTO(user);

        } catch (Exception e) {
            throw e;
        }
        return userDetailDTO;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        Page<User> userPage = null;
        try {
            userPage = userDAO.findAll(pageable);
        } catch (Exception e) {
            throw e;
        }

        return userPage;
    }

    @Override
    public User save(UserDTO userDTO) {

        User user = null;

        try {
            Optional<User> userOptional = userDAO.findByEmail(userDTO.getEmail());

            if (userOptional.isPresent()) {
                throw new ObjectPropertyRepeatedException("Ya existe un usuario con este correo: " + userDTO.getEmail());
            }

            userOptional = userDAO.findByEmail(userDTO.getUsername());
            if (userOptional.isPresent()) {
                throw new ObjectPropertyRepeatedException("Ya existe el username: " + userDTO.getUsername());
            }

            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

            user = userMapper.UserDTOToUser(userDTO);

            userDAO.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user;
    }


    @Override
    public User update(Long id, UpdateUserDTO updateUserDTO) {

        User user = null;
        try {

            if (updateUserDTO.isEmpty()) {
                System.out.println("Esta vacia");
                throw new ObjectPropertyRepeatedException("Peticion vacía");
            }


            Optional<User> userOptional = userDAO.findById(id);
            if (userOptional.isEmpty()) {
                throw new ObjectNotFoundException("No existe un usuario con id: " + id);
            }

            user = userOptional.get();


            if (updateUserDTO.getUsername() != null && !updateUserDTO.getUsername().isEmpty() && !updateUserDTO.getUsername().isBlank()) {
                Optional<User> userOptional1 = userDAO.findByUsername(updateUserDTO.getUsername());

                if (userOptional1.isPresent() && userOptional1.get().getId() != id) {
                    throw new ObjectPropertyRepeatedException("Ya existe un username llamado: " + updateUserDTO.getUsername());
                }

                user.setUsername(updateUserDTO.getUsername());
            }

            if (updateUserDTO.getEmail() != null && !updateUserDTO.getEmail().isEmpty() && !updateUserDTO.getEmail().isBlank()) {
                Optional<User> userOptional2 = userDAO.findByEmail(updateUserDTO.getEmail());

                if (userOptional2.isPresent() && userOptional2.get().getId() != id) {
                    throw new ObjectPropertyRepeatedException("Este correo ya esta registrado");
                }

                user.setEmail(updateUserDTO.getEmail());
            }

            if (updateUserDTO.getName() != null && !updateUserDTO.getName().isEmpty() && !updateUserDTO.getName().isBlank()) {
                user.setName(updateUserDTO.getName());
            }

            if (updateUserDTO.getLastName() != null && !updateUserDTO.getLastName().isEmpty() && !updateUserDTO.getLastName().isBlank()) {
                user.setLastName(updateUserDTO.getLastName());
            }

            if (updateUserDTO.getPassword() != null && !updateUserDTO.getPassword().isEmpty() && !updateUserDTO.getPassword().isBlank()) {
                user.setPassword(passwordEncoder.encode(updateUserDTO.getPassword()));
            }

            if (updateUserDTO.getRole() != null) {
                user.setRole(Role.valueOf(updateUserDTO.getRole()));
            }

            if (updateUserDTO.getDisable() != null && !updateUserDTO.getDisable().isEmpty() && !updateUserDTO.getDisable().isBlank()) {
                user.setEnabled(Boolean.parseBoolean(updateUserDTO.getDisable()));
            }

            userDAO.save(user);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user;
    }


    @Override
    public void deleteById(Long id) {
        Optional<User> userOptional;
        User user = null;
        try {
            userOptional = userDAO.findById(id);

            if (userOptional.isEmpty()) {
                throw new ObjectNotFoundException("No existe un usuario con id: " + id);
            }

            user = userOptional.get();
            user.setEnabled(false);
            userDAO.save(user);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, String> register(UserDTO userDTO) {
        User user = save(userDTO);
        String jwtToken = jwtService.generateToken(user);
        Map<String, String> user1 = new HashMap<>();
        user1.put("usuario", user.getUsername());
        user1.put("nombre", user.getName());
        user1.put("correo", user.getEmail());
        user1.put("rol", user.getRole().toString());
        user1.put("jwt", jwtToken);
        return user1;
    }

    @Override
    public Map<String, Object> login(LoginDTO loginDTO) {

        Map<String, Object> user1 = new LinkedHashMap<>();
        try {

            Optional<User> userOptional = userDAO.findByUsername(loginDTO.getUsername());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(),
                            loginDTO.getPassword()
                    )
            );

            User user = userOptional.get();
            String jwtToken = jwtService.generateToken(user);
            user1.put("jwt", jwtToken);
            user1.put("usuario", user.getUsername());
            user1.put("apellidos", user.getLastName());
            user1.put("nombre", user.getName());
            user1.put("correo", user.getEmail());
            user1.put("rol", user.getRole().toString());

        } catch (Exception e) {
            throw e;
        }
        return user1;
    }
}


