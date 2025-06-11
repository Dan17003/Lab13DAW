package pe.edu.tecsup.semana13.controller;

import pe.edu.tecsup.semana13.entity.Curso;
import pe.edu.tecsup.semana13.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public String crearCurso(@RequestBody Curso curso) {
        cursoRepository.save(curso);
        return "Curso guardado exitosamente";
    }

    @GetMapping
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Curso obtenerCurso(@PathVariable Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public String actualizarCurso(@PathVariable Long id,
                                  @RequestBody Curso cursoActualizado) {
        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isPresent()) {
            Curso c = curso.get();
            c.setNombre(cursoActualizado.getNombre());
            cursoRepository.save(c);
            return "Curso actualizado exitosamente";
        } else {
            return "Curso no encontrado";
        }
    }

    @DeleteMapping("/{id}")
    public String eliminarCurso(@PathVariable Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return "Curso eliminado exitosamente";
        } else {
            return "Curso no encontrado";
        }
    }
}
