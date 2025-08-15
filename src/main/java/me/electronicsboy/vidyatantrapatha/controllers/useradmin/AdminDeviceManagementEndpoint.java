package me.electronicsboy.vidyatantrapatha.controllers.useradmin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.electronicsboy.vidyatantrapatha.temporary.objects.TemporaryDeviceObject;
import me.electronicsboy.vidyatantrapatha.temporary.repositories.TemporaryDeviceObjectRepository;

@RequestMapping("/admin/devices")
@RestController
public class AdminDeviceManagementEndpoint {
	@Autowired
	private TemporaryDeviceObjectRepository deviceRepo;
	
	@GetMapping("/listDevices")
    public ResponseEntity<List<TemporaryDeviceObject>> listFile() {
    	return ResponseEntity.ok(deviceRepo.findAll());
    }
}
