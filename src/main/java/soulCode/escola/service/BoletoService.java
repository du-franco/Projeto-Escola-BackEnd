package soulCode.escola.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soulCode.escola.model.Aluno;
import soulCode.escola.model.Boleto;
import soulCode.escola.model.StatusBoleto;
import soulCode.escola.repository.BoletoRepository;

@Service
public class BoletoService {
	
	@Autowired
	private BoletoRepository boletoRepository;
	
	@Autowired
	private AlunoService alunoService;
	
	public List<Boleto> buscarTodosBoletos(){
		return boletoRepository.findAll();
	}
	
	public Boleto buscarUmBoleto(Integer codigo) {
		Optional<Boleto> boleto = boletoRepository.findById(codigo);
		return boleto.orElseThrow();
	}
	
	public List<Boleto> buscarBoletosDoAluno(Integer ra_aluno) {
		List<Boleto> boleto = boletoRepository.buscarBoletosDoAluno(ra_aluno);
		return boleto;
	}
	
	public Boleto adicionarBoleto(Boleto boleto, Integer ra_aluno) {
		boleto.setCodigo(null);
		Aluno aluno = alunoService.buscarUmAluno(ra_aluno);
		boleto.setAluno(aluno);
		return boletoRepository.save(boleto);
	}
	
	public Boleto pagarBoleto(Integer codigo) {
		Boleto boleto = buscarUmBoleto(codigo);
		StatusBoleto stl = StatusBoleto.RECEBIDO;
		boleto.setBo_status(stl);
		return boletoRepository.save(boleto);
	}
	
	public Boleto cancelarBoleto(Integer codigo) {
		Boleto boleto = buscarUmBoleto(codigo);
		StatusBoleto stl = StatusBoleto.CANCELADO;
		boleto.setBo_status(stl);
		return boletoRepository.save(boleto);
	}
	
	public Boleto editarBoleto(Boleto boleto, Integer codigo, Integer ra_aluno) {
		buscarUmBoleto(codigo);
		Aluno aluno = alunoService.buscarUmAluno(ra_aluno);
		boleto.setAluno(aluno);
		return boletoRepository.save(boleto);
	}

}
