package com.vitae.curriculumVitae.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "perfils")
public class Perfil {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "sobre_mi", columnDefinition = "TEXT")
    private String sobreMi;
    
    @Column(columnDefinition = "TEXT")
    private String habilidades;
    
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    
    @Column(name = "nombre_ref")
    private String nombreRef;
    
    @Column(name = "numero_ref")
    private String numeroRef;
    
    // Enum para idiomas establecidos
    public enum Idioma {
        ESPAÑOL, INGLES, FRANCES, PORTUGUES, ITALIANO, ALEMAN, MANDARIN, OTRO
    }
    
    // Constructores
    public Perfil() {}
    
    public Perfil(String sobreMi, String habilidades, Idioma idioma, 
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
    public void setSobreMi(String sobreMi) { this.sobreMi = sobreMi; }
    
    public String getHabilidades() { return habilidades; }
    public void setHabilidades(String habilidades) { this.habilidades = habilidades; }
    
    public Idioma getIdioma() { return idioma; }
    public void setIdioma(Idioma idioma) { this.idioma = idioma; }
    
    public String getNombreRef() { return nombreRef; }
    public void setNombreRef(String nombreRef) { this.nombreRef = nombreRef; }
    
    public String getNumeroRef() { return numeroRef; }
    public void setNumeroRef(String numeroRef) { this.numeroRef = numeroRef; }
}
