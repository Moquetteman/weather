package uavignon.fr.weather;

import java.io.Serializable;

import javax.crypto.SecretKey;

/**
 * Created by Massimo on 25/09/2015.
 */
public class City implements Serializable{
    protected String city;
    protected String country;
    protected String lastweather; //date
    protected String wind; //km/h
    protected String windDirection;
    protected String pressure; //hPa
    protected String temperature; // Celsius


    public City(String city, String country) {
        this.city = city;
        this.country = country;
        this.lastweather = null;
        this.wind = null;
        this.windDirection = null;
        this.pressure = null;
        this.temperature = null;
    }

    @Override
    public String toString() {
        return this.city+" (" + this.country + ")";
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getLastweather() {
        return lastweather;
    }

    public String getWind() {
        return wind;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public String getPressure() {
        return pressure;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLastweather(String lastweather) {
        this.lastweather = lastweather;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}