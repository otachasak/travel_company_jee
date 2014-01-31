package cz.cvut.fel.jee.travel_company.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import cz.cvut.fel.jee.travel_company.entities.dto.CustomerDTO;

import java.util.List;

/**
 * Implementation of Customer Entity.
 * Possible candidate for refactoring if more "human" classes are to be added.
 */
@Entity
@NamedQuery(name="findAllCustomers", query="SELECT c FROM Customer c")
public class Customer extends BaseEntity {

    @NotNull
    private String name;

    private String email;

    @OneToMany
    private List<Reservation> reservations;

    public Customer(CustomerDTO customer) {
		super();
		this.name = customer.getName();
		this.email = customer.getEmail();
	}

	public Customer() {
		super();
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
