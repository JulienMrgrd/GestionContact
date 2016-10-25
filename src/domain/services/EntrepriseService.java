package domain.services;

import domain.dao.interfaces.IEntrepriseDAO;
import domain.services.interfaces.IEntrepriseService;

public class EntrepriseService implements IEntrepriseService {
	
	IEntrepriseDAO dao;
	
	public EntrepriseService(IEntrepriseDAO dao){
		this.dao = dao;
	}

}
