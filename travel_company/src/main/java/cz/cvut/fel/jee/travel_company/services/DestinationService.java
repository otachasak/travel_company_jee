package cz.cvut.fel.jee.travel_company.services;

import cz.cvut.fel.jee.travel_company.dao.DestinationDao;
import cz.cvut.fel.jee.travel_company.entities.Destination;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.dto.DestinationDTO;
import cz.cvut.fel.jee.travel_company.util.Location;
import cz.cvut.fel.jee.travel_company.util.MapApiResponse;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;
import org.jboss.resteasy.client.ClientResponse;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author vlada
 * @version: 31/01/14
 */
@Named
@Stateless
public class DestinationService extends BasicService {
	
	private static final String mapAddress = "http://maps.googleapis.com/maps/api/geocode/json";

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
    
    @PermitAll
    public DestinationDTO findDestinationById(Long id){
    	return new DestinationDTO(this.destinationDao.findDestination(id));
    }

    @RolesAllowed({"root"})
    public void addDestination(String newDestinationName) {
        Destination newDestination = new Destination();
        newDestination.setName(newDestinationName);
        Location loc = this.getPosition(newDestinationName);
        newDestination.setLatitude(loc.getLat());
        newDestination.setLongtitude(loc.getLng());
        destinationDao.addDestination(newDestination);
    }

    @RolesAllowed({"root"})
    public void deleteDestination(long destinationId) throws EntityNotFoundException {
            destinationDao.deleteDestination(destinationId);
    }
    
    @RolesAllowed({"root"})
    public DestinationDTO updateDestination(DestinationDTO destination) throws EntityNotFoundException{
    	this.destinationDao.updateDatination(new Destination(destination));
    	return destination;
    }
    
    	private Location getPosition(String destination){
    		
    		Client client = ClientBuilder.newBuilder().build();
            WebTarget target = client.target(mapAddress);
             String response = target.queryParam("address", destination).queryParam("sensor", "false").request("application/json").get(String.class);

            Gson gson = new Gson();
            MapApiResponse resp = gson.fromJson(response, MapApiResponse.class);
            if(!resp.getStatus().equals("OK")){
                throw new RuntimeException("Google map api status: " + resp.getStatus());
            } 
            return resp.getResults().get(0).getGeometry().getLocation();
    }
    
}
