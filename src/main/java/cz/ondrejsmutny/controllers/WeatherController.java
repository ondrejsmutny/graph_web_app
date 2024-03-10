package cz.ondrejsmutny.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import cz.ondrejsmutny.models.ParseHtmlService;
import cz.ondrejsmutny.models.WeatherDTO;

@Controller
public class WeatherController {

    @Autowired
    private ParseHtmlService parseHtmlService;

    @GetMapping("weather")
    public String getWeatherData(Model model) {
        String hourlyTemperatures = null;

        // Getting weather data
        try {
            hourlyTemperatures = parseHtmlService.parseWeatherWebsiteNextDayHourly();
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Nastala chyba při načítání dat o počasí.");

            return "error";
        }

        WeatherDTO weatherDTO = new WeatherDTO(hourlyTemperatures);
        model.addAttribute("weatherDTO", weatherDTO);

        return "weather";
    }
}
