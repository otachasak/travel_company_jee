package cz.cvut.fel.jee.travel_company.entities.dto;

import cz.cvut.fel.jee.travel_company.entities.Vacation;

import java.sql.Date;

/**
 * DTO for Vacation Entity
 */
public class VacationDTO {

    private Date startDate;

    private Date endDate;

    private Long destinationId;

    private String destinationName;

    private Integer places;

    private int numberOfReservations;


    public VacationDTO(Vacation srcVacation) {
        this.setDestinationName(srcVacation.getDestination().getName());
        this.setDestinationId(srcVacation.getDestination().getId());
        this.setStartDate(srcVacation.getStartDate());
        this.setEndDate(srcVacation.getEndDate());
        this.setPlaces(srcVacation.getPlaces());
        this.setNumberOfReservations(srcVacation.getReservations().size());
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Long destinationId) {
        this.destinationId = destinationId;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public int getNumberOfReservations() {
        return numberOfReservations;
    }

    public void setNumberOfReservations(int numberOfReservations) {
        this.numberOfReservations = numberOfReservations;
    }
}
