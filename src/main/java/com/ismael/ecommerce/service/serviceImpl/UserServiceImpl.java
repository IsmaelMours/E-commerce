package com.ismael.ecommerce.service.serviceImpl;

import com.ismael.ecommerce.dto.UserDTO;
import com.ismael.ecommerce.model.User;
import com.ismael.ecommerce.repository.UserRepository;
import com.ismael.ecommerce.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDTO addUser(UserDTO userDTO) {
        var user = new User();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());

        var saveUser = userRepository.save(user);

        return new UserDTO(saveUser.getFirstName(), saveUser.getLastName(), saveUser.getEmail());
    }

    @Override
    public UserDTO getUserById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User not found with id: " + id));
        return new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail());
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()){
            throw new EntityNotFoundException("User not found with id: " + id);
        }

        User existingUser = optionalUser.get();

        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setEmail(userDTO.getEmail());
        User upadtedUser = userRepository.save(existingUser);

        return new UserDTO(upadtedUser.getFirstName(), upadtedUser.getLastName(), upadtedUser.getEmail());
    }
}
