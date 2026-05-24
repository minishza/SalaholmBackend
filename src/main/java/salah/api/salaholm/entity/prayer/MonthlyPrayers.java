package salah.api.salaholm.entity.prayer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import salah.api.salaholm.entity.location.Location;

import java.util.List;

@Entity
@Table(name = "monthly_prayers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyPrayers {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(mappedBy = "monthlyPrayers", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Prayer> monthlyPrayers;

    @ManyToOne
    @JoinColumn(name="location_id", nullable=false)
    @JsonBackReference
    private Location location;
}
