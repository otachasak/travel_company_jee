package cz.cvut.fel.jee.travel_company.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import cz.cvut.fel.jee.travel_company.entities.dto.DestinationDTO;

/**
 * Entity implementation class for Entity: Destination
 *
 */
@Entity
@NamedQuery(name="findAllDestinations", query="SELECT d FROM Destination d")
public class Destination extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@NotNull
	private String name;
	private double latitude;
	private double longtitude;

	public Destination() {
		super();
	}

	public Destination(DestinationDTO destination) {
		super();
		this.setId(destination.getId());
		this.name = destination.getName();
		this.latitude = destination.getLatitude();
		this.longtitude = destination.getLongtitude();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}
	   
}
