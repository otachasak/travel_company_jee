package cz.cvut.fel.jee.travel_company.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import cz.cvut.fel.jee.travel_company.entities.dto.ReservationDTO;

/**
 * Entity implementation class for Entity: Reservation
 *
 */
@Entity
@NamedQuery(name="findAllReservations", query="SELECT r FROM Reservation r")
public class Reservation extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Min(1)
	private Integer places;
	
	@ManyToOne
	@NotNull
	private Vacation vacation;

	public Reservation() {
		super();
	}
	
	public Reservation(ReservationDTO reservation){
		super();
		this.places = reservation.getPlaces();
	}

	public Integer getPlaces() {
		return places;
	}

	public void setPlaces(Integer places) {
		this.places = places;
	}

	public Vacation getVacation() {
		return vacation;
	}

	public void setVacation(Vacation vacation) {
		this.vacation = vacation;
	}
   
}
