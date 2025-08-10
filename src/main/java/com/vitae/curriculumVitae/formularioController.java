package com.vitae.curriculumVitae;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

//@RequestMapping("/api") este annotation es para definir un prefijo comun en todos los endpoints ejemplo: localhost:8080/api/formulario
public class formularioController {
    
    //Endpopint

    @GetMapping("/formulario")
    public String formulario() {
        return "Formulario de Currículo Vitae";
    }

}
