package projectfsh.investhelper.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import projectfsh.investhelper.dtos.AccountResponseDTO;
import projectfsh.investhelper.dtos.CreateAccountDTO;
import projectfsh.investhelper.dtos.CreateUserDTO;
import projectfsh.investhelper.dtos.UpdateUserDto;
import projectfsh.investhelper.entity.Account;
import projectfsh.investhelper.entity.BillingAddress;
import projectfsh.investhelper.entity.User;
import projectfsh.investhelper.repository.AccountRepository;
import projectfsh.investhelper.repository.BillingAddressRepository;
import projectfsh.investhelper.repository.UserRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    private AccountRepository accountRepository;

    private BillingAddressRepository billingAddressRepository;

    public UserService(UserRepository userRepository, AccountRepository accountRepository, BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.billingAddressRepository = billingAddressRepository;
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

    public void updateUserById(String userId, UpdateUserDto updateUserDto) {
        var id = UUID.fromString(userId);

        var userEntity = userRepository.findById(id);

        if(userEntity.isPresent()) {
            var user = userEntity.get();

            if(updateUserDto.username()!= null) {
                user.setUsername(updateUserDto.username());
            }
            if(updateUserDto.password()!= null) {
                user.setPassword(updateUserDto.password());
            }

            userRepository.save(user);
        }
    }

    public void createAccount(String userId, CreateAccountDTO createAccountDTO) {
        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        var account = new Account(
                UUID.randomUUID(),
                user,
                null,
                createAccountDTO.description(),
                new ArrayList<>()
        );

        var accountCreated = accountRepository.save(account);

        var billingAddress = new BillingAddress(
                accountCreated.getAccountId(),
                accountCreated,
                createAccountDTO.street(),
                createAccountDTO.number()
        );

        billingAddressRepository.save(billingAddress);

    }

    public List<AccountResponseDTO> listAccountsById(String userId) {
        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found accounts"));

        var accounts = user.getAccounts()
                .stream()
                .map(account -> new AccountResponseDTO(
                        account.getAccountId().toString(),
                        account.getDescription()
                )).toList();

        return accounts;
    }
}
