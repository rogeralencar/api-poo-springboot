package br.api.notebook.service;

import br.api.notebook.model.RoleEntity;
import org.springframework.stereotype.Service;

public interface RoleService {
    RoleEntity saveRole(RoleEntity roleEntity);
    void addRoleToUser(String email, String roleName);
}
