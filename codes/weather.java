import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherMessage {
    public static void main(String[] args) {
        try {
            // OpenWeatherMap APIから天気情報を取得する
            String apiKey = "your_api_key_here";
            String city = "Tokyo";
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 取得したJSONデータから天気情報を抽出する
            JSONObject jsonObject = new JSONObject(response.toString());
            JSONObject main = jsonObject.getJSONObject("main");
            double temp = main.getDouble("temp");
            double pressure = main.getDouble("pressure");

            // 天気と気圧に応じて応援メッセージを出力する
            if (temp >= 25 && pressure >= 1013) {
                System.out.println("暑い中、がんばりましょう！");
            } else if (temp < 5 && pressure <= 1000) {
                System.out.println("寒い中、がんばりましょう！");
            } else {
                System.out.println("今日も一日頑張りましょう！");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
