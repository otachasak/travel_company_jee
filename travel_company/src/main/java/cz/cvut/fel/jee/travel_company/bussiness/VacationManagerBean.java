package cz.cvut.fel.jee.travel_company.bussiness;

import javax.ejb.Stateless;
import javax.inject.Inject;

import java.util.List;

import cz.cvut.fel.jee.travel_company.dao.VacationDao;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.dto.VacationDTO;

@Stateless
public class VacationManagerBean {
	
	@Inject
	private VacationDao vacationDao;

	public VacationManagerBean() {
	}
	
	public List<VacationDTO> findAllVacations(){
		return this.vacationDao.findAllVacations();
	}
	
	public VacationDTO findVacation(Long id) throws EntityNotFoundException{
		return this.vacationDao.findVacation(id);
	}
	
	public void addVacation(VacationDTO vacation){
		this.vacationDao.addVacation(vacation);
	}
	
	public void updateVacation(VacationDTO vacation) throws EntityNotFoundException{
		this.vacationDao.updateVacation(vacation);
	}
	
	public void deleteVacation(Long id) throws EntityNotFoundException{
		this.vacationDao.deleteVacation(id);
	}

}
