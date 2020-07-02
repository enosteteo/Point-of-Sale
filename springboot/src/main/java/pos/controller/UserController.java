package pos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> ff9392eef2843a31c374452482c018331554efbc

import pos.dto.UserDTO;
import pos.model.User;
import pos.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

<<<<<<< HEAD
@CrossOrigin(origins = "")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = "Application/json")
    @CacheEvict(value = "cacheUserEvict", allEntries = true) // remove do cache coisas que napo estao sendo utilizadas
    @CachePut(value = "cacheUserPut") // indentifica atualizações no banco
    public ResponseEntity<List<User>> users() throws InterruptedException {
        List<User> users = userService.users();
        //Thread.sleep(6000); /*simulando processo lento*/
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }


    // EXEMPLO DE VERSIONAMENTO DE API, PODE TAMBÉM COLOCAR DIRETO NO REQUESTMAPPING
    @GetMapping(value = "v1/{idUser}", produces = "Application/json")
    @CacheEvict(value = "cacheUserEvict", allEntries = true)
    @CachePut(value = "cacheUserPut")
    public ResponseEntity<User> indexV1(@PathVariable(value = "idUser") Long id) {
        System.out.println(" CHAMANDO VERSÃO 1 de buscar user por id.");
        Optional<User> user = userService.userWithId(id);
        return new ResponseEntity<User>(user.get(), HttpStatus.OK);
    }


    @GetMapping(value = "v2/{idUser}", produces = "Application/json")
    public ResponseEntity<User> indexV2(@PathVariable(value = "idUser") Long id) {
        System.out.println(" CHAMANDO VERSÃO 2  de buscar user por id.");
        Optional<User> user = userService.userWithId(id);
        return new ResponseEntity<User>(user.get(), HttpStatus.OK);
    }


    @PostMapping(value = "", produces = "Application/json")
    public ResponseEntity<UserDTO> store(@RequestBody UserDTO userDTO) throws Exception {
//		for (int phone = 0; phone < user.getPhones().size(); phone++) {
//			user.getPhones().get(phone).setUser(user);
//		}

        String passwordCrypted = new BCryptPasswordEncoder().encode(userDTO.getPassword());
        userDTO.setPassword(passwordCrypted);

        System.out.println("senha foi criptografada: ");
        userService.create(userDTO.parserToUser());
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);

    }


    @PutMapping(value = "/{id}", produces = "Application/json")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) throws Exception {

        if (userDTO.getPassword() != null) {
            String passwordCrypted = new BCryptPasswordEncoder().encode(userDTO.getPassword());
            userDTO.setPassword(passwordCrypted);
        }

        userService.update(userDTO.parserToUser());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }


    @DeleteMapping(value = "/{id}", produces = "Application/text")
    public String delete(@PathVariable(value = "id") Long id) {
        userService.delete(id);
        return "User Deleted";
=======
    private UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }

    public UserService getService() {
        return this.userService;
    }

    @GetMapping(value = "", produces = "Application/json")
    public ResponseEntity<List<UserDTO>> list() {
        List<UserDTO> users = getService().get();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "Application/json")
    public ResponseEntity<User> findById(@PathVariable(value = "id") Integer id) {
        User user = getService().findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "Application/json")
    public ResponseEntity<User> create(@RequestBody UserDTO userDTO) throws Exception {
        User userSalved = getService().create(getService().toUserModel(userDTO));
        return new ResponseEntity<>(userSalved, HttpStatus.CREATED);
>>>>>>> ff9392eef2843a31c374452482c018331554efbc
    }

/*    @PutMapping(value = "/", produces = "Application/json")
    public ResponseEntity<User> update(@RequestBody User user) throws Exception {
        User userUpdate = getService().update(user);
        return new ResponseEntity<>(userUpdate, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{idUser}", produces = "Application/text")
    public ResponseEntity<?> delete(@PathVariable(value = "idUser") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/
}
