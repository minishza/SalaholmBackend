package salah.api.salaholm.entity.prayer;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import salah.api.salaholm.entity.calendar.PrayerCalendar;
import salah.api.salaholm.entity.location.Location;

import java.util.ArrayList;
import java.util.HashSet;
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

    @OneToMany(mappedBy = "prayer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PrayerCalendar> prayerCalendars = new HashSet<>();

    @OneToMany(mappedBy = "prayer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PrayerTime> prayerTimes = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name="location_id", nullable=false)
    private Location location;
}
