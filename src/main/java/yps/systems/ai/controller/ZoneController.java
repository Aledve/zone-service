package yps.systems.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yps.systems.ai.model.Zone;
import yps.systems.ai.repository.IZoneRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/zoneService")
public class ZoneController {

    private final IZoneRepository zoneRepository;

    @Autowired
    public ZoneController(IZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @GetMapping
    ResponseEntity<List<Zone>> getAll() {
        return ResponseEntity.ok(zoneRepository.findAll());
    }

    @GetMapping("/{elementId}")
    ResponseEntity<Zone> getByElementId(@PathVariable String elementId) {
        return zoneRepository.findById(elementId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/available")
    ResponseEntity<List<Zone>> getAvailableZones() {
        return ResponseEntity.ok(zoneRepository.findAvailableZones());
    }

    @PostMapping
    ResponseEntity<String> save(@RequestBody Zone zone) {
        if (zone.getOccupiedSpaces() == null) {
            zone.setOccupiedSpaces(0); // Iniciar en 0 si es nueva
        }
        Zone savedZone = zoneRepository.save(zone);
        return new ResponseEntity<>("Zone created with ID: " + savedZone.getElementId(), HttpStatus.CREATED);
    }

    @PutMapping("/{elementId}")
    ResponseEntity<String> update(@PathVariable String elementId, @RequestBody Zone zone) {
        Optional<Zone> existing = zoneRepository.findById(elementId);
        if (existing.isPresent()) {
            zone.setElementId(existing.get().getElementId());
            zoneRepository.save(zone);
            return new ResponseEntity<>("Zone updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Zone not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{elementId}")
    ResponseEntity<String> delete(@PathVariable String elementId) {
        if (zoneRepository.existsById(elementId)) {
            zoneRepository.deleteById(elementId);
            return new ResponseEntity<>("Zone deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Zone not found", HttpStatus.NOT_FOUND);
    }
}
