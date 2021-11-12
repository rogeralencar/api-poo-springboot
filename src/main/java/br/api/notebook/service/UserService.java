package br.api.notebook.service;

import br.api.notebook.dto.UserDTO;
import br.api.notebook.model.UserEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    UserEntity saveUser(UserEntity userEntity) throws IOException;
    List<UserDTO> getUsers();
    UserEntity getUserById(Long id);
    UserEntity updateUser(UserEntity user);
    void deleteUser(Long id);
    UserEntity findByEmail();
    String getUserByToken();
    UserDTO convertEntityToDTO(UserEntity userEntity);
}
