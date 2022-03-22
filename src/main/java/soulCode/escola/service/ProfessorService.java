package soulCode.escola.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import soulCode.escola.model.Professor;
import soulCode.escola.model.Turma;
import soulCode.escola.repository.ProfessorRepository;
import soulCode.escola.repository.TurmaRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private TurmaService turmaService;
	
//	@Autowired
//	private TurmaRepository turmaRepository;
	
	public List<Professor> mostrarTodosProfessores(){
		return professorRepository.findAll();
	}
	
	public Professor mostrarUmProfessor(Integer id_professor) {
		Optional<Professor> professor = professorRepository.findById(id_professor);
		return professor.orElseThrow();
	}
	
	public Professor buscarProfDaTurma(Integer id_turma) {
		Professor professor = professorRepository.buscarProfDaTurma(id_turma);
		return professor;
	}
	
	public Professor buscarProfessorPeloNome(String pro_nome) {
		Professor professor = professorRepository.fetchByName(pro_nome);
		return professor;
	}
	
	public List<Professor> professorSemTurma(){
		return professorRepository.professorSemTurma();
	}
	
//	public Professor InserirProfessorSemTurma(Professor professor) {
//		professor.setId_professor(null);
//		return professorRepository.save(professor);
//	}
	
	public List<List> ProfesssorComSuaTurma(){
		return professorRepository.professorComSuaTurma();
	}
	
	public Professor InserirProfessor(Integer id_turma, Professor professor) {
		professor.setId_professor(null);
		if (id_turma != null) {
			Turma turma = turmaService.buscarUmaTurma(id_turma);
			professor.setTurma(turma);
		}
		return professorRepository.save(professor);
	}
	
//	public Integer buscaIdProfessor(Integer id_turma) {
//		return professorRepository.buscaIdProfessor(id_turma);
//	}
//	
//	public Professor editarProfessor(Integer id_turma, Professor professor) {
//		professor = mostrarUmProfessor(professor.getId_professor());
//		if (id_turma != null) {
//			Turma turma = turmaService.buscarUmaTurma(id_turma);
//			professor.setTurma(turma);
//			turma.setProfessor(professor);
//		}
//		return professorRepository.save(professor);
//		
//	}
	
//	public Professor editarProfessor(Integer id_turma, Professor professor) {
//
//		if(id_turma != null) {
//			Professor dadosProfessor = mostrarUmProfessor(professor.getId_professor());
//			Turma turmaAnterior = dadosProfessor.getTurma();
//		if(turmaAnterior != null) {
//			turmaAnterior.setProfessor(null);
//			turmaRepository.save(turmaAnterior);
//		}
//			Turma turma = turmaService.buscarUmaTurma(id_turma);
//			professor.setTurma(turma);
//			turma.setProfessor(professor);
//		}
//		
//		return professorRepository.save(professor);
//	}
	
	public Professor editarProfessor(Professor professor) {
		mostrarUmProfessor(professor.getId_professor());
		return professorRepository.save(professor);
	}
	
	public Professor salvarFoto(Integer id_professor, String caminhoFoto) {
		Professor professor = mostrarUmProfessor(id_professor);
		professor.setPro_foto(caminhoFoto);
		return professorRepository.save(professor);
	}
	
}
