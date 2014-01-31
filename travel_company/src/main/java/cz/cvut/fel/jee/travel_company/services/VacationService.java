package cz.cvut.fel.jee.travel_company.services;

import cz.cvut.fel.jee.travel_company.dao.CustomerDao;
import cz.cvut.fel.jee.travel_company.dao.DestinationDao;
import cz.cvut.fel.jee.travel_company.dao.VacationDao;
import cz.cvut.fel.jee.travel_company.entities.Customer;
import cz.cvut.fel.jee.travel_company.entities.Destination;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.Vacation;
import cz.cvut.fel.jee.travel_company.entities.dto.CustomerDTO;
import cz.cvut.fel.jee.travel_company.entities.dto.DestinationDTO;
import cz.cvut.fel.jee.travel_company.entities.dto.VacationDTO;

import javax.ejb.Stateful;
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
public class VacationService extends BasicService {

    @Inject
    private VacationDao vacationDao;
    @Inject
    private DestinationDao destinationDao;
    @Inject
    private CustomerDao customerDao;

    public List<VacationDTO> getAllVacations() {
        List<Vacation> vacations = vacationDao.findAllVacations();
        return originalToDTos(Vacation.class, VacationDTO.class, vacations);
    }

    public VacationDTO findVacationById(long vacationId) {
        try {
            Vacation v = vacationDao.findVacation(vacationId);
            return new VacationDTO(v);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public void addVacation(VacationDTO vacationDTO) {
        Vacation vacation = new Vacation(vacationDTO);
        vacation.setDestination(destinationDao.findDestination(vacationDTO.getDestination().getId()));

        vacationDao.addVacation(vacation);
    }

    public void updateVacation(VacationDTO vacationDTO) {
        try {
            Vacation v = vacationDao.findVacation(vacationDTO.getId());
            Destination d = destinationDao.findDestination(vacationDTO.getDestination().getId());
            v.setPlaces(vacationDTO.getPlaces());
            v.setStartDate(new java.sql.Date(vacationDTO.getStartDate().getTime()));
            v.setEndDate(new java.sql.Date(vacationDTO.getEndDate().getTime()));
            v.setDestination(d);
            vacationDao.updateVacation(v);
        } catch (EntityNotFoundException e) {
        }
    }

    public void deleteVacation(long vacationId) {
        try {
            vacationDao.deleteVacation(vacationId);
        } catch (EntityNotFoundException e) {
            logger.log(Level.WARNING, "Unable to delete vacation.", e);
        }
    }

    public void makeReservation(CustomerDTO customerDTO, VacationDTO vacationDTO, int places) {
        try {
            Customer customer = customerDao.findCustomer(customerDTO.getId());
            Vacation vacation = vacationDao.findVacation(vacationDTO.getId());
            vacation.makeReservation(customer, places);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Unable to make reservation.", e);
        }

    }
}
