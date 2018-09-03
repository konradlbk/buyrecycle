package pl.gda11_3.buyrecycle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.gda11_3.buyrecycle.model.*;
import pl.gda11_3.buyrecycle.service.AppUserService;
import pl.gda11_3.buyrecycle.service.MaterialService;
import pl.gda11_3.buyrecycle.service.OfferService;
import pl.gda11_3.buyrecycle.service.TaraService;

import java.time.LocalDate;
import java.util.*;

@Controller

public class OfferController {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private TaraService taraService;

    @Autowired
    private OfferService offerService;

    @Autowired
    private AppUserService appUserService;


    @GetMapping(path = "/addOfferForm")
    public String getOfferForm(Model model) {


        Offer offer = new Offer();
        Material material = new Material();


        List<Names> materialNames = new ArrayList<Names>(EnumSet.allOf(Names.class));
        List<Forms> materialForm = new ArrayList<Forms>(EnumSet.allOf(Forms.class));

        model.addAttribute("materialNames", materialNames);
        model.addAttribute("materialForm", materialForm);

        model.addAttribute("material", material);



        List<PackageType> packateTypes = new ArrayList<PackageType>(EnumSet.allOf(PackageType.class));

        model.addAttribute("packageTypes", packateTypes);
        model.addAttribute("offer", offer);



        return "addOfferForm";
    }

    @PostMapping(path = "/addOfferForm")
    public String showOffer(Offer offer) {
        offerService.addMaterial(offer);

        return "index";
    }



    @GetMapping(path = "/offerslist/")
    public String listSort(Model model, @RequestParam(value = "material", required = false) String name, @RequestParam(value = "materialform", required = false) String materialForm,
                           @RequestParam(value = "search", required = false) String pSearch) {
        if (materialForm==null){
        List<Offer> offerList = materialService.findMaterial(name);
        model.addAttribute("mname", name);
        List<Names> materialNames = new ArrayList<Names>(EnumSet.allOf(Names.class));

        model.addAttribute("materials", materialNames);
        model.addAttribute("offerList", offerList);


        } else {
            List<Offer> offerListByForm = materialService.findMaterialByForm(materialForm);
            model.addAttribute("formToFind", materialForm);

            List<Forms> materialForms = new ArrayList<>(EnumSet.allOf(Forms.class));

            model.addAttribute("materialForms", materialForms);
            model.addAttribute("offerList", offerListByForm);

        }
        if (pSearch!=null){
            List<Offer> offerList=offerService.findBySearch(pSearch);
            model.addAttribute("search", pSearch);
            model.addAttribute("offerList", offerList);

        }



        if (name!=null && name.equals("all") && materialForm.equals("allforms")){
            List<Offer> offerList2 = offerService.getAllTaras();
            List<Names> materialNames2 = new ArrayList<Names>(EnumSet.allOf(Names.class));


            model.addAttribute("offerList", offerList2);
        }



        return "offerslist";
    }


    @GetMapping(path = "/details/{id}")
    public String details(Model model, @PathVariable(name = "id") Long id) {
        Optional<Offer> offerOptional = offerService.find(id);
        if (offerOptional.isPresent()) {
            model.addAttribute("offerDetails", offerOptional.get());
            return "detailsPage";
        }
        return "";
    }


    @GetMapping(path = "/offerslist/sort/name")
    public String listName(Model model) {
        Material material = new Material();
        List<Offer> offerList = offerService.getAllTaras();
        model.addAttribute("offerListName", offerList);

        offerList.sort(new Comparator<Offer>() {
            @Override
            public int compare(Offer o1, Offer o2) {
                return o1.getOfferName().compareTo(o2.getOfferName());
            }
        });
        return "offerslistName";
    }


    @GetMapping(path = "/offerslist/sort/nameReverse")
    public String listNameReverse(Model model) {
        Material material = new Material();
        List<Offer> offerList = offerService.getAllTaras();

        model.addAttribute("offerListNameReverse", offerList);

        offerList.sort(new Comparator<Offer>() {
            @Override
            public int compare(Offer o1, Offer o2) {
                return o2.getOfferName().compareTo(o1.getOfferName());
            }
        });

        return "offerslistNameReverse";
    }

    @GetMapping(path = "/offerslist/sort/price")
    public String listPrice(Model model) {
        Material material = new Material();
        // pobieram z bazy danych listę wydarzeń
        List<Offer> offerList = offerService.getAllTaras();

        model.addAttribute("offerListPrice", offerList);

        offerList.sort(new Comparator<Offer>() {
            @Override
            public int compare(Offer o1, Offer o2) {
                return Integer.compare(o1.getMaterial().getPrice(), o2.getMaterial().getPrice());
            }
        });

        // uruchamiam widok.
        return "offerslistPrice";
    }

    @GetMapping(path = "/offerslist/sort/priceReverse")
    public String listPriceReverse(Model model) {
        Material material = new Material();
        // pobieram z bazy danych listę wydarzeń
        List<Offer> offerList = offerService.getAllTaras();
        // przekazuję listę do modelu (jako atrybut)

        model.addAttribute("offerListPriceReverse", offerList);

        offerList.sort(new Comparator<Offer>() {
            @Override
            public int compare(Offer o1, Offer o2) {
                return Integer.compare(o2.getMaterial().getPrice(), o1.getMaterial().getPrice());
            }
        });

        // uruchamiam widok.
        return "offerslistPriceReverse";
    }

    @GetMapping(path = "/offerslist/search/{search}")
    public String searchOffers(Model model, @PathVariable(name = "search") String pSearch ){


        return "offerslist";
    }
}
