package cz.cvut.fel.jee.travel_company.entities.dto;

import java.io.Serializable;

import cz.cvut.fel.jee.travel_company.entities.Customer;

/**
 * DTO for Customer
 * @author Ota
 *
 */

public class CustomerDTO implements Serializable {
    public static final String passwordHidden = "*******";
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String email;
    private String password;
	
	public CustomerDTO() {
		super();
	}

	public CustomerDTO(Customer customer) {
		super();
		this.id = customer.getId();
		this.name = customer.getName();
		this.email = customer.getEmail();
        //this.password = customer.getPassword();
        this.password = passwordHidden; //:-)
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
