package cz.cvut.fel.jee.travel_company.dao;

import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.Vacation;

import java.util.List;

/**
 * Vacation DAO for retrieving vacations from databse
 */
public interface VacationDao {

    /**
     * Find all vacations in the system.
     * @return List of all vacations dtos
     */
    public List<Vacation> findAllVacations();
    
    public Vacation findVacation(Long id) throws EntityNotFoundException;
    
    /**
     * Inserts vacation into DB
     * @param vacation - vacation to be added
     */
    public void addVacation(Vacation vacation);
    
    public void updateVacation(Vacation vacation) throws EntityNotFoundException;
    
    public void deleteVacation(Long id) throws EntityNotFoundException;
    
    
}
