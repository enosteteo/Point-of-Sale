package pos.dto;

<<<<<<< HEAD
import pos.model.Phone;
import pos.model.Role;
import pos.model.User;

import java.util.List;

public class UserDTO {

    private String name;

//    private String cpf;

    private String email;

    private String password;

//    private Role role;
//
    private List<Phone> phone;

    //    public User parserToUser() {
//        return new User(name, cpf, email, role, password, phone);
//    }
    public User parserToUser() {
        String password = this.password;
        this.setPassword("");
        return new User(name, email, password, phone);
=======
import pos.model.Role;

public class UserDTO {

    private Integer id;

    private String name;

    private String email;

    private String phone;

    private String cpf;

    private String newPassword;

    private RoleDTO role;

    public UserDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
>>>>>>> ff9392eef2843a31c374452482c018331554efbc
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

<<<<<<< HEAD
//    public String getCpf() {
//        return cpf;
//    }
//
//    public void setCpf(String cpf) {
//        this.cpf = cpf;
//    }

=======
>>>>>>> ff9392eef2843a31c374452482c018331554efbc
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

<<<<<<< HEAD
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
=======
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
>>>>>>> ff9392eef2843a31c374452482c018331554efbc
    }
}
