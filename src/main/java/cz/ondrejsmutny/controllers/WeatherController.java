package cz.ondrejsmutny.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.LinkedHashMap;
import cz.ondrejsmutny.models.ParseHtmlService;
import cz.ondrejsmutny.models.WeatherDTO;

@Controller
public class WeatherController {

    @Autowired
    private ParseHtmlService parseHtmlService;

    @GetMapping("weather")
    public String getWeatherData(Model model) {
        String hourlyTemperatures = null;

        try {
            hourlyTemperatures = parseHtmlService.parseWeatherWebsiteNextDayHourly();
            // Zde můžete pracovat s výsledkem analýzy webové stránky
        } catch (Exception e) {
            // Zde můžete zachytit a zpracovat chybu
        }

        WeatherDTO weatherDTO = new WeatherDTO(hourlyTemperatures);
        // Předání objektu WeatherDTO do Thymeleaf šablony
        model.addAttribute("weatherDTO", weatherDTO);

        return "weather";
    }
}
