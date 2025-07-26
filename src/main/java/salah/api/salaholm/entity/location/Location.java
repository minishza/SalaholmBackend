package salah.api.salaholm.entity.location;

import jakarta.persistence.*;
import salah.api.salaholm.entity.prayer.Prayer;

import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String municipality;
    private int population;

    @OneToOne(mappedBy = "location", cascade = CascadeType.ALL)
    private Coordinates coordinates;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "prayer", cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private List<Prayer> prayers;
}
