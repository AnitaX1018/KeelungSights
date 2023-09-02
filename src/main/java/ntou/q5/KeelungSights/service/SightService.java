package ntou.q5.KeelungSights.service;

import ntou.q5.KeelungSights.entity.Sight;
import ntou.q5.KeelungSights.exception.NotFoundException;
import ntou.q5.KeelungSights.parameter.SightQueryParameter;
import ntou.q5.KeelungSights.repository.SightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SightService {
    @Autowired
    private SightRepository repository;

    public Sight getSight(String sightname) {
        Optional<Sight> optionalSight = repository.findBySightName(sightname);
        if (optionalSight.isPresent()) {
            return optionalSight.get();
        } else {
            throw new NotFoundException("Can't find sight.");
        }
    }

    public List<Sight> getSights(SightQueryParameter param) {
        if (param.getZone() != null) {
            // ?zone=""
            return repository.findByZoneContaining(param.getZone());
        } else {
            // 所有景點
            return repository.findAll();
        }
    }

}
