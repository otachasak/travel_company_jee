package cz.cvut.fel.jee.travel_company.dao;

import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.dto.CustomerDTO;

import java.util.List;

public interface CustomerDao {

	public List<CustomerDTO> findAllCustomers();
	public CustomerDTO findCustomer(Long id) throws EntityNotFoundException;
	public void addCustomer(CustomerDTO customer);
	public void updateCustomer(CustomerDTO customer) throws EntityNotFoundException;
	public void deleteCustomer(Long id) throws EntityNotFoundException;
	
}
