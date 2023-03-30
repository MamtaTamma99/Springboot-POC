package com.restCrud.controller;

import com.restCrud.dbo.User;
import com.restCrud.dto.UserDTO;
import com.restCrud.exception.ResourceNotFoundException;
import com.restCrud.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author mamta.t
 */
@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers()
    {
        log.debug("Get all users request received.");
        List<User> allUsers = userService.getAllUsers();
        if(allUsers.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int userId) throws Exception {
        log.debug("Get user by id request received.");
        UserDTO user = userService.getUserById(userId);
        if(user == null) {
            throw new ResourceNotFoundException("user", "id", userId);
        }
        return ResponseEntity.of(Optional.of(user));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        log.debug("Create user request received.");
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") int userId, @Valid @RequestBody UserDTO updateUserDTO) throws Exception {
        log.debug("Update user request received.");
        UserDTO updatedUser = userService.updateUser(userId, updateUserDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int userId) throws Exception {
        log.debug("Delete user request received.");
         userService.deleteUser(userId);
         return ResponseEntity.ok().build();
    }
}
