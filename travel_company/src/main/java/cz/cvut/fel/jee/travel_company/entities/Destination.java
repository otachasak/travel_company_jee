package cz.cvut.fel.jee.travel_company.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	   
}
