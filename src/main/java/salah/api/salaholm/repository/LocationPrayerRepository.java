package salah.api.salaholm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import salah.api.salaholm.entity.location.Location;

@Repository
public interface LocationPrayerRepository extends JpaRepository<Location, Long> {
    Location findLocationByMunicipality(String municipality);
}