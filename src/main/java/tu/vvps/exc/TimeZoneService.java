package tu.vvps.exc;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import tu.vvps.exc.dao.StaticTimeZoneDAO;
import tu.vvps.exc.model.TimeZoneRequestDTO;

import java.io.IOException;

public class TimeZoneService {

    private static final String API_URL = "http://worldtimeapi.org/api/timezone/";

    private TimeZoneService() {
    }

    public static TimeZoneService getInstance() {
        return LazyHolder.INSTANCE;
    }

    public TimeZoneRequestDTO getByCity(String city) {

        if (city.contains("/")) {

            return getByCityFromAPI(city);

        } else if (!"".equals(StaticTimeZoneDAO.getInstance().getZoneByCity(city))) {

            String userEnteredZone = StaticTimeZoneDAO.getInstance().getZoneByCity(city);
            return getByCityFromAPI(String.format("%s/%s", userEnteredZone, city));

        } else {
            System.out.println("Error 1");
            return null;
        }
    }

    public TimeZoneRequestDTO getByCityFromAPI(String city) {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpGet request = new HttpGet(API_URL + city);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                System.out.println(response.getStatusLine().getStatusCode());   // 200
                return parseResponse(response.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new TimeZoneRequestDTO("", "", "");
    }

    private TimeZoneRequestDTO parseResponse(HttpEntity response) throws IOException {
        if (response == null) return new TimeZoneRequestDTO("", "", "");

        String jsonString = EntityUtils.toString(response);

        JSONObject jsonObject = new JSONObject(jsonString);

        return new TimeZoneRequestDTO(
                jsonObject.getString(TimeZoneRequestDTO.ABBREVIATION),
                jsonObject.getString(TimeZoneRequestDTO.TIMEZONE),
                jsonObject.getString(TimeZoneRequestDTO.UTC_OFFSET));

    }

    private static class LazyHolder {
        private static final TimeZoneService INSTANCE = new TimeZoneService();
    }
}
