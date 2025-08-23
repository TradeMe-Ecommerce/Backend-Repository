package com.example.demo.controller;


import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;

import com.example.demo.service.PermissionService;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public String role(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "role_list";
    }

    @PostMapping
    public String createRole(@ModelAttribute("role") Role role, @RequestParam("permissions") List<Long> permissions) {
        try {
            role.setPermissions(permissionService.findByIds(permissions));
            roleService.createRole(role);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return "redirect:/role";
    }

    @GetMapping("/add")
    public String addRole(Model model) {
        model.addAttribute("role", new Role());
        model.addAttribute("permissions", permissionService.findAll());
        return "create_role";
    }

    @GetMapping("/details")
    public String getDetails(Model model, @RequestParam("id") long id) {
        Role role = roleService.findById(id);
        List<Permission> permissions = permissionService.findAll();
        model.addAttribute("role", role);
        model.addAttribute("permissions", permissions);
        return "role_details";
    }

    @PostMapping("/details")
    public String updateRol(@RequestParam("roleId") long roleId, @RequestParam("permissions") List<Long> permissions) {
        try {
            Role role = roleService.findById(roleId);
            role.setPermissions(permissionService.findByIds(permissions));
            roleService.updateRole(role);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return "redirect:/role";
    }

    @PostMapping("/delete")
    public String deleteRole(@RequestParam("id") long id) {
        try {
            roleService.deleteRole(id);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return "redirect:/role";
    }

}
