package br.com.adaca.repository;

import br.com.adaca.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface TutorRepository extends JpaRepository<Tutor, Integer> {
    Tutor findByUsuario(@Param("usuario") String usuario);
}
