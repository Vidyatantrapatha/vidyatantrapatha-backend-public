package me.electronicsboy.vidyatantrapatha.controllers.useradmin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import me.electronicsboy.vidyatantrapatha.data.FileType;
import me.electronicsboy.vidyatantrapatha.data.PrivilegeLevel;
import me.electronicsboy.vidyatantrapatha.exceptions.UnprivilagedExpection;
import me.electronicsboy.vidyatantrapatha.models.FileObject;
import me.electronicsboy.vidyatantrapatha.models.User;
import me.electronicsboy.vidyatantrapatha.repositories.FileObjectRepository;
import me.electronicsboy.vidyatantrapatha.responses.OkResponse;
import me.electronicsboy.vidyatantrapatha.responses.SafeFileResponse;
import me.electronicsboy.vidyatantrapatha.services.FileStorageService;

@RequestMapping("/admin/files")
@RestController
public class AdminFileManagementController {
	private final FileStorageService fileService;
	private final FileObjectRepository fileObjectRepository;
    
    public AdminFileManagementController(FileStorageService fileService, FileObjectRepository fileObjectRepository) {
		this.fileService = fileService;
		this.fileObjectRepository = fileObjectRepository;
	}
    
    @PostMapping("/upload")
    public ResponseEntity<SafeFileResponse> uploadFile(
        @RequestParam("file") MultipartFile file,
        @RequestParam String grade,
        @RequestParam String subject,
        @RequestParam String filename,
        @RequestParam(required = false) String term,
        @RequestParam(required = false) String teacher,
        @RequestParam FileType fileType
    ) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();
		if(currentUser.getPrivilegeLevel().compareTo(PrivilegeLevel.ADMIN) <= 0)
			throw new UnprivilagedExpection("You aren't privilaged enough to do this!");
		
        try {
            FileObject stored = fileService.storeFile(file, filename, fileType, grade, subject, term, teacher);
            return ResponseEntity.ok(SafeFileResponse.fromFileObject(stored));
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
    }
    
    @PostMapping("/delete/{id}")
    public ResponseEntity<OkResponse> deleteFile(@PathVariable long id) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();
		if(currentUser.getPrivilegeLevel().compareTo(PrivilegeLevel.ADMIN) <= 0)
			throw new UnprivilagedExpection("You aren't privilaged enough to do this!");
		
        fileService.deleteFile(fileObjectRepository.findById(id).orElseThrow());
        return ResponseEntity.ok(new OkResponse("File with id %d has been deleted".formatted(id)));
    }
}
