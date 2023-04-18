package com.springbootPOC.mapper;

import com.springbootPOC.dbo.User;
import com.springbootPOC.dto.UserDTO;
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
