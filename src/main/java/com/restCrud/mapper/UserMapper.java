package com.restCrud.mapper;

import com.restCrud.dbo.User;
import com.restCrud.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author mamta.t
 */
@Mapper(componentModel ="spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);

    User toUserDBO(UserDTO userDTO);

    List<UserDTO> toUsersDTO(List<User> users);
}
