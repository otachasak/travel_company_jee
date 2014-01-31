package cz.cvut.fel.jee.travel_company.view.beans;

import cz.cvut.fel.jee.travel_company.dao.DestinationDao;
import cz.cvut.fel.jee.travel_company.entities.Destination;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.dto.DestinationDTO;
import cz.cvut.fel.jee.travel_company.services.DestinationService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 */

@Named
@SessionScoped
public class DestinationsBB extends BasicBB {
    @Inject
    DestinationService destinationService;

    private String newDestinationName;

    public List<DestinationDTO> getAllDestinations() {
        return destinationService.findAllDestinations();
    }

    public void addDestination() {
        destinationService.addDestination(newDestinationName);
        newDestinationName = "";
    }

    public void deleteDestination(long destinationId) {
        destinationService.deleteDestination(destinationId);
    }

    public String getNewDestinationName() {
        return newDestinationName;
    }

    public void setNewDestinationName(String newDestinationName) {
        this.newDestinationName = newDestinationName;
    }

}
