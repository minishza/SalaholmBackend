package salah.api.salaholm.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import salah.api.salaholm.entity.location.Location;

@Repository
@CacheConfig
public interface LocationPrayerRepository extends JpaRepository<Location, Long> {
    @Cacheable(key = "#city.toLowerCase()", value = "locationCache")
    Location findLocationByCity(String city);
}