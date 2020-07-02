package pos.model;

import java.io.Serializable;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity	
public class User implements UserDetails {

private static final long serialVersionUID = 1L;
@Id
@PrimaryKeyJoinColumn
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id; 
private String name;  

@Column(unique=true) // permite apenas 1 item na coluca, evita duplicidade
private String email;
private String password;

@OneToMany(mappedBy ="user", orphanRemoval = true, cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
private List<Phone> phones; //*

@OneToMany(fetch = FetchType.EAGER ) /* sempre que carregar o usuário, vai carregar as roles dele que estão no BD*/
@JoinTable(name = "users_role", uniqueConstraints = @UniqueConstraint ( 
				columnNames = { "user_id", "role_id"}, name = "unique_role_user" ), /* colunas cridas automáticamente*/
				joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", table = "user", unique = false,
				foreignKey = @ForeignKey (name = "user_fk", value = ConstraintMode.CONSTRAINT)),
				
				inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", table = "role", unique = false, updatable = false,
				foreignKey = @ForeignKey(name="role_fk", value = ConstraintMode.CONSTRAINT)
					)) 
private List<Role> roles;

/* Vai criar  a tabela users_role, com as colunas user_id e roled_id, criamos uma constraint unique_role_user
 *  em seguida fazendo um join da coluna */

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
=======
>>>>>>> ff9392eef2843a31c374452482c018331554efbc

import javax.persistence.*;

<<<<<<< HEAD
	public User() {
		super();
	}

	public User(String name, String email, String password, List<Phone> phones) {
		setName(name);
		setEmail(email);
		setPassword(password);
		setPhones(phones);
	}

	@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	User other = (User) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}
=======
@Entity(name = "User")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;
>>>>>>> ff9392eef2843a31c374452482c018331554efbc

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "deleted")
    private boolean deleted;

    @ManyToOne
    private Role role;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
