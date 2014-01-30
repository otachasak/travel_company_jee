package cz.cvut.fel.jee.travel_company.dao;

import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.dto.VacationDTO;

import java.util.List;

/**
 * Vacation DAO for retrieving vacations from databse
 */
public interface VacationDao {

    /**
     * Find all vacations in the system.
     * @return List of all vacations dtos
     */
    public List<VacationDTO> findAllVacations();
    
    public VacationDTO findVacation(Long id) throws EntityNotFoundException;
    
    /**
     * Inserts vacation into DB
     * @param vacation - vacationDTO to be added
     */
    public void addVacation(VacationDTO vacation);
    
    public void updateVacation(VacationDTO vacation) throws EntityNotFoundException;
    
    public void deleteVacation(Long id) throws EntityNotFoundException;
    
    
}
