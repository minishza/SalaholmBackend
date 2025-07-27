package salah.api.salaholm.dto.location;

import lombok.Builder;
import lombok.Data;
import salah.api.salaholm.dto.prayer.PrayersDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class LocationDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String municipality;

    private CoordinatesDTO coordinates;

    private List<PrayersDTO> prayers;
}
