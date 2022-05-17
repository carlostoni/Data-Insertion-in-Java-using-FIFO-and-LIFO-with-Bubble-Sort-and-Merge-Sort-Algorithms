package facade;

import java.util.List;

import dao.DadosInpeDAO;
import dao.IDadosInpeDAO;
import entity.DadosInpe;

public class DadosInpeFacade {
	
	private IDadosInpeDAO dao;
	
	public DadosInpeFacade() {
		this.dao = new DadosInpeDAO();
		
	}
	
	public int salvar(DadosInpe dadosinpe) { // metado de acceso para DadosInpeDAO
		return dao.salvar(dadosinpe);
		
		}
	
	public int update(DadosInpe dadosinpe) { // metado de acceso para DadosInpeDAO
		return dao.update(dadosinpe);
		
	}
	
	public int remover(Long id) { // // metado de acceso para DadosInpeDAO
		return dao.remover(id);
		
	}
	public List<DadosInpe> findAll(){ //// metado de acceso para DadosInpeDAO
		return dao.findAll();
		
	}
}
