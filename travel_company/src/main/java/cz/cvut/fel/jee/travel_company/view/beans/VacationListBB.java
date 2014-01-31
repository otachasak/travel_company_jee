package cz.cvut.fel.jee.travel_company.view.beans;

import cz.cvut.fel.jee.travel_company.dao.VacationDao;
import cz.cvut.fel.jee.travel_company.entities.Vacation;
import cz.cvut.fel.jee.travel_company.entities.dto.VacationDTO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@ManagedBean(name = "vacationListBB")
@SessionScoped
public class VacationListBB {

    @Inject
    VacationDao vacationDao;

    public List<VacationDTO> getAllVacations() {
        List<Vacation> vacations = vacationDao.findAllVacations();

        List<VacationDTO> vacationDTOs = new ArrayList<>(vacations.size());
        for(Vacation vacation : vacations) {
            vacationDTOs.add(new VacationDTO(vacation));
        }

        return vacationDTOs;
    }
}
