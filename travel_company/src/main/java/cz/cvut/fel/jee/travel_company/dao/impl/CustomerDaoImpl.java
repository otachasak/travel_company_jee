package cz.cvut.fel.jee.travel_company.dao.impl;

import java.util.List;
import java.util.ArrayList;

import cz.cvut.fel.jee.travel_company.dao.CustomerDao;
import cz.cvut.fel.jee.travel_company.dao.impl.base.BaseDaoImpl;
import cz.cvut.fel.jee.travel_company.entities.Customer;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.dto.CustomerDTO;

public class CustomerDaoImpl extends BaseDaoImpl implements CustomerDao{

	@Override
	public List<CustomerDTO> findAllCustomers() {
		List<Customer> dbCustomers = this.em.createNamedQuery("findAllCustomers", Customer.class).getResultList();
		
		List<CustomerDTO> customers = new ArrayList<CustomerDTO>();
		for(Customer customer : dbCustomers){
			customers.add(new CustomerDTO(customer));
		}
		return customers;
	}

	@Override
	public void addCustomer(CustomerDTO customer) {
		em.persist(new Customer(customer));
	}

	@Override
	public CustomerDTO findCustomer(Long id) throws EntityNotFoundException {
		Customer dbCustomer = this.findDbCustomer(id);
		return new CustomerDTO(dbCustomer);
	}

	@Override
	public void updateCustomer(CustomerDTO customer)
			throws EntityNotFoundException {
		Customer dbCustomer = this.findDbCustomer(customer.getId());
		dbCustomer.setName(customer.getName());
		dbCustomer.setEmail(customer.getEmail());
		this.em.persist(dbCustomer);
	}

	@Override
	public void deleteCustomer(Long id) throws EntityNotFoundException {
		Customer dbCustomer = this.findDbCustomer(id);
		em.remove(dbCustomer);
	}
	
	private Customer findDbCustomer(Long id) throws EntityNotFoundException{
		Customer dbCustomer = this.em.find(Customer.class, id);
		if(dbCustomer == null){
			throw new EntityNotFoundException(Customer.class.getName(), id);
		}
		return dbCustomer;
	}

}
