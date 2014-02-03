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

import cz.cvut.fel.jee.travel_company.bussiness.ReservationManagerBean;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.dto.ReservationDTO;

@Path("/reservation")
@Produces("application/json")
@Consumes("application/json")
public class ReservationResource {

	@Inject
	private ReservationManagerBean reservationMB;
	
	@GET
	@Path("/")
	public Collection<ReservationDTO> findAllReservations(){
		return this.reservationMB.findAllReservation();
	}
	
	@GET
	@Path("/{id}/")
	public ReservationDTO findReservation(@PathParam("id") Long id){
		try {
			ReservationDTO reservation = this.reservationMB.findReservation(id);
			if(reservation == null){
				throw new WebApplicationException(Response.Status.UNAUTHORIZED);
			}
			return reservation;
		} catch (EntityNotFoundException e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}
	
	@POST
	@Path("/")
	public ReservationDTO addReservation(ReservationDTO reservation){
		this.reservationMB.addReservation(reservation);
		return reservation;
	}
	
	@PUT
	@Path("/{id}/")
	public ReservationDTO updateReservation(@PathParam("id") Long id, ReservationDTO reservation){
		reservation.setId(id);
		try {
			reservation = this.reservationMB.updateReservation(reservation);
			if(reservation == null){
				throw new WebApplicationException(Response.Status.UNAUTHORIZED);
			}
		} catch (EntityNotFoundException e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return reservation;
	}
	
	@DELETE
	@Path("/{id}/")
	public void deleteReservation(@PathParam("id") Long id){
		try {
			this.reservationMB.deleteReservation(id);
		} catch (EntityNotFoundException e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}
	
}
