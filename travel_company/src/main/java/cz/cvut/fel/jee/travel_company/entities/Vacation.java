package cz.cvut.fel.jee.travel_company.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import cz.cvut.fel.jee.travel_company.entities.dto.VacationDTO;

/**
 * Entity implementation class for Entity: Vacation
 *
 */
@Entity
@NamedQuery(name="Vacation.findAllVacations", query="SELECT v FROM Vacation v")
public class Vacation extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Date startDate;
	
	@NotNull
	private Date endDate;
	
	@ManyToOne
	@NotNull
	private Destination destination;
	
	@Min(1)
	private Integer places;
	
	private BigDecimal price;
	
	@OneToMany
	private Collection<Reservation> reservations;

	public Vacation() {
		super();
	}

	public Vacation(VacationDTO vacation) {
		super();
		this.startDate = new Date(vacation.getStartDate().getTime());
		this.endDate = new Date(vacation.getEndDate().getTime());
		this.destination = new Destination(vacation.getDestination());
		this.places = vacation.getPlaces();
		this.price = vacation.getPrice();
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

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public Integer getPlaces() {
		return places;
	}

	public void setPlaces(Integer places) {
		this.places = places;
	}

	public Collection<Reservation> getReservations() {
        if(reservations == null) {
            return Collections.EMPTY_SET;
        }
		return reservations;
	}

	public void setReservations(Collection<Reservation> reservations) {
		this.reservations = reservations;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

    public int totalReservations() {
        int reservationsCount = 0;
        for(Reservation r : getReservations()) {
            reservationsCount += r.getPlaces();
        }
        return reservationsCount;
    }

    @Transient
    public boolean makeReservation(Customer c, int places) {
        if(totalReservations() < getPlaces() + places) {
            Reservation r = new Reservation();
            r.setCustomer(c);
            r.setVacation(this);
            r.setPlaces(places);

            getReservations().add(r);

            return true;
        } else {
            return false;
        }
    }
}
