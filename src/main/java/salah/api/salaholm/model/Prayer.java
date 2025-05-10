package salah.api.salaholm.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(schema = "daily_prayers")
public class Prayer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String hijri;
    private String georgian;

    private String fajr;
    private String sunrise;
    private String zuhr;
    private String asr;
    private String maghrib;
    private String isha;

    private String city;
}
