package salah.api.salaholm.entity.location;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import salah.api.salaholm.entity.prayer.Prayers;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "location")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String municipality;

    @OneToOne(mappedBy = "location",  cascade = CascadeType.ALL)
    @JsonManagedReference
    private Coordinates coordinates;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Prayers> prayers;
}
