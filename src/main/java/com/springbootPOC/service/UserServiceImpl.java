package com.springbootPOC.service;

import com.springbootPOC.constants.ResponseMessageCodes;
import com.springbootPOC.constants.UserSortingColumns;
import com.springbootPOC.dbo.User;
import com.springbootPOC.dto.PageDTO;
import com.springbootPOC.dto.UserDTO;
import com.springbootPOC.exception.InvalidArgumentException;
import com.springbootPOC.exception.WebApplicationException;
import com.springbootPOC.mapper.UserMapper;
import com.springbootPOC.repository.UserRepository;
import com.springbootPOC.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.springbootPOC.constants.UserSortingColumns.USER_NAME;
import static com.springbootPOC.utils.PageUtils.getContentFromPage;
import static com.springbootPOC.utils.PageUtils.getPageableFromPageDTO;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;
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
    @Autowired
    private UserValidator userValidator;

    @Override
    public List<UserDTO> getAllUsers(PageDTO<UserSortingColumns> page) throws InvalidArgumentException {
        log.info("Inside getAllUsers method");
        userValidator.validateUserSortColumn(page);
        PageRequest pageable = getPageableFromPageDTO(page);

        Page<User> allUsers = userRepository.findAll(pageable);
        long count = userRepository.count();

        page.setTotalRecords(count);
        List<User> users = getContentFromPage(allUsers);
        log.info("Successfully fetched all Users");
        return userMapper.toUsersDTO(users);
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
            throw new WebApplicationException(BAD_REQUEST, ResponseMessageCodes.BAD_REQUEST.getCode(), "Invalid User ID received in request: " + userId);
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
    public void deleteUser(int userId) throws WebApplicationException {
        log.info("Inside delete User method");

        if(!userRepository.existsByUserId(userId)){
            log.warn("UserId doesn't exist" + userId);
            throw new WebApplicationException(BAD_REQUEST, ResponseMessageCodes.BAD_REQUEST.getCode(), "UserId doesn't exist with this id " + userId);
        }
        log.info("Successfully deleted user with this "+ userId);
        this.userRepository.deleteById(userId);
    }


}
