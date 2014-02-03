package cz.cvut.fel.jee.travel_company.view.beans;

import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.dto.CustomerDTO;
import cz.cvut.fel.jee.travel_company.entities.dto.ReservationDTO;
import cz.cvut.fel.jee.travel_company.entities.dto.VacationDTO;
import cz.cvut.fel.jee.travel_company.services.CustomerService;
import cz.cvut.fel.jee.travel_company.services.ReservationService;
import cz.cvut.fel.jee.travel_company.services.VacationService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private Integer places;

    private List<ReservationDTO> selectedReservations;

    public List<ReservationDTO> getSelectedReservations() {
        return selectedReservations;
    }

    public void performFiltering() {
        if(selectedVacation == null && selectedCustomer == null) {
            selectedReservations = reservationService.getAllReservations();
        }
        if(selectedVacation != null && selectedCustomer == null) {
            selectedReservations = reservationService.getReservationsOfVacation(selectedVacation.getId());
        }
        if(selectedCustomer != null && selectedVacation == null) {
            selectedReservations = reservationService.getCustomersReservations(selectedCustomer.getId());
        }
        if(selectedCustomer != null && selectedVacation != null) {
            selectedReservations = reservationService.getReservationsOfVacationForCustomer(selectedVacation.getId(),
                    selectedCustomer.getId());
        }
    }

    public void createReservation() {
        if(selectedCustomer == null || selectedVacation == null) {
            return;
        }
        reservationService.createReservation(selectedCustomer.getId(),
                selectedVacation.getId(),
                getPlaces());
        setPlaces(null);
        performFiltering();
    }

    public void markAsPayed(long reservationId) {
        reservationService.markAsPayed(reservationId);
        performFiltering();
    }

    public void markAsNew(long reservationId) {
        reservationService.markAsNew(reservationId);
        performFiltering();
    }


    public void deleteReservation(Long reservationId) {
        try {
			reservationService.deleteReservation(reservationId);
		} catch (EntityNotFoundException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
		}
        performFiltering();
    }

    public List<String> getAllCustomers() {
        nameToCustomer.clear();
        List<CustomerDTO> customers = customerService.getAllCustomers();
        List<String> result = convertToString(customers, nameToCustomer, new ToString<CustomerDTO>() {
            @Override
            public String toString(CustomerDTO customerDTO) {
                return customerDTO.getName() + ", " + customerDTO.getEmail();
            }
        });
        result.add("X");
        nameToCustomer.put("X", null);
        return result;
    }

    public String getSelectedCustomer() {
        if (selectedCustomer == null) {
            return "X";
        }
        return selectedCustomer.getName() + ", " + selectedCustomer.getEmail();
    }

    public void setSelectedCustomer(String customer) {
        selectedCustomer = nameToCustomer.get(customer);
    }

    public List<String> getAllVacations() {
        nameToVacation.clear();
        List<VacationDTO> vacations = vacationService.getAllVacations();
        List<String> result = convertToString(vacations, nameToVacation, new ToString<VacationDTO>() {
            @Override
            public String toString(VacationDTO vacationDTO) {
                return vacationDTO.getDestinationName() + ", " + vacationDTO.getStartDate() + " - "
                        + vacationDTO.getEndDate() + ", " + vacationDTO.getId();
            }
        });
        result.add("X");
        nameToVacation.put("X", null);
        return result;
    }

    public String getSelectedVacation() {
        if (selectedVacation == null) {
            return "X";
        }
        String label = selectedVacation.getDestinationName() + ", " + selectedVacation.getStartDate() + " - "
                + selectedVacation.getEndDate() + ", " + selectedVacation.getId();

        return label;
    }

    public void setSelectedVacation(String vacation) {
        selectedVacation = nameToVacation.get(vacation);
    }

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }
}
