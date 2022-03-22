package soulCode.escola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import soulCode.escola.model.Boleto;

public interface BoletoRepository extends JpaRepository<Boleto, Integer>{
	
	@Query(value = "SELECT * FROM bd_escola.boleto WHERE ra_aluno = :ra_aluno", nativeQuery = true)
	List<Boleto> buscarBoletosDoAluno(Integer ra_aluno);

}
