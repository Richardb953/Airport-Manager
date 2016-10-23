package cz.muni.airport.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 *
 * @author Andrea Navrátilová
 */

@Entity
public class Steward {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    public Steward() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
	
	public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	@Override
    public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
	}

	@Override
    public boolean equals(Object obj) {
		if ((obj == null) || !(obj instanceof Steward)) {
            return false;
        }
        if (obj == this) {
            return true;
		}

        Steward steward = (Steward) obj;
		if (id != null) {
			return id.equals(steward.getId());
		} else {
			return steward.getId() == null;
		}
	}
}
