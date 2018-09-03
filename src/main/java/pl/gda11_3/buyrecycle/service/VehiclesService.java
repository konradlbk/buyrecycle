package pl.gda11_3.buyrecycle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gda11_3.buyrecycle.model.company.Vehicle;
import pl.gda11_3.buyrecycle.repository.VehiclesRepository;

import java.util.Optional;

@Service
public class VehiclesService {
    @Autowired
    private VehiclesRepository VehiclesRepository;




    public void add(Vehicle vehicle) {
        VehiclesRepository.save(vehicle);
    }

    public Vehicle findByID(Long companyId) {
        Optional<Vehicle> vehicleOptional = VehiclesRepository.findById(companyId);
        if(vehicleOptional.isPresent()) {
            Vehicle editedVehicle = vehicleOptional.get();
            return editedVehicle;
        }
        return vehicleOptional.get();
    }
}
