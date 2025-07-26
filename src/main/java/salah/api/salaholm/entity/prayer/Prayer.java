package salah.api.salaholm.entity.prayer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import salah.api.salaholm.entity.calendar.PrayerCalendar;
import salah.api.salaholm.entity.location.Location;

import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Prayer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "prayer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<PrayerCalendar> prayerCalendars;

    @OneToMany(mappedBy = "prayer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PrayerTime> prayerTimes;

    @ManyToOne
    @JoinColumn(name="cart_id", nullable=false)
    @JsonBackReference
    private Location location;
}
