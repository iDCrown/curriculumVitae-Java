package com.vitae.curriculumVitae.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "experiencias")
public class Experiencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Pattern(regexp="^(?=.{4,100}$)[A-Za-zÁÉÍÓÚÑáéíóúñ]+(?:\\s+[A-Za-zÁÉÍÓÚÑáéíóúñ]+)*$", message="El nombre solo puede contener letras y espacios, y tener entre 4 y 100 caracteres")
    @Column(name = "nombre_empresa", nullable = false)
    private String nombreEmpresa;
    
    @NotBlank(message = "El cargo es obligatorio")
    @Pattern(regexp="^(?=.{4,60}$)[A-Za-zÁÉÍÓÚÑáéíóúñ]+(?:\\s+[A-Za-zÁÉÍÓÚÑáéíóúñ]+)*$", message="El nombre del cargo solo puede contener letras y espacios, y tener entre 4 y 60 caracteres")
    @Column(name = "nombre_cargo", nullable = false)
    private String nombreCargo;

    @Column(name = "fecha_inicio")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;
    
    @NotBlank(message = "La descripción es obligatoria")
    @Pattern(regexp="^[\\p{L}\\p{M}\\p{P}\\p{Zs}\\n]{10,1000}$", message="La descripción solo puede contener letras, espacios y signos de puntuación básicos, y tener entre 10 y 1000 caracteres")
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum_id")
    private Curriculum curriculum;
    
    // Constructores
    public Experiencia() {}
    
    public Experiencia(String nombreEmpresa, String nombreCargo, 
                      LocalDate fechaInicio, String descripcion) {
        this.nombreEmpresa = nombreEmpresa;
        this.nombreCargo = nombreCargo;
        this.fechaInicio = fechaInicio;
        this.descripcion = descripcion;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombreEmpresa() { return nombreEmpresa; }
    public void setNombreEmpresa(String nombreEmpresa) { 
        if (nombreEmpresa != null && !nombreEmpresa.isEmpty()) {
            this.nombreEmpresa = nombreEmpresa.substring(0, 1).toUpperCase() + nombreEmpresa.substring(1).toLowerCase();
        } else {
            this.nombreEmpresa = nombreEmpresa;
        }
    }
    
    public String getNombreCargo() { return nombreCargo; }
    public void setNombreCargo(String nombreCargo) { 
        if (nombreCargo != null && !nombreCargo.isEmpty()) {
            this.nombreCargo = nombreCargo.substring(0, 1).toUpperCase() + nombreCargo.substring(1).toLowerCase();
        } else {
            this.nombreCargo = nombreCargo;
        }
    }
    
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { 
        if (descripcion != null && !descripcion.isEmpty()) {
            this.descripcion = descripcion.substring(0, 1).toUpperCase() + descripcion.substring(1).toLowerCase();
        } else {
            this.descripcion = descripcion;
        }
     }

    public Curriculum getCurriculum() { return curriculum; }
    public void setCurriculum(Curriculum curriculum) { this.curriculum = curriculum; }
}
