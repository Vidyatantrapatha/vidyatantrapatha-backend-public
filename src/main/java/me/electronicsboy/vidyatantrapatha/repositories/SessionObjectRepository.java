package me.electronicsboy.vidyatantrapatha.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.electronicsboy.vidyatantrapatha.models.SessionObject;

@Repository
public interface SessionObjectRepository extends JpaRepository<SessionObject, Long> {
	boolean existsById(String id);
	Optional<SessionObject> findById(String id);
}
