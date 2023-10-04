package com.menchaca.inventory.model.dto;

import com.menchaca.inventory.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDetailDTO {

    private Long id;

    @NotNull(message = "El campo nombre es obligatorio")
    @Size(min = 2, max = 30 , message ="El nombre debe tener al menos 2 letras y no puede exceder los  30 caractéres" )
    private String name;

    @NotNull(message = "El campo ya apellido es obligatorio")
    @Size(min = 2, max = 30 , message ="El apellido debe tener al menos 2 letras y no puede exceder los 30 caractéres" )
    private String lastName;

    @NotNull(message = "El username es obligatorio")
    private String username;

    @NotNull(message = "El username es obligatorio")
    @Email(message = "Debe introducir un correo valido")
    private String email;


    @NotNull(message = "El campo ¨role¨ es obligatorio")
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull(message = "El campo ¨disable¨ es obligatorio")
    private boolean disable = false;


}
