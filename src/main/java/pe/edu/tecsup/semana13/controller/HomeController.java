package pe.edu.tecsup.semana13.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * Este método mapea la ruta raíz de la aplicación ("/").
     * Cuando un usuario navega a http://localhost:8080/, este método se ejecuta.
     * @return El nombre de la vista a renderizar, en este caso "index.html".
     */
    @GetMapping("/")
    public String showHomePage() {
        return "index"; // Esto le dice a Thymeleaf que busque y muestre /templates/index.html
    }
}