package pl.gda11_3.buyrecycle.model;

import lombok.Data;
import pl.gda11_3.buyrecycle.model.company.Company;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;

    private String name;

    @OneToOne
    private Company company;

    public AppUser() {
    }

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @OneToOne
    private WishList wishList;


}
