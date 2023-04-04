package com.springbootPOC.service;

import com.springbootPOC.constants.UserSortingColumns;
import com.springbootPOC.dbo.User;
import com.springbootPOC.dto.PageDTO;
import com.springbootPOC.dto.UserDTO;
import com.springbootPOC.exception.InvalidArgumentException;

import java.util.List;

/**
 * @author mamta.t
 */

public interface UserService {

    List<UserDTO> getAllUsers(PageDTO<UserSortingColumns> page) throws InvalidArgumentException;
    UserDTO getUserById(int id) throws Exception;
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(int userId, UserDTO updateUserDTO) throws Exception;
    void deleteUser(int id) throws Exception;

}
