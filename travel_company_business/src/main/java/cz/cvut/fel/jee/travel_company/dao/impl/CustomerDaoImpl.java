package cz.cvut.fel.jee.travel_company.dao.impl;

import cz.cvut.fel.jee.travel_company.dao.CustomerDao;
import cz.cvut.fel.jee.travel_company.dao.impl.base.BaseDaoImpl;
import cz.cvut.fel.jee.travel_company.entities.Customer;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.inject.Named;
import java.util.List;

@Named
@Stateful
public class CustomerDaoImpl extends BaseDaoImpl implements CustomerDao{

    @Override
    @PermitAll
	public List<Customer> findAllCustomers() {
		List<Customer> customers = this.em.createNamedQuery("findAllCustomers", Customer.class).getResultList();
		return customers;
	}

	@Override
    @RolesAllowed({"root"})
	public void addCustomer(Customer customer) {
		em.persist(customer);
	}

	@Override
    @PermitAll
	public Customer findCustomer(Long id) throws EntityNotFoundException {
		Customer dbCustomer = this.findDbCustomer(id);
		return dbCustomer;
	}

    @Override
    @PermitAll
    public Customer findCustomerByName(String name) throws EntityNotFoundException {
        return (Customer)em.createQuery("select c from Customer c " +
                "where c.name = :name")
                .setParameter("name", name).getSingleResult();
    }


    @Override
    @RolesAllowed({"root"})
	public void updateCustomer(Customer customer)
			throws EntityNotFoundException {
		Customer dbCustomer = this.findDbCustomer(customer.getId());
		dbCustomer.setName(customer.getName());
		dbCustomer.setEmail(customer.getEmail());
		this.em.persist(dbCustomer);
	}

	@Override
    @RolesAllowed({"root"})
	public void deleteCustomer(Long id) throws EntityNotFoundException {
		Customer dbCustomer = this.findDbCustomer(id);
		em.remove(dbCustomer);
	}

    @PermitAll
    private Customer findDbCustomer(Long id) throws EntityNotFoundException{
		Customer dbCustomer = this.em.find(Customer.class, id);
		if(dbCustomer == null){
			throw new EntityNotFoundException(Customer.class.getName(), id);
		}
		return dbCustomer;
	}

}
