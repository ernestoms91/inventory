package com.menchaca.inventory.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.menchaca.inventory.model.DepartmentName;
import com.menchaca.inventory.model.Item;
import com.menchaca.inventory.validation.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BusinessOfficeDTO {

    @JsonIgnore
    private Long id;

    @NotBlank(message = "Es un campo obligatorio")
    private String manager;


    @ValueOfEnum(enumClass = DepartmentName.class, message = "Los valores permitidos son: COMUNICACION, REDES, REPORTEROS, REDACCION, MONITOREO, ADMINISTRACION")
    private String departmentName;

    private List<Item> itemsList;

}
