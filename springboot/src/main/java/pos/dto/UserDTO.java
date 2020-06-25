package pos.dto;

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
//    private List<Phone> phone;

    //    public User parserToUser() {
//        return new User(name, cpf, email, role, password, phone);
//    }
    public User parserToUser() {
        return new User(name, email, password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getCpf() {
//        return cpf;
//    }
//
//    public void setCpf(String cpf) {
//        this.cpf = cpf;
//    }

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

//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
//    public List<Phone> getPhone() {
//        return phone;
//    }
//
//    public void setPhone(List<Phone> phone) {
//        this.phone = phone;
//    }
}
