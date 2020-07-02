package pos.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pos.model.User;

@Repository
<<<<<<< HEAD
public interface UserRepository extends CrudRepository<User, Long>{


	@Query("select user from User user where user.email = ?1")
	User findUserbyEmail(String email);

//	@Query("select user from User user where user.cpf = ?1")
//	User findByCpf(String cpf);
=======
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    User findUserById(Integer id);

    User findByEmailAndPassword(String email, String password);
>>>>>>> ff9392eef2843a31c374452482c018331554efbc
}
