package cz.cvut.fel.jee.travel_company.view.beans;

import cz.cvut.fel.jee.travel_company.entities.dto.CustomerDTO;
import cz.cvut.fel.jee.travel_company.entities.dto.DestinationDTO;
import cz.cvut.fel.jee.travel_company.entities.dto.VacationDTO;
import cz.cvut.fel.jee.travel_company.services.CustomerService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @author vlada
 * @version: 31/01/14
 */
@Named
@SessionScoped
public class CustomersBB extends BasicBB {

    @Inject
    private CustomerService customerService;

    private CustomerDTO modifiedCustomer = new CustomerDTO();


    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public void addCustomer() {
        if(modifiedCustomer.getId() == null) {
            customerService.addCustomer(modifiedCustomer);
        } else {
            customerService.updateCustomer(modifiedCustomer);
        }
        modifiedCustomer = new CustomerDTO();
    }

    public void editCustomer(long customerId) {
        modifiedCustomer = customerService.getCustomerById(customerId);
    }

    public void deleteCustomer(long customerId) {
        customerService.deleteCustomer(customerId);
    }

    public String getCustomerName() {
        return modifiedCustomer.getName();
    }

    public void setCustomerName(String name) {
        modifiedCustomer.setName(name);
    }

    public String getCustomerEmail() {
        return modifiedCustomer.getEmail();
    }

    public void setCustomerEmail(String email) {
        modifiedCustomer.setEmail(email);
    }

    public String getCustomerPassword() {
        return modifiedCustomer.getPassword();
    }

    public void setCustomerPassword(String password) {
        modifiedCustomer.setPassword(password);
    }

}
