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

	public Destination() {
		super();
	}

	public Destination(DestinationDTO destination) {
		super();
		this.setId(destination.getId());
		this.name = destination.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	   
}
