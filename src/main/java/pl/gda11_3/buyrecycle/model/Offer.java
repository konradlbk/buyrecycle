package pl.gda11_3.buyrecycle.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private String offerName;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private Material material;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Tara tara;

 private String ownerName;


}
