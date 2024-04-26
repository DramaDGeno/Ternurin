package drama.Ternurin.Model.Repositories;

import drama.Ternurin.Model.AnimalBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalRepo extends JpaRepository<AnimalBean, Integer> {

    Optional<AnimalBean> findByName(String name);
}
