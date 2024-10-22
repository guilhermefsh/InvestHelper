package projectfsh.investhelper.service;

import org.springframework.stereotype.Service;
import projectfsh.investhelper.controller.CreateUserDTO;
import projectfsh.investhelper.entity.User;
import projectfsh.investhelper.repository.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDTO createUserDTO) {

        var entity = new User(
                UUID.randomUUID(),
                createUserDTO.username(),
                createUserDTO.email(),
                createUserDTO.password(),
                Instant.now(),
                null
        );

        var userSaved = userRepository.save(entity);
        return userSaved.getUserId();
    }

    public Optional <User> getUserById(String userId) {
        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> listAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(String userId) {
        var id = UUID.fromString(userId);
        var userExists = userRepository.existsById(UUID.fromString(userId));

        if(userExists) {
            userRepository.deleteById(id);
        }
    }
}
