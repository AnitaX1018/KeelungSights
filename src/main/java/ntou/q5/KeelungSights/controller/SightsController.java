package ntou.q5.KeelungSights.controller;


import ntou.q5.KeelungSights.entity.Sight;
import ntou.q5.KeelungSights.parameter.SightQueryParameter;
import ntou.q5.KeelungSights.service.SightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/SightAPI",produces = MediaType.APPLICATION_JSON_VALUE)
public class SightsController {
    @Autowired
    private SightService sightService;

    //單一sight
    @GetMapping("/{sightname}")
    public ResponseEntity<Sight> getSight(@PathVariable("sightname") String sightname) {
        Sight sight = sightService.getSight(sightname);
        return ResponseEntity.ok(sight);
    }

    //http://localhost:8080/SightAPI?zone=七堵
    @GetMapping
    public ResponseEntity<List<Sight>> geSights(@ModelAttribute SightQueryParameter param) {
        List<Sight> sights = sightService.getSights(param);
        return ResponseEntity.ok(sights);
    }
}
