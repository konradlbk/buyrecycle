package pl.gda11_3.buyrecycle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.gda11_3.buyrecycle.model.AppUser;
import pl.gda11_3.buyrecycle.model.RegisterAppUserDTO;
import pl.gda11_3.buyrecycle.model.company.Company;
import pl.gda11_3.buyrecycle.model.company.Pricelist;
import pl.gda11_3.buyrecycle.repository.AppUserRepository;
import pl.gda11_3.buyrecycle.service.AppUserDetailsService;
import pl.gda11_3.buyrecycle.service.AppUserService;

import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserDetailsService appUserDetailsService;


    @GetMapping(path = "/index")
    public String getOfferForm(Model model) {


        return "index";
    }

    @GetMapping(path = "/register")
    public String register(Model model){
        model.addAttribute("user_dto", new RegisterAppUserDTO());
        return "userRegister";
    }

    @PostMapping(path = "/register")
    public String registerSend(Model model, RegisterAppUserDTO dto){
        if (!dto.getConfirm_password().equals(dto.getPassword())){
            model.addAttribute("user_dto", dto);
            model.addAttribute("error_message", "Passwords do not match");
            return "userRegister";
        }

        if (!appUserService.registerUser(dto)){
            model.addAttribute("user_dto", dto);
            model.addAttribute("error_message", "This username is taken!");
            return "userRegister";

        }
        // todo: dodaj uzytkownika do bazy
        return "redirect:/login";
    }

    @GetMapping(path = "/login")
    public String login(){
        return "login";
    }

    @GetMapping(path = "/about")
    public String about(){
        return "about";
    }

    @GetMapping(path = "/contact")
    public String contact(){
        return "contact";
    }

    @GetMapping(path = "/panel")

    public String userPanel(Model model, @RequestParam(value = "name", required = false) String editName){
        String loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<AppUser> optionalLoggedInUser = appUserService.findUserByUsername(loggedInUserName);
        if (optionalLoggedInUser.isPresent()) {
            AppUser appUser = optionalLoggedInUser.get();

            if (editName!=null){

                appUser.setName(editName);
                appUserService.saveUser(appUser);

            }

            model.addAttribute("currentUser", appUser);
        }

        return "userPanel";
    }

    @PostMapping(path = "/panel")
    public String editUser(Model model, @RequestParam(value = "editName", required = false) String editName){


        return "userPanel";






    }

    @PostMapping(path = "/company/changePriceList/{id_company}")
    public String addPricelist(@PathVariable(name = "id_company") Company company, String username, Model model) {
        username="konrad";
        appUserService.addCompanyToUser(company, username);
//        companyService.changePricelist(pricelist, companyId);
//        model.addAttribute("company", companyService.find(companyId).get());
        return "pricelistCapacity";
    }


}
