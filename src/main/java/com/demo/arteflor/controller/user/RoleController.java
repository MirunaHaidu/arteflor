package com.demo.arteflor.controller.user;

import com.demo.arteflor.dto.user.UserDto;
import com.demo.arteflor.model.user.Role;
import com.demo.arteflor.repository.user.RoleRepository;
import com.demo.arteflor.service.user.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
@ControllerAdvice
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping("/role/getRoles")
    public ResponseEntity<List<Role>> getAllRoles(){
        return ResponseEntity.ok(roleService.getRoles());
    }
}
