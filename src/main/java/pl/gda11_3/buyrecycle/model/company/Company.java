package pl.gda11_3.buyrecycle.model.company;

import lombok.*;
import pl.gda11_3.buyrecycle.model.AppUser;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String phoneNumber;
    private String email;
    private String name;
    private String NIP;
    private boolean transport;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    protected Pricelist priceList;



    @OneToOne
    private AppUser appUser;


    public String getContacts() {
        return "e-mail:" + getEmail() +
                " " + "tel:" + getPhoneNumber();
    }

}
