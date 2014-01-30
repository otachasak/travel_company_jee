package cz.cvut.fel.jee.travel_company.dao.impl;

import java.util.ArrayList;
import java.util.List;

import cz.cvut.fel.jee.travel_company.dao.ReservationDao;
import cz.cvut.fel.jee.travel_company.dao.impl.base.BaseDaoImpl;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.Reservation;
import cz.cvut.fel.jee.travel_company.entities.dto.ReservationDTO;


public class ReservationDaoImpl extends BaseDaoImpl implements ReservationDao {

	@Override
	public List<ReservationDTO> findAllReservations() {
		List<Reservation> dbReservations = this.em.createNamedQuery("findAllReservations", Reservation.class).getResultList();
		
		List<ReservationDTO> reservations = new ArrayList<ReservationDTO>();
		for(Reservation reservation : dbReservations){
			reservations.add(new ReservationDTO(reservation));
		}
		return reservations;
	}

	@Override
	public void addReservation(ReservationDTO reservation) {
		this.em.persist(new Reservation(reservation));
	}

	@Override
	public ReservationDTO findReservation(Long id)
			throws EntityNotFoundException {
		Reservation dbReservation = this.findDbReservation(id);
		return new ReservationDTO(dbReservation);
	}

	@Override
	public void updateReservation(ReservationDTO reservation)
			throws EntityNotFoundException {
		Reservation dbReservation = this.findDbReservation(reservation.getId());
		dbReservation.setPlaces(reservation.getPlaces());
	}

	@Override
	public void deleteReservation(Long id) throws EntityNotFoundException {
		Reservation dbReservation = this.findDbReservation(id);
		this.em.remove(dbReservation);
	}
	
	/**
	 * Find reservation with given id otherwise throws exception
	 * @param id
	 * @return
	 * @throws EntityNotFoundException
	 */
	private Reservation findDbReservation(Long id) throws EntityNotFoundException{
		Reservation dbReservation = this.em.find(Reservation.class, id);
		if(dbReservation == null){
			throw new EntityNotFoundException(Reservation.class.getName(), id);
		}
		return dbReservation;
	}

}
