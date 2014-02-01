package cz.cvut.fel.jee.travel_company.dao.impl;

import cz.cvut.fel.jee.travel_company.dao.DestinationDao;
import cz.cvut.fel.jee.travel_company.dao.impl.base.BaseDaoImpl;
import cz.cvut.fel.jee.travel_company.entities.Destination;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;

import javax.ejb.Stateful;
import javax.inject.Named;
import java.util.List;

@Named
@Stateful
public class DestinationDaoImpl extends BaseDaoImpl implements DestinationDao {

	@Override
	public List<Destination> findAllDestinations() {
		List<Destination> dbDestinations = this.em.createNamedQuery("findAllDestinations", Destination.class).getResultList();
        return dbDestinations;
	}

	@Override
	public void addDestination(Destination destination) {
		em.persist(destination);
	}

	@Override
	public void updateDatination(Destination destination) throws EntityNotFoundException {
		Destination dbDestination = this.findDbDestination(destination.getId());
		dbDestination.setName(destination.getName());
		this.em.persist(dbDestination);
	}

	@Override
	public Destination findDestination(Long id) {
        try {
            Destination destination = this.findDbDestination(id);
            return destination;
        } catch (EntityNotFoundException e) {
            return null;
        }
	}

	@Override
	public void deleteDestination(Long id) throws EntityNotFoundException {
		Destination dbDestination = this.findDbDestination(id);
		em.remove(dbDestination);
	}

	private Destination findDbDestination(Long id) throws EntityNotFoundException{
		Destination dbDestination = this.em.find(Destination.class, id);
		if(dbDestination == null){
			throw new EntityNotFoundException(Destination.class.getName(), id);
		}
		return dbDestination;
	}

}
