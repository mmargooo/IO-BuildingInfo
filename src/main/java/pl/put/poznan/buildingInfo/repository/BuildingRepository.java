package pl.put.poznan.buildingInfo.repository;

import org.springframework.data.repository.CrudRepository;
import pl.put.poznan.buildingInfo.model.Building;

public interface BuildingRepository extends CrudRepository<Building, String> {
}
