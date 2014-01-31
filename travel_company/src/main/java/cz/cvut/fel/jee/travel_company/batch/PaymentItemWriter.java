package cz.cvut.fel.jee.travel_company.batch;

import java.math.BigDecimal;
import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Inject;

import cz.cvut.fel.jee.travel_company.dao.ReservationDao;
import cz.cvut.fel.jee.travel_company.entities.EntityNotFoundException;
import cz.cvut.fel.jee.travel_company.entities.Reservation;
import cz.cvut.fel.jee.travel_company.entities.ReservationState;

public class PaymentItemWriter extends AbstractItemWriter {
	
	@Inject
	private ReservationDao reservationDao;

	@Override
	public void writeItems(List<Object> list) throws Exception {
		for(Object o : list){
			PaymentIncomeRecord payment = (PaymentIncomeRecord) o;
			try{
				Reservation reservation = this.reservationDao.findReservation(payment.getId());
				if(reservation.getState() == ReservationState.NEW){
					BigDecimal amount = reservation.getVacation().getPrice().multiply(new BigDecimal(reservation.getPlaces()));
					if(amount.compareTo(payment.getAmount()) == 0){
						reservation.setState(ReservationState.PAID);
						this.reservationDao.updateReservation(reservation);
					}
				}
			} catch(EntityNotFoundException e){
				continue;
			}
		}
	}

}
