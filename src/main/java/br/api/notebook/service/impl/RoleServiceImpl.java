package br.api.notebook.service.impl;

import br.api.notebook.model.RoleEntity;
import br.api.notebook.model.UserEntity;
import br.api.notebook.repository.RoleRepository;
import br.api.notebook.repository.UserRepository;
import br.api.notebook.service.RoleService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepo;
    private final UserRepository userRepo;

    public RoleServiceImpl(RoleRepository roleRepo, UserRepository userRepo) {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
    }

    @Override
    public RoleEntity saveRole(RoleEntity roleEntity) {
        return roleRepo.save(roleEntity);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        UserEntity userEntity = userRepo.findByEmail(email);
        userEntity.getRoles().removeAll(userEntity.getRoles());
        RoleEntity roleEntity = roleRepo.findByName(roleName);
        userEntity.getRoles().add(roleEntity);
    }
}
