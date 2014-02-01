package cz.cvut.fel.jee.travel_company.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Super class for all entities
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean equals(Object o) {
        if(o instanceof BaseEntity) {
            BaseEntity other = (BaseEntity)o;
            return getId() == other.getId();
        }
        return super.equals(o);
    }
}
