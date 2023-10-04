package com.menchaca.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity(name = "items")

public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El número de inventario es obligatorio")
    @Column(unique = true)
    private int stockNumber;

    @NotNull(message = "El campo ¨electric¨ es obligatorio")
    private boolean electric;

    @NotNull(message = "El campo ¨type¨ es obligatorio")
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotNull(message = "El campo ¨description¨ es obligatorio")
    @Size(min = 2, max = 100 , message ="La descripción debe tener al menos 2 letras y no puede exceder los 100 caractéres" )
    private String description;

//    @Nullable
    @Size(min = 2, max = 100 , message ="La observación debe tener al menos 2 letras y no puede exceder los 100 caractéres" )
    private String observation;

    @NotNull(message = "El campo ¨price¨ es obligatorio")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;

    @ManyToOne(targetEntity = BusinessOffice.class)
    @JoinColumn(name = "id_department" , nullable = false)
    @JsonIgnore
    private BusinessOffice businessOffice;

    @NotNull
    private boolean broken;

    @NotNull
    private  boolean withdrawn ;

    private  String image;

}
