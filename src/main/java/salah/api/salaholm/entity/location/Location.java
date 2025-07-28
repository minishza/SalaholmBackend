package salah.api.salaholm.entity.location;

import jakarta.persistence.*;
import lombok.*;
import salah.api.salaholm.entity.prayer.Prayers;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String municipality;
    private String city;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates;

    @OneToMany(mappedBy = "location", cascade = CascadeType.PERSIST)
    private List<Prayers> prayers;
}
