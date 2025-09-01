package com.vitae.curriculumVitae.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitae.curriculumVitae.model.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    
}
