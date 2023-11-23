package com.Login.Page.firstApp.controller;

import com.Login.Page.firstApp.model.dto.UserDto;
import com.Login.Page.firstApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/app")
public class HomeController {
    @Autowired
    private UserService userService;

    private String loggedInEmail; // To store the email from the login page
    private String loggedInPassword; // To store the password from the login page

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        loggedInEmail = email; // Store the email for later use
        loggedInPassword = password; // Store the password for later use

        model.addAttribute("email", email);
        model.addAttribute("password", password);
        return "redirect:/app/home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("userDto", new UserDto());
        return "home";
    }

    @PostMapping("/users/saveUserFromLoginPage")
    public String saveUserFromLoginPage(Model model) {
        if (loggedInEmail != null && loggedInPassword != null) {
            // Save the data from the login page to the database
            UserDto userDto = new UserDto();
            userDto.setEmail(loggedInEmail);
            userDto.setPassword(loggedInPassword);

            userService.saveUser(userDto);
        }

        // Clear the stored email and password
        loggedInEmail = null;
        loggedInPassword = null;

        // Redirect back to the login page
        return "redirect:/app/home";
    }

}
