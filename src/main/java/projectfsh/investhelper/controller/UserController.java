package projectfsh.investhelper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectfsh.investhelper.entity.User;
import projectfsh.investhelper.service.UserService;

import java.net.URI;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDTO createUserDTO) {
        var userId = userService.createUser(createUserDTO);
        return ResponseEntity.created(URI.create("/api/users/" + userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        return null;
    }
}
