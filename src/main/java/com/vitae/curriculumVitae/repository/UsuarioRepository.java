package com.vitae.curriculumVitae.repository;

import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitae.curriculumVitae.model.User;

@Repository
public interface UsuarioRepository extends JpaRepository<User, Integer> { //el user es la entidad y Integer es el tipo de dato de la clave primaria.
    // Este repositorio hereda de JpaRepository, lo que proporciona métodos CRUD básicos para la entidad User.
    // No es necesario definir métodos adicionales a menos que se requieran consultas personalizadas.

    //Método para poder buscar por medio del correo, con esto se pueden evitar los duplicados
    Optional<User> findByCorreo(String correo);

    //Método para buscar CV por profesión
    List<User> findByProfesion(String profesion);

    //Método para buscar por nombre de manera parcial
    List<User> findByNombreContainingIgnoreCase(String nombre);
}
