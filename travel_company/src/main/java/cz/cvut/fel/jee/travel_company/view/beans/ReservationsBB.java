package cz.cvut.fel.jee.travel_company.view.beans;

import cz.cvut.fel.jee.travel_company.entities.dto.CustomerDTO;
import cz.cvut.fel.jee.travel_company.entities.dto.DestinationDTO;
import cz.cvut.fel.jee.travel_company.entities.dto.ReservationDTO;
import cz.cvut.fel.jee.travel_company.entities.dto.VacationDTO;
import cz.cvut.fel.jee.travel_company.services.CustomerService;
import cz.cvut.fel.jee.travel_company.services.ReservationService;
import cz.cvut.fel.jee.travel_company.services.VacationService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vlada
 * @version: 31/01/14
 */
@Named
@SessionScoped
public class ReservationsBB extends BasicBB {

    @Inject
    VacationService vacationService;
    @Inject
    private ReservationService reservationService;
    @Inject
    private CustomerService customerService;
    private CustomerDTO selectedCustomer;
    private VacationDTO selectedVacation;
    private Map<String, CustomerDTO> nameToCustomer = new HashMap<>();
    private Map<String, VacationDTO> nameToVacation = new HashMap<>();

    public List<ReservationDTO> getCustomersReservations(long customerId) {
        return reservationService.getCustomersReservations(customerId);
    }

    public List<String> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        List<String> result = new ArrayList<>();
        for (CustomerDTO customer : customers) {
            String label = customer.getName() + ", " + customer.getEmail();
            result.add(label);
            nameToCustomer.put(label, customer);
        }
        result.add("");
        nameToCustomer.put("", null);
        return result;
    }

    public String getSelectedCustomer() {
        if (selectedCustomer == null) {
            return "";
        }
        return selectedCustomer.getName() + ", " + selectedCustomer.getEmail();
    }

    public void setSelectedCustomer(String customer) {
        selectedCustomer = nameToCustomer.get(customer);
    }

    public List<String> getAllVacations() {
        List<VacationDTO> vacations = vacationService.getAllVacations();
        List<String> result = new ArrayList<>();
        for (VacationDTO vacation : vacations) {
            String label = vacation.getDestinationName() + ", " + vacation.getStartDate() + " - "
                    + vacation.getEndDate() + ", " + vacation.getId();
            result.add(label);
            nameToVacation.put(label, vacation);
        }
        result.add("");
        nameToCustomer.put("", null);
        return result;
    }

    public String getSelectedVacation() {
        if (selectedVacation == null) {
            return "";
        }
        String label = selectedVacation.getDestinationName() + ", " + selectedVacation.getStartDate() + " - "
                + selectedVacation.getEndDate() + ", " + selectedVacation.getId();

        return label;
    }

    public void setSelectedVacation(String vacation) {
        selectedVacation = nameToVacation.get(vacation);
    }
}
