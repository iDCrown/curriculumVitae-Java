package com.vitae.curriculumVitae.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "curriculums")
public class Curriculum {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Pattern(regexp="^(?=.{4,30}$)[A-Za-zÁÉÍÓÚÑáéíóúñ]+(?:\\s+[A-Za-zÁÉÍÓÚÑáéíóúñ]+)*$", message="El nombre solo puede contener letras y espacios, y tener entre 4 y 30 caracteres")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Pattern(regexp="^(?=.{4,30}$)[A-Za-zÁÉÍÓÚÑáéíóúñ]+(?:\\s+[A-Za-zÁÉÍÓÚÑáéíóúñ]+)*$", message="El apellido solo puede contener letras y espacios, y tener entre 4 y 30 caracteres")
    @Column(nullable = false)
    private String apellido;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Pattern(regexp="^(?=.{4,60}$)[A-Za-zÁÉÍÓÚÑáéíóúñ]+(?:\\s+[A-Za-zÁÉÍÓÚÑáéíóúñ]+)*$", message="El titulo solo puede contener letras y espacios, y tener entre 4 y 50 caracteres")
    @Column(nullable = false)
    private String titulo;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Pattern(regexp="^\\+?[0-9\\s-]{7,15}$", message="El número de teléfono solo puede contener dígitos, espacios, guiones y opcionalmente un '+' al inicio, y tener entre 7 y 15 caracteres")
    @Column(name = "numero_tel")
    private String numeroTel;
    
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido, contener '@' y un dominio")
    @Pattern(
    regexp = "^[A-Za-z0-9._%+-]{5,}@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
    message = "El email debe tener al menos 5 caracteres antes del @ y un dominio válido"
    )
    @Column(nullable = false)
    private String email;
    
    @NotBlank(message = "El país es obligatorio")
    private String pais;
    
    @NotBlank(message = "El LinkedIn es obligatorio")
    @Pattern(regexp="^(https?://)?(www\\.)?linkedin\\.com/.*$", message="El LinkedIn debe ser una URL válida ")
    private String linkedin;

    @Lob
    private byte[] img_perfil;
    
    // Relación OneToOne con Perfil
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_perfil")
    @Valid 
    private Perfil perfil;

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = true)
    @Valid 
    private List<Experiencia> experiencias = new ArrayList<>();
    
    // Constructores
    public Curriculum() {}
    
    public Curriculum(String nombre, String apellido, String titulo, String numeroTel, 
                     String email, String pais, String linkedin) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.titulo = titulo;
        this.numeroTel = numeroTel;
        this.email = email;
        this.pais = pais;
        this.linkedin = linkedin;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { 
        if (nombre != null && !nombre.isEmpty()) {
            this.nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
        } else {
            this.nombre = nombre;
        }
    }
    
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { 
        if (apellido != null && !apellido.isEmpty()) {
            this.apellido = apellido.substring(0, 1).toUpperCase() + apellido.substring(1).toLowerCase();
        } else {
            this.apellido = apellido;
        }
    }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { 
       if (titulo != null && !titulo.isEmpty()) {
            this.titulo = titulo.substring(0, 1).toUpperCase() + titulo.substring(1).toLowerCase();
        } else {
            this.titulo = titulo;
        }
    }
    
    public String getNumeroTel() { return numeroTel; }
    public void setNumeroTel(String numeroTel) { this.numeroTel = numeroTel; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
    
    public String getLinkedin() { return linkedin; }
    public void setLinkedin(String linkedin) { this.linkedin = linkedin; }

    public byte[] getImg_perfil() { return img_perfil; }
    public void setImg_perfil(byte[] img_perfil) { this.img_perfil = img_perfil; }
    
    public Perfil getPerfil() { return perfil; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }
    
    public List<Experiencia> getExperiencias() { return experiencias; }
    public void setExperiencias(List<Experiencia> experiencias) { this.experiencias = experiencias; }
}