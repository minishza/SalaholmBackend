package salah.api.salaholm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data //remove later
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Prayer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    private String hijri;
    private String georgian;

    private String fajr;
    private String sunrise;
    private String zuhr;
    private String asr;
    private String maghrib;
    private String isha;

    private String scrapedSite;
}
