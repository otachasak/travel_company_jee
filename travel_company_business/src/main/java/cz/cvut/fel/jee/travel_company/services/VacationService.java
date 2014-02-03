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
import cz.cvut.fel.jee.travel_company.util.Distance;
import cz.cvut.fel.jee.travel_company.util.MapApiResponse;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.google.gson.Gson;

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
        Destination destination = destinationDao.findDestination(vacationDTO.getDestination().getId());
        vacation.setDestination(destination);
   /*     double distance = this.getDistance(destination);
        vacation.setDistance(distance);*/
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
    
    private double getDistance(Destination destination){
    	double pragueLatitude = 50.0755381;
    	double pragueLongtitude = 14.4378005;
    	
    	Client client = ClientBuilder.newBuilder().build();
        WebTarget target = client.target("http://aos.czacm.org/flight-distance");
         String response = target.queryParam("from", pragueLatitude + "," + pragueLongtitude).queryParam("to", destination.getLatitude() + "," + destination.getLongtitude()).request("application/json").get(String.class);

        Gson gson = new Gson();
        Distance distance = gson.fromJson(response, Distance.class);
        return distance.getLength();
    }
    
}
