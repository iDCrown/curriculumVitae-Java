package com.vitae.curriculumVitae;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<User, Integer> { //el user es la entidad y Integer es el tipo de dato de la clave primaria.
    // Este repositorio hereda de JpaRepository, lo que proporciona métodos CRUD básicos para la entidad User.
    // No es necesario definir métodos adicionales a menos que se requieran consultas personalizadas.
}
