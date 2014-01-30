package cz.cvut.fel.jee.travel_company.bussiness;

import javax.ejb.Stateless;
import javax.inject.Inject;

import java.util.List;

import cz.cvut.fel.jee.travel_company.dao.ReservationDao;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.dto.ReservationDTO;

@Stateless
public class ReservationManagerBean {

	@Inject
	private ReservationDao reservationDao;
	
	public ReservationDTO findReservation(Long id) throws EntityNotFoundException{
		return this.reservationDao.findReservation(id);
	}
	
	public List<ReservationDTO> findAllReservation(){
		return this.reservationDao.findAllReservations();
	}
	
	public void addReservation(ReservationDTO reservation){
		this.reservationDao.addReservation(reservation);
	}
	
	public void updateReservation(ReservationDTO reservation) throws EntityNotFoundException{
		this.reservationDao.updateReservation(reservation);
	}
	
	public void deleteReservation(Long id) throws EntityNotFoundException{
		this.reservationDao.deleteReservation(id);
	}
	
}
