package cz.cvut.fel.jee.travel_company.dao;

import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.dto.ReservationDTO;

import java.util.List;

public interface ReservationDao {
	
	public List<ReservationDTO> findAllReservations();
	public ReservationDTO findReservation(Long id) throws EntityNotFoundException;
	public void addReservation(ReservationDTO reservation);
	public void updateReservation(ReservationDTO reservation) throws EntityNotFoundException;
	public void deleteReservation(Long id) throws EntityNotFoundException;

}
