package drama.Ternurin.Controller;

import drama.Ternurin.Model.AnimalBean;
import drama.Ternurin.Service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animal") //acceso global /animal
@CrossOrigin(origins = "*") //cualquier origen puede acceder
@RequiredArgsConstructor

public class AnimalController {
    private final AnimalService animalService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllAnimals() {
        return ResponseEntity.ok().body(animalService.getAllAnimals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnimalById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(animalService.getAnimalById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnimal(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(animalService.delete(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveAnimal(@RequestBody AnimalBean animalBean) {
        return ResponseEntity.ok().body(animalService.save(animalBean));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAnimal(@RequestBody AnimalBean animalBean) {
        return ResponseEntity.ok().body(animalService.update(animalBean));
    }
}

