package com.menchaca.inventory.model.dto;

import com.menchaca.inventory.model.DepartmentName;
import com.menchaca.inventory.model.Type;
import com.menchaca.inventory.validation.NullOrNotBlank;
import com.menchaca.inventory.validation.ValueOfEnum;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateItemDTO {
    private Long id;

    @Nullable
    @Size(min = 3, message = "El número debe contener al menos 3 dígitos")
    @Pattern(regexp = "\\d+", message = "Debe introducir solo números")
    private String stockNumber;

    @Nullable
    @Pattern(regexp = "^true$|^false$", message = "Tiene que ser un dato de tipo booleano")
    private String electric;

    @Nullable
    @ValueOfEnum(enumClass = Type.class, message = "Los valores permitidos son:  MESA, SIllA, SPLIT, AIRE, SOFA, REFRIGERADOR, GRABADORA, CUADRO")
    private String type;

    @Nullable
    @Size(min = 2, max = 100, message = "La descripción debe tener al menos 2 letras y no puede exceder los 100 caractéres")
    private String description;

    @Nullable
    @Size(min = 2, max = 100, message = "La observación debe tener al menos 2 letras y no puede exceder los 100 caractéres")
    private String observation;

    @Nullable
    @Pattern(regexp = "\\d+", message = "Debe introducir solo números")
    private String price;

    @Nullable
    @Pattern(regexp = "\\d+", message = "Debe introducir solo números")
    private String id_department;

    @Nullable
    @Pattern(regexp = "^true$|^false$", message = "Tiene que ser un dato de tipo booleano")
    private String broken ;

    @Nullable
    @Pattern(regexp = "^true$|^false$", message = "Tiene que ser un dato de tipo booleano")
    private String withdrawn ;

    public boolean isEmpty() {
        return ( (stockNumber == null || stockNumber.isEmpty()) && (electric == null || electric.isEmpty()) && (type == null || type.isEmpty())
                && (description == null || description.isEmpty()) && (observation == null || observation.isEmpty())&& (price == null || price.isEmpty())
                && (id_department == null || id_department.isEmpty()) && (broken == null || broken.isEmpty()) && (withdrawn == null || withdrawn.isEmpty()));
    }

}

