package com.floralstack.floralstackbackend.user;

import com.floralstack.floralstackbackend.plant.Plant;
import com.floralstack.floralstackbackend.plant.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void createUser(@RequestBody @Valid User user){
        userService.createUser(user);
    }

    @GetMapping(path = "{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return userService.getUser(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping
    public void updateUser(@RequestBody @Valid User user){
        userService.updateUser(user);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable("id") Integer id) { userService.deleteUser(id); }
}
