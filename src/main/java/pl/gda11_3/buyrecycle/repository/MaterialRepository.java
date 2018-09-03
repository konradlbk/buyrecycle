package pl.gda11_3.buyrecycle.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gda11_3.buyrecycle.model.Material;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    Optional<Material> findByName(String name);

    List<Material> findAllByName(String name);

    List<Material> findAllByForm(String form);


    }

