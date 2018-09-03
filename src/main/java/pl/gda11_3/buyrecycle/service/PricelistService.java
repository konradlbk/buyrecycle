package pl.gda11_3.buyrecycle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gda11_3.buyrecycle.model.company.Company;
import pl.gda11_3.buyrecycle.model.company.Pricelist;
import pl.gda11_3.buyrecycle.repository.CompanyRepository;
import pl.gda11_3.buyrecycle.repository.PricelistRepository;

import java.util.Optional;

@Service
public class PricelistService {

    @Autowired
    private PricelistRepository pricelistRepository;



    public void add(Pricelist pricelist) {
        pricelistRepository.save(pricelist);
    }

    public Pricelist findByID(Long companyId) {
        Optional<Pricelist> pricelistOptional = pricelistRepository.findById(companyId);
        if(pricelistOptional.isPresent()) {
            Pricelist editedPricelist = pricelistOptional.get();
            return editedPricelist;
        }
        return pricelistOptional.get();
    }
}
