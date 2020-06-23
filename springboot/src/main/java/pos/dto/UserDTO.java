package pos.dto;

import pos.model.Phone;
import pos.model.Role;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UserDTO {

    @NotNull(message = "{name.not.null}")
    @NotBlank(message = "{name.not.blank}")
    private String name;

    @NotNull(message = "{email.not.null}")
    @Email(message = "{email.not.valid}")
    private String email;
    private String password;

    @NotBlank(message = "{phone.not.blank}")
    private List<Phone> phones; //*

    @OneToMany(fetch = FetchType.EAGER) /* sempre que carregar o usuário, vai carregar as roles dele que estão no BD*/
    @JoinTable(name = "users_role", uniqueConstraints = @UniqueConstraint(
            columnNames = {"user_id", "role_id"}, name = "unique_role_user"), /* colunas cridas automáticamente*/
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", table = "user", unique = false,
                    foreignKey = @ForeignKey(name = "user_fk", value = ConstraintMode.CONSTRAINT)),

            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", table = "role", unique = false, updatable = false,
                    foreignKey = @ForeignKey(name = "role_fk", value = ConstraintMode.CONSTRAINT)
            ))
    private List<Role> roles;

}

