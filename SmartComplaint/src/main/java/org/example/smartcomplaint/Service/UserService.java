package org.example.smartcomplaint.Service;

import lombok.RequiredArgsConstructor;
import org.example.smartcomplaint.Dto.LoginDTO;
import org.example.smartcomplaint.Dto.UserDTO;
import org.example.smartcomplaint.Entity.User;
import org.example.smartcomplaint.Exception.ResourceNotFoundException;
import org.example.smartcomplaint.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Register User
    public User registerUser(UserDTO dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        user.setRole(
                dto.getRole() != null && !dto.getRole().isEmpty()
                        ? dto.getRole()
                        : "USER"
        );

        return userRepository.save(user);
    }

    // Get All Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    // Login User
    public User loginUser(LoginDTO loginDTO) {

        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invalid Email"));

        if (!passwordEncoder.matches(
                loginDTO.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid Password");
        }

        return user;
    }

    // Get User By ID
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id : " + id));
    }

    // Update User
    public User updateUser(Long id, UserDTO dto) {

        User user = getUserById(id);

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        if (dto.getPassword() != null &&
                !dto.getPassword().isEmpty()) {

            user.setPassword(
                    passwordEncoder.encode(dto.getPassword()));
        }

        user.setRole(dto.getRole());

        return userRepository.save(user);
    }

    // Delete User
    public void deleteUser(Long id) {

        User user = getUserById(id);

        userRepository.delete(user);
    }
}