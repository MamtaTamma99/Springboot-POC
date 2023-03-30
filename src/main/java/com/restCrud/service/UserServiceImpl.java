package com.restCrud.service;

import com.restCrud.dbo.User;
import com.restCrud.dto.UserDTO;
import com.restCrud.mapper.UserMapper;
import com.restCrud.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author mamta.t
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        log.info("Inside getAllUsers method");

        List<User> usersList = userRepository.findAll();
        log.info("Successfully fetched all Users");
        return usersList;
    }

    @Override
    public UserDTO getUserById(int userId) throws Exception {
        log.info("Inside getUserById method");

        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent())
        {
            log.info("Successfully fetched User with id" + userId);
            return  userMapper.toUserDTO(user.get());
        }else {
            log.warn("No User details found with id: " + userId);
            throw new Exception(HttpStatus.NOT_FOUND.value() + "No User details found with id: " + userId);
        }
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        log.info("Inside create User method");

        User user = userMapper.toUserDBO(userDTO);
        User savedUser = userRepository.save(user);
        log.info("Successfully created new user");
        return userMapper.toUserDTO(savedUser);
    }
    @Override
    public UserDTO updateUser(int userId, UserDTO updateUserDTO){
        log.info("Inside update User method");
        Optional<User> userById = userRepository.findById(userId);
        User userDBO = userById.get();
        userDBO.setUsername(updateUserDTO.getUsername());
        userDBO.setFirstName(updateUserDTO.getFirstName());
        userDBO.setLastName(updateUserDTO.getLastName());
        userDBO.setEmail(updateUserDTO.getEmail());
        userDBO.setPassword(updateUserDTO.getPassword());
        User user = userRepository.save(userDBO);
        log.info("Successfully updated user with this "+ userId);
        return userMapper.toUserDTO(user);
    }

    @Override
    public void deleteUser(int userId) throws Exception {
        log.info("Inside delete User method");

        if(!userRepository.existsByUserId(userId)){
            log.warn("UserId doesn't exist" + userId);
            throw new Exception(BAD_REQUEST + "UserId doesn't exist with this id " + userId);
        }
        log.info("Successfully deleted user with this "+ userId);
        this.userRepository.deleteById(userId);
    }


}
