package salah.api.salaholm.util.scraper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import salah.api.salaholm.entity.location.Location;
import salah.api.salaholm.entity.prayer.Prayers;
import salah.api.salaholm.mapper.PrayerMapper;
import salah.api.salaholm.util.parser.LocationProvider;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static salah.api.salaholm.util.Constants.CITIES_URL;
import static salah.api.salaholm.util.Constants.PRAYERS_URL;

@Component
@Slf4j
@RequiredArgsConstructor
public class PrayerScraper {
    private final PrayerMapper prayerMapper;
    private final LocationProvider locationProvider;

    public Location getYearlyPrayersByCity(String city) {
        Location location = locationProvider.prepareLocationBuilder(city);

        ExecutorService executor = Executors.newFixedThreadPool(6);

        try {
            List<CompletableFuture<List<Prayers>>> futures = IntStream.rangeClosed(1, 12)
                    .mapToObj(month -> CompletableFuture.supplyAsync(() -> {
                        try {
                            return fetchMonthlyPrayers(city, month, location);
                        } catch (IOException e) {
                            throw new RuntimeException("Failed to fetch month " + month, e);
                        }
                    }, executor))
                    .toList();

            List<Prayers> yearlyPrayers = futures.stream()
                    .map(CompletableFuture::join)
                    .flatMap(List::stream)
                    .toList();

            location.setPrayers(yearlyPrayers);
            return location;
        } finally {
            executor.shutdown();
        }
    }

    private List<Prayers> fetchMonthlyPrayers(String city, int month, Location location) throws IOException {
        Map<String, String> formBody = Map.of(
                "ifis_bonetider_page_city", city,
                "ifis_bonetider_page_month", Integer.toString(month)
        );

        var body = Jsoup.connect(PRAYERS_URL)
                .timeout(5000)
                .data(formBody)
                .post();

        List<Element> prayerTable = body.select("#ifis_bonetider td");
        int prayerRows = prayerTable.size() / 7;

        return IntStream.range(0, prayerRows)
                .mapToObj(i -> {
                    List<String> row = prayerTable.subList(i * 7, i * 7 + 7)
                            .stream()
                            .map(Element::text)
                            .toList();

                    Prayers prayer = prayerMapper.toPrayers(row, month);
                    prayer.setLocation(location);
                    return prayer;
                })
                .toList();
    }

    public List<String> scrapeAvailableCities() {
        try {
            Document pageBody = Jsoup.connect(CITIES_URL).get();
            Elements cities = pageBody.select("#ifis_bonetider_page_cities");
            return cities.stream().map(Element::text).toList();
        } catch (IOException e) {
            log.error("Error connecting to {}", CITIES_URL, e);
            return  List.of("ERROR FETCHING CITIES");
        }
    }
}
