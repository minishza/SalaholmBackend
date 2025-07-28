package salah.api.salaholm.entity.location;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Coordinates {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    private double latitude;
    private double longitude;

    @OneToOne(mappedBy = "coordinates")
    private Location location;
}
