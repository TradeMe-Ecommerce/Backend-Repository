package com.example.demo.controller;


import com.example.demo.entity.Permission;
import com.example.demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public String getAllPermissions(Model model) {
        List<Permission> permissions = permissionService.findAll();
        model.addAttribute("permissions", permissions);
        return "permission_list";
    }

    @GetMapping("/add")
    public String createPermissionForm(Model model) {
        model.addAttribute("permission", new Permission());
        return "create_permission";
    }

    @PostMapping
    public String createPermission(@ModelAttribute("permission") Permission permission) {
        try {
            permissionService.createPermission(permission);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return "redirect:/permission";
    }


}
