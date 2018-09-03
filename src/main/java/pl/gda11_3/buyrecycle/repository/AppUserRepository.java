package pl.gda11_3.buyrecycle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.gda11_3.buyrecycle.model.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByusername(String username);



}
