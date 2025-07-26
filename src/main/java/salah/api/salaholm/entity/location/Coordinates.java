package salah.api.salaholm.entity.location;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table
public class Coordinates {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private int latitude;
    private int longitude;
    private int sweref99tmX;
    private int sweref99tmY;

    @OneToOne(mappedBy = "location_id")
    @JsonBackReference
    private Location location;
}
