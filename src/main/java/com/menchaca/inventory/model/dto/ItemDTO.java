package com.menchaca.inventory.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.menchaca.inventory.model.BusinessOffice;
import com.menchaca.inventory.model.Type;
import com.menchaca.inventory.validation.ValueOfEnum;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ItemDTO {
    private Long id;

    @NotNull(message = "El número de inventario es obligatorio")
    @Size(min = 3, message = "El número debe contener al menos 3 dígitos")
    @Pattern(regexp = "\\d+", message = "Debe introducir solo números")
    private String stockNumber;

    @NotNull(message = "El campo ¨electric¨ es obligatorio")
    private boolean electric;

    @ValueOfEnum(enumClass = Type.class, message = "Los valores permitidos son:  MESA, SIllA, SPLIT, AIRE, SOFA, REFRIGERADOR, GRABADORA, CUADRO")
    private String type;

    @NotNull(message = "El campo ¨description¨ es obligatorio")
    @Size(min = 2, max = 100, message = "La descripción debe tener al menos 2 letras y no puede exceder los 100 caractéres")
    private String description;

    //    @Nullable
    @Size(min = 2, max = 100, message = "La observación debe tener al menos 2 letras y no puede exceder los 100 caractéres")
    private String observation;

    @NotNull(message = "El campo ¨price¨ es obligatorio")
    @Digits(fraction = 2, integer = 10, message = "El monto debe tener entre 2 decimales y 10 digitos enteros")
    private BigDecimal price;

    @NotNull(message = "El campo ¨id_department¨ es obligatorio")
    private  int id_department;

    @NotNull
    private boolean broken = false;

    @NotNull
    private boolean withdrawn = false;

}

