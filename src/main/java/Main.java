import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите город для поиска прогноза погоды : ");
        String city = scanner.nextLine();
        Parser parser = new Parser();
        System.out.println(parser.findWeatherForecast(city));
    }
}
