package br.api.notebook.controller;

import br.api.notebook.model.RoleEntity;
import br.api.notebook.model.RoleToUser;
import br.api.notebook.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/role/save")
    public ResponseEntity<String> saveRole(@RequestBody RoleEntity roleEntity){
        roleService.saveRole(roleEntity);
        return ResponseEntity.ok().body("Cargo adicionado com sucesso");
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/role/addtouser")
    public ResponseEntity<String> addRoleToUser(@RequestBody RoleToUser role){
        roleService.addRoleToUser(role.getEmail(), role.getRoleName());
        return ResponseEntity.ok().body("Cargo atribuído com sucesso ao usuário: " + role.getEmail());
    }
}
