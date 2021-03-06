package cz.cvut.fel.jee.travel_company.view.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.dto.DestinationDTO;
import cz.cvut.fel.jee.travel_company.entities.dto.VacationDTO;
import cz.cvut.fel.jee.travel_company.services.DestinationService;
import cz.cvut.fel.jee.travel_company.services.VacationService;

/**
 *
 */
@ManagedBean(name = "vacationsBB")
@SessionScoped
public class VacationsBB extends BasicBB {

    @Inject
    private VacationService vacationService;

    @Inject
    private DestinationService destinationService;

    private VacationDTO modifiedVacation = new VacationDTO();

    private Map<String, DestinationDTO> nameToDestination = new HashMap<>();

    public List<VacationDTO> getAllVacations() {
        return vacationService.getAllVacations();
    }

    public void addVacation() {
        if(modifiedVacation.getId() == null) {
            vacationService.addVacation(modifiedVacation);
        } else {
           try {
			vacationService.updateVacation(modifiedVacation);
		} catch (EntityNotFoundException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
		}
        }
        modifiedVacation = new VacationDTO();
    }

    public void deleteVacation(long vacationId) {
        try {
			vacationService.deleteVacation(vacationId);
		} catch (EntityNotFoundException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
		}
    }

    public void editVacation(long vacationId) {
        try {
			modifiedVacation = vacationService.findVacationById(vacationId);
		} catch (EntityNotFoundException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
		}
    }

    public List<String> getAvailableDestinations() {
        List<DestinationDTO> destinations = destinationService.findAllDestinations();
        List<String> result = convertToString(destinations, nameToDestination,
                new ToString<DestinationDTO>() {
            @Override
            public String toString(DestinationDTO destinationDTO) {
                return destinationDTO.getName();
            }
        });
        return result;
    }

    public String getNewVacationDestination() {
        if(modifiedVacation == null) {
            return null;
        }
        if(modifiedVacation.getDestination() == null) {
            return null;
        }

        return modifiedVacation.getDestination().getName();
    }

    public void setNewVacationDestination(String newVacationDestination) {
        modifiedVacation.setDestination(nameToDestination.get(newVacationDestination));
    }

    public Date getStartDate() {
        return modifiedVacation.getStartDate();
    }

    public void setStartDate(Date startDate) {
        modifiedVacation.setStartDate(startDate);
    }

    public Date getEndDate() {
        return modifiedVacation.getEndDate();
    }

    public void setEndDate(Date endDate) {
        modifiedVacation.setEndDate(endDate);
    }

    public Integer getPlaces() {
        return modifiedVacation.getPlaces();
    }

    public void setPlaces(Integer places) {
        modifiedVacation.setPlaces(places);
    }
    
    public BigDecimal getPrice(){
    	return this.modifiedVacation.getPrice();
    }
    
    public void setPrice(BigDecimal price){
    	this.modifiedVacation.setPrice(price);
    }
}
