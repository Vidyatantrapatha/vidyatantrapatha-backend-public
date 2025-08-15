package me.electronicsboy.vidyatantrapatha.controllers.useradmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.electronicsboy.vidyatantrapatha.data.PrivilegeLevel;
import me.electronicsboy.vidyatantrapatha.exceptions.UnprivilagedExpection;
import me.electronicsboy.vidyatantrapatha.exceptions.UserNotFoundException;
import me.electronicsboy.vidyatantrapatha.models.User;
import me.electronicsboy.vidyatantrapatha.repositories.UserRepository;
import me.electronicsboy.vidyatantrapatha.responses.PrivilageLevelResponse;

@RequestMapping("/info/users")
@RestController
public class UserInfoController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/me")
	public ResponseEntity<User> authenticatedUserInfo() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();
		return ResponseEntity.ok(currentUser);
    }
	
	@GetMapping("/me/privilage_level")
	public ResponseEntity<PrivilageLevelResponse> getPrivilageLevelString() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    User user = (User) authentication.getPrincipal();

	    return ResponseEntity.ok(new PrivilageLevelResponse().setPrivilageLevel(user.getPrivilegeLevel()));
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<User> getById(@PathVariable long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(((User) authentication.getPrincipal()).getPrivilegeLevel().compareTo(PrivilegeLevel.ADMIN) <= 0)
			throw new UnprivilagedExpection("You aren't privilaged enough to do this!");
		
		if(!userRepository.existsById(id))
			throw new UserNotFoundException("No user with id %d exists!".formatted(id));
		
        return ResponseEntity.ok(userRepository.findById(id).get());
    }
}
