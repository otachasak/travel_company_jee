package cz.cvut.fel.jee.travel_company.dao.impl;

import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.Vacation;
import cz.cvut.fel.jee.travel_company.dao.VacationDao;
import cz.cvut.fel.jee.travel_company.dao.impl.base.BaseDaoImpl;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.inject.Named;

/**
 */
@Named
@Stateful
public class VacationDaoImpl extends BaseDaoImpl implements VacationDao {
    @Override
    @PermitAll
    public List<Vacation> findAllVacations() {
        return em.createNamedQuery("Vacation.findAllVacations", Vacation.class)
                .getResultList();
    }

	@Override
    @RolesAllowed({"root"})
	public void addVacation(Vacation vacation) {
		em.persist(vacation);
	}

	@Override
    @PermitAll
	public Vacation findVacation(Long id) throws EntityNotFoundException {
		Vacation dbVacation = this.findDbVacation(id);
		return dbVacation;
	}

	@Override
    @RolesAllowed({"root"})
	public void updateVacation(Vacation vacation) throws EntityNotFoundException {
		Vacation dbVacation = this.findDbVacation(vacation.getId());
		dbVacation.setDestination(vacation.getDestination());
		dbVacation.setStartDate(vacation.getStartDate());
		dbVacation.setEndDate(vacation.getEndDate());
		dbVacation.setPlaces(vacation.getPlaces());
		dbVacation.setPrice(vacation.getPrice());
		this.em.persist(dbVacation);
	}

	@Override
    @RolesAllowed({"root"})
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
