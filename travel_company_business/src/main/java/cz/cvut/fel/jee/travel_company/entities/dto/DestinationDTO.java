package cz.cvut.fel.jee.travel_company.entities.dto;

import java.io.Serializable;

import cz.cvut.fel.jee.travel_company.entities.Destination;

public class DestinationDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private double latitude;
	private double longtitude;

	public DestinationDTO() {
		super();
	}

	public DestinationDTO(Destination destination) {
		this.name = destination.getName();
		this.id = destination.getId();
		this.latitude = destination.getLatitude();
		this.longtitude = destination.getLongtitude();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
    public String toString() {
        return "DestionationDTO {id:" + getId()+ ", name:" + getName() + "}";
    }
}
