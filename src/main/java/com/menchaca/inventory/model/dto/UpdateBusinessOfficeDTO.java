package com.menchaca.inventory.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.menchaca.inventory.model.DepartmentName;
import com.menchaca.inventory.model.Item;
import com.menchaca.inventory.validation.NullOrNotBlank;
import com.menchaca.inventory.validation.ValueOfEnum;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBusinessOfficeDTO {

    @NullOrNotBlank
    private String manager;

    @Nullable
    @ValueOfEnum(enumClass = DepartmentName.class, message = "Los valores permitidos son: COMUNICACION, REDES, REPORTEROS, REDACCION, MONITOREO, ADMINISTRACION")
    private String departmentName;

    public boolean isEmpty() {
        return (manager == null || manager.isEmpty()) && (departmentName == null || departmentName.isEmpty());
    }
}
