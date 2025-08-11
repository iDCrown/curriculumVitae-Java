package com.vitae.curriculumVitae;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity // Esta anotación indica que esta clase es una entidad JPA, lo que significa que se mapeará a una tabla en la base de datos.
public class User {
    @Id // Esta anotación indica que este campo es la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Esta anotación indica que el valor de este campo se generará automáticamente.
    @Column(unique = true, nullable = false) //Esta anotación esta indicando que la columna id debe de ser única y no puede quedar con un valor nulo. 
    private Integer id;

    private String nombre;

    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Email cannot be empty")
    private String correo; 

    private String telefono;
    private String profesion;
    private String tiempoExp;
    private String habilidades;
    private String descripcion;

    /* 
    Como utilizamos hibernate, no es necesario escribir get y set para cada campo,
    !pero! si se maneja autenitcación y autorización, es recomendable tenerlos. porque reflexion se salta los set y ingresa directamente al campo, ignorando validaciones.
    RECOMENDABLE: usar getters y setters para que hibernate no ingrese directamente a los campos y se salten las validaciones.
    */ 

    //getters y setters
    
    public Integer getId() {
        return id;  
    }
    public void setId(Integer id) {
        this.id = id;
    }

    // nombre

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    // correo

    public String getCorreo(){
        return correo;
    }
    public void setCorreo(String correo){
        this.correo = correo;
    }

    // telefono

    public String getTelefono(){
        return telefono;
    }
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    // profesion

    public String getProfesion(){
        return profesion;
    }
    public void setProfesion(String profesion){
        this.profesion = profesion;     
    }

    // tiempoExp

    public String getTiempoExp(){
        return tiempoExp;
    }
    public void setTiempoExp(String tiempoExp){
        this.tiempoExp = tiempoExp;
    }

    // habilidades

    public String getHabilidades(){
        return habilidades;
    }
    public void setHabilidades(String habilidades){
        this.habilidades = habilidades; 
    }

    // descripcion

    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

}
