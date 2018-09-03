package pl.gda11_3.buyrecycle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.gda11_3.buyrecycle.model.AppUser;
import pl.gda11_3.buyrecycle.model.company.Capacity;
import pl.gda11_3.buyrecycle.model.company.Company;
import pl.gda11_3.buyrecycle.model.company.Pricelist;
import pl.gda11_3.buyrecycle.model.company.Vehicle;
import pl.gda11_3.buyrecycle.service.AppUserService;
import pl.gda11_3.buyrecycle.service.CompanyService;


import java.util.*;

@Controller
@RequestMapping(path = "/company/")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AppUserService appUserService;

    @GetMapping(path = "/delivery/list")
    public String list(Model model) {
        List<Company> deliveryCompanies = companyService.getAllDeliveryCompany();
        model.addAttribute("deliveryCompanies", deliveryCompanies);
        return "deliveryList";
    }

    @GetMapping(path = "/list")
    public String companyList(Model model) {
        List<Company> companyList = companyService.getAllcompany();
        model.addAttribute("companies", companyList);
        return "companyList";
    }

    @GetMapping(path = "/details/{id}")
    public String details(Model model, @PathVariable(name = "id") Long id) {
        Optional<Company> companyOptional = companyService.find(id);
        if (companyOptional.isPresent()) {
            model.addAttribute("companyDetails", companyOptional.get());
            return "detailsPageCompany";
        }
        return "";
    }

    @GetMapping(path = "/pricelistCapacity/{id_company}")
    public String pricelistCapacity(Model model, @PathVariable(name = "id_company") Long companyId) {
        Company company = companyService.find(companyId).get();
        model.addAttribute("company", company);

        return "pricelistCapacity";
    }

    @GetMapping(path = "/pricelistCapacity/table")
    public String pricelistCapacityTable(Model model) {

        List<Company> companyList = companyService.getAllDeliveryCompany();
        model.addAttribute("companyList", companyList);

        return "pricelistTable";
    }

    @GetMapping(path = "/add")
    public String add(Model model) {
        Company company = new Company();
        model.addAttribute("company", company);
        return "companyAdd";
    }

    @PostMapping(path = "/add")
    public String add(Company company, Model model) {

        String loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<AppUser> optionalLoggedInUser = appUserService.findUserByUsername(loggedInUserName);
        if (optionalLoggedInUser.isPresent()) {
            AppUser appUser = optionalLoggedInUser.get();
            company.setPriceList(new Pricelist());
            company.setVehicles(new ArrayList<>());
            companyService.add(company, appUser);


        }
            return "redirect:/company/list/";
    }


    // Szczegóły firmy
    // Edytuj cennik
    // GET Formularz edycji cen
    // POST Formularz -> ceny (zapis)


    @GetMapping(path = "/add/changePriceList/{id_company}")
    public String addPricelist(@PathVariable(name = "id_company") Long companyId, Model model) {
        // todo: po zalogowaniu sprawdzic czy uzytkownik jest wlascicielem firmy
        Optional<Company> company = companyService.find(companyId);
        if (company.isPresent()) {
            Company editedPrice = company.get();
            Pricelist pricelist = editedPrice.getPriceList();

            model.addAttribute("priceList", pricelist);
            model.addAttribute("id_company", companyId);
        }
        return "pricelistCapacityChange";
    }


    @PostMapping(path = "/add/changePriceList/{id_company}")
    public String addPricelist(@PathVariable(name = "id_company") Long companyId, Pricelist pricelist, Model model) {
        companyService.changePricelist(pricelist, companyId);
        model.addAttribute("company", companyService.find(companyId).get());
        return "pricelistCapacity";
    }

    @GetMapping(path = "/list/sort/name")
    public String listName(Model model) {
        List<Company> companyList = companyService.getAllcompany();
        model.addAttribute("companyList", companyList);
        companyList.sort(new Comparator<Company>() {
            @Override
            public int compare(Company o1, Company o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return "companyListSortName";
    }

    @GetMapping(path = "/list/sort/reverseName")
    public String listReverseName(Model model) {
        List<Company> companyList = companyService.getAllcompany();
        model.addAttribute("companyList", companyList);
        companyList.sort(new Comparator<Company>() {
            @Override
            public int compare(Company o1, Company o2) {
                return o2.getName().compareTo(o1.getName());
            }
        });
        return "companyListSortReverseName";
    }


    @GetMapping(path = "/add/changeVehicle/{id_company}")
    public String addVehicles(@PathVariable(name = "id_company") Long companyId, Model model, Vehicle vehicle) {
        // todo: po zalogowaniu sprawdzic czy uzytkownik jest wlascicielem firmy
        Optional<Company> company = companyService.find(companyId);
        if (company.isPresent()) {
            Company editedPrice = company.get();
            List<Vehicle> vehicles = editedPrice.getVehicles();
            List<Capacity> capacityList = new ArrayList<>(EnumSet.allOf(Capacity.class));
            model.addAttribute("vehicles", vehicles);
            model.addAttribute("capacityList", capacityList);
            model.addAttribute("vehicle", vehicle);
            model.addAttribute("id_company", companyId);
        }
        return "vehiclesChange";
    }

    @PostMapping(path = "/add/changeVehicle/{id_company}")
    public String addPricelist(@PathVariable(name = "id_company") Long companyId, Model model, Vehicle vehicle) {
        companyService.changeVehicles(vehicle, companyId);
        return "redirect:/company/vehicleList/" + companyId;
    }

    @GetMapping(path = "/vehicleList/{id_company}")
    public String vehicleList(Model model, @PathVariable(name = "id_company") Long companyId) {

        Company company = companyService.find(companyId).get();
        List<Vehicle> vehicleList = company.getVehicles();

        model.addAttribute("company", company);
        model.addAttribute("vehicleList", vehicleList);

        return "vehicleList";
    }

}
