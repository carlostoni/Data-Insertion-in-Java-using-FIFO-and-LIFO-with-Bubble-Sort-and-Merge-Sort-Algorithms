package dao;

import java.util.List;

import entity.DadosInpe;

public interface IDadosInpeDAO { // interface para salva, alterar e excluir  
	
	int salvar(DadosInpe dadosinpe);
	
	int update(DadosInpe dadosinpe);
	
	int remover(Long id);
	
	List<DadosInpe> findAll();
	

}
