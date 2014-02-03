package cz.cvut.fel.jee.travel_company.dao;

import cz.cvut.fel.jee.travel_company.entities.Customer;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;

import javax.annotation.security.RolesAllowed;
import java.util.List;

public interface CustomerDao {

	public List<Customer> findAllCustomers();

	public Customer findCustomer(Long id) throws EntityNotFoundException;

    public Customer findCustomerByName(String name) throws EntityNotFoundException;

	public void addCustomer(Customer customer);

	public void updateCustomer(Customer customer) throws EntityNotFoundException;

	public void deleteCustomer(Long id) throws EntityNotFoundException;
}
