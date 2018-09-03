package pl.gda11_3.buyrecycle.model.company;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String country;
    private String town;
    private String postcode;
    private String street;
    private String numberOfBuilding;


    public String getAllAddress(){
        return getPostcode() + " " +getCountry()+" "+getStreet()+ " " +getNumberOfBuilding();
    }
}
