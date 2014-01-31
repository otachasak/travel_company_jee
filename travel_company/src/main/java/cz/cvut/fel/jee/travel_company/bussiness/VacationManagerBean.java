package cz.cvut.fel.jee.travel_company.bussiness;

import javax.ejb.Stateless;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import cz.cvut.fel.jee.travel_company.dao.VacationDao;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.Vacation;
import cz.cvut.fel.jee.travel_company.entities.dto.VacationDTO;

@Stateless
public class VacationManagerBean {
	
	@Inject
	private VacationDao vacationDao;

	public VacationManagerBean() {
	}
	
	public List<VacationDTO> findAllVacations(){
		List<Vacation> dbVacations = this.vacationDao.findAllVacations();
		ArrayList<VacationDTO> vacations = new ArrayList<VacationDTO>();
		for(Vacation dbVacation : dbVacations){
			vacations.add(new VacationDTO(dbVacation));
		}
		return vacations;
	}
	
	public VacationDTO findVacation(Long id) throws EntityNotFoundException{
		Vacation dbVacation = this.vacationDao.findVacation(id);
		return new VacationDTO(dbVacation);
	}
	
	public void addVacation(VacationDTO vacation){
		this.vacationDao.addVacation(new Vacation(vacation));
	}
	
	public void updateVacation(VacationDTO vacation) throws EntityNotFoundException{
		this.vacationDao.updateVacation(new Vacation(vacation));
	}
	
	public void deleteVacation(Long id) throws EntityNotFoundException{
		this.vacationDao.deleteVacation(id);
	}

}
