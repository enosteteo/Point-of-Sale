package pos.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pos.model.User;
import pos.repository.UserRepository;

@Service
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
    }
}
