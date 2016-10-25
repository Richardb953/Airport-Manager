package java.cz.muni.airport.model;

import javafx.util.converter.DateTimeStringConverter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * @author Ricahrd Bariny on 23.10.2016.
 *
 * Entity of Flight
 *
 */

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private Airplane airplane;

    @Column
    private Airport destintationPort;

    @Column
    private Airport sourcePort;

    @Column
    private Date arrival;

    @Column
    private Date departure;

    private List<Steward> stewards;


}
