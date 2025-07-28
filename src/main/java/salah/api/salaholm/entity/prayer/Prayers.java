package salah.api.salaholm.entity.prayer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import salah.api.salaholm.entity.calendar.PrayerCalendar;
import salah.api.salaholm.entity.location.Location;

import java.util.List;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Prayers {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(mappedBy = "prayers", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PrayerCalendar> prayerCalendars;

    @ManyToOne
    @JoinColumn(name="location_id", nullable=false)
    @JsonBackReference
    private Location location;
}
