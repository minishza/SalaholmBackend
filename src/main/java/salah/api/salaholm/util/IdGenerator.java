package salah.api.salaholm.util;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteAtomicSequence;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator {
    private final IgniteAtomicSequence coordinatesSeq;
    private final IgniteAtomicSequence prayerSeq;
    private final IgniteAtomicSequence prayerTimeSeq;
    private final IgniteAtomicSequence prayerCalendarSeq;

    public IdGenerator(Ignite ignite) {
        this.coordinatesSeq     = ignite.atomicSequence("coordinatesIdSeq", 0, true);
        this.prayerSeq          = ignite.atomicSequence("prayerIdSeq", 0, true);
        this.prayerTimeSeq      = ignite.atomicSequence("prayerTimeIdSeq", 0, true);
        this.prayerCalendarSeq  = ignite.atomicSequence("prayerCalendarIdSeq", 0, true);
    }

    public long nextCoordinatesId() {
        return coordinatesSeq.incrementAndGet();
    }

    public long nextPrayerId() {
        return prayerSeq.incrementAndGet();
    }

    public long nextPrayerTimeId() {
        return prayerTimeSeq.incrementAndGet();
    }

    public long nextPrayerCalendarId() {
        return prayerCalendarSeq.incrementAndGet();
    }
}
