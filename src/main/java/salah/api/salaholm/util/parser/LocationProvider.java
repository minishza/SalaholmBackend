package salah.api.salaholm.util.parser;


import com.opencagedata.jopencage.JOpenCageGeocoder;
import com.opencagedata.jopencage.model.JOpenCageForwardRequest;
import com.opencagedata.jopencage.model.JOpenCageLatLng;
import com.opencagedata.jopencage.model.JOpenCageResponse;
import com.opencagedata.jopencage.model.JOpenCageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import salah.api.salaholm.entity.location.Coordinates;
import salah.api.salaholm.entity.location.Location;
import salah.api.salaholm.util.IdGenerator;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LocationProvider {
    private IdGenerator idGenerator;

    public Location prepareLocationBuilder(String city) {
        JOpenCageResult locationResult = getJOpenCageResult(city);

        var municipality = getCity(locationResult);

        UUID locationUUID = UUID.randomUUID();
        Location location = Location.builder()
                .id(locationUUID)
                .municipality(municipality)
                .build();

        persistCoordinates(locationResult, locationUUID);

        return location;
    }

    private void persistCoordinates(JOpenCageResult response, UUID locationUUID) {
        JOpenCageLatLng location = response.getGeometry();

        var coords = Coordinates.builder()
                .id(idGenerator.nextCoordinatesId())
                .latitude(location.getLat())
                .longitude(location.getLng())
                .locationUUID(locationUUID)
                .build();

        //persist
    }

    private String getCity(JOpenCageResult response) {
        return response.getFormatted();
    }

    private JOpenCageForwardRequest getJOpenCageForwardRequest(String city) {
        var request = new JOpenCageForwardRequest(city);
        request.setLanguage("en"); // show results in a specific language using an IETF format language code
        request.setLimit(1); // only return the first 5 results (default is 10)
        request.setNoAnnotations(false); // exclude additional info such as calling code, timezone, and currency
        request.setMinConfidence(3); // restrict to results with a confidence rating of at least 3 (out of 10)
        request.setRestrictToCountryCode("SE");
        return request;
    }
    private JOpenCageResult getJOpenCageResult(String city) {
        //move to secret vault
        var apiKey = "56820c6646184270ac33780344ae4707";
        var jOpenCageGeocoder = new JOpenCageGeocoder(apiKey);

        JOpenCageForwardRequest request = getJOpenCageForwardRequest(city);
        JOpenCageResponse response = jOpenCageGeocoder.forward(request);

        return response.getResults().get(0);
    }
    private Long hashToLong(String input) {
        return input.hashCode() & 0xffffffffL;
    }
}
