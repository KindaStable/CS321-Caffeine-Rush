package com.example.LinguaLens;

import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;


// Modified code. Source: https://github.com/GoogleCloudPlatform/community/blob/master/archived/make-an-http-request-to-the-cloud-vision-api-from-java.md

public class Reader {
    static Utilities u = new Utilities();

    private static final String TARGET_URL = "https://vision.googleapis.com/v1/images:annotate?";
    private static final String API_KEY = "key=AIzaSyDXUth_0LBYFrs_4OI0xX9NKjZom_C-_To";

    public static String read(String filePath) {
        String resp = "";
        long startTime = System.currentTimeMillis(); // Record the start time
    
        try {
            // Create a URL object with the target URL and create a connection to that URL
            URL serverUrl = new URL(TARGET_URL + API_KEY);
            URLConnection urlConnection = serverUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
    
            // Set the method and Content-Type of the connection
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");
    
            // Prepare the connection to be written to enable creation of the data portion of the request
            httpConnection.setDoOutput(true);
    
            // Create a writer and use it to write the data portion of the request
            try (BufferedWriter httpRequestBodyWriter = new BufferedWriter(new OutputStreamWriter(httpConnection.getOutputStream()))) {
                String requestBody = String.format("{\"requests\":  [{ \"features\":  [ {\"type\": \"TEXT_DETECTION\"}], \"image\": {\"content\": \"%s\"}}]}", encodeImage(filePath));
                httpRequestBodyWriter.write(requestBody);
            }
    
            // Wait until resp has a value or until 10 seconds have passed
            while (resp.isEmpty() && (System.currentTimeMillis() - startTime) < 10000) {
                if (httpConnection.getInputStream() != null) {
                    try (Scanner httpResponseScanner = new Scanner(httpConnection.getInputStream())) {
                        while (httpResponseScanner.hasNext()) {
                            String line = httpResponseScanner.nextLine();
                            resp += line;
                        }
                    }
                }
                UI.showDialogue("Reading image...", "OK", 1);
                Thread.sleep(100); // Sleep for 100 milliseconds before checking again
            }

            if (!resp.isEmpty()) {
                resp = extractDescription(resp);
            }
    
        } catch (Exception e) {
            //e.printStackTrace();
            System.exit(1);
        }
    
        return resp;
    }


    public static String extractDescription(String resp) {
        String keyword = "\"description\": \"";
        int startIndex = resp.indexOf(keyword);
        if (startIndex == -1) {
            return "Description not found";
        }
        startIndex += keyword.length();
        int endIndex = resp.indexOf("\"", startIndex);
        if (endIndex == -1) {
            return "Description not found";
        }
        String description = resp.substring(startIndex, endIndex);
        return description.replace("\\n", System.lineSeparator());
    }
    

    private static String encodeImage(String filePath) throws IOException {
        byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
