package pl.put.poznan.buildingInfo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.put.poznan.buildingInfo.model.Level;

@Repository
public interface LevelRepository extends CrudRepository<Level,String> {
}
