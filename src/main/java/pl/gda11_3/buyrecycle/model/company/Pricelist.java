package pl.gda11_3.buyrecycle.model.company;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor

@Data
public class Pricelist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double price_2;
    private double price_5;
    private double price_10;
    private double price_20;


}
