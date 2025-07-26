package salah.api.salaholm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import salah.api.salaholm.entity.prayer.Prayer;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrayerRepository extends JpaRepository<Prayer, Long> {
    List<Prayer> findAllByCity(String city);
    Optional<Prayer> findByGregorian(Gregorian gregorian);
}