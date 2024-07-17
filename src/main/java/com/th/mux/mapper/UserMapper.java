package com.th.mux.mapper;

import com.th.mux.dto.UserDto;
import com.th.mux.model.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        userDto.setDepartmentId(user.getDepartment().getId());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
