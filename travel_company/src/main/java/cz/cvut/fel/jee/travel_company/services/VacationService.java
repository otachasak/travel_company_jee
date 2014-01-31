package cz.cvut.fel.jee.travel_company.services;

import cz.cvut.fel.jee.travel_company.dao.DestinationDao;
import cz.cvut.fel.jee.travel_company.dao.VacationDao;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.Vacation;
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


    public List<VacationDTO> getAllVacations() {
        List<Vacation> vacations = vacationDao.findAllVacations();

        List<VacationDTO> vacationDTOs = new ArrayList<>(vacations.size());
        for(Vacation vacation : vacations) {
            vacationDTOs.add(new VacationDTO(vacation));
        }

        return vacationDTOs;
    }

    public void addVacation(VacationDTO vacationDTO) {
        Vacation vacation = new Vacation(vacationDTO);
        vacation.setDestination(destinationDao.findDestination(vacationDTO.getDestination().getId()));

        vacationDao.addVacation(vacation);
    }

    public void deleteVacation(long vacationId) {
        try {
            vacationDao.deleteVacation(vacationId);
        } catch (EntityNotFoundException e) {
            logger.log(Level.WARNING, "Unable to delete vacation.", e);
        }
    }
}
