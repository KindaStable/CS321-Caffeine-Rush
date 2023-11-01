package com.example.LinguaLens;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


// This code reaches out to OpenAI's ChatGPT and asks it to return the translated text. 
// It then returns the translated text to the Reader class. 
public class Translator {
    Utilities u = new Utilities();

    public static String translate(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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";
        String model = "gpt-3.5-turbo";
    

        try {

            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // The request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // print unextracted response for testing
            // System.out.println(response.toString());

            // calls the method to extract the message.
            return extractMessageFromJSONResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractMessageFromJSONResponse(String response) {
        // print response for testing
        // System.out.println(response);
        int start = response.indexOf("content")+ 11;
        int end = response.indexOf("\"", start);
        String message = response.substring(start, end);

        // Replace escaped characters with their actual values
        message = message.replace("\\n", "\n");
        message = message.replace("\\r", "\r");
        message = message.replace("\\t", "\t");


        // TODO: this return will be obsolete when the StorageManager is implemented.
        // We will simply store the translated text in the database and return nothing.
        // For our uses now, we need to return the translated text so that it can be displayed.
        return message;
    }

}
