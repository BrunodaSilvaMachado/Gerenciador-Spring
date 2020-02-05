package br.com.adaca.service;

import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;
import br.com.adaca.model.Administrador;
import br.com.adaca.model.Role;
import br.com.adaca.repository.AdministradorRepository;
import br.com.adaca.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdministradorService implements UserDetailsService {

    @Autowired
    private AdministradorRepository administradorRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Lista todos os administradores cadastrados no banco de dados
     *
     * @return Lista com todos os administradores cadastrados
     */
    public List<Administrador> listar() {
        List<Administrador> administradores = new ArrayList<>();
        for (Administrador administrador : administradorRepository.findAll()) {
            administradores.add(administrador);
        }
        if (administradores.isEmpty()) throw new NotFoundException("Nenhum administrador encontrado!");
        return (administradores);
    }

    /**
     * Efetua uma busca por ID de administrador cadastrado
     *
     * @param id ID de administrador já existente no banco de dados
     * @return Objeto do administrador encontrado
     */
    public Administrador selecionar(Integer id) {
        Optional<Administrador> administrador = administradorRepository.findById(id);
        if (!administrador.isPresent()) throw new NotFoundException("Administrador não encontrado! Id: " + id);
        return (administrador.get());
    }

    /**
     * Salva o cadastro do administrador no banco de dados
     *
     * @param administrador Objeto preenchido do cadastro a ser gravado
     * @return Objeto do administador salvo
     */
    public Administrador salvar(Administrador administrador) {
        if (administrador.getId() != null) {
            Optional<Administrador> op = administradorRepository.findById(administrador.getId());
            if (op.isPresent()) throw new ConflictException("O administrador já existe!");
        }
        administrador.setSenha(passwordEncoder.encode(administrador.getSenha()));
        Role userRole = roleRepository.findByRole("ADMIN");
        administrador.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        return (administradorRepository.save((administrador)));
    }

    /**
     * Altera o cadastro do administrador no bando de dados
     *
     * @param administrador Objeto preenchido com os dados já alterados
     * @return Objeto do administrador alterado
     */
    public Administrador alterar(Administrador administrador) {
        Administrador adm = null;
        if (administrador.getId() != null) {
            administrador.setSenha(passwordEncoder.encode(administrador.getSenha()));
            adm = (administradorRepository.save((administrador)));
        }
        return adm;
    }


    /**
     * Efetua uma busca por ID de administrador cadastrado e remove-o do banco
     * de dados
     *
     * @param id ID de administrador já existente no banco de dados
     */
    public void remover(Integer id) {
        Optional<Administrador> administrador = administradorRepository.findById(id);
        if (!administrador.isPresent()) {
            throw new NotFoundException("id: " + id);
        } else {
            administradorRepository.delete(administrador.get());
        }
    }

    /**
     * Remove o cadastro do administrador do banco de dados
     *
     * @param administrador Objeto preenchido do cadastro já existente no banco de dados
     */
    public void remover(Administrador administrador) {
        administradorRepository.delete((administrador));
    }

    public Administrador findByUsuario(String usuario) {
        return (administradorRepository.findByUsuario(usuario));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrador administrador = findByUsuario(username);
        if (administrador == null) {
            throw new UsernameNotFoundException("Usuario ou senha invalidos.");
        }
        return new org.springframework.security.core.userdetails.User(administrador.getUsuario(), administrador.getSenha(),
                mapRolesToAuthorities(administrador.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }
}
