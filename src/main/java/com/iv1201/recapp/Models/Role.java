package com.iv1201.recapp.Models;

import com.iv1201.recapp.Config.Validation.ValidateRoleId;
import jakarta.persistence.*;

/**
 * POJO for user roles.
 */
@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_sequence"
    )
    @ValidateRoleId
    private Long id;

    @Column(
            name = "role_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
//    @ValidateRoleName
    private String roleName;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
