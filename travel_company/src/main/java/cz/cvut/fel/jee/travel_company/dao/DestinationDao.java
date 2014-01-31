package cz.cvut.fel.jee.travel_company.dao;

import java.util.List;

import cz.cvut.fel.jee.travel_company.entities.Destination;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;

public interface DestinationDao {

	public List<Destination> findAllDestinations();

	public Destination findDestination(Long id) throws EntityNotFoundException;
	public void addDestination(Destination destination);
	public void updateDatination(Destination destination) throws EntityNotFoundException;
	public void deleteDestination(Long id) throws EntityNotFoundException;
	
}
