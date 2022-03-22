package soulCode.escola.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import soulCode.escola.model.Professor;
import soulCode.escola.service.ProfessorService;
import soulCode.escola.utils.UploadFileUtil;


@RestController
@RequestMapping("escola")
@CrossOrigin
public class UploadFileController {
	
	@Autowired
	private ProfessorService professorService;
	
	@PostMapping("/envio/{id_professor}")
	public ResponseEntity<String> enviarDados(@PathVariable Integer id_professor, MultipartFile foto, @RequestParam("nome") String nome){
		String fileName = nome;
		String uploadDir = "/extensãoJava_projetoEscola_Cópia/projetoEscolaFront/src/assets/img";
		String nomeMaisCaminho = "/assets/img/" + nome;
		
		professorService.salvarFoto(id_professor, nomeMaisCaminho);
		
		try {
			UploadFileUtil.salvarArquivo(uploadDir, fileName, foto);
		}catch(Exception e){
			System.out.println("O arquivo não foi enviado" + e);
		}
		
		System.out.println("Deu certo:" + nomeMaisCaminho);
		return ResponseEntity.ok("Arquivo enviado");
	}

}
