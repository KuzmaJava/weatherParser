import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    static List<WeatherSlot> weatherSlots = new ArrayList<WeatherSlot>();

    static StringBuilder builder = new StringBuilder();

    private static Document getPage(String city) throws IOException {
        String url = "http://sinoptik.ua/погода-";
        Document page = Jsoup.parse(new URL(url.concat(city)), 3000);
        return page;
    }

    static void createWeatherSlots() {
        // create weather slots
        WeatherSlot weatherSlot1 = new WeatherSlot(1, "ночь");
        WeatherSlot weatherSlot2 = new WeatherSlot(2, "ночь");
        WeatherSlot weatherSlot3 = new WeatherSlot(3, "утро");
        WeatherSlot weatherSlot4 = new WeatherSlot(4, "утро");
        WeatherSlot weatherSlot5 = new WeatherSlot(5, "день");
        WeatherSlot weatherSlot6 = new WeatherSlot(6, "день");
        WeatherSlot weatherSlot7 = new WeatherSlot(7, "вечер");
        WeatherSlot weatherSlot8 = new WeatherSlot(8, "вечер");
        weatherSlots.add(weatherSlot1);
        weatherSlots.add(weatherSlot2);
        weatherSlots.add(weatherSlot3);
        weatherSlots.add(weatherSlot4);
        weatherSlots.add(weatherSlot5);
        weatherSlots.add(weatherSlot6);
        weatherSlots.add(weatherSlot7);
        weatherSlots.add(weatherSlot8);
    }

    static void setTimes(Document page, Element element) throws IOException {
        Element elem = element.select("tr").first();
        Elements elements = elem.select("td");

        for (int i = 0; i < elements.size(); i++) {
            weatherSlots.get(i).setTime(elements.get(i).text());
        }
    }

    private static void setTemperatures(Document page, Element element) {
        Element elem = element.select("tr").first();
        Elements elements = elem.select("td");

        for (int i = 0; i < elements.size(); i++) {
            weatherSlots.get(i).setTemperature(elements.get(i).text());
        }
    }

    private static void setHumidities(Document page, Element element) {
        Element elem = element.select("tr").first();
        Elements elements = elem.select("td");

        for (int i = 0; i < elements.size(); i++) {
            weatherSlots.get(i).setHumidity(elements.get(i).text());
        }
    }

    String findWeatherForecast(String city) throws IOException {
        Document page = getPage(city);
        createWeatherSlots();
        Element tBodyElement = page.select("tbody").first();
        Element grayTimeElement = tBodyElement.select("tr[class=gray time]").first();
        Element temperatureSensElement = tBodyElement.select("tr[class=temperatureSens]").first();
        Element grayElement = tBodyElement.select("tr[class=gray]").first().nextElementSibling();

        setTimes(page, grayTimeElement);
        setTemperatures(page, temperatureSensElement);
        setHumidities(page, grayElement);
        builder.append("Прогноз погоды для города " + city + " на " + LocalDate.now() + "\n");
        builder.append("__________________________________" + "\n");
        for (WeatherSlot weatherSlot : weatherSlots) {
            builder.append(weatherSlot + "\n");
        }
        return builder.toString();
    }
}
