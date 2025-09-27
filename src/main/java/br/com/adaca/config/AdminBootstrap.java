package br.com.adaca.config;

import br.com.adaca.model.Administrador;
import br.com.adaca.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminBootstrap {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public ApplicationRunner initializeAdmin(AdministradorRepository administradorRepository) {
        return args -> {
            if (administradorRepository.count() == 0) {
                Administrador admin = new Administrador();
                admin.setNome("Administrador Inicial");
                admin.setUsuario("admin");
                admin.setSenha(passwordEncoder.encode("admin123")); // Troque a senha após o primeiro login!
                admin.setNivelacesso(1); // Ajuste conforme sua lógica de níveis
                administradorRepository.save(admin);
                System.out.println("Administrador padrão criado: usuário=admin, senha=admin123");
            }
        };
    }
}
