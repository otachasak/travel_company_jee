package cz.cvut.fel.jee.travel_company.services;

import cz.cvut.fel.jee.travel_company.dao.CustomerDao;
import cz.cvut.fel.jee.travel_company.entities.Customer;
import cz.cvut.fel.jee.travel_company.entities.Destination;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.Vacation;
import cz.cvut.fel.jee.travel_company.entities.dto.CustomerDTO;
import cz.cvut.fel.jee.travel_company.entities.dto.DestinationDTO;
import cz.cvut.fel.jee.travel_company.entities.dto.VacationDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * @author vlada
 * @version: 31/01/14
 */

@Named
@Stateless
public class CustomerService extends BasicService {

    @Inject
    private CustomerDao customerDao;

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerDao.findAllCustomers();
        return originalToDTos(Customer.class, CustomerDTO.class, customers);
    }

    public CustomerDTO getCustomerById(Long customerId) {
        try {
            Customer c = customerDao.findCustomer(customerId);
            return new CustomerDTO(c);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public void addCustomer(CustomerDTO newCustomer) {
        Customer customer = new Customer();
        customer.setName(newCustomer.getName());
        customer.setEmail(newCustomer.getEmail());
        customerDao.addCustomer(customer);
    }

    public void updateCustomer(CustomerDTO customerDTO) {
        try {
            Customer customer = customerDao.findCustomer(customerDTO.getId());
            customer.setName(customerDTO.getName());
            customer.setEmail(customerDTO.getEmail());
            customerDao.updateCustomer(customer);
        } catch (EntityNotFoundException e) {
        }
    }

    public void deleteCustomer(long customerId) {
        try {
            customerDao.deleteCustomer(customerId);
        } catch (EntityNotFoundException e) {
            logger.log(Level.WARNING, "Unable to delete customer.", e);
        }
    }
}
