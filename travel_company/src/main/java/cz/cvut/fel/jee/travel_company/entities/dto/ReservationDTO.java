package cz.cvut.fel.jee.travel_company.entities.dto;

import cz.cvut.fel.jee.travel_company.entities.Reservation;

public class ReservationDTO {
	
	private Long id;
	private Integer places;
	private VacationDTO vacation;
	
	public ReservationDTO() {
		super();
	}

	public ReservationDTO(Reservation reservation) {
		super();
		this.id = reservation.getId();
		this.places = reservation.getPlaces();
		this.vacation = new VacationDTO(reservation.getVacation());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPlaces() {
		return places;
	}

	public void setPlaces(Integer places) {
		this.places = places;
	}

	public VacationDTO getVacation() {
		return vacation;
	}

	public void setVacation(VacationDTO vacation) {
		this.vacation = vacation;
	}

}
