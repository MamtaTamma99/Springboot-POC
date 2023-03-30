package com.restCrud.service;

import com.restCrud.dbo.User;
import com.restCrud.dto.UserDTO;

import java.util.List;

/**
 * @author mamta.t
 */

public interface UserService {

    List<User> getAllUsers();
    UserDTO getUserById(int id) throws Exception;
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(int userId, UserDTO updateUserDTO) throws Exception;
    void deleteUser(int id) throws Exception;

}
