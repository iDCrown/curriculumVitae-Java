package com.vitae.curriculumVitae.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitae.curriculumVitae.model.Curriculum;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Long> { //el user es la entidad y Integer es el tipo de dato de la clave primaria.

}
