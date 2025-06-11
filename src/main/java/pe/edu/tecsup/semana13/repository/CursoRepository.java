package pe.edu.tecsup.semana13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.tecsup.semana13.entity.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
