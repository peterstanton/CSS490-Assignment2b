import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final String baseGeocode = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private static final String googleAPI = "AIzaSyAXOUJZME49BZfMWw3XGCqdrZ0-2MvQj6U";
    private static final String baseWeather = "https://api.darksky.net/forecast/";
    private static final String weatherKey = "6695e2cfcb5944be3b28b2bcbe4cce57";

    public static void main(String[] args) {
        System.out.println("*****************************************************************************************");
        System.out.println("Welcome to Peter Stanton's CSS490 Weather application!");
        System.out.println("Powered by Google and Dark Sky");
        System.out.println("https://darksky.net/poweredby/");
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.println("Please enter the address, zip code, or city name you want weather for below:");
            String input = in.nextLine();
            if(input.isEmpty()) {
                continue;
            }
            String[] parsed = input.split("\\W+");
            URL googleURL = processGeoURL(parsed);
            String geoResult = processResponse(googleURL);
            ArrayList<String> geoSplit = new ArrayList<String>(Arrays.asList(geoResult.split("\n")));
            int geoLatIndex = -1;
            int geoLongIndex = -1;
            for (int i = 0; i < geoSplit.size(); i++) {
                if (geoSplit.get(i).contains("location")) {
                    geoLatIndex = i + 1;
                    geoLongIndex = i + 2;
                    break;
                }
            }
            if (geoLatIndex == -1) {
                return;
            }
            double geoLat = Double.parseDouble(geoSplit.get(geoLatIndex).split(":")[1].replaceAll(",", ""));
            double geoLong = Double.parseDouble(geoSplit.get(geoLongIndex).split(":")[1].replaceAll(",", ""));
            URL darkSkyURL = processWeatherURL(geoLat, geoLong);
            String weatherResult = processResponse(darkSkyURL);
            printResults(weatherResult, input);
        }
    }

    private static void printResults(String weatherResult, String locale) {
        //stuff here.
        String[] results = weatherResult.split("\\{");
        StringBuilder output = new StringBuilder();
        String[] curWeather;
        String[] minutelyWeather;
        String[] hourlyWeather;
        output.append("Weather for: " + locale + "\n");
        for(int i = 0; i < results.length; i++) {
            String cur = results[i];
            if(cur.contains("currently")) {
                output.append("Timezone: " + cur.split(",")[2].split(":")[1] + "\n");
                output.append("--------------------------------------------------------------------");
                curWeather = results[i+1].split(",");
            }
            else if(cur.contains("minutely")) {
                minutelyWeather = results[i+1].split(",");
            }
            else if(cur.contains("hourly")) {
                hourlyWeather = results[i+1].split(",");
            }
        }
        output.append("Overview");


        //should loop through with a switch tree and on switch, stuff values in a string builder, then
        //spit it out when I'm done.
    }


    //more stuff here.
    private static URL processGeoURL(String[] parsed) {
        String joined = String.join("+", parsed);
        joined += "&key=" + googleAPI;
        try {
            return new URL(baseGeocode + joined);
        } catch (MalformedURLException e) {
            //blah
        }
        return null;
    }

    private static String processResponse(URL inURL) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inURL.openStream()));
            String outResult;
            StringBuilder builder = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            try {
                while ((read = reader.read(chars)) != -1)
                    builder.append(chars, 0, read);

                outResult = builder.toString();
                return outResult;
            } catch (IOException e) {
                //blah
            }
        } catch (IOException e) {
            //blah
        }
        return null;
    }

    private static URL processWeatherURL(double geoLat, double geoLong) {
        String joined = baseWeather + weatherKey + '/' + geoLat + ',' + geoLong;
        try {
            return new URL(joined);
        } catch (MalformedURLException e) {
            //blah
        }
        return null;
    }
}
