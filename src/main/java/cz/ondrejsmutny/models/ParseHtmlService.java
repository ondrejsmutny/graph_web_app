package cz.ondrejsmutny.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedHashMap;

@Service
public class ParseHtmlService {

    public static class ParsingException extends Exception {
        public ParsingException(String message) {
            super(message);
        }
    }

    /**
     * Parses the specified website and extracts the hourly temperatures in Celsius degrees.
     *
     * @return JSON in String where the key is the hour in a day and the value is the temperature in Celsius degrees.
     * @throws ParsingException If there is an error while parsing the website content.
     * @throws JsonProcessingException If there is an error while parsing LinkedHashMap to JSON in String.
     */
    public String parseWeatherWebsiteNextDayHourly() throws ParsingException,
            JsonProcessingException {

        LinkedHashMap<String, String> hourlyTemperatures = new LinkedHashMap<>();

        try {
            Document document = Jsoup.connect("https://pocasi.idnes.cz/").get();

            Element dayForecastDiv = document.selectFirst("div#dayforecast");

            if (dayForecastDiv != null) {
                // Searching for content with hourly temperatures
                Elements hourElements = dayForecastDiv.select("ul li");

                for (Element hourElement : hourElements) {
                    // Getting hour
                    String hourText = hourElement.ownText();

                    // Getting temperature
                    Element temperatureElement = hourElement.selectFirst("i");
                    String temperatureText = (temperatureElement != null) ? temperatureElement.text() : "";
                    temperatureText = temperatureText.substring(0, temperatureText.length() - 1);

                    hourlyTemperatures.put(hourText, temperatureText);
                }
            } else {
                throw new ParsingException("The specified <div> element with id=\"dayforecast\" was not found in the document.");
            }
        } catch (IOException e) {
            throw new ParsingException("An error occurred while parsing the website content: " + e.getMessage());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(hourlyTemperatures);
    }
}