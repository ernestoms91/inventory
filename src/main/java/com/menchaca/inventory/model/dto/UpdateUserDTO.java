package com.menchaca.inventory.model.dto;

import com.menchaca.inventory.model.Role;
import com.menchaca.inventory.model.Type;
import com.menchaca.inventory.validation.ValueOfEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UpdateUserDTO {

    private Long id;

    @Nullable
    @Size(min = 2, max = 30, message = "El nombre debe tener al menos 2 letras y no puede exceder los  30 caractéres")
    private String name;

    @Nullable
    @Size(min = 2, max = 30, message = "El apellido debe tener al menos 2 letras y no puede exceder los 30 caractéres")
    private String lastName;

    @Nullable
    @Size(min = 2, max = 30, message = "El usuario debe tener al menos 4 caracteres y no puede exceder los 30")
    private String username;

    @Nullable
    @Email(message = "Debe introducir un correo valido")
    private String email;


    //    It contains at least 8 characters and at most 20 characters.
//    It contains at least one digit.
//    It contains at least one upper case alphabet.
//    It contains at least one lower case alphabet.
//    It contains at least one special character which includes !@#$%&*()-+=^.
//    It doesn’t contain any white space.
    @Nullable
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>.]).{8,20}$", message = "Debe introducir una contraseña segura")
    private String password;

    @Nullable
    @ValueOfEnum(enumClass = Role.class, message = "No es un role permitido")
    private String role;

    @Nullable
    @Pattern(regexp = "^true$|^false$", message = "Tiene que ser un dato de tipo booleano")
    private String disable;

    public boolean isEmpty() {
        return  (name == null || name.trim().isEmpty()) &&
                (lastName == null || lastName.trim().isEmpty()) &&
                (username == null || username.trim().isEmpty()) &&
                (email == null || email.trim().isEmpty()) &&
                (password == null || password.trim().isEmpty()) &&
                (role == null || role.toString().isEmpty()) &&
                (disable == null || disable.trim().isEmpty());
    }
}
