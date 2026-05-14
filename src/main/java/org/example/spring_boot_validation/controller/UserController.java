package org.example.spring_boot_validation.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.example.spring_boot_validation.entity.User;
import org.example.spring_boot_validation.service.UserService;
import org.example.spring_boot_validation.validation_group.Create;
import org.example.spring_boot_validation.validation_group.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User createUser(@Validated({Create.class, Update.class}) @RequestBody User user) {
        return service.saveUser(user);
    }

    // FETCH ALL
    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    // FETCH BY ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable Long id,
            @Valid @RequestBody User user) {

        return service.updateUser(id, user);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {

        service.deleteUser(id);

        return "User deleted successfully";
    }


}