package salah.api.salaholm.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Prayer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String hijri;

    @OneToOne(mappedBy = "prayer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Gregorian gregorian;

    @OneToMany(mappedBy = "prayer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PrayerTime> prayerTimes;

    private String city;
}
