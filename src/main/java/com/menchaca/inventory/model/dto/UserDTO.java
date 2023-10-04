package com.menchaca.inventory.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.menchaca.inventory.model.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDTO  {

    private Long id;

    @NotBlank(message = "No puede estar vacío")
    @NotNull(message = "El campo nombre es obligatorio")
    @Size(min = 2, max = 30 , message ="El nombre debe tener al menos 2 letras y no puede exceder los  30 caractéres" )
    private String name;

    @NotBlank(message = "No puede estar vacío")
    @NotNull(message = "El campo ya apellido es obligatorio")
    @Size(min = 2, max = 30 , message ="El apellido debe tener al menos 2 letras y no puede exceder los 30 caractéres" )
    private String lastName;

    @NotBlank(message = "No puede estar vacío")
    @NotNull(message = "El username es obligatorio")
    @Size(min = 2, max = 30, message = "El usuario debe tener al menos 4 caracteres y no puede exceder los 30")
    private String username;

    @NotNull(message = "El username es obligatorio")
    @Email(message = "Debe introducir un correo valido")
    private String email;


//    It contains at least 8 characters and at most 20 characters.
//    It contains at least one digit.
//    It contains at least one upper case alphabet.
//    It contains at least one lower case alphabet.
//    It contains at least one special character which includes !@#$%&*()-+=^.
//    It doesn’t contain any white space.
    @NotNull(message = "La contraseña es obligatoria")
    @Pattern(regexp =  "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>.]).{8,20}$", message = "Debe introducir una contraseña segura")
    private String password;

    @NotNull(message = "El campo ¨role¨ es obligatorio")
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull(message = "El campo ¨disable¨ es obligatorio")
    private boolean disable = false;


}
