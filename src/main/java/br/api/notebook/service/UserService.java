package br.api.notebook.service;

import br.api.notebook.dto.UserDTO;
import br.api.notebook.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity saveUser(UserEntity userEntity);
    List<UserDTO> getUsers();
    Optional<UserEntity> getUserById(Long id);
    UserEntity updateUser(UserEntity user);
    void deleteUser(Long id);
}
