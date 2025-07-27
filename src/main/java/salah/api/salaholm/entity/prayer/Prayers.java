package salah.api.salaholm.entity.prayer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import salah.api.salaholm.entity.calendar.PrayerCalendar;
import salah.api.salaholm.entity.location.Location;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Prayers implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(mappedBy = "prayer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PrayerCalendar> prayerCalendars;

    @OneToMany(mappedBy = "prayer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PrayerTime> prayerTimes;

    @ManyToOne
    @JoinColumn(name="cart_id", nullable=false)
    @JsonBackReference
    private Location location;
}
