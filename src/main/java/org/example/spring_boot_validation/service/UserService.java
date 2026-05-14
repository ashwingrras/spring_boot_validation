package org.example.spring_boot_validation.service;


import jakarta.validation.Valid;
import org.example.spring_boot_validation.entity.User;
import org.example.spring_boot_validation.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(@Valid UserRepository repository) {
        this.repository = repository;
    }

    public User saveUser(User user) {
        return repository.save(user);
    }

    // FETCH ALL
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    // FETCH BY ID
    public User getUserById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + id));
    }

    // UPDATE
    public User updateUser(Long id, User updatedUser) {

        User existingUser = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + id));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setMobile(updatedUser.getMobile());
        existingUser.setAge(updatedUser.getAge());
        existingUser.setSalary(updatedUser.getSalary());
        existingUser.setBirthDate(updatedUser.getBirthDate());
        existingUser.setJoiningDate(updatedUser.getJoiningDate());
        existingUser.setTermsAccepted(updatedUser.isTermsAccepted());

        return repository.save(existingUser);
    }

    // DELETE
    public void deleteUser(Long id) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + id));

        repository.delete(user);
    }

}
