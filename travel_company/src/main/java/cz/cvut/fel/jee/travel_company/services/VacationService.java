package cz.cvut.fel.jee.travel_company.services;

import cz.cvut.fel.jee.travel_company.dao.CustomerDao;
import cz.cvut.fel.jee.travel_company.dao.DestinationDao;
import cz.cvut.fel.jee.travel_company.dao.VacationDao;
import cz.cvut.fel.jee.travel_company.entities.Customer;
import cz.cvut.fel.jee.travel_company.entities.Destination;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.Vacation;
import cz.cvut.fel.jee.travel_company.entities.dto.CustomerDTO;
import cz.cvut.fel.jee.travel_company.entities.dto.VacationDTO;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
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

    @PermitAll
    public List<VacationDTO> getAllVacations() {
        List<Vacation> vacations = vacationDao.findAllVacations();
        List<VacationDTO> ret = originalToDTos(Vacation.class, VacationDTO.class, vacations);
        logger.info("ret.size()=" + ret.size());
        return ret;
    }

    @PermitAll
    public VacationDTO findVacationById(long vacationId) throws EntityNotFoundException {
            Vacation v = vacationDao.findVacation(vacationId);
            return new VacationDTO(v);
    }

    @RolesAllowed({"root"})
    public void addVacation(VacationDTO vacationDTO) {
        Vacation vacation = new Vacation(vacationDTO);
        vacation.setDestination(destinationDao.findDestination(vacationDTO.getDestination().getId()));

        vacationDao.addVacation(vacation);
    }

    @RolesAllowed({"root"})
    public void updateVacation(VacationDTO vacationDTO) throws EntityNotFoundException {
            Vacation v = vacationDao.findVacation(vacationDTO.getId());
            Destination d = destinationDao.findDestination(vacationDTO.getDestination().getId());
            v.setPlaces(vacationDTO.getPlaces());
            v.setPrice(vacationDTO.getPrice());
            v.setStartDate(new java.sql.Date(vacationDTO.getStartDate().getTime()));
            v.setEndDate(new java.sql.Date(vacationDTO.getEndDate().getTime()));
            v.setDestination(d);
            vacationDao.updateVacation(v);
    }

    @RolesAllowed({"root"})
    public void deleteVacation(long vacationId) throws EntityNotFoundException {
            vacationDao.deleteVacation(vacationId);
    }
}
