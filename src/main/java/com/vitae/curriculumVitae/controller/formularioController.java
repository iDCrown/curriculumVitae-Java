package com.vitae.curriculumVitae.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping("/plantillaCreativa")
    public String mostrarPlantillaCreativa() {
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
        curriculumRepository.save(curriculum);
    } catch (Exception ex) {
        ex.printStackTrace(); // <<-- mira el stacktrace en consola: 'Caused by:' es la clave
        model.addAttribute("errorMessage", "Error al guardar CV: " + ex.getMessage());
        model.addAttribute("paises", PAISES);
        model.addAttribute("idiomas", IDIOMAS);
        return "formulario";
    }

    return "redirect:/api/plantillaCreativa";
}



//     @PostMapping("/create-cv")
//     public ResponseEntity<?> crearCV(@RequestBody Curriculum Curriculum){
//         try {
//             //Validación de entrada de campos. valida si estan vacios.
//             if (Curriculum.getNombre() == null || Curriculum.getNombre().trim().isEmpty()) {
//             return ResponseEntity.badRequest().body("El nombre es un campo obligatorio");
//             }

//             if (Curriculum.getCorreo() == null || Curriculum.getCorreo().trim().isEmpty()) {
//                 return ResponseEntity.badRequest().body("El correo es un campo obligatorio");
                
//             }

//             //Validar el formato básico de un correo electrónico
//             if (!Curriculum.getCorreo().contains("@") && !Curriculum.getCorreo().contains(".")) {
//                 return ResponseEntity.badRequest().body("El formato del correo no es válido");
//             }

//             //Verifica si el correo ya fue registrado en el sistema.
//             Optional<Curriculum> existingCurriculum = CurriculumRepository.findByCorreo(Curriculum.getCorreo());
//             if(existingCurriculum.isPresent()){
//                 return ResponseEntity.badRequest().body("Ya existe un CV con este correo");
//             }

//             if (Curriculum.getTelefono() == null || Curriculum.getTelefono().trim().isEmpty()) {
//                 return ResponseEntity.badRequest().body("El teléfono es obligatorio");
//             }
            
//             if (Curriculum.getProfesion() == null || Curriculum.getProfesion().trim().isEmpty()) {
//                 return ResponseEntity.badRequest().body("La profesión es obligatoria");
//             }
            
//             if (Curriculum.getTiempoExp() == null || Curriculum.getTiempoExp().trim().isEmpty()) {
//                 return ResponseEntity.badRequest().body("El tiempo de experiencia es obligatorio");
//             }
            
//             if (Curriculum.getHabilidades() == null || Curriculum.getHabilidades().trim().isEmpty()) {
//                 return ResponseEntity.badRequest().body("Las habilidades son obligatorias");
//             }
            
//             if (Curriculum.getDescripcion() == null || Curriculum.getDescripcion().trim().isEmpty()) {
//                 return ResponseEntity.badRequest().body("La descripción es obligatoria");
//             }

//             //Se valida si que el nombre tenga por lo menos 2 carácteres
//             if (Curriculum.getNombre().trim().length() < 2) {
//                 return ResponseEntity.badRequest().body("El nombre debe tener al menos 2 caracteres");
//             }

//             //Verificar la longitud del telefono del usuario
//             String telefonoLimpio = Curriculum.getTelefono().replaceAll("^[0-9]", "");
//             if (telefonoLimpio.trim().length() == 10) {
//                 return ResponseEntity.badRequest().body("El Telefono debe tener 10 dígitos");
//             }

//             //Guardar el CV del usuario
//             Curriculum saveCurriculum = CurriculumRepository.save(Curriculum);

//             return ResponseEntity.status(HttpStatus.CREATED).body(saveCurriculum).ok(saveCurriculum);
        
//     } catch (Exception e) {
//         System.err.println("❌ Error al guardar CV: " + e.getMessage());
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el CV: " + e.getMessage());
//     }
//   }

//   //Aquí se obtienen todos los CVs que hay registrados
//   @GetMapping("/cv")
//   //Se especifica que se esta llamando a una lista de usuarios
//   public ResponseEntity<List<Curriculum>> obtenerTodosLosCVs(){
//     try {
//       List<Curriculum> Curriculums = CurriculumRepository.findAll();
//             return ResponseEntity.ok(Curriculums);
//     } catch (Exception e) {
//       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//     }
//   }
}
