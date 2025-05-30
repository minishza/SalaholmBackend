package salah.api.salaholm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import salah.api.salaholm.model.Gregorian;
import salah.api.salaholm.model.Prayer;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrayerRepository extends JpaRepository<Prayer, Long> {
    List<Prayer> findAllByCity(String city);
    Optional<Prayer> findPrayerByIdAndCity(Long id, String city);
    List<Prayer> findPrayersByCity(String city);
    Optional<Prayer> findPrayerByGregorian(Gregorian gregorian);
}