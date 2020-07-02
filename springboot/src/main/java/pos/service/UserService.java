package pos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pos.core.util.CryptoUtil;
import pos.dto.RoleDTO;
import pos.dto.UserDTO;
import pos.model.Role;
import pos.model.User;
import pos.repository.UserRepository;

@Service
<<<<<<< HEAD
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    protected UserRepository getRepository() {
        return repository;
    }

    public List<User> users() {
        List<User> users = (List<User>) repository.findAll();
        return users;
    }

    public User userEmail(String email) {
        return repository.findUserbyEmail(email);
    }

    public Optional<User> userWithId(Long id) {
        Optional<User> user = repository.findById(id);
        return user;
    }

    private void validateCreate(User model) throws Exception {
        if (getRepository().findUserbyEmail(model.getEmail()) != null) {
            throw new Exception("email.exists");
        }
        if (!model.getEmail().matches("[a-zA-Z._-]+@+[a-zA-Z.]+")) {
            throw new Exception("email.invalid");
        }
        if (model.getName().length() < 3 | model.getName().length() < 250 |!model.getName().matches("[a-zA-Z\\s]*")) {
            throw new Exception("name.invalid");
        }
        if (!model.getName().matches("[a-zA-Z\\s]*")) {
            throw new Exception(model.getName());
        }
    }

    public User update(User model) throws Exception {
        validateCreate(model);
        return repository.save(model);
    }

    public User create(User model) throws Exception {
        validateCreate(model);
        return repository.save(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
        return;
    }

    //consultar usuario no banco
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repository.findUserbyEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        // caso esteja correta, já retorna email, password e autorizações
        // poderia retonar, apenas o user, mas retornando um new user, ele já retorna com alguns padrões
        // do propio framework com validações
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities());
        //
=======
public class UserService {

    private UserRepository userRepository;
    private RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public UserRepository getRepository() {
        return this.userRepository;
    }

    public RoleService getRoleService() { return this.roleService;}

    public List<UserDTO> get() {
        List<User> users = (List<User>) userRepository.findAll();
        List<UserDTO> usersDto = new ArrayList<>();
        for (User u: users) {
            usersDto.add(toUserDto(u));
        }
        return usersDto;
    }

    public User userEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Integer id) {
        return userRepository.findUserById(id);
    }

    public User create(User user) throws Exception {
        if (validateCreate(user)) {
            user.setPassword(CryptoUtil.hash(user.getPassword()));
            user.setEmail(user.getEmail().toLowerCase());
            return userRepository.save(user);
        } else {
            throw new Exception();
        }
    }

    private boolean validateCreate(User user) {

        return true;
    }

    public User update(User user) throws Exception {
        if (this.validateUpdate(user)) {
            return userRepository.save(user);
        } else {
            throw new Exception();
        }
    }

    private boolean validateUpdate(User user) {
        User userTemporary = this.findUserbyEmail(user.getEmail());

        if (!userTemporary.getPassword().equals(user.getPassword())) {
            String passwordCrypted = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(passwordCrypted);
        }

        return false;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User findUserbyEmail(String email) {
        return getRepository().findByEmail(email);
    }

    public User findByEmailAndPassword(String username, String password) {
        return getRepository().findByEmailAndPassword(username.toLowerCase(), CryptoUtil.hash(password));
    }

    public User toUserModel(UserDTO userDTO) {
        User user = new User();
        Role role = getRoleService().getRoleById(userDTO.getRole().getId());
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setCpf(userDTO.getCpf());
        user.setName(userDTO.getName());
        user.setPhone(userDTO.getPhone());
        user.setPassword(userDTO.getNewPassword());

        user.setRole(role);
        return user;
    }

    public UserDTO toUserDto(User user) {
        UserDTO userDTO = new UserDTO();
        Role role = getRoleService().getRoleById(user.getRole().getId());
        RoleDTO roleDTO = getRoleService().toRoleDTO(role);
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setPhone(user.getPhone());

        userDTO.setRole(roleDTO);
        return userDTO;
>>>>>>> ff9392eef2843a31c374452482c018331554efbc
    }
}
