package br.api.notebook.service.impl;

import br.api.notebook.dto.UserDTO;
import br.api.notebook.model.AddressEntity;
import br.api.notebook.model.RoleEntity;
import br.api.notebook.model.UserEntity;
import br.api.notebook.repository.RoleRepository;
import br.api.notebook.repository.UserRepository;
import br.api.notebook.service.AddressService;
import br.api.notebook.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final AddressService addressService;

    private final PasswordEncoder passwordEncoder;
    private final HttpServletRequest request;

    public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo,
                           AddressService addressService, PasswordEncoder passwordEncoder,
                           HttpServletRequest request) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.addressService = addressService;
        this.passwordEncoder = passwordEncoder;
        this.request = request;
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
    public UserEntity saveUser(UserEntity userEntity) throws IOException {
        Collection<RoleEntity> role = new ArrayList<>();
        RoleEntity roleEntity = roleRepo.findByName("ROLE_USER");
        AddressEntity addressEntity = addressService.saveAddress(userEntity.getCep());
        role.add(roleEntity);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(role);
        userEntity.setAddress(addressEntity);
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

    @Override
    public UserEntity findByEmail() {
        return userRepo.findByEmail(getUserByToken());
    }

    @Override
    public String getUserByToken(){
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }
}
