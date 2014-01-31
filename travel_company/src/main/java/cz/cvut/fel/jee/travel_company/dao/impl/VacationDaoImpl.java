package cz.cvut.fel.jee.travel_company.dao.impl;

import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.Vacation;
import cz.cvut.fel.jee.travel_company.dao.VacationDao;
import cz.cvut.fel.jee.travel_company.dao.impl.base.BaseDaoImpl;

import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Named;

/**
 */
@Named
@Stateful
public class VacationDaoImpl extends BaseDaoImpl implements VacationDao {
    @Override
    public List<Vacation> findAllVacations() {
        return em.createNamedQuery("Vacation.findAllVacations", Vacation.class)
                .getResultList();
    }

	@Override
	public void addVacation(Vacation vacation) {
		em.persist(vacation);
	}

	@Override
	public Vacation findVacation(Long id) throws EntityNotFoundException {
		Vacation dbVacation = this.findDbVacation(id);
		return dbVacation;
	}

	@Override
	public void updateVacation(Vacation vacation) throws EntityNotFoundException {
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
