package cz.cvut.fel.jee.travel_company.dao.impl;

import cz.cvut.fel.jee.travel_company.entities.Destination;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.Vacation;
import cz.cvut.fel.jee.travel_company.entities.dto.VacationDTO;
import cz.cvut.fel.jee.travel_company.dao.VacationDao;
import cz.cvut.fel.jee.travel_company.dao.impl.base.BaseDaoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.LockModeType;

/**
 * Created with IntelliJ IDEA.
 * User: vlada
 * Date: 28/01/14
 * Time: 22:10
 * To change this template use File | Settings | File Templates.
 */
public class VacationDaoImpl extends BaseDaoImpl implements VacationDao {
    @Override
    public List<VacationDTO> findAllVacations() {
        List<Vacation> vacations = em.createNamedQuery("Vacation.findAllVacations", Vacation.class)
                .getResultList();

        List<VacationDTO> vacationDTOs = new ArrayList<>(vacations.size());
        for(Vacation vacation : vacations) {
            vacationDTOs.add(new VacationDTO(vacation));
        }

        return vacationDTOs;
    }

	@Override
	public void addVacation(VacationDTO vacation) {
		em.persist(vacation);
	}

	@Override
	public VacationDTO findVacation(Long id) throws EntityNotFoundException {
		Vacation dbVacation = this.findDbVacation(id);
		return new VacationDTO(dbVacation);
	}

	@Override
	public void updateVacation(VacationDTO vacation) throws EntityNotFoundException {
		Vacation dbVacation = this.findDbVacation(vacation.getId());
		/*
		 * TODO
		 */
		this.em.persist(dbVacation);
	}

	@Override
	public void deleteVacation(Long id) throws EntityNotFoundException {
		Vacation dbVacation = this.findDbVacation(id);
		this.em.remove(dbVacation);
	}
	
	private Vacation findDbVacation(Long id) throws EntityNotFoundException{
		Vacation vacation = this.em.find(Vacation.class, id);
		if(vacation == null){
			throw new EntityNotFoundException(Vacation.class.getName(), id);
		}
		return vacation;
	}
}
