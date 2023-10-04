package com.menchaca.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Entity(name="usuarios")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El campo nombre es obligatorio")
    @Size(min = 2, max = 30 , message ="El nombre debe tener al menos 2 letras y no puede exceder los  30 caractéres" )
    private String name;

    @NotNull(message = "El campo apellido es obligatorio")
    @Size(min = 2, max = 30 , message ="El apellido debe tener al menos 2 letras y no puede exceder los 30 caractéres" )
    private String lastName;

    @NotNull(message = "El username es obligatorio")
    @Column(unique = true)
    private String username;

    @NotNull(message = "El username es obligatorio")
    @Email(message = "Debe introducir un correo valido")
    @Column(unique = true)
    private String email;

    @NotNull(message = "La contraseña es obligatoria")
    @JsonIgnore
    private String password;

    @NotNull(message = "El campo ¨role¨ es obligatorio")
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull(message = "El campo ¨enabled¨ es obligatorio")
    private boolean enabled;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password ;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() { return enabled;}
}
