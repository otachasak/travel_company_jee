package cz.cvut.fel.jee.travel_company.services;

import cz.cvut.fel.jee.travel_company.dao.DestinationDao;
import cz.cvut.fel.jee.travel_company.entities.Destination;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.dto.DestinationDTO;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
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

    @Resource
    SessionContext ejbContext;

    @PermitAll
    public List<DestinationDTO> findAllDestinations() {
        List<Destination> destinations = destinationDao.findAllDestinations();

        logger.info("User " + ejbContext.getCallerPrincipal() + " is root " + ejbContext.isCallerInRole("root") +
                " is customer " + ejbContext.isCallerInRole("customer"));

        return originalToDTos(Destination.class, DestinationDTO.class, destinations);
    }

    @RolesAllowed({"root"})
    public void addDestination(String newDestinationName) {
        Destination newDestination = new Destination();
        newDestination.setName(newDestinationName);
        destinationDao.addDestination(newDestination);
    }

    @RolesAllowed({"root"})
    public void deleteDestination(long destinationId) {
        try {
            destinationDao.deleteDestination(destinationId);
        } catch (EntityNotFoundException e) {
            logger.log(Level.WARNING, "Unable to delete destination.", e);
        }
    }
}
