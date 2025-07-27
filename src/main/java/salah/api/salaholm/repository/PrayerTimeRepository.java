package salah.api.salaholm.repository;

import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import salah.api.salaholm.entity.prayer.PrayerTime;

@RepositoryConfig(cacheName = "prayerTime")
public interface PrayerTimeRepository extends IgniteRepository<PrayerTime, Long> {
}
