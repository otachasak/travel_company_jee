package cz.cvut.fel.jee.travel_company.dao;

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
}
