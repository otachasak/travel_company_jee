package cz.cvut.fel.jee.travel_company.entities.dto;

import cz.cvut.fel.jee.travel_company.entities.Reservation;
import cz.cvut.fel.jee.travel_company.entities.Vacation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * DTO for Vacation Entity
 */
public class VacationDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

    private Date startDate;

    private Date endDate;

    private DestinationDTO destination;

    private Integer places;

    private List<ReservationDTO> reservations;
    
    private BigDecimal price;

    private int reservationsCount = 0;
    
    private double distance;

    public VacationDTO() {
		super();
	}

	public VacationDTO(Vacation srcVacation) {
    	this.id = srcVacation.getId();
        this.destination = new DestinationDTO(srcVacation.getDestination());
        this.setStartDate(srcVacation.getStartDate());
        this.setEndDate(srcVacation.getEndDate());
        this.setPlaces(srcVacation.getPlaces());
        this.reservations = new ArrayList<>();
        for(Reservation srcReservation : srcVacation.getReservations()){
            reservationsCount += srcReservation.getPlaces();
//        	this.reservations.add(new ReservationDTO(srcReservation));
        }
        this.price = srcVacation.getPrice();
        this.distance = srcVacation.getDistance();
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public DestinationDTO getDestination() {
        return destination;
    }

    public void setDestination(DestinationDTO destination) {
        this.destination = destination;
    }

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public List<ReservationDTO> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationDTO> reservations) {
        this.reservations = reservations;
    }

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}


    public String getDestinationName() {
        return destination.getName();
    }

    public int getNumberOfReservations() {
        return reservationsCount;
    }

    public int getReservationsCount() {
		return reservationsCount;
	}

	public void setReservationsCount(int reservationsCount) {
		this.reservationsCount = reservationsCount;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
    public String toString() {
        return "VacationDTO {destination:" + getDestination()
                + ", startDate:" + getStartDate()
                + ", endDate:" + getEndDate()
                + ", places:" + getPlaces() + "}";
    }

}
