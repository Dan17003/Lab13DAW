package pe.edu.tecsup.semana13.controller;

import pe.edu.tecsup.semana13.entity.Estudiante;
import pe.edu.tecsup.semana13.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @PostMapping
    public String crearEstudiante(@RequestBody Estudiante estudiante) {
        estudianteRepository.save(estudiante);
        return "Estudiante guardado exitosamente";
    }

    @GetMapping
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Estudiante obtenerEstudiante(@PathVariable Long id) {
        return estudianteRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public String actualizarEstudiante(@PathVariable Long id,
                                       @RequestBody Estudiante estudianteActualizado) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(id);
        if (estudiante.isPresent()) {
            Estudiante e = estudiante.get();
            e.setNombre(estudianteActualizado.getNombre());
            e.setCursos(estudianteActualizado.getCursos());
            estudianteRepository.save(e);
            return "Estudiante actualizado exitosamente";
        } else {
            return "Estudiante no encontrado";
        }
    }

    @DeleteMapping("/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {
        if (estudianteRepository.existsById(id)) {
            estudianteRepository.deleteById(id);
            return "Estudiante eliminado exitosamente";
        } else {
            return "Estudiante no encontrado";
        }
    }
}
