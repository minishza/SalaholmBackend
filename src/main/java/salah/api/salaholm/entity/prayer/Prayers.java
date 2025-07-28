package salah.api.salaholm.entity.prayer;

import jakarta.persistence.*;
import lombok.*;
import salah.api.salaholm.entity.calendar.PrayerCalendar;
import salah.api.salaholm.entity.location.Location;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Prayers {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(mappedBy = "prayers", cascade = CascadeType.PERSIST)
    private List<PrayerCalendar> prayerCalendars;

    @ManyToOne
    @JoinColumn(name="location_id", nullable=false)
    private Location location;
}
