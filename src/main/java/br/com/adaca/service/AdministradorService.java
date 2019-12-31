package br.com.adaca.service;

import br.com.adaca.dto.AdministradorDTO;
import br.com.adaca.exception.ConflictException;
import br.com.adaca.exception.NotFoundException;
import br.com.adaca.mapper.AdministradorMapper;
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
    private AdministradorMapper administradorMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Lista todos os administradores cadastrados no banco de dados
     *
     * @return Lista com todos os administradores cadastrados
     */
    public List<AdministradorDTO> listar() {
        List<Administrador> administradores = new ArrayList<>();
        for (Administrador administrador : administradorRepository.findAll()) {
            administradores.add(administrador);
        }
        if (administradores.isEmpty()) throw new NotFoundException("Nenhum administrador encontrado!");
        return administradorMapper.toDto(administradores);
    }

    /**
     * Efetua uma busca por ID de administrador cadastrado
     *
     * @param id ID de administrador já existente no banco de dados
     * @return Objeto do administrador encontrado
     */
    public AdministradorDTO selecionar(Integer id) {
        Optional<Administrador> administrador = administradorRepository.findById(id);
        if (administrador.isEmpty()) throw new NotFoundException("Administrador não encontrado! Id: " + id);
        return administradorMapper.toDto(administrador.get());
    }

    /**
     * Salva o cadastro do administrador no banco de dados
     *
     * @param administrador Objeto preenchido do cadastro a ser gravado
     * @return Objeto do administador salvo
     */
    public AdministradorDTO salvar(AdministradorDTO administrador) {
        if (administrador.getId() != null) {
            Optional<Administrador> op = administradorRepository.findById(administrador.getId());
            if (op.isPresent()) throw new ConflictException("O administrador já existe!");
        }
        administrador.setSenha(passwordEncoder.encode(administrador.getSenha()));
        Role userRole = roleRepository.findByRole("ADMIN");
        administrador.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        return administradorMapper.toDto(administradorRepository.save(administradorMapper.toEntity(administrador)));
    }

    /**
     * Altera o cadastro do administrador no bando de dados
     *
     * @param administrador Objeto preenchido com os dados já alterados
     * @return Objeto do administrador alterado
     */
    public AdministradorDTO alterar(AdministradorDTO administrador) {
        AdministradorDTO adm = null;
        if (administrador.getId() != null) {
            administrador.setSenha(passwordEncoder.encode(administrador.getSenha()));
            adm = administradorMapper.toDto(administradorRepository.save(administradorMapper.toEntity(administrador)));
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
        if (administrador.isEmpty()) {
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
    public void remover(AdministradorDTO administrador) {
        administradorRepository.delete(administradorMapper.toEntity(administrador));
    }

    public AdministradorDTO findByUsuario(String usuario) {
        return administradorMapper.toDto(administradorRepository.findByUsuario(usuario));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrador administrador = administradorRepository.findByUsuario(username);
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
