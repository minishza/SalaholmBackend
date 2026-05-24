package salah.api.salaholm.dto.prayer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MonthlyPrayersDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<PrayersDTO> prayers;
}
