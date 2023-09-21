package com.menchaca.inventory.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginDTO {

    @NotNull(message = "EL usuario es obligatorio")
    private String username;
    @NotNull(message = "EL password es obligatorio")
    private String password;

}
