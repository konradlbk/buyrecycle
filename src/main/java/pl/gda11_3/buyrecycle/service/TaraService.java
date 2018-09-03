package pl.gda11_3.buyrecycle.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gda11_3.buyrecycle.model.Material;
import pl.gda11_3.buyrecycle.model.Offer;
import pl.gda11_3.buyrecycle.model.Tara;
import pl.gda11_3.buyrecycle.repository.MaterialRepository;
import pl.gda11_3.buyrecycle.repository.TaraRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaraService {

    @Autowired
    private TaraRepository taraRepository;

    public List<Tara> getAllTaras() {
        return taraRepository.findAll();
    }

    public void addMaterial(Tara tara){
        taraRepository.save(tara);

    }

    public Optional<Tara> find(Long id) {
        return taraRepository.findById(id);
    }
}
