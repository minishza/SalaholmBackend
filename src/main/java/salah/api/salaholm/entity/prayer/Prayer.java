package salah.api.salaholm.entity.prayer;

import lombok.Builder;
import lombok.Getter;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.util.UUID;

@Builder
public class Prayer {

    @Getter
    @QuerySqlField(index = true)
    private Long id;

    @QuerySqlField(index = true)
    private UUID locationUUID;
}
