package com.springbootPOC.controller;

import com.springbootPOC.constants.UserSortingColumns;
import com.springbootPOC.dto.DefaultResponse;
import com.springbootPOC.dto.PageDTO;
import com.springbootPOC.dto.UserDTO;
import com.springbootPOC.exception.InvalidArgumentException;
import com.springbootPOC.exception.ResourceNotFoundException;
import com.springbootPOC.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
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

import static com.springbootPOC.utils.DefaultResponseBuilder.noContent;
import static com.springbootPOC.utils.DefaultResponseBuilder.success;

/**
 * @author mamta.t
 */
@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<DefaultResponse<List<UserDTO>>> getAllUsers(PageDTO<UserSortingColumns> page) throws InvalidArgumentException {
        log.debug("Get all users request received.");
        List<UserDTO> allUsers = userService.getAllUsers(page);
        return CollectionUtils.isEmpty(allUsers) ? noContent(allUsers, page).build() : success(allUsers, page).build();
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
