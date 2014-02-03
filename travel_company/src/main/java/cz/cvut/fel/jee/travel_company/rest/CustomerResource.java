package cz.cvut.fel.jee.travel_company.rest;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import cz.cvut.fel.jee.travel_company.entities.dto.CustomerDTO;
import cz.cvut.fel.jee.travel_company.services.CustomerService;

@Path("/customer")
@Produces("application/json")
@Consumes("application/json")
public class CustomerResource {
	
	@Inject
	private CustomerService customerMB;
	
	@GET
	@Path("/")
	public Collection<CustomerDTO> getAllCustomers(){
		return this.customerMB.getAllCustomers();
	}
	
	@GET
	@Path("/{id}/")
	public CustomerDTO findCustomer(@PathParam("id") Long id){
			CustomerDTO customer = this.customerMB.getCustomerById(id);
			if(customer == null){
				throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
			return customer;
	}
	
	@POST
	@Path("/")
	public CustomerDTO addCustomer(CustomerDTO customer){
		this.customerMB.addCustomer(customer);
		return customer;
	}
	
	@PUT
	@Path("/{id}/")
	public CustomerDTO updateCustomer(@PathParam("id") Long id, CustomerDTO customer){
		customer.setId(id);
			this.customerMB.updateCustomer(customer);
		return customer;
	}
	
	@DELETE
	@Path("/{id}/")
	public void deleteCustomer(@PathParam("id") Long id){
		this.customerMB.deleteCustomer(id);
	}

}
