package drama.Ternurin.Service;

import drama.Ternurin.Model.AnimalBean;
import drama.Ternurin.Model.Repositories.AnimalRepo;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service

public class AnimalService {

    private final AnimalRepo animalRepo;

    //Este transactional trae a todos los ternurines registrados.
    @Transactional(readOnly = true)
    public ResponseEntity<List<AnimalBean>> getAllAnimals() {
        List<AnimalBean> animals = animalRepo.findAll();
        return ResponseEntity.ok().body(animals);
    }

    //Este transactional trae a un ternurin mediante su ID
    @Transactional(readOnly = true)
    public ResponseEntity<AnimalBean> getAnimalById(int id) {
        if (!animalRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        AnimalBean animal = animalRepo.findById(id).get();
        return ResponseEntity.ok().body(animal);
    }

    //Este transactional revisa que el nombre del ternurin no este registrado, y si no lo está, lo registra-
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<?> save(AnimalBean animal) {
        if (animalRepo.findByName(animal.getName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Name already exists!");
        }

        AnimalBean savedAnimal = animalRepo.save(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnimal);
    }

    //Este transactional revisa que exista el id, si existe, lo elimina
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<String> delete(int id) {
        if (!animalRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ternurin not found:( " + id);
        }
        try {
            animalRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Ternurin deleted!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting ternurin " + id);
        }
    }

    //este transaccional revisa que el ternurin exista, si existe, hace el update
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<?> update(AnimalBean animal) {
        if(!animalRepo.existsById(Math.toIntExact(animal.getId()))){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ternurin not found " + animal.getId());
        }

        if (animalRepo.findByName(animal.getName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Name already exists!");
        }

        AnimalBean updatedAnimal = animalRepo.save(animal);
        return ResponseEntity.status(HttpStatus.OK).body(updatedAnimal);
    }
}
