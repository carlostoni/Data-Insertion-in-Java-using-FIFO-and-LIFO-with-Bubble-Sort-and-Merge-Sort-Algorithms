package controller;

import java.util.List;

import entity.DadosInpe;
import facade.DadosInpeFacade;

public class DadosInpeController {
	
	private DadosInpeFacade facade;
	
	public DadosInpeController() { // metado de acceso para DadosInpeFacade 
		this.facade = new DadosInpeFacade();
		
	}
	public int addDadosInpe(DadosInpe dadosinpe) {// metado de acceso para DadosInpeFacade
		return facade.salvar(dadosinpe);
		
	}
		public int alterarDadosInpe(DadosInpe dadosinpe) {// metado de acceso para DadosInpeFacade
			return facade.update(dadosinpe);
		}
	public int excluirDadosInpe(Long id) {// metado de acceso para DadosInpeFacade
		return facade.remover(id);
	}
	
	public List<DadosInpe> findDadosInpe() {// metado de acceso para DadosInpeFacade
		return facade.findAll();
		
	}
}
