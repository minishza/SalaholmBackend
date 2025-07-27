package salah.api.salaholm.repository;

import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import salah.api.salaholm.entity.location.Location;

@RepositoryConfig(cacheName = "location")
public interface LocationRepository extends IgniteRepository<Location, Long> {
}
