package pe.edu.tecsup.semana13.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.tecsup.semana13.entity.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

}