package cz.ondrejsmutny;

import cz.ondrejsmutny.models.ParseHtmlService;
import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) {
        String url = "https://pocasi.idnes.cz/";

        ParseHtmlService parseHtmlService = new ParseHtmlService();

        LinkedHashMap<String, String> result;

        try {
            result = parseHtmlService.parseWeatherWebsiteNextDayHourly();
            // Zde můžete pracovat s výsledkem analýzy webové stránky
        } catch (ParseHtmlService.ParsingException e) {
            // Zde můžete zachytit a zpracovat chybu
        }

        String end = "end";
    }
}
