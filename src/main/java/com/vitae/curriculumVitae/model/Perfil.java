package com.vitae.curriculumVitae.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.regex.Matcher;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "perfils")
public class Perfil {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "La descripción es obligatoria")
    @Pattern(regexp="^[\\p{L}\\s,.!?]{1,500}$", message="Sobre Mi solo puede contener letras, espacios y signos de puntuación básicos, y tener entre 1 y 200 caracteres")
    @Column(name = "sobre_mi", columnDefinition = "TEXT")
    private String sobreMi;
    
    @NotBlank(message = "La descripción es obligatoria")
    @Pattern(regexp="^[\\p{L}\\s,.!?]{1,500}$", message="Sobre Mi solo puede contener letras, espacios y signos de puntuación básicos, y tener entre 1 y 100 caracteres")
    @Column(columnDefinition = "TEXT")
    private String habilidades;
    
    @NotBlank(message = "El idioma es obligatorio")
    @Column(columnDefinition = "TEXT")
    private String idioma;
    
    @NotBlank(message = "El nombre es obligatoria")
    @Pattern(regexp="^[\\p{L}\\s]{1,500}$", message="El nombre solo puede contener letras y espacios, y tener entre 1 y 50 caracteres")
    @Column(name = "nombre_ref")
    private String nombreRef;
    
    @NotBlank(message = "El número es obligatorio")
    @Pattern(regexp="^\\+?[0-9\\s-]{7,15}$", message="El número de teléfono solo puede contener dígitos, espacios, guiones y opcionalmente un '+' al inicio, y tener entre 7 y 15 caracteres")
    @Column(name = "numero_ref")
    private String numeroRef;
    
    // Constructores
    public Perfil() {}
    
    public Perfil(String sobreMi, String habilidades, String idioma, 
                  String nombreRef, String numeroRef) {
        this.sobreMi = sobreMi;
        this.habilidades = habilidades;
        this.idioma = idioma;
        this.nombreRef = nombreRef;
        this.numeroRef = numeroRef;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getSobreMi() { return sobreMi; }
    public void setSobreMi(String sobreMi) { 
       if (sobreMi != null && !sobreMi.isEmpty()) {
            this.sobreMi = sobreMi.substring(0, 1).toUpperCase() + sobreMi.substring(1).toLowerCase();
        } else {
            this.sobreMi = sobreMi;
        }
    }
    
    public String getHabilidades() { return habilidades; }
    public void setHabilidades(String habilidades) {  this.habilidades = habilidades;}
    
    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    
    public String getNombreRef() { return nombreRef; }
    public void setNombreRef(String nombreRef) { 
        if (nombreRef != null && !nombreRef.isEmpty()) {
            this.nombreRef = nombreRef.substring(0, 1).toUpperCase() + nombreRef.substring(1).toLowerCase();
        } else {
            this.nombreRef = nombreRef;
        }
    }
    
    public String getNumeroRef() { return numeroRef; }
    public void setNumeroRef(String numeroRef) { this.numeroRef = numeroRef; }
}
