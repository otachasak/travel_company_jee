package cz.cvut.fel.jee.travel_company.services;

import cz.cvut.fel.jee.travel_company.dao.DestinationDao;
import cz.cvut.fel.jee.travel_company.entities.Destination;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.dto.DestinationDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * @author vlada
 * @version: 31/01/14
 */
@Named
@Stateless
public class DestinationService extends BasicService {

    @Inject
    DestinationDao destinationDao;

    public List<DestinationDTO> findAllDestinations() {

        List<Destination> destinations = destinationDao.findAllDestinations();

        List<DestinationDTO> destinationDTOs = new ArrayList<>(destinations.size());
        for (Destination destination : destinations) {
            destinationDTOs.add(new DestinationDTO(destination));
        }

        return destinationDTOs;
    }

    public void addDestination(String newDestinationName) {
        Destination newDestination = new Destination();
        newDestination.setName(newDestinationName);
        destinationDao.addDestination(newDestination);
    }

    public void deleteDestination(long destinationId) {
        try {
            destinationDao.deleteDestination(destinationId);
        } catch (EntityNotFoundException e) {
            logger.log(Level.WARNING, "Unable to delete destination.", e);
        }
    }
}
