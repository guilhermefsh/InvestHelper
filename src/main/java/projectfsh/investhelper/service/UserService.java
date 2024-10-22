package projectfsh.investhelper.service;

import org.springframework.stereotype.Service;
import projectfsh.investhelper.controller.CreateUserDTO;
import projectfsh.investhelper.entity.User;
import projectfsh.investhelper.repository.UserRepository;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDTO createUserDTO) {

        var entity = new User(
                Instant.now(),
                createUserDTO.username(),
                createUserDTO.email(),
                Instant.now(),
                UUID.randomUUID(),
                createUserDTO.password()
        );

        var userSaved = userRepository.save(entity);
        return userSaved.getUserId();
    }
}
