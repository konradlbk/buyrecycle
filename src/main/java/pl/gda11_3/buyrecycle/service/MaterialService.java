package pl.gda11_3.buyrecycle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gda11_3.buyrecycle.model.Material;
import pl.gda11_3.buyrecycle.model.Offer;
import pl.gda11_3.buyrecycle.repository.MaterialRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaterialService {

@Autowired
    private MaterialRepository materialRepository;

    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    public void addMaterial(Material material){
        materialRepository.save(material);

    }

    public Optional<Material> find(Long id) {
        return materialRepository.findById(id);
    }

    public List<Offer> findMaterial(String materialName) {
        List<Material> materials = materialRepository.findAllByName(materialName);

        return materials.stream().map(material -> material.getOffer()).flatMap(List::stream).collect(Collectors.toList());
    }

    public List<Offer> findMaterialByForm(String materialForm) {
        List<Material> materials = materialRepository.findAllByForm(materialForm);

        return materials.stream().map(material -> material.getOffer()).flatMap(List::stream).collect(Collectors.toList());
    }




}
