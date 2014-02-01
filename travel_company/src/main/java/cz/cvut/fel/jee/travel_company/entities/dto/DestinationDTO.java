package cz.cvut.fel.jee.travel_company.entities.dto;

import cz.cvut.fel.jee.travel_company.entities.Destination;

public class DestinationDTO {
	
	private Long id;
	private String name;

	public DestinationDTO() {
		super();
	}

	public DestinationDTO(Destination destination) {
		this.name = destination.getName();
		this.id = destination.getId();
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

    @Override
    public String toString() {
        return "DestionationDTO {id:" + getId()+ ", name:" + getName() + "}";
    }
}
