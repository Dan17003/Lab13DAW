package pe.edu.tecsup.semana13.controller;
import pe.edu.tecsup.semana13.entity.Producto;
import pe.edu.tecsup.semana13.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository repo;

    @PostMapping
    public Producto crear(@RequestBody Producto p) {
        return repo.save(p);
    }

    @GetMapping
    public List<Producto> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtener(@PathVariable int id) {
        return repo.findById(id)
                .map(p -> ResponseEntity.ok(p))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable int id, @RequestBody Producto p) {
        return repo.findById(id)
                .map(prod -> {
                    prod.setNombre(p.getNombre());
                    prod.setPrecio(p.getPrecio());
                    return ResponseEntity.ok(repo.save(prod));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        try {
            return repo.findById(id)
                    .map(p -> {
                        repo.deleteById(id);
                        return ResponseEntity.ok().build(); // devuelve 200 OK vacío
                    })
                    .orElse(ResponseEntity.notFound().build()); // devuelve 404
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar: " + e.getMessage()); // este es un String
        }
    }
}