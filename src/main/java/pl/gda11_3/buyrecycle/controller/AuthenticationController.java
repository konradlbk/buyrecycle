package pl.gda11_3.buyrecycle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.gda11_3.buyrecycle.model.AppUser;
import pl.gda11_3.buyrecycle.service.AppUserService;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
@ControllerAdvice
public class AuthenticationController {
    @Autowired
    private AppUserService appUserService;

    @ModelAttribute
    public void addAttributes(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String loggedInUserName = authentication.getName();
            Optional<AppUser> appUser = appUserService.findUserByUsername(loggedInUserName );

            if(appUser.isPresent()) {
                model.addAttribute("whishlist_id", appUser.get().getWishList().getId());
                model.addAttribute("loggedUser", appUser.get().getUsername());
            }
        }
    }
}
