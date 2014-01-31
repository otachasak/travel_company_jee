package cz.cvut.fel.jee.travel_company.dao;

import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.Reservation;

import java.util.List;

public interface ReservationDao {
	
	public List<Reservation> findAllReservations();
	public Reservation findReservation(Long id) throws EntityNotFoundException;
	public void addReservation(Reservation reservation);
	public void updateReservation(Reservation reservation) throws EntityNotFoundException;
	public void deleteReservation(Long id) throws EntityNotFoundException;

}
