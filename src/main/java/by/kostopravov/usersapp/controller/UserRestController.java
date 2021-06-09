package by.kostopravov.usersapp.controller;

import by.kostopravov.usersapp.model.UserAccount;
import by.kostopravov.usersapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<UserAccount> getAllUserAccounts() {
        return userService.getAllUserAccounts();
    }

    @GetMapping(path = "/user/{id}")
    public UserAccount getEmployeeById(@PathVariable Long id) {
        return userService.getUserAccountById(id);
    }

}

