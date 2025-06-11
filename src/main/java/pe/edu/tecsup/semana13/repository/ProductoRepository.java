package pe.edu.tecsup.semana13.repository;

import pe.edu.tecsup.semana13.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer> {

}
