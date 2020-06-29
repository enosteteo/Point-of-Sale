package pos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import pos.dto.UserDTO;
import pos.model.User;
import pos.repository.UserRepository;
import pos.service.UserService;


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
    }


}
