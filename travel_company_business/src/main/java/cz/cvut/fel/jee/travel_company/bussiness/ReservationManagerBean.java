package cz.cvut.fel.jee.travel_company.bussiness;

import javax.ejb.Stateless;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import cz.cvut.fel.jee.travel_company.dao.ReservationDao;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.Reservation;
import cz.cvut.fel.jee.travel_company.entities.dto.ReservationDTO;

@Stateless
public class ReservationManagerBean {

	@Inject
	private ReservationDao reservationDao;
	
	public ReservationDTO findReservation(Long id) throws EntityNotFoundException{
		Reservation dbReservation = this.reservationDao.findReservation(id);
		return new ReservationDTO(dbReservation);
	}
	
	public List<ReservationDTO> findAllReservation(){
		List<Reservation> dbReservations = this.reservationDao.findAllReservations();
		List<ReservationDTO> reservations = new ArrayList<ReservationDTO>();
		for(Reservation dbReservation : dbReservations){
			reservations.add(new ReservationDTO(dbReservation));
		}
		return reservations;
	}
	
	public void addReservation(ReservationDTO reservation){
		this.reservationDao.addReservation(new Reservation(reservation));
	}
	
	public void updateReservation(ReservationDTO reservation) throws EntityNotFoundException{
		this.reservationDao.updateReservation(new Reservation(reservation));
	}
	
	public void deleteReservation(Long id) throws EntityNotFoundException{
		this.reservationDao.deleteReservation(id);
	}
	
}
