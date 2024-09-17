package com.example.userregistrationportal.controller;

import com.example.userregistrationportal.model.User;
import com.example.userregistrationportal.model.UserResponse;
import com.example.userregistrationportal.service.OcrService;
import com.example.userregistrationportal.service.UserService;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OcrService ocrService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(
            @Valid @RequestParam String firstName,
            @Valid @RequestParam String lastName,
            @Valid @RequestParam String email,
            @Valid @RequestParam String phoneNumber,
            @Valid @RequestParam String aadharNumber,
            @Valid @RequestParam String password, // Added password field
            @RequestParam MultipartFile aadharImage) {

        // Validate and handle the uploaded file
        if (aadharImage.isEmpty()) {
            return ResponseEntity.badRequest().body(new UserResponse("Error", "Aadhar card image is required."));
        }

        File tempFile;
        try {
            // Save the image to a temporary file
            tempFile = File.createTempFile("aadhar-", aadharImage.getOriginalFilename());
            aadharImage.transferTo(tempFile);

            // Check if the uploaded image contains the Aadhar number
            if (!ocrService.isAadhaarImage(tempFile)) {
                tempFile.delete();
                return ResponseEntity.badRequest().body(new UserResponse("Error", "Invalid Aadhar card image."));
            }

            // Validate Aadhar number
            String extractedAadharNumber = ocrService.extractAadhaarNumberFromImage(tempFile);
            if (!extractedAadharNumber.equals(aadharNumber)) {
                tempFile.delete();
                return ResponseEntity.badRequest().body(new UserResponse("Error", "Aadhar number does not match."));
            }

            // Check if email exists in the verification_status table
            if (!userService.emailExistsInVerificationStatus(email)) {
                tempFile.delete();
                return ResponseEntity.badRequest().body(new UserResponse("Error", "Email is not present in verification status table."));
            }

            // Register user
            User user = new User(firstName, lastName, email, phoneNumber, aadharNumber, password); // Include password
            userService.saveUser(user);

            // Delete the temporary file
            tempFile.delete();

            return ResponseEntity.ok(new UserResponse("Success", "Registration Successful"));
        } catch (IOException e) {
            return ResponseEntity.status(500).body(new UserResponse("Error", "Failed to process the image."));
        } catch (TesseractException e) {
            return ResponseEntity.status(500).body(new UserResponse("Error", "Failed to extract Aadhar number from the image."));
        }
    }
    
    

	/*
	 * @PostMapping("/check-email") public ResponseEntity<UserResponse>
	 * checkUserExists(@RequestParam String email) { boolean userExists =
	 * userService.emailExists(email); if (userExists) { return
	 * ResponseEntity.ok(new UserResponse("Info", "Already Present User")); } else {
	 * return ResponseEntity.ok(new UserResponse("Info", "New User")); } }
	 * 
	 */   
    
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<UserResponse> handleDuplicateEntry(DataIntegrityViolationException ex) {
        return ResponseEntity.badRequest().body(new UserResponse("Error", "Duplicate Entry"));
    }
}
