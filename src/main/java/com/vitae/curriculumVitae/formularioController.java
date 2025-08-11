package com.vitae.curriculumVitae;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api") //este annotation es para definir un prefijo comun en todos los endpoints ejemplo: localhost:8080/api/formulario
public class formularioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    //Endpopint

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("user", new User());
        return "formulario"; // busca formulario.html en /templates
    }

    @GetMapping("/lista")
        public String mostrarLista( Model model) {
            // Obtiene todos los usuarios de la base de datos y los agrega al modelo
            // para que estén disponibles en la vista lista.html
        List<User> users = usuarioRepository.findAll();
            model.addAttribute("users", users);
            return "lista"; 
    }
    
    @PostMapping("/create-cv")
    public String creatCV( @ModelAttribute User user){
        usuarioRepository.save(user);
        return "redirect:/api/lista"; //redirecciona a la lista de CVs
    }


//     @PostMapping("/create-cv")
//     public ResponseEntity<?> crearCV(@RequestBody User user){
//         try {
//             //Validación de entrada de campos. valida si estan vacios.
//             if (user.getNombre() == null || user.getNombre().trim().isEmpty()) {
//             return ResponseEntity.badRequest().body("El nombre es un campo obligatorio");
//             }

//             if (user.getCorreo() == null || user.getCorreo().trim().isEmpty()) {
//                 return ResponseEntity.badRequest().body("El correo es un campo obligatorio");
                
//             }

//             //Validar el formato básico de un correo electrónico
//             if (!user.getCorreo().contains("@") && !user.getCorreo().contains(".")) {
//                 return ResponseEntity.badRequest().body("El formato del correo no es válido");
//             }

//             //Verifica si el correo ya fue registrado en el sistema.
//             Optional<User> existingUser = usuarioRepository.findByCorreo(user.getCorreo());
//             if(existingUser.isPresent()){
//                 return ResponseEntity.badRequest().body("Ya existe un CV con este correo");
//             }

//             if (user.getTelefono() == null || user.getTelefono().trim().isEmpty()) {
//                 return ResponseEntity.badRequest().body("El teléfono es obligatorio");
//             }
            
//             if (user.getProfesion() == null || user.getProfesion().trim().isEmpty()) {
//                 return ResponseEntity.badRequest().body("La profesión es obligatoria");
//             }
            
//             if (user.getTiempoExp() == null || user.getTiempoExp().trim().isEmpty()) {
//                 return ResponseEntity.badRequest().body("El tiempo de experiencia es obligatorio");
//             }
            
//             if (user.getHabilidades() == null || user.getHabilidades().trim().isEmpty()) {
//                 return ResponseEntity.badRequest().body("Las habilidades son obligatorias");
//             }
            
//             if (user.getDescripcion() == null || user.getDescripcion().trim().isEmpty()) {
//                 return ResponseEntity.badRequest().body("La descripción es obligatoria");
//             }

//             //Se valida si que el nombre tenga por lo menos 2 carácteres
//             if (user.getNombre().trim().length() < 2) {
//                 return ResponseEntity.badRequest().body("El nombre debe tener al menos 2 caracteres");
//             }

//             //Verificar la longitud del telefono del usuario
//             String telefonoLimpio = user.getTelefono().replaceAll("^[0-9]", "");
//             if (telefonoLimpio.trim().length() == 10) {
//                 return ResponseEntity.badRequest().body("El Telefono debe tener 10 dígitos");
//             }

//             //Guardar el CV del usuario
//             User saveUser = usuarioRepository.save(user);

//             return ResponseEntity.status(HttpStatus.CREATED).body(saveUser).ok(saveUser);
        
//     } catch (Exception e) {
//         System.err.println("❌ Error al guardar CV: " + e.getMessage());
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el CV: " + e.getMessage());
//     }
//   }

//   //Aquí se obtienen todos los CVs que hay registrados
//   @GetMapping("/cv")
//   //Se especifica que se esta llamando a una lista de usuarios
//   public ResponseEntity<List<User>> obtenerTodosLosCVs(){
//     try {
//       List<User> users = usuarioRepository.findAll();
//             return ResponseEntity.ok(users);
//     } catch (Exception e) {
//       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//     }
//   }
}
