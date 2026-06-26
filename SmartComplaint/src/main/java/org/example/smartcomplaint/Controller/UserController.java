package org.example.smartcomplaint.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.smartcomplaint.Dto.LoginDTO;
import org.example.smartcomplaint.Dto.UserDTO;
import org.example.smartcomplaint.Entity.User;
import org.example.smartcomplaint.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    // Register User
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(
            @Valid @RequestBody UserDTO userDTO) {

        User user = userService.registerUser(userDTO);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    // Login User
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(
            @Valid @RequestBody LoginDTO loginDTO) {

        User user = userService.loginUser(loginDTO);

        return ResponseEntity.ok(user);
    }

    // Get All Users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Get User By ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                userService.getUserById(id));
    }

    // Update User
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserDTO userDTO) {

        return ResponseEntity.ok(
                userService.updateUser(id, userDTO));
    }

    // Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.ok("User Deleted Successfully");
    }
}