package pl.gda11_3.buyrecycle.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private String name;
    private String form;
    private String color;
    private Integer price;
    private String description;
    private int tarAmount;

    @OneToMany(mappedBy = "material", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Offer> offer;
}
