package part1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class SCChallengeEmail {

    public static void main(String args[]) throws Exception {

        String url_string = "https://www.ecs.soton.ac.uk/people/";

        // Prompts user for input
        System.out.println("Enter the ID of the user you would like to search:");
        // Creates a buffered reader object to read in from the console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Creates a String object from the input
        String name = reader.readLine();
        // Closes BufferedReader object now we're done
        reader.close();
        // Creates url path by joining url and the input
        String urlSearch = String.join("", url_string, name);

        // Creates a url object to get the data from
        URL url = new URL(urlSearch);
        // Creates a BufferedReader object to read the url
        BufferedReader urlReader = new BufferedReader(new InputStreamReader(url.openStream()));

        // Iterates over every html line to search for desired data
        String inputLine;
        String desiredName = "";

        boolean found = false;
        while ((inputLine = urlReader.readLine()) != null) {
            if (inputLine.contains("property=\"name\"")) {
                found = true;
                int start = inputLine.indexOf("property=\"name\"");
                int end = inputLine.indexOf("<em property=\"honorificSuffix\">");
                System.out.println(inputLine.substring(start+16, end));
            }
        }
        if (!found) {
            System.out.println("No name found");
        }
        urlReader.close();
    }

}
