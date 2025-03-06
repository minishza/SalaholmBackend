package salah.api.salaholm.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Builder
@RequiredArgsConstructor
@Data //remove later
@RedisHash("Prayer")
public class Prayer implements Serializable {

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
