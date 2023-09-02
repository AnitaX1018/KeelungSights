package ntou.q5.KeelungSights.repository;

import ntou.q5.KeelungSights.entity.Sight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SightRepository extends MongoRepository<Sight, String> {

    Optional<Sight> findBySightName(String sightname);

    List<Sight> findByZoneContaining(String zone);
}
