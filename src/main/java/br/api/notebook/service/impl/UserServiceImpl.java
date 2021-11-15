package br.api.notebook.service.impl;

import br.api.notebook.dto.UserDTO;
import br.api.notebook.model.UserEntity;
import br.api.notebook.repository.UserRepository;
import br.api.notebook.service.RoleService;
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
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final HttpServletRequest request;


    public UserServiceImpl(UserRepository userRepo, RoleService roleService, PasswordEncoder passwordEncoder,
                           HttpServletRequest request) {
        this.userRepo = userRepo;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.request = request;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByEmail(email);
        if(userEntity == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userEntity.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }

    @Override
    public List<UserDTO> getUsers() {
        return userRepo.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserDto(UserEntity userEntity){
        return convertEntityToDTO(userEntity);
    }

    @Override
    public UserEntity findByEmail() {
        return userRepo.findByEmail(getUserByToken());
    }

    @Override
    public Optional<UserEntity> getById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepo.save(userEntity);
        roleService.addRoleToUser(userEntity.getEmail(), "ROLE_USER");
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        UserEntity user = findByEmail();
        if(!(userEntity.getName() == null)){
            user.setName(userEntity.getName());
        } else if (!(userEntity.getEmail() == null)){
            user.setEmail(userEntity.getEmail());
        } else if (!(userEntity.getPassword() == null)){
            user.setPassword(userEntity.getPassword());
        } else if (!(userEntity.getPaymentMethod() == null)){
            user.setPaymentMethod(userEntity.getPaymentMethod());
        } else if (!(userEntity.getAddress() == null)){
            user.setAddress(userEntity.getAddress());
        } else if (!(userEntity.getAge() == 0)){
            user.setAge(userEntity.getAge());
        } else if (!(userEntity.getCpf() == null)){
            user.setCpf(userEntity.getCpf());
        }
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    private String getUserByToken(){
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    private UserDTO convertEntityToDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setName(userEntity.getName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setAge(userEntity.getAge());
        userDTO.setCpf(userEntity.getCpf());
        return userDTO;
    }
}
