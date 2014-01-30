package cz.cvut.fel.jee.travel_company.bussiness;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import cz.cvut.fel.jee.travel_company.dao.impl.CustomerDaoImpl;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.dto.CustomerDTO;

@Stateless
public class CustomerManagerBean {
	
	@Inject
	private CustomerDaoImpl customerDao;
	
	public List<CustomerDTO> getAllCustomers(){
		return this.customerDao.findAllCustomers();
	}
	
	public void addCustomer(CustomerDTO customer){
		this.customerDao.addCustomer(customer);
	}
	
	public CustomerDTO findCustomer(Long id) throws EntityNotFoundException{
		return this.customerDao.findCustomer(id);
	}
	
	public void updateCustomer(CustomerDTO customer) throws EntityNotFoundException{
		this.customerDao.updateCustomer(customer);
	}
	
	public void deleteCustomer(Long id) throws EntityNotFoundException{
		this.customerDao.deleteCustomer(id);
	}

}
