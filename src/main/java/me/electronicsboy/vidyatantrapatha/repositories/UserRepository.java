package me.electronicsboy.vidyatantrapatha.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import me.electronicsboy.vidyatantrapatha.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	
	List<User> findAllByEnabled(boolean enabled);
}
