package salah.api.salaholm.dto.location;

import lombok.Builder;
import lombok.Data;
import salah.api.salaholm.dto.prayer.PrayersDTO;

import java.util.List;

@Data
@Builder
public class LocationDTO {
    private String municipality;

    private CoordinatesDTO coordinates;

    private List<PrayersDTO> prayers;
}
