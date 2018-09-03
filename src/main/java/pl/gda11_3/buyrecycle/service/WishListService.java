package pl.gda11_3.buyrecycle.service;

import lombok.Setter;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pl.gda11_3.buyrecycle.model.Offer;
import pl.gda11_3.buyrecycle.model.WishList;
import pl.gda11_3.buyrecycle.repository.WishListRepository;

import java.util.Optional;

@org.springframework.stereotype.Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;
    @Autowired
    private OfferService offerService;

    public void add(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public Optional<WishList> findListById(Long id) {
        return wishListRepository.findById(id);
    }

    public void removeOfferFromList(Long listId, Long offerId) {
        Optional<WishList> optionalWishList = findListById(listId);
        if (optionalWishList.isPresent()) {
            Optional<Offer> optionalOffer = offerService.find(offerId);
            if (optionalOffer.isPresent()) {
                WishList list = optionalWishList.get();
                list.getItems().remove(optionalOffer.get());
                wishListRepository.save(list);
            }
        }
    }

    public void addOfferToList(Long listId, Long offerId) {
        Optional<WishList> optionalWishList = findListById(listId);
        if (optionalWishList.isPresent()) {
            Optional<Offer> optionalOffer = offerService.find(offerId);
            if (optionalOffer.isPresent()) {
                WishList list = optionalWishList.get();
                list.getItems().add(optionalOffer.get());
                wishListRepository.save(list);
            }
        }
    }
}