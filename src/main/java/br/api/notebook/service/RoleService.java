package br.api.notebook.service;

import br.api.notebook.model.RoleEntity;

public interface RoleService {
    RoleEntity saveRole(RoleEntity roleEntity);
    void addRoleToUser(String email, String roleName);
}
