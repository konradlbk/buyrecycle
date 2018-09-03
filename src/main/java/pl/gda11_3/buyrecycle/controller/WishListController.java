package pl.gda11_3.buyrecycle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gda11_3.buyrecycle.model.AppUser;
import pl.gda11_3.buyrecycle.model.Names;
import pl.gda11_3.buyrecycle.model.Offer;
import pl.gda11_3.buyrecycle.model.WishList;
import pl.gda11_3.buyrecycle.service.AppUserService;
import pl.gda11_3.buyrecycle.service.MaterialService;
import pl.gda11_3.buyrecycle.service.WishListService;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/wishList")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private MaterialService materialService;



    @GetMapping(path = "/wishList/{id}")
    public String wishList(Model model, @PathVariable(name = "id") Long id) {

        Optional<WishList> optionalWishList = wishListService.findListById(id);

        if (optionalWishList.isPresent()){

            List<Offer> offerWishes = optionalWishList.get().getItems();


            model.addAttribute("offerWishes", offerWishes);



        }



        return "wishList";
    }

    @GetMapping(path = "/addToWishList/{id}")
    public String addToWishList(@PathVariable(name = "id") Long id) {
        String loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<AppUser> optionalUser = appUserService.findUserByUsername(loggedInUserName);
        if (optionalUser.isPresent()) {
            AppUser loggedInUser = optionalUser.get();
            WishList wishList = loggedInUser.getWishList();

            wishListService.addOfferToList(wishList.getId(), id);
        }
        return "redirect:/wishList/wishList/" + optionalUser.get().getWishList().getId();
    }

    @GetMapping(path = "/removeFromWishList/{id}")
    public String removeFromWishList(@PathVariable(name = "id") Long id) {
        String loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<AppUser> optionalUser = appUserService.findUserByUsername(loggedInUserName);
        if (optionalUser.isPresent()) {
        AppUser loggedInUser = optionalUser.get();
        WishList wishList = loggedInUser.getWishList();

        wishListService.removeOfferFromList(wishList.getId(), id);
    }
        return"redirect:/wishList/wishList/" + optionalUser.get().getWishList().getId();
}

}