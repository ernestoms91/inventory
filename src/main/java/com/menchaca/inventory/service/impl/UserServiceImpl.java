package com.menchaca.inventory.service.impl;

import com.menchaca.inventory.exception.ObjectNotFoundException;
import com.menchaca.inventory.exception.ObjectPropertyRepeatedException;
import com.menchaca.inventory.mapper.UserMapper;
import com.menchaca.inventory.model.User;
import com.menchaca.inventory.model.dto.LoginDTO;
import com.menchaca.inventory.model.dto.UpdateItemDTO;
import com.menchaca.inventory.model.dto.UserDTO;
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
    public UserDTO findById(Long id) throws ObjectNotFoundException {
        UserDTO userDTO = null;
        try {

            Optional<User> itemOptional = userDAO.findById(id);
            if (itemOptional.isEmpty()) {
                throw new ObjectNotFoundException("No existe un usuario con id: " + id);
            }

            User user = itemOptional.get();
            userDTO = userMapper.UserToUserDTO(user);

        } catch (Exception e) {
            throw e;
        }
        return userDTO;
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
    public User update(Long id, UpdateItemDTO updateItemDTO) {
        return null;
//        Item item = null;
//        try {
//
//            if (updateItemDTO.isEmpty()) {
//                System.out.println("Esta vacia");
//                throw new ObjectPropertyRepeatedException("Peticion vacía");
//            }
//
//
//            Optional<Item> itemOptional = itemDAO.findById(id);
//            if (itemOptional.isEmpty()) {
//                throw new ObjectNotFoundException("No existe un item con id: " + id);
//            }
//
//            item = itemOptional.get();
//
//
//            if (updateItemDTO.getStockNumber() != null && !updateItemDTO.getStockNumber().isEmpty() && !updateItemDTO.getStockNumber().isBlank()) {
//                Optional<Item> itemOptional1 = itemDAO.findByStockNumber(Integer.parseInt(updateItemDTO.getStockNumber()));
//
//                if (itemOptional1.isPresent() && itemOptional1.get().getId() != id) {
//                    throw new ObjectPropertyRepeatedException("Ya existe un objeto con este numero de inventario: " + updateItemDTO.getStockNumber());
//                }
//
//                item.setStockNumber(Integer.parseInt(updateItemDTO.getStockNumber()));
//            }
//
//
//            if (updateItemDTO.getElectric() != null && !updateItemDTO.getElectric().isEmpty() && !updateItemDTO.getElectric().isBlank()) {
//                item.setElectric(Boolean.parseBoolean(updateItemDTO.getElectric()));
//            }
//            if (updateItemDTO.getType() != null && !updateItemDTO.getType().isEmpty() && !updateItemDTO.getType().isBlank()) {
//                item.setType(Type.valueOf(updateItemDTO.getType()));
//            }
//
//            if (updateItemDTO.getDescription() != null && !updateItemDTO.getDescription().isEmpty() && !updateItemDTO.getDescription().isBlank()) {
//                item.setDescription(updateItemDTO.getDescription());
//            }
//
//            if (updateItemDTO.getObservation() != null && !updateItemDTO.getObservation().isEmpty() && !updateItemDTO.getObservation().isBlank()) {
//                item.setObservation(updateItemDTO.getObservation());
//            }
//            if (updateItemDTO.getPrice() != null) {
//                item.setPrice(BigDecimal.valueOf(Long.parseLong(updateItemDTO.getPrice())));
//            }
//            if (updateItemDTO.getBroken() != null && !updateItemDTO.getBroken().isEmpty() && !updateItemDTO.getBroken().isBlank()) {
//                item.setBroken(Boolean.parseBoolean(updateItemDTO.getBroken()));
//            }
//            if (updateItemDTO.getWithdrawn() != null && !updateItemDTO.getWithdrawn().isEmpty() && !updateItemDTO.getWithdrawn().isBlank()) {
//                item.setWithdrawn(Boolean.parseBoolean(updateItemDTO.getWithdrawn()));
//            }
//
//            Optional<BusinessOffice> businessOfficeOptional = businessOfficeDAO.findById(Long.valueOf(updateItemDTO.getId_department()));
//
//            if (businessOfficeOptional.isEmpty()) {
//                throw new ObjectNotFoundException("No existe ningun departamento con el id: " + updateItemDTO.getId_department());
//            }
//
//            if (updateItemDTO.getId_department() != null && !updateItemDTO.getId_department().isEmpty() && !updateItemDTO.getId_department().isBlank()) {
//                item.setBusinessOffice(BusinessOffice.builder()
//                        .id(Long.valueOf(updateItemDTO.getId_department())).build());
//            }
//
//            itemDAO.save(item);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return item;
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
            user.setDisable(true);
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
    public Map<String, String> login(LoginDTO loginDTO) {


        Map<String, String> user1 = new HashMap<>();
        try {

            Optional<User> userOptional = userDAO.findByUsername(loginDTO.getUsername());
//            if (userOptional.isEmpty()) {
//                throw new ObjectNotFoundException("Creedenciales incorrectas" );
//            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(),
                            loginDTO.getPassword()
                    )
            );
//           if(!authentication.isAuthenticated()){
//               throw new ObjectNotFoundException("Creedenciales incorrectas" );
//           }

            User user = userOptional.get();
            String jwtToken = jwtService.generateToken(user);

            user1.put("usuario", user.getUsername());
//            user1.put("nombre", user.getName());
//            user1.put("correo", user.getEmail());
//            user1.put("rol", user.getRole().toString());
            user1.put("jwt", jwtToken);

        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
        return user1;
    }
}


