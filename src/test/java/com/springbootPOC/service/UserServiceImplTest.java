package com.springbootPOC.service;

import com.springbootPOC.constants.UserSortingColumns;
import com.springbootPOC.dbo.User;
import com.springbootPOC.dto.PageDTO;
import com.springbootPOC.dto.SortDirection;
import com.springbootPOC.dto.UserDTO;
import com.springbootPOC.exception.InvalidArgumentException;
import com.springbootPOC.mapper.UserMapper;
import com.springbootPOC.repository.UserRepository;
import com.springbootPOC.validator.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * @author mamta.t
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    
    @InjectMocks
    private UserServiceImpl userServiceImpl;
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserValidator userValidator;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @BeforeEach
    void init()
    {
        ReflectionTestUtils.setField(userServiceImpl,"userMapper", userMapper);
        ReflectionTestUtils.setField(userServiceImpl,"userValidator", userValidator);
    }

    @Test
    void testGetAllUsers() throws InvalidArgumentException {
        PageDTO<UserSortingColumns> page = new PageDTO<>(1L, 1L, "username", SortDirection.ASC);
        Page<User> allUsersPage = new PageImpl<>(List.of(user()));
        when(userRepository.findAll(any(Pageable.class))).thenReturn(allUsersPage);
        when(userRepository.count()).thenReturn(2L);
        List<UserDTO> allUsers = userServiceImpl.getAllUsers(page);
        assertNotNull(allUsers);
    }

    @Test
    void testGetUserById() throws Exception {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user()));
        UserDTO userById = userServiceImpl.getUserById(1);
        assertNotNull(userById);
        assertEquals(1,userById.getUserId());
    }

    @Test
    void testGetUserByIdNotFound() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        Exception exception = assertThrows(Exception.class, () -> userServiceImpl.getUserById(1));
        assertEquals("404No User details found with id: " + 1, exception.getMessage());
    }

    @Test
    void testCreateUser() {
        when(userRepository.save(any(User.class))).thenReturn(user());
        UserDTO userById = userServiceImpl.createUser(userDTO());
        assertEquals(1,userById.getUserId());
    }

    @Test
    void testUpdateUser() {
        UserDTO userDTO = userDTO();
        User user = user();
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        UserDTO userById = userServiceImpl.updateUser(1, userDTO);
        assertEquals(1,userById.getUserId());
        assertEquals(userDTO.getFirstName(), user.getFirstName());
    }

    @Test
    void testDeleteUser() throws Exception {
        int userId = 1;
        when(userRepository.existsByUserId(anyInt())).thenReturn(true);
        doNothing().when(userRepository).deleteById(userId);
        userServiceImpl.deleteUser(userId);
        assertTrue(true);
    }

    @Test
    void testDeleteUserFoundById() throws Exception {
        int userId = 1;
        when(userRepository.existsByUserId(anyInt())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, () -> userServiceImpl.deleteUser(userId));
        assertFalse(false);
        assertEquals("400 BAD_REQUESTUserId doesn't exist with this id " + userId, exception.getMessage());
    }

    private User user() {
        User user = new User();
        user.setUserId(1);
        user.setUsername("test");
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("test@gmail.com");
        user.setPassword("test@123");
        return user;
    }

    private UserDTO userDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1);
        userDTO.setUsername("test");
        userDTO.setFirstName("test");
        userDTO.setLastName("test");
        userDTO.setEmail("test@gmail.com");
        userDTO.setPassword("test@123");
        return userDTO;
    }
}
