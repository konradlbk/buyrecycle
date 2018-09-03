package pl.gda11_3.buyrecycle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.gda11_3.buyrecycle.model.AppUser;
import pl.gda11_3.buyrecycle.model.RegisterAppUserDTO;
import pl.gda11_3.buyrecycle.model.WishList;
import pl.gda11_3.buyrecycle.model.company.Company;
import pl.gda11_3.buyrecycle.model.company.Pricelist;
import pl.gda11_3.buyrecycle.repository.AppUserRepository;
import pl.gda11_3.buyrecycle.repository.CompanyRepository;
import pl.gda11_3.buyrecycle.repository.WishListRepository;

import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private WishListRepository wishListRepository;

    public boolean registerUser(RegisterAppUserDTO dto){
        Optional<AppUser> appUser = appUserRepository.findByusername(dto.getUsername());
        if (appUser.isPresent()){
            return false;
        }
        AppUser newUser = new AppUser(dto.getUsername(),
                bCryptPasswordEncoder.encode(dto.getPassword()));

        WishList wishList = new WishList();

        wishList = wishListRepository.save(wishList);

        newUser.setWishList(wishList);



        newUser = appUserRepository.save(newUser);



        wishList.setAppUser(newUser);
        wishListRepository.save(wishList);

        return true;
    }

    public Optional<AppUser> findUserByUsername(String name){
        Optional<AppUser> optionalAppUser = appUserRepository.findByusername(name);
        return optionalAppUser;
    }

    public void saveUser(AppUser appUser) {


            appUserRepository.save(appUser);

        }

    public void addCompanyToUser(Company company, String name) {
        Optional<AppUser> optionalAppUser = appUserRepository.findByusername(name);

        if (optionalAppUser.isPresent()) {
            AppUser addCompany = optionalAppUser.get();

            Company addedCompany = addCompany.getCompany();

            addedCompany.setAddress(company.getAddress());
            addedCompany.setEmail(company.getEmail());
            addedCompany.setName(company.getName());
            addedCompany.setNIP(company.getNIP());
            addedCompany.setPhoneNumber(company.getPhoneNumber());


            companyRepository.save(addedCompany);
            appUserRepository.save(addCompany);

        }

    }


    }

