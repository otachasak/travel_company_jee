package cz.cvut.fel.jee.travel_company.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import cz.cvut.fel.jee.travel_company.entities.dto.CustomerDTO;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.password = new BASE64Encoder().encode(md.digest((password).getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isPasswordValid(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return password.equals(new BASE64Encoder().encode(md.digest(password.getBytes("UTF-8"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
