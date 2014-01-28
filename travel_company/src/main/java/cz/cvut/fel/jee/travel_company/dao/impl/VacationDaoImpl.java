package cz.cvut.fel.jee.travel_company.dao.impl;

import cz.cvut.fel.jee.travel_company.entities.Vacation;
import cz.cvut.fel.jee.travel_company.entities.dto.VacationDTO;
import cz.cvut.fel.jee.travel_company.dao.VacationDao;
import cz.cvut.fel.jee.travel_company.dao.impl.base.BaseDaoImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vlada
 * Date: 28/01/14
 * Time: 22:10
 * To change this template use File | Settings | File Templates.
 */
public class VacationDaoImpl extends BaseDaoImpl implements VacationDao {
    @Override
    public List<VacationDTO> findAllVacations() {
        List<Vacation> vacations = em.createNamedQuery("Vacation.findAllVacations", Vacation.class)
                .getResultList();

        List<VacationDTO> vacationDTOs = new ArrayList<>(vacations.size());
        for(Vacation vacation : vacations) {
            vacationDTOs.add(new VacationDTO(vacation));
        }

        return vacationDTOs;
    }
}
