package cz.cvut.fel.jee.travel_company.dao;

import java.util.List;

import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.dto.DestinationDTO;

public interface DestinationDao {

	public List<DestinationDTO> findAllDestinations();
	public DestinationDTO findDestination(Long id) throws EntityNotFoundException;
	public void addDestination(DestinationDTO destination);
	public void updateDatination(DestinationDTO destination) throws EntityNotFoundException;
	public void deleteDestination(Long id) throws EntityNotFoundException;
	
}
