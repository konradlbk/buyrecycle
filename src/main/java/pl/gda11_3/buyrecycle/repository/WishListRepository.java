package pl.gda11_3.buyrecycle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gda11_3.buyrecycle.model.Offer;
import pl.gda11_3.buyrecycle.model.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
}
