package salah.api.salaholm.dto.location;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class CoordinatesDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    private double latitude;
    private double longitude;
}
