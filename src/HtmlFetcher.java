import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HtmlFetcher {

    public static String fetch(String urlString) throws Exception {

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        // Timeouts
        connection.setConnectTimeout(50000);
        connection.setReadTimeout(50000);

        // Evita bloqueios por alguns servidores (Google, etc)
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setInstanceFollowRedirects(true);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );

        StringBuilder html = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            html.append(line).append("\n");
        }

        reader.close();

        return html.toString();
    }
}
