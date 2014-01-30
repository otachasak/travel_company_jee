package cz.cvut.fel.jee.travel_company.dao.impl;

import java.util.List;
import java.util.ArrayList;

import cz.cvut.fel.jee.travel_company.dao.DestinationDao;
import cz.cvut.fel.jee.travel_company.dao.impl.base.BaseDaoImpl;
import cz.cvut.fel.jee.travel_company.entities.Destination;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.dto.DestinationDTO;

public class DestinationDaoImpl extends BaseDaoImpl implements DestinationDao{

	@Override
	public List<DestinationDTO> findAllDestinations() {
		List<Destination> dbDestinations = this.em.createNamedQuery("findAllDestinations", Destination.class).getResultList();
		
		List<DestinationDTO> destinations = new ArrayList<DestinationDTO>();
		for(Destination destination : dbDestinations){
			destinations.add(new DestinationDTO(destination));
		}
		return destinations;
	}

	@Override
	public void addDestination(DestinationDTO destination) {
		this.em.persist(new Destination(destination));
	}

	@Override
	public void updateDatination(DestinationDTO destination) throws EntityNotFoundException {
		Destination dbDestination = this.findDbDestination(destination.getId());
		dbDestination.setName(destination.getName());
		this.em.persist(dbDestination);
	}

	@Override
	public DestinationDTO findDestination(Long id) throws EntityNotFoundException {
		Destination dbDestination = this.findDbDestination(id);
		return new DestinationDTO(dbDestination);
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
