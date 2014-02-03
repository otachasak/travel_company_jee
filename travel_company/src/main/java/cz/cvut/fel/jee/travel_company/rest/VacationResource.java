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

import cz.cvut.fel.jee.travel_company.bussiness.VacationManagerBean;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.dto.VacationDTO;

@Path("/vacation")
@Produces("application/json")
@Consumes("application/json")
public class VacationResource {
	
	@Inject
	private VacationManagerBean vacationMB;
	
	@GET
	@Path("/")
	public Collection<VacationDTO> findAllVacations(){
		return this.vacationMB.findAllVacations();
	}
	
	@GET
	@Path("/{id}/")
	public VacationDTO findVacation(@PathParam("id") Long id){
		VacationDTO vacation;
		try {
			vacation = this.vacationMB.findVacation(id);
		} catch (EntityNotFoundException e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return vacation;
	}
	
	@POST
	@Path("/")
	public VacationDTO addVacation(VacationDTO vacation){
		this.vacationMB.addVacation(vacation);
		return vacation;
	}
	
	@PUT
	@Path("/{id}/")
	public VacationDTO updateVacation(@PathParam("id") Long id, VacationDTO vacation){
		try {
			this.vacationMB.updateVacation(vacation);
		} catch (EntityNotFoundException e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return vacation;
	}
	
	@DELETE
	@Path("/{id}/")
	public void deleteVacation(@PathParam("id") Long id){
		try {
			this.vacationMB.deleteVacation(id);
		} catch (EntityNotFoundException e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

}
