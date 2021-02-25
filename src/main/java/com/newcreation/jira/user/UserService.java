package com.newcreation.jira.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setEmailId(userDTO.getEmailId());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRoles(userDTO.getRoles());
        userRepository.save(user);
        return user;
    }

    public List<UserDTO> getAll(String email) {
        return userRepository.findAll().stream()
                .filter(e->!e.getEmailId().equals(email))
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }
}
