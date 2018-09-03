package pl.gda11_3.buyrecycle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gda11_3.buyrecycle.model.Tara;


@Repository
public interface TaraRepository extends JpaRepository<Tara, Long> {
}
