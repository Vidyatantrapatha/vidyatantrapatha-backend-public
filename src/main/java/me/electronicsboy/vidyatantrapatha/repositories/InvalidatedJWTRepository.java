package me.electronicsboy.vidyatantrapatha.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import me.electronicsboy.vidyatantrapatha.models.InvalidatedJWT;

@Repository
public interface InvalidatedJWTRepository extends CrudRepository<InvalidatedJWT, Long> {
	Optional<InvalidatedJWT> findByToken(String token);
	boolean existsByToken(String token);
	void deleteByExpiryTimeBefore(LocalDateTime expiry);
}
