package cz.cvut.fel.jee.travel_company.rest;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import cz.cvut.fel.jee.travel_company.bussiness.DestinationManagerBean;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.dto.DestinationDTO;

@Path("/destination")
@Produces("application/json")
@Consumes("application/json")
public class DestinationResource {

	@Inject
	private DestinationManagerBean destinationMB;
	
	@GET
	@Path("/")
	public Collection<DestinationDTO> findAllDestiunations(){
		return this.destinationMB.findAllDestinations();
	}
	
	@GET
	@Path("/{id}/")
	public DestinationDTO findDestination(@PathParam("id") Long id){
		DestinationDTO destination;
		try {
			destination = this.destinationMB.findDestination(id);
		} catch (EntityNotFoundException e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return destination;
	}	
	
	@POST
	@Path("/")
	public DestinationDTO addDestination(DestinationDTO destination){
		this.destinationMB.addDestination(destination);
		return destination;
	}
	
	@PUT
	@Path("/{id}/")
	public DestinationDTO updateDestination(@PathParam("id") Long id, DestinationDTO destination){
		destination.setId(id);
		try {
			this.destinationMB.updateDestination(destination);
		} catch (EntityNotFoundException e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return destination;
	}
	
	@DELETE
	@Path("/{id}/")
	public void deleteDestination(@PathParam("id") Long id){
		try {
			this.destinationMB.deleteDestination(id);
		} catch (EntityNotFoundException e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}
}
