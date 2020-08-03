package br.com.rede.qualis.prova.repository;

import org.springframework.stereotype.Repository;

import br.com.rede.qualis.prova.entities.Procedimento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long> {
		
	@Query("SELECT COUNT(p.id) FROM Procedimento p WHERE p.procedimento = ?1 and p.idade = ?2 and p.sexo = ?3")
	Integer findAutorizaProcedimento(Integer procedimento, Integer idade, char sexo);
	
	@Query("SELECT COUNT(p.id) FROM Procedimento p WHERE p.procedimento = ?1 and p.idade = ?2 and p.sexo = ?3 and p.permitido= ?4")
	Integer isExistProcedimento(Integer procedimento, Integer idade, char sexo, Boolean autoriza);
	
}
