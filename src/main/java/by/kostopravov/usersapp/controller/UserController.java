package by.kostopravov.usersapp.controller;

import by.kostopravov.usersapp.model.UserAccount;
import by.kostopravov.usersapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PreAuthorize("hasAuthority('canWrite')")
    @GetMapping("/user/new")
    public String getPageForCreateNewUserAccount(@ModelAttribute("newUser") UserAccount userAccount) {
        return "new";
    }

    @PreAuthorize("hasAuthority('canWrite')")
    @PostMapping("/createuser")
    public String createNewUser(@ModelAttribute("newUser") @Valid UserAccount userAccount, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "new";
        UserAccount newUserAccount = new UserAccount(userAccount.getUserName(),
                userAccount.getFirstName(),
                userAccount.getLastName(),
                userAccount.getRole(),
                userAccount.getStatus(),
                userAccount.getPasswordForValid());
        try {
            userService.saveUserAccount(newUserAccount);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            return "new";
        }
        return "redirect:/user";
    }

    @PreAuthorize("hasAuthority('canWrite')")
    @GetMapping("/user/{id}/edit")
    public String getPageForEditUserAccount(@PathVariable Long id, Model model) {
        UserAccount userForEdit = userService.editUserAccount(id);
        model.addAttribute("editedUser", userForEdit);
        return "edit";
    }

    @PreAuthorize("hasAuthority('canWrite')")
    @PostMapping("/editeuser")
    public String editeUser(@ModelAttribute("editedUser") @Valid UserAccount editedUserAccount,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "edit";
        UserAccount editedNewUserAccount = new UserAccount(editedUserAccount.getId(),
                editedUserAccount.getUserName(),
                editedUserAccount.getFirstName(),
                editedUserAccount.getLastName(),
                editedUserAccount.getRole(),
                editedUserAccount.getStatus(),
                editedUserAccount.getCreatedAt(),
                editedUserAccount.getPasswordForValid());
        try {
        userService.saveUserAccount(editedNewUserAccount);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            return "edit";
        }
        return "redirect:/user";
    }
}
