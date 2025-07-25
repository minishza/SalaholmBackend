package salah.api.salaholm.entity.calendar;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import salah.api.salaholm.entity.prayer.Prayer;

import java.util.UUID;

@Data
@Entity
@Table
public class Gregorian {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int date;
    private String dayOfWeek;
    private String month;
    private int year;

    @OneToOne
    @JoinColumn(name = "prayer_id")
    @JsonBackReference
    private Prayer prayer;

    public Gregorian(int i, String s, String s1, int i1, Prayer prayer) {
        this.date = i;
        this.dayOfWeek = s;
        this.month = s1;
        this.year = i1;
        this.prayer = prayer;
    }
    public Gregorian(){}
}
