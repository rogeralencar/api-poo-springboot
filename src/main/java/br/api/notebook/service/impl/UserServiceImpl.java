package br.api.notebook.service.impl;

import br.api.notebook.dto.UserDTO;
import br.api.notebook.model.RoleEntity;
import br.api.notebook.model.UserEntity;
import br.api.notebook.repository.RoleRepository;
import br.api.notebook.repository.UserRepository;
import br.api.notebook.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByEmail(email);
        if(userEntity == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        } else {
            System.out.println("Usuário encontrado");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userEntity.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }

    private UserDTO convertEntityToDto(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setName(userEntity.getName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setAge(userEntity.getAge());
        userDTO.setCpf(userEntity.getCpf());
        userDTO.setRoles(userEntity.getRoles());
        return userDTO;
    }

    @Override
    public List<UserDTO> getUsers() {
        return userRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        Collection<RoleEntity> role = new ArrayList<>();
        RoleEntity roleEntity = roleRepo.findByName("ROLE_USER");
        role.add(roleEntity);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(role);
        return userRepo.save(userEntity);
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        return userRepo.save(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }


}
