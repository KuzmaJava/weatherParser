public class WeatherSlot {
    private int id;
    private String dayTime;
    private String time;
    private String temperature;
    private String humidity;

    public WeatherSlot(int id, String dayTime) {
        this.id = id;
        this.dayTime = dayTime;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return dayTime + ", " + time + ", " + temperature + ", " + "влажность: " + humidity + "%";
    }
}
