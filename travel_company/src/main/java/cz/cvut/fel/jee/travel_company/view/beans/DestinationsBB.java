package cz.cvut.fel.jee.travel_company.view.beans;

import cz.cvut.fel.jee.travel_company.dao.DestinationDao;
import cz.cvut.fel.jee.travel_company.entities.Destination;
import cz.cvut.fel.jee.travel_company.entities.dto.DestinationDTO;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

@Named
@SessionScoped
public class DestinationsBB implements Serializable {
    @Inject
    DestinationDao destinationDao;
    private String newDestinationName;

    public List<DestinationDTO> getAllDestinations() {

        List<Destination> destinations = destinationDao.findAllDestinations();

        List<DestinationDTO> destinationDTOs = new ArrayList<>(destinations.size());
        for (Destination destination : destinations) {
            destinationDTOs.add(new DestinationDTO(destination));
        }

        return destinationDTOs;
    }

    public void addDestination() {
        Destination newDestination = new Destination();
        newDestination.setName(newDestinationName);
        destinationDao.addDestination(newDestination);
    }

    public String getNewDestinationName() {
        return newDestinationName;
    }

    public void setNewDestinationName(String newDestinationName) {
        this.newDestinationName = newDestinationName;
    }


}
