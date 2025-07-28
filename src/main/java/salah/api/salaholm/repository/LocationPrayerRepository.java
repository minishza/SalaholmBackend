package salah.api.salaholm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import salah.api.salaholm.entity.location.Location;

import java.util.Optional;

@Repository
public interface LocationPrayerRepository extends JpaRepository<Location, Long> {
    Optional<Location> findLocationByCity(String city);
}