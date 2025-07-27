package salah.api.salaholm.repository;

import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import salah.api.salaholm.entity.calendar.PrayerCalendar;

@RepositoryConfig(cacheName = "prayerCalendar")
public interface PrayerCalendarRepository extends IgniteRepository<PrayerCalendar, Long> {
}