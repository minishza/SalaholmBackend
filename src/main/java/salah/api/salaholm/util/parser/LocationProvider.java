package salah.api.salaholm.util.parser;


import com.opencagedata.jopencage.JOpenCageGeocoder;
import com.opencagedata.jopencage.model.JOpenCageForwardRequest;
import com.opencagedata.jopencage.model.JOpenCageLatLng;
import com.opencagedata.jopencage.model.JOpenCageResponse;
import com.opencagedata.jopencage.model.JOpenCageResult;
import org.springframework.stereotype.Component;
import salah.api.salaholm.entity.location.Coordinates;
import salah.api.salaholm.entity.location.Location;

@Component
public class LocationProvider {
    public Location prepareLocationBuilder(String city) {
        JOpenCageResult locationResult = getJOpenCageResult(city);

        var coordinates = prepareCoordinatesBuilder(locationResult);
        var municipality = getCity(locationResult);

        Location.LocationBuilder locationBuilder = Location.builder()
                .municipality(municipality);

        Location location = locationBuilder.build();
        coordinates.setLocation(location);
        location.setCoordinates(coordinates);

        location.setId(hashToLong(city));

        return location;
    }

    private Coordinates prepareCoordinatesBuilder(JOpenCageResult response) {
        JOpenCageLatLng location = response.getGeometry();

        return Coordinates.builder()
                .latitude(location.getLat())
                .longitude(location.getLng())
                .build();
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
