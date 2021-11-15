package br.api.notebook;

import br.api.notebook.model.NotebookEntity;
import br.api.notebook.model.RoleEntity;
import br.api.notebook.model.UserEntity;
import br.api.notebook.service.NotebookService;
import br.api.notebook.service.RoleService;
import br.api.notebook.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class NotebookApplication  {

    public static void main(String[] args) {
        SpringApplication.run(NotebookApplication .class, args);
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService, RoleService roleService, NotebookService notebookService) {
        return args -> {
            roleService.saveRole(new RoleEntity(null, "ROLE_ADMIN"));
            roleService.saveRole(new RoleEntity(null, "ROLE_USER"));

            userService.saveUser(new UserEntity("Administrator", "adm@gmail.com", "adminA1@", 18, "15464454641", new ArrayList<>(), null));

            roleService.addRoleToUser("adm@gmail.com", "ROLE_ADMIN");

            notebookService.saveNote(new NotebookEntity("Acer", "Aspire Nitro 5", "Notebook apropriado para jogos e edições de vídeos e imagens!",
                    "8 GB", "1 TB", "Intel i7-11850HQ", 15.7f, 6.599f));

            notebookService.saveNote(new NotebookEntity("Asus", "ROG X5", "Notebook robusto para jogos e edições de vídeos.",
                    "8 GB", "1 TB", "Intel i7-11850HQ", 15.7f, 9.599f));

            notebookService.saveNote(new NotebookEntity("Apple", "Macbook Pro", "Indicado para profissionais.",
                    "8 GB", "1 TB", "M1 Max", 15.0f, 21.599f));
        };
    }
}
