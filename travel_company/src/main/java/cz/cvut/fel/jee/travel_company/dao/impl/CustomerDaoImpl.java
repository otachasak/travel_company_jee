package cz.cvut.fel.jee.travel_company.dao.impl;

import java.util.List;
import cz.cvut.fel.jee.travel_company.dao.CustomerDao;
import cz.cvut.fel.jee.travel_company.dao.impl.base.BaseDaoImpl;
import cz.cvut.fel.jee.travel_company.entities.Customer;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;

import javax.ejb.Stateful;
import javax.inject.Named;

@Named
@Stateful
public class CustomerDaoImpl extends BaseDaoImpl implements CustomerDao{

	@Override
	public List<Customer> findAllCustomers() {
		List<Customer> customers = this.em.createNamedQuery("findAllCustomers", Customer.class).getResultList();
		return customers;
	}

	@Override
	public void addCustomer(Customer customer) {
		em.persist(customer);
	}

	@Override
	public Customer findCustomer(Long id) throws EntityNotFoundException {
		Customer dbCustomer = this.findDbCustomer(id);
		return dbCustomer;
	}

	@Override
	public void updateCustomer(Customer customer)
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
