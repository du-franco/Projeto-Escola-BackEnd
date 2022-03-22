package soulCode.escola.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soulCode.escola.model.Aluno;
import soulCode.escola.model.Turma;
import soulCode.escola.repository.AlunoRepository;
import soulCode.escola.service.exceptions.ObjectNotFoundException;

@Service
public class AlunoService {

	// fazendo a injeção de dependências
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private TurmaService turmaService;

	// o primeiro serviço que vamos implementar é a listagem de todos os alunos
	// cadastrados

	public List<Aluno> mostrarTodosAlunos() {
		return alunoRepository.findAll();
	}
	
	public List<List> alunosComTurma(){
		return alunoRepository.alunosComTurma();
	}

	// Optional - nos ajuda a evitar os erros (NullPointerException)
	// tira a necessidade da verificação de criarmos codificação (if aluno != null)
	// orElseThrow - se o aluno estiver presente no banco de dados, retorna o aluno.
	// se não lança uma exceção

	public Aluno buscarUmAluno(Integer ra_aluno) {
		Optional<Aluno> aluno = alunoRepository.findById(ra_aluno);
		return aluno.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não cadastrado! O id procurado foi: " + ra_aluno));
	}
	
	public Aluno inserirAlunoNaTurma(Integer ra_aluno, Turma turma) {
		Aluno aluno = buscarUmAluno(ra_aluno);
		aluno.setTurma(turma);
		return alunoRepository.save(aluno);
	}
	
	public Aluno deixarAlunoSemTurma(Integer ra_aluno) {
		Aluno aluno = buscarUmAluno(ra_aluno);
		aluno.setTurma(null);
		return alunoRepository.save(aluno);
	}
	
	public Aluno InserirAluno(Integer id_turma, Aluno aluno) {
		aluno.setRa_aluno(null);
		Turma turma = turmaService.buscarUmaTurma(id_turma);
		aluno.setTurma(turma);
		return alunoRepository.save(aluno);
	}
	
	public void deletarUmAluno(Integer ra_aluno) {
		alunoRepository.deleteById(ra_aluno);
	}
	
	public Aluno editarAluno(Aluno aluno) {
		buscarUmAluno(aluno.getRa_aluno());
		return alunoRepository.save(aluno);
	}
	
	public List<Aluno> buscarAlunoTurma(Integer id_turma){
		List<Aluno> aluno = alunoRepository.fetchByTurma(id_turma);
		return aluno;
	}

}
