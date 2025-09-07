package com.vitae.curriculumVitae.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vitae.curriculumVitae.model.Curriculum;
import com.vitae.curriculumVitae.model.Experiencia;
import com.vitae.curriculumVitae.model.Perfil;
import com.vitae.curriculumVitae.repository.CurriculumRepository;
import com.vitae.curriculumVitae.repository.PerfilRepository;


import jakarta.validation.Valid;

import com.vitae.curriculumVitae.repository.ExperienciaRepository;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


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

        String base64 = Base64.getEncoder().encodeToString(curriculum.getImg_perfil());
        model.addAttribute("fileContent", base64);

        model.addAttribute("curriculum", curriculum);
        model.addAttribute("perfil", curriculum.getPerfil());
        model.addAttribute("experiencias", curriculum.getExperiencias());
        
        return "plantillaCreativa"; // busca plantillaCreativa.html en /templates
    }

    @GetMapping("/editar/{id}")
    public String editarCv(@PathVariable Long id, Model model) {
        Curriculum curriculum = curriculumRepository.findById(id).orElse(null);
        if (curriculum == null) {
            return "redirect:/api/landingPage"; // o una página de error amigable
        }
        model.addAttribute("curriculum", curriculum);
        model.addAttribute("paises", PAISES);
        model.addAttribute("idiomas", IDIOMAS);
        return "formulario"; // reutilizamos el formulario para editar

        
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
    public String creatCV(@RequestParam("file") MultipartFile file,  @Valid @ModelAttribute Curriculum curriculum, BindingResult bindingResult, Model model ) throws Exception {
        // creamos un objeto de tipo Curriculum para guardar los datos cuardados y poder obtener el id
        Curriculum saved = null;
        if (bindingResult.hasErrors()) {
        model.addAttribute("paises", PAISES);
        model.addAttribute("idiomas", IDIOMAS);
        bindingResult.getAllErrors().forEach(e -> System.out.println("Binding error: " + e));
        return "formulario";
    }

    if (curriculum.getNombre().equalsIgnoreCase(curriculum.getApellido())) {
        bindingResult.rejectValue("apellido", "error.curriculum", "El apellido no puede ser igual al nombre");
    }


    // Validación referencias. Tanto el nombre como el número

    //Validación de nombre. valida si el nombre del aplicante es igual al de la referencia
    if (curriculum.getPerfil() != null) {
        String aplicante = (curriculum.getNombre() + " " + curriculum.getApellido()).trim().toLowerCase();
        String referencia = curriculum.getPerfil().getNombreRef().trim().toLowerCase();

        if (aplicante.equals(referencia)) {
            model.addAttribute("paises", PAISES);
            model.addAttribute("idiomas", IDIOMAS);
            model.addAttribute("errorReferencia", "La referencia no puede ser la misma persona que aplica.");
            return "formulario";
        }
    }

    //Validación de número. Al igual que en el nombre, se evalua si el telefono de la referencia es igual al del aplicante. En dado caso no permite la creación del CV
    if (curriculum.getNumeroTel() != null && curriculum.getPerfil().getNumeroRef().trim().toLowerCase() != null &&
        curriculum.getNumeroTel().equals(curriculum.getPerfil().getNumeroRef().trim().toLowerCase())) {
        bindingResult.rejectValue("perfil.numeroRef", "error.curriculum", "El teléfono de la referencia no puede ser el mismo del aplicante");
    }



        // Manejar la imagen de perfil
        if(!file.isEmpty()){
            curriculum.setImg_perfil(file.getBytes());
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

@GetMapping("/lista")
public String mostrarLista(Model model) {
    List<Curriculum> curriculums = curriculumRepository.findAll();
    model.addAttribute("curriculums", curriculums);
    return "lista"; 
}

}
