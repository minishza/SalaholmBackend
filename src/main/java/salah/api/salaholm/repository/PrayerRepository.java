package salah.api.salaholm.repository;

import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import salah.api.salaholm.entity.location.Location;

@RepositoryConfig(cacheName = "prayers")
public interface PrayerRepository extends IgniteRepository<Location, Long> {
    Location findLocationByMunicipality(String municipality);
}