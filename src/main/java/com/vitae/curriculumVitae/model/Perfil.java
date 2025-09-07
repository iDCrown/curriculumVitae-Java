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
    @Pattern(regexp= "^[\\p{L}\\p{M}\\p{P}\\p{Zs}\\n]{10,800}$", message="Sobre Mi solo puede contener letras, espacios y signos de puntuación básicos, y tener entre 10 y 1000 caracteres")
    @Column(name = "sobre_mi", columnDefinition = "TEXT")
    private String sobreMi;
    
    @NotBlank(message = "Las habilidades son obligatorias")
    @Size(min = 1, max = 500, message = "Debes tener por lo menos una habilidad (1-500 caracteres).")
    @Pattern(regexp = "^(?=.{1,500}$)(?=.*\\p{L})(?!\\d+$)[\\p{L}\\d\\s.,+#/\\-]+$", message = "Debes tener por lo menos una habilidad para el trabajo al que estas aplicando. No se permiten solo números")
    @Column(columnDefinition = "TEXT")
    private String habilidades;
    
    @NotBlank(message = "El idioma es obligatorio")
    @Column(columnDefinition = "TEXT")
    private String idioma;
    
    @NotBlank(message = "El nombre es obligatoria")
    @Pattern(regexp="^(?=.{4,100}$)[A-Za-zÁÉÍÓÚÑáéíóúñ]+(?:\\s+[A-Za-zÁÉÍÓÚÑáéíóúñ]+)*$", message="El nombre solo puede contener letras y espacios, y tener entre 4 y 100 caracteres")
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
    public void setIdioma(String idioma) { 
         if (idioma != null && !idioma.isBlank()) {
        // Convierte la primera letra a mayúscula y el resto a minúscula
        this.idioma = idioma.substring(0, 1).toUpperCase() + idioma.substring(1).toLowerCase();
    } else {
        this.idioma = idioma;
    }
    }
    
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
