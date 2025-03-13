package com.generation.devasfit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.devasfit.model.Treinos;

public interface TreinosRepository extends JpaRepository<Treinos, Long> {

	public List<Treinos>findAllByNomeTreinoContainingIgnoreCase(@Param("nomeTreino") String nomeTreino);
	
}
