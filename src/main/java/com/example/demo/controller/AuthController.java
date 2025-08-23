package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.exception.WeakPasswordException;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/sign_up")
    public String singUp(Model model) {
        model.addAttribute("user", new User());
        return "sign_up";
    }

    @PostMapping("/sign_up")
    public String createUser( @ModelAttribute("user") User user,
                             BindingResult binding,
                             RedirectAttributes redirect,
                             Model model) {

        if (binding.hasErrors()) {
            return "sign_up";
        }

        String rawPwd = user.getPassword();
        try {
            userService.createUser(user);

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), rawPwd));
            SecurityContextHolder.getContext().setAuthentication(auth);

            redirect.addFlashAttribute("success", "¡Registro exitoso, bienvenido " + user.getName() + "!");

            return "redirect:/home";

        }
        catch (EmailAlreadyExistsException e) {
            model.addAttribute("error",
                    "Ese correo ya está registrado.");
            return "sign_up";
        }
        catch (WeakPasswordException e) {
            model.addAttribute("error",
                    "La contraseña no cumple los requisitos de seguridad.");
            return "sign_up";
        }
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/home/user")
    public String homeUser() {
        return "home_user";
    }

    @GetMapping("/home/admin")
    public String homeAdmin() {
        return "home_admin";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

}

