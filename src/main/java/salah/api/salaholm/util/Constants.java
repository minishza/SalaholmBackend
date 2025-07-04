package salah.api.salaholm.util;

import org.springframework.stereotype.Component;

@Component
public final class Constants {
    private Constants() {}
    public static final String ISLAMISKA_CONNECTION_URL = "https://www.islamiskaforbundet.se/bonetider/";
    public static final String ISLAMISKA_MONTH_OPTIONS = "#ifis_bonetider_page_months option:nth-child(-n+12)";
    public static final String ISLAMISKA_CITIES_OPTIONS = "#ifis_bonetider_page_cities option";
    public static final String ISLAMISKA_PRAYERS_TABLE = "tbody tr";
    public static final String LAST_CITY = "Östersund";
}
