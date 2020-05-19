package ui.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class HolidayInfo {
    private static BufferedReader br = null;
    private static String apikey = "91fe7101532e0862b92ee14c6be29621b9083c18";
    private static String calendarific = "https://calendarific.com/api/v2";
    private static String theURL = calendarific + "/holidays?api_key=" + apikey
            + "&country=CA&year=2019&type=national&location=ca-bc";

    // REQUIRES: the website and APIkey are valid and the connection is successful
    // EFFECTS: connect to Calendarific.com and print out the 2019 national holidays celebrated in BC
    //          as well as the location which I retrieved the information from
    public static String printHolidays() throws IOException {
        try {
            URLConnection connection = new URL(theURL).openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
                    + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36)");
            connection.connect();

            br = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.defaultCharset()));
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                findRelevantInfo(line, sb);
            }

            return "Here is the list of Canada's national holidays that are celebrated in BC: \n" + sb
                    + "\n\nRetrieved from: " + theURL;

        } finally {

            if (br != null) {
                br.close();
            }

        }
    }

    // EFFECTS: build the string body by filtering details that I need: event name and event description
    private static void findRelevantInfo(String line, StringBuilder sb) {
        String[] pair = line.split("\\{");

        for (String str : pair) {
            if (str.contains("description")) {
                String sub = str.substring(0, str.indexOf(","));
                String sub2 = str.substring(str.indexOf(",") + 1,
                        str.indexOf(",", str.indexOf(",") + 1));
                sb.append(System.lineSeparator());
                sb.append(sub);
                sb.append(System.lineSeparator());
                sb.append(sub2);
                sb.append(System.lineSeparator());
            }
        }
    }
}
