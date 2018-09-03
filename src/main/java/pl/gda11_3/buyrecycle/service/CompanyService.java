package pl.gda11_3.buyrecycle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gda11_3.buyrecycle.model.AppUser;
import pl.gda11_3.buyrecycle.model.company.Company;
import pl.gda11_3.buyrecycle.model.company.Pricelist;
import pl.gda11_3.buyrecycle.model.company.Vehicle;
import pl.gda11_3.buyrecycle.repository.AppUserRepository;
import pl.gda11_3.buyrecycle.repository.CompanyRepository;
import pl.gda11_3.buyrecycle.repository.PricelistRepository;
import pl.gda11_3.buyrecycle.repository.VehiclesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PricelistRepository pricelistRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private VehiclesRepository vehiclesRepository;





    public List<Company> getAllDeliveryCompany() {
        return companyRepository.findAllByTransport(true);
    }

    public void add(Company company, AppUser appUser) {
        company.setAppUser(appUser);
        companyRepository.save(company);
        appUser.setCompany(company);


        appUserRepository.save(appUser);
    }

    public List<Company> getAllcompany() {
        return companyRepository.findAll();
    }

    public Optional<Company> find(Long companyId) {
        return companyRepository.findById(companyId);
    }

    public void changePricelist(Pricelist pricelist, Long id_company) {
        Optional<Company> company = companyRepository.findById(id_company);
        if (company.isPresent()) {
            Company editedPrice = company.get();
            Pricelist list = editedPrice.getPriceList();

            list.setPrice_2(pricelist.getPrice_2());
            list.setPrice_5(pricelist.getPrice_5());
            list.setPrice_10(pricelist.getPrice_10());
            list.setPrice_20(pricelist.getPrice_20());
            pricelistRepository.save(list);
            companyRepository.save(editedPrice);
        }
    }

    public List<Company> findAll() {
        return getAllcompany();
    }


    public void addVehicle(Vehicle vehicle) {
        vehiclesRepository.save(vehicle);
    }

    public void addVehicleToList(Vehicle vehicle) {

    }

    public void changeVehicles(Vehicle vehicle, Long companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isPresent()) {
            Company editedCompany = company.get();
            vehicle = vehiclesRepository.save(vehicle);

            editedCompany.getVehicles().add(vehicle);
            companyRepository.save(editedCompany);

        }
    }
}