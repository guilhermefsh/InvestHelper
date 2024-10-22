package projectfsh.investhelper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectfsh.investhelper.dtos.CreateUserDTO;
import projectfsh.investhelper.dtos.UpdateUserDto;
import projectfsh.investhelper.entity.User;
import projectfsh.investhelper.service.UserService;

import java.net.URI;
import java.util.List;

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
        var user = userService.getUserById(userId);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> listAllUsers() {
        var users = userService.listAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId,
                                               @RequestBody UpdateUserDto updateUserDTO) {
        userService.updateUserById(userId, updateUserDTO);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
