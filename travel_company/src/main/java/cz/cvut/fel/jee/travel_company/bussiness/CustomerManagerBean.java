package cz.cvut.fel.jee.travel_company.bussiness;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import cz.cvut.fel.jee.travel_company.dao.CustomerDao;
import cz.cvut.fel.jee.travel_company.dao.impl.CustomerDaoImpl;
import cz.cvut.fel.jee.travel_company.entities.Customer;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.dto.CustomerDTO;

@Stateless
public class CustomerManagerBean {
	
	@Inject
	private CustomerDao customerDao;
	
	public List<CustomerDTO> getAllCustomers(){
		List<Customer> dbCustomers = this.customerDao.findAllCustomers();
		List<CustomerDTO> customers = new ArrayList<>();
		for(Customer dbCustomer : dbCustomers){
			customers.add(new CustomerDTO(dbCustomer));
		}
		return customers;
	}
	
	public void addCustomer(CustomerDTO customer){
		this.customerDao.addCustomer(new Customer(customer));
	}
	
	public CustomerDTO findCustomer(Long id) throws EntityNotFoundException{
		Customer dbCustomer = this.customerDao.findCustomer(id);
		return new CustomerDTO(dbCustomer);
	}
	
	public void updateCustomer(CustomerDTO customer) throws EntityNotFoundException{
		this.customerDao.updateCustomer(new Customer(customer));
	}
	
	public void deleteCustomer(Long id) throws EntityNotFoundException{
		this.customerDao.deleteCustomer(id);
	}

}
