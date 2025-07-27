package salah.api.salaholm.entity.location;

import lombok.Builder;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.util.UUID;


@Builder
public class Location {
    @QuerySqlField(index = true)
    private UUID id;

    @QuerySqlField(index = true)
    private String municipality;

    @QuerySqlField(index = true)
    private Long coordinateId;
}
