package br.api.notebook.service;

import br.api.notebook.dto.UserDTO;
import br.api.notebook.model.UserEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(UserEntity userEntity) throws IOException;
    List<UserDTO> getUsers();
    UserEntity findByEmail();
    Optional<UserEntity> getById(Long id);
    UserEntity updateUser(UserEntity user);
    UserDTO getUserDto(UserEntity userEntity);
    void deleteUser(Long id);
}
