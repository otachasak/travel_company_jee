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

    private VacationDTO newVacation = new VacationDTO();

    private Map<String, DestinationDTO> nameToDestination = new HashMap<>();

    public List<VacationDTO> getAllVacations() {
        return vacationService.getAllVacations();
    }

    public void addVacation() {
        logger.info("addVacation(): " + newVacation);
        vacationService.addVacation(newVacation);
        newVacation = new VacationDTO();
    }

    public void deleteVacation(long vacationId) {
        vacationService.deleteVacation(vacationId);
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
        if(newVacation == null) {
            return null;
        }
        if(newVacation.getDestination() == null) {
            return null;
        }

        return newVacation.getDestination().getName();
    }

    public void setNewVacationDestination(String newVacationDestination) {
        newVacation.setDestination(nameToDestination.get(newVacationDestination));
    }

    public Date getStartDate() {
        return newVacation.getStartDate();
    }

    public void setStartDate(Date startDate) {
        newVacation.setStartDate(startDate);
    }

    public Date getEndDate() {
        return newVacation.getEndDate();
    }

    public void setEndDate(Date endDate) {
        newVacation.setEndDate(endDate);
    }

    public Integer getPlaces() {
        return newVacation.getPlaces();
    }

    public void setPlaces(Integer places) {
        newVacation.setPlaces(places);
    }
}
