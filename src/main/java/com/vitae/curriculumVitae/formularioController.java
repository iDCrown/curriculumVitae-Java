package com.vitae.curriculumVitae;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

@Controller

//@RequestMapping("/api") este annotation es para definir un prefijo comun en todos los endpoints ejemplo: localhost:8080/api/formulario
public class formularioController {
    //Endpopint

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("user", new User());
        return "formulario"; // busca formulario.html en /templates
    }

     @GetMapping("/lista")
    public String mostrarLista() {
        return "lista"; 
    }
    

}
