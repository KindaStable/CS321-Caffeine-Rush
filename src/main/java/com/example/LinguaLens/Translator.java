package com.example.LinguaLens;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class handles the translation of text using OpenAI's ChatGPT.
 * 
 * @author Garrett Thrower
 */
public class Translator {
    Utilities u = new Utilities();

    /**
     * Decodes a Base64 encoded string to plaintext.
     * @param base64String The Base64 encoded string to decode.
     * @return The decoded plaintext string.
     */
    public static String decodeBase64(String base64String) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64String);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

    /**
     * Translates a given prompt using OpenAI's ChatGPT and stores the translation.
     * @param prompt The text to translate.
     */
    public static void translate(String prompt) {
        // Define the URL for the API endpoint
        String url = "https://api.openai.com/v1/chat/completions";
        // Define the model to be used for the translation
        String model = "gpt-3.5-turbo-1106";

        try {
            // Create a URL object with the API endpoint
            URL obj = new URL(url);
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            // Set the request method to POST
            connection.setRequestMethod("POST");
            // Set the Authorization header with the API key
            connection.setRequestProperty("Authorization", "Bearer " + decodeBase64("c2stOVpwR2EwMHRpSUFwalY4NmdpWENUM0JsYmtGSk1RZDc3cThKeGhVbnQ3RzUxbk1a"));
            // Set the Content-Type header to application/json
            connection.setRequestProperty("Content-Type", "application/json");

            // Create the body of the request
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            // Enable output for the connection
            connection.setDoOutput(true);
            // Write the body of the request to the connection
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Create a BufferedReader to read the response from the API
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();

            // Read the response line by line
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // Extract the translated message from the response and store it
            StorageManager.storeTranslation(extractMessageFromJSONResponse(response.toString()));

        } catch (IOException e) {
            // If an error occurs during the request, throw a RuntimeException
            throw new RuntimeException(e);
        }
    }

    
    /**
     * Extracts the translated message from the JSON response of the OpenAI API.
     * The method expects the JSON response to have a specific structure:
     * The "content" field should be nested inside the "message" object, 
     * which is in turn nested inside the first object of the "choices" array.
     * If the JSON response doesn't have this structure, the method will throw a RuntimeException.
     *
     * @param response The JSON response from the OpenAI API.
     * @return The extracted translated message.
     * @throws RuntimeException if the JSON response doesn't have the expected structure or if an error occurs during parsing.
     */
    public static String extractMessageFromJSONResponse(String response) {
        try {
            // Parse the JSON response string into a JsonNode object
            JsonNode rootNode = new ObjectMapper().readTree(response);
            
            // Navigate through the nested structure of the JsonNode to get to the "content" field
            // The path method returns a missing node (not null) if the field doesn't exist, which allows us to chain calls
            // The get method returns null if the array doesn't have an element at the specified index
            // The asText method converts the value of the JsonNode to a String
            return rootNode.path("choices").get(0).path("message").path("content").asText();
        } catch (Exception e) {
            // If an error occurs during parsing or if the JSON response doesn't have the expected structure, throw a RuntimeException
            throw new RuntimeException("Failed to extract message from JSON response", e);
        }
    }
}