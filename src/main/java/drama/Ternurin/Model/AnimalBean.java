package drama.Ternurin.Model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "animal")
public class AnimalBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 50, nullable = false, unique = true)
    private  String name;
    @Column(name = "species", length = 50, nullable = false)
    private  String species;
    @Column(name = "type", length = 50, nullable = false)
    private String type;


}
