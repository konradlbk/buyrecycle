package pl.gda11_3.buyrecycle.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.gda11_3.buyrecycle.model.AppUser;
import pl.gda11_3.buyrecycle.model.Offer;
import pl.gda11_3.buyrecycle.model.Tara;
import pl.gda11_3.buyrecycle.repository.MaterialRepository;
import pl.gda11_3.buyrecycle.repository.OfferRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private MaterialRepository materialRepository;

    public List<Offer> getAllTaras() {
        return offerRepository.findAll();
    }

    public void addMaterial(Offer offer){
        String loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        offer.setOwnerName(loggedInUserName);
        offerRepository.save(offer);


    }

    public Optional<Offer> find(Long id) {
        return offerRepository.findById(id);
    }

    public List<Offer> findBySearch(String pSearch){
        return offerRepository.findAllByOfferNameContaining(pSearch);
    }


}
