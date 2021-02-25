package com.newcreation.jira.user;

import com.newcreation.jira.common.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO){
        if(userRepository.findById(userDTO.getEmailId()).isPresent())
            return new ResponseEntity<>("User already exist" , HttpStatus.BAD_REQUEST);
        userService.createUser(userDTO);
        return ResponseEntity.ok("User added succesfully");
    }

    @GetMapping
    public String def(){
        return "test";
    }

    @GetMapping("/api/account")
    public Map<String, Object> test(){
        System.out.println(SecurityUtils.getCurrentLoggedIn());
        return Map.of("user", new UserDTO(userRepository.findById(SecurityUtils.getCurrentLoggedIn()).get()));
//        return "TEST";
    }
    @GetMapping("/api/users/all")
    public List<UserDTO> all(){
        return  userService.getAll(SecurityUtils.getCurrentLoggedIn());
    }
}
