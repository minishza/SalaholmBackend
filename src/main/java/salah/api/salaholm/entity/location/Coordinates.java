package salah.api.salaholm.entity.location;

import lombok.Builder;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.util.UUID;

@Builder
public class Coordinates {

    @QuerySqlField(index = true)
    private Long id;

    @QuerySqlField(index = true)
    private double latitude;
    @QuerySqlField(index = true)
    private double longitude;

    @QuerySqlField(index = true)
    private UUID locationUUID;
}
