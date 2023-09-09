package com.menchaca.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity(name="departments")

public class BusinessOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El campo ¨departmentName¨ es obligatorio")
    @Enumerated(EnumType.STRING)
    private DepartmentName departmentName;

    @NotNull(message = "El campo ¨manager¨ es obligatorio")
    @Size(min = 2, max = 30 , message ="El manager deber conterner al menos 2 letras" )
    private String manager;

    @OneToMany(mappedBy = "businessOffice", cascade = { CascadeType.ALL}, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Item>  itemsList;

}
