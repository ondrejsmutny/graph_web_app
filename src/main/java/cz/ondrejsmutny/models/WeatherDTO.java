package cz.ondrejsmutny.models;

public class WeatherDTO {
    private String hourlyTemperatures;

    public WeatherDTO(String hourlyTemperatures) {
        this.hourlyTemperatures = hourlyTemperatures;
    }

    public String getHourlyTemperatures() {
        return hourlyTemperatures;
    }

    public void setHourlyTemperatures(String hourlyTemperatures) {
        this.hourlyTemperatures = hourlyTemperatures;
    }
}