package com.demo.arteflor.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    private Integer roleId;
    private String roleName;
    @Override
    public String getAuthority() {
        return roleName;
    }
}
