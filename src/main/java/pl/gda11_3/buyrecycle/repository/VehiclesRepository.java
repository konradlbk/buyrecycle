package pl.gda11_3.buyrecycle.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gda11_3.buyrecycle.model.company.Pricelist;
import pl.gda11_3.buyrecycle.model.company.Vehicle;

@Repository
public interface VehiclesRepository extends JpaRepository<Vehicle, Long> {
}
