package cz.muni.airport.enums;

/**
 *
 * @author Karolína Božková, github name: Kayeeec
 */
public enum PlaneType {
    BUSINESS_JET("Business Jet"),
    AIRLINER("Airliner"),
    CARGO("Cargo"),
    TWIN_PISTON("Twin piston"),
    HELICOPTER("Helicopter")
    ;
    private final String text;

    private PlaneType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return this.getText();
    }
    
    
    
    
    
    
    
    

    
    
    
    
    
}
