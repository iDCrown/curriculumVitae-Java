package com.vitae.curriculumVitae.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vitae.curriculumVitae.model.Curriculum;
import com.vitae.curriculumVitae.model.Experiencia;
import com.vitae.curriculumVitae.model.Perfil;
import com.vitae.curriculumVitae.repository.CurriculumRepository;
import com.vitae.curriculumVitae.repository.PerfilRepository;

import jakarta.websocket.server.PathParam;

import com.vitae.curriculumVitae.repository.ExperienciaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api") //este annotation es para definir un prefijo comun en todos los endpoints ejemplo: localhost:8080/api/formulario
public class formularioController {

    @Autowired
    private CurriculumRepository curriculumRepository;
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private ExperienciaRepository experienciaRepository;
    
    private static final String[] PAISES = {
        "Colombia", "México", "España", "Argentina", "Chile", "Perú", 
        "Venezuela", "Ecuador", "Bolivia", "Uruguay", "Paraguay",
        "Estados Unidos", "Canadá", "Brasil", "Francia", "Alemania",
        "Reino Unido", "Italia", "Países Bajos", "Suecia"
    };
    
    private static final String[] IDIOMAS = {
        "Español","Inglés","Portugués","Francés","Alemán","Italiano","Mandarín","Árabe"
    };
    
    //Endpopint

    @GetMapping("/landingPage")
    public String mostrarLandingPage() {
        return "landingPage"; // busca landingPage.html en /templates
    }

    @GetMapping("/plantillaCreativa/{id}")
    public String mostrarPlantillaCreativa(@PathVariable Long id, Model model) {
        Curriculum curriculum = curriculumRepository.findById(id).orElse(null);
        if (curriculum == null) {
            return "redirect:/api/landingPage"; // o una página de error amigable
        }
        model.addAttribute("curriculum", curriculum);
        model.addAttribute("perfil", curriculum.getPerfil());
        model.addAttribute("experiencias", curriculum.getExperiencias());
        
        return "plantillaCreativa"; // busca plantillaCreativa.html en /templates
    }


    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        Curriculum curriculum = new Curriculum();
    
    // INICIALIZAR PERFIL
    Perfil perfil = new Perfil();
    curriculum.setPerfil(perfil);
    
    // INICIALIZAR LISTA DE EXPERIENCIAS
    List<Experiencia> experiencias = new ArrayList<>();
    // Agregar experiencias vacías (máximo 3)
    experiencias.add(new Experiencia());
    experiencias.add(new Experiencia());
    experiencias.add(new Experiencia());
    curriculum.setExperiencias(experiencias);
    
    model.addAttribute("curriculum", curriculum);
    model.addAttribute("paises", PAISES);
    model.addAttribute("idiomas", IDIOMAS);
    return "formulario";
    }
    
    @PostMapping("/create-cv")
    public String creatCV(@ModelAttribute Curriculum curriculum, BindingResult bindingResult, Model model){
        // creamos un objeto de tipo Curriculum para guardar los datos cuardados y poder obtener el id
        Curriculum saved = null;
        if (bindingResult.hasErrors()) {
        model.addAttribute("paises", PAISES);
        model.addAttribute("idiomas", IDIOMAS);
        bindingResult.getAllErrors().forEach(e -> System.out.println("Binding error: " + e));
        return "formulario";
    }

    try {
        // asignar parent en experiencias si es necesario
        if (curriculum.getExperiencias() != null) {
            for (Experiencia e : curriculum.getExperiencias()) {
                if (e != null) e.setCurriculum(curriculum);
            }
        }
        saved = curriculumRepository.save(curriculum);
    } catch (Exception ex) {
        ex.printStackTrace(); // <<-- mira el stacktrace en consola: 'Caused by:' es la clave
        model.addAttribute("errorMessage", "Error al guardar CV: " + ex.getMessage());
        model.addAttribute("paises", PAISES);
        model.addAttribute("idiomas", IDIOMAS);
        return "formulario";
    }

    return "redirect:/api/plantillaCreativa/" + saved.getId();
}


}
