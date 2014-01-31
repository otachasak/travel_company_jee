package cz.cvut.fel.jee.travel_company.view.beans;

import cz.cvut.fel.jee.travel_company.dao.DestinationDao;
import cz.cvut.fel.jee.travel_company.dao.VacationDao;
import cz.cvut.fel.jee.travel_company.entities.Destination;
import cz.cvut.fel.jee.travel_company.entities.Vacation;
import cz.cvut.fel.jee.travel_company.entities.dto.DestinationDTO;
import cz.cvut.fel.jee.travel_company.entities.dto.VacationDTO;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DestinationsBB {
    @Inject
    DestinationDao destinationDaoDao;


   List<DestinationDTO> getAllDestinations() {

       List<Destination> destinations = destinationDaoDao.findAllDestinations();

       List<DestinationDTO> destinationDTOs = new ArrayList<>(destinations.size());
       for(Destination destination : destinations) {
           destinationDTOs.add(new DestinationDTO(destination));
       }

       return destinationDTOs;
   }
}
