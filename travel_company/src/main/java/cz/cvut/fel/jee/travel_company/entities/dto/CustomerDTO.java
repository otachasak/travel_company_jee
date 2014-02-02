package cz.cvut.fel.jee.travel_company.entities.dto;

import java.io.Serializable;

import cz.cvut.fel.jee.travel_company.entities.Customer;

/**
 * DTO for Customer
 * @author Ota
 *
 */
public class CustomerDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String email;
	
	public CustomerDTO() {
		super();
	}

	public CustomerDTO(Customer customer) {
		super();
		this.id = customer.getId();
		this.name = customer.getName();
		this.email = customer.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
