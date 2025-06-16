package pe.edu.tecsup.semana13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.tecsup.semana13.entity.Curso;
import pe.edu.tecsup.semana13.repository.CursoRepository;

@Controller
@RequestMapping("/cursos") // Ruta base para el módulo de cursos
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    // Muestra la lista de todos los cursos
    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("cursos", cursoRepository.findAll());
        return "Curso/lista"; // Apunta a templates/Curso/lista.html
    }

    // Muestra el formulario para crear un nuevo curso
    @GetMapping("/nuevo")
    public String mostrarFormularioDeNuevoCurso(Model model) {
        model.addAttribute("curso", new Curso());
        return "Curso/form"; // Apunta a templates/Curso/form.html
    }

    // Guarda un curso (sea nuevo o una actualización)
    @PostMapping("/guardar")
    public String guardarCurso(@ModelAttribute Curso curso) {
        cursoRepository.save(curso);
        return "redirect:/cursos"; // Redirige a la lista de cursos
    }

    // Muestra el formulario para editar un curso existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model model) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de Curso no válido:" + id));
        model.addAttribute("curso", curso);
        return "Curso/form"; // Reutiliza el mismo formulario
    }

    // Elimina un curso por su ID
    @GetMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable Long id) {
        cursoRepository.deleteById(id);
        return "redirect:/cursos";
    }
}