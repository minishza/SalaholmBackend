package salah.api.salaholm.repository;

import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import salah.api.salaholm.entity.location.Coordinates;

import java.util.UUID;

@RepositoryConfig(cacheName = "coordinates")
public interface CoordinatesRepository extends IgniteRepository<Coordinates, UUID> {
}
