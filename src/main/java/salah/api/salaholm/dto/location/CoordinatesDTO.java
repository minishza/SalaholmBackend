package salah.api.salaholm.dto.location;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoordinatesDTO {
    private double latitude;
    private double longitude;
}
