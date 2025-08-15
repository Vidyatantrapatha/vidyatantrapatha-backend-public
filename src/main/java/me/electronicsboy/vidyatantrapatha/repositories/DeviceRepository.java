package me.electronicsboy.vidyatantrapatha.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import me.electronicsboy.vidyatantrapatha.models.Device;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {
	Optional<Device> findByDeviceId(String deviceId);
	List<Device> findAllByEnabled(boolean enabled);
}
