package salah.api.salaholm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import salah.api.salaholm.model.Prayer;

@Repository
public interface PrayerRepository extends JpaRepository<Prayer, Long> {}