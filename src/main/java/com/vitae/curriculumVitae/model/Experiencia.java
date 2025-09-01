package com.vitae.curriculumVitae.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "experiencias")
public class Experiencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre_empresa", nullable = false)
    private String nombreEmpresa;
    
    @Column(name = "nombre_cargo", nullable = false)
    private String nombreCargo;
    
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    // Constructores
    public Experiencia() {}
    
    public Experiencia(String nombreEmpresa, String nombreCargo, 
                      Date fechaInicio, String descripcion) {
        this.nombreEmpresa = nombreEmpresa;
        this.nombreCargo = nombreCargo;
        this.fechaInicio = fechaInicio;
        this.descripcion = descripcion;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombreEmpresa() { return nombreEmpresa; }
    public void setNombreEmpresa(String nombreEmpresa) { this.nombreEmpresa = nombreEmpresa; }
    
    public String getNombreCargo() { return nombreCargo; }
    public void setNombreCargo(String nombreCargo) { this.nombreCargo = nombreCargo; }
    
    public Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
