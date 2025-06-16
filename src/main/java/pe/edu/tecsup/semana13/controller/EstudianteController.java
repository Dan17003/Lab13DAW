package pe.edu.tecsup.semana13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.tecsup.semana13.entity.Estudiante;
import pe.edu.tecsup.semana13.repository.CursoRepository;
import pe.edu.tecsup.semana13.repository.EstudianteRepository;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CursoRepository cursoRepository; // Necesitamos esto para listar los cursos en el formulario

    // Muestra la lista de todos los estudiantes
    @GetMapping
    public String listarEstudiantes(Model model) {
        model.addAttribute("estudiantes", estudianteRepository.findAll());
        return "Estudiante/lista"; // Apunta a templates/Estudiante/lista.html
    }

    // Muestra el formulario para un nuevo estudiante
    @GetMapping("/nuevo")
    public String mostrarFormularioDeNuevoEstudiante(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        // Pasamos todos los cursos disponibles a la vista para poder seleccionarlos
        model.addAttribute("allCursos", cursoRepository.findAll());
        return "Estudiante/form"; // Apunta a templates/Estudiante/form.html
    }

    // Guarda un estudiante (nuevo o actualizado)
    @PostMapping("/guardar")
    public String guardarEstudiante(@ModelAttribute Estudiante estudiante) {
        estudianteRepository.save(estudiante);
        return "redirect:/estudiantes"; // Redirige a la lista de estudiantes
    }

    // Muestra el formulario para editar un estudiante existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model model) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de Estudiante no válido:" + id));
        model.addAttribute("estudiante", estudiante);
        // Pasamos también la lista de todos los cursos para la edición
        model.addAttribute("allCursos", cursoRepository.findAll());
        return "Estudiante/form";
    }

    // Elimina un estudiante por su ID
    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {
        // Importante: Spring Data JPA maneja la eliminación de la tabla de unión automáticamente
        estudianteRepository.deleteById(id);
        return "redirect:/estudiantes";
    }
}