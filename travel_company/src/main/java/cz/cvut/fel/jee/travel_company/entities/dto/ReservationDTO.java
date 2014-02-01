package cz.cvut.fel.jee.travel_company.entities.dto;

import cz.cvut.fel.jee.travel_company.entities.Reservation;
import cz.cvut.fel.jee.travel_company.entities.ReservationState;

public class ReservationDTO {
	
	private Long id;
	private Integer places;
	private VacationDTO vacation;
	private ReservationState state;
    private CustomerDTO customer;
	
	public ReservationDTO() {
		super();
		this.state = ReservationState.NEW;
	}

	public ReservationDTO(Reservation reservation) {
		super();
		this.id = reservation.getId();
		this.places = reservation.getPlaces();
		this.vacation = new VacationDTO(reservation.getVacation());
		this.state = reservation.getState();
        this.customer = new CustomerDTO(reservation.getCustomer());
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

	public ReservationState getState() {
		return state;
	}

	public void setState(ReservationState state) {
		this.state = state;
	}

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
}
