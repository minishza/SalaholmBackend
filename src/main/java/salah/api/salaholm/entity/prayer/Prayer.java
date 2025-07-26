package salah.api.salaholm.entity.prayer;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import salah.api.salaholm.entity.calendar.PrayerCalendar;
import salah.api.salaholm.entity.location.Location;

import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Prayer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String hijri;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "prayer_id")
    private Set<PrayerCalendar> prayerCalendars;

    @OneToMany(mappedBy = "prayer", cascade = CascadeType.ALL)
    @JoinColumn(name = "prayer_id", referencedColumnName = "id")
    @JsonManagedReference
    private List<PrayerTime> prayerTimes;

    @ManyToOne()
    @JoinColumn(name="location_id", nullable=false)
    private Location location;
}
