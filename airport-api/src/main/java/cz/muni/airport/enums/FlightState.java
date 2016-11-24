package cz.muni.airport.enums;

/**
 * Created by Richard Bariny on 24.11.2016.
 *
 * FlightState for FlightDTO
 * @author Richard Bariny, github name:Richardb953
 */
public enum FlightState {

    /**
     * States depends on other entities
     * if flight do not have airplane and steward it is open
     * if flight have available airplane and steward it is accpeted
     * if flight do not have available airplane or stewards it is declined
     * if flight has available is in state verify it is actualy waiting for check availibility
     */
    OPEN, ACCEPTED, DECLINED, IN_VERIFY
}
