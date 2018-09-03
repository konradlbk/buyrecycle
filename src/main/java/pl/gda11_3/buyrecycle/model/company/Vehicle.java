package pl.gda11_3.buyrecycle.model.company;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Capacity capacity;
    private int length;
    private int width;
    private int height;



    public double getPriceCapacity(Pricelist pricelist) {
        if (capacity == Capacity.CAPACITY_2t) {
            return pricelist.getPrice_2();
        }
        if (capacity == Capacity.CAPACITY_5t) {
            return pricelist.getPrice_5();
        }
        if (capacity == Capacity.CAPACITY_10t) {
            return pricelist.getPrice_10();
        }
        if (capacity == Capacity.CAPACITY_20t) {
            return pricelist.getPrice_20();
        } else
            return 0;
    }

    public int getNumberCapacity(Pricelist pricelist) {
        if (capacity == Capacity.CAPACITY_2t) {
            return 2;
        }
        if (capacity == Capacity.CAPACITY_5t) {
            return 5;
        }
        if (capacity == Capacity.CAPACITY_10t) {
            return 10;
        }
        if (capacity == Capacity.CAPACITY_20t) {
            return 20;
        } else
            return 0;
    }


}

