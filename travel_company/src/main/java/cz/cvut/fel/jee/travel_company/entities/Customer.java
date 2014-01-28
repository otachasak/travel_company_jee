package cz.cvut.fel.jee.travel_company.entities;

import javax.validation.constraints.NotNull;

/**
 * Implementation of Customer Entity.
 * Possible candidate for refactoring if more "human" classes are to be added.
 */
public class Customer extends BaseEntity {

    @NotNull
    private String name;

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
