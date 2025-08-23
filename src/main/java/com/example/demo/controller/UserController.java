package com.example.demo.controller;



import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user_list";
    }

    @GetMapping("/details")
    public String getDetails(Model model, @RequestParam("id") long id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user_details";
    }

    @GetMapping("/edit_role")
    public String editRole(Model model, @RequestParam("id") long id) {
        User user = userService.findById(id);
        List<Role> roles = roleService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "edit_user_role";
    }

    @PostMapping("/edit_role")
    public String updateRole(@RequestParam("userId") long userId, @RequestParam("roleId") long roleId) {
        Role role = roleService.findById(roleId);
        User user = userService.findById(userId);
        user.setRole(role);
        userService.updateUser(user);
        return "redirect:/user/details?id=" + user.getId();
    }

}
