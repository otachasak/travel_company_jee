package cz.cvut.fel.jee.travel_company.bussiness;

import javax.ejb.Stateless;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import cz.cvut.fel.jee.travel_company.dao.DestinationDao;
import cz.cvut.fel.jee.travel_company.entities.Destination;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.dto.DestinationDTO;

@Stateless
public class DestinationManagerBean {
	
	@Inject
	private DestinationDao destinationDao;
	
	public List<DestinationDTO> findAllDestinations(){
		List<Destination> dbDestinations = destinationDao.findAllDestinations();

        List<DestinationDTO> destinations = new ArrayList<>();
        for(Destination destination : dbDestinations){
            destinations.add(new DestinationDTO(destination));
        }
        return destinations;

	}
	
	public void addDestination(DestinationDTO destination){
		this.destinationDao.addDestination(destination);
	}
	
	public void updateDestination(DestinationDTO destination) throws EntityNotFoundException{
		this.destinationDao.updateDatination(destination);
	}
	
	public DestinationDTO findDestination(Long id) throws EntityNotFoundException{
		return this.destinationDao.findDestination(id);
	}
	
	public void deleteDestination(Long id) throws EntityNotFoundException{
		this.destinationDao.deleteDestination(id);
	}

}
