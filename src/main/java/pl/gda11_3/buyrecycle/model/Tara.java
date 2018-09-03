package pl.gda11_3.buyrecycle.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Tara {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String packageType;
    private String seal;
    private String details;
    private int weight;
    private int packagePrice;


}