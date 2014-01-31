package cz.cvut.fel.jee.travel_company.view.beans;

import cz.cvut.fel.jee.travel_company.entities.dto.DestinationDTO;
import cz.cvut.fel.jee.travel_company.entities.dto.VacationDTO;
import cz.cvut.fel.jee.travel_company.services.DestinationService;
import cz.cvut.fel.jee.travel_company.services.VacationService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.*;

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
           vacationService.updateVacation(modifiedVacation);
        }
        modifiedVacation = new VacationDTO();
    }

    public void deleteVacation(long vacationId) {
        vacationService.deleteVacation(vacationId);
    }

    public void editVacation(long vacationId) {
        modifiedVacation = vacationService.findVacationById(vacationId);
    }

    public List<String> getAvailableDestinations() {
        List<DestinationDTO> destinations = destinationService.findAllDestinations();
        List<String> result = new ArrayList<>();
        for(DestinationDTO destination : destinations) {
            result.add(destination.getName());
            nameToDestination.put(destination.getName(), destination);
        }
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
}
