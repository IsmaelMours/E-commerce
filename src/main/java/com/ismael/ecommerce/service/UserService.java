package com.ismael.ecommerce.service;

import com.ismael.ecommerce.dto.UserDTO;

public interface UserService {
    UserDTO addUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
    void deleteUserById(Long id);
    UserDTO updateUser(Long id, UserDTO userDTO);
}
