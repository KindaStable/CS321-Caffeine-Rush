package com.example.LinguaLens;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

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
        String url = "https://api.openai.com/v1/chat/completions";
        String model = "gpt-3.5-turbo-1106";

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + decodeBase64("c2stOVpwR2EwMHRpSUFwalY4NmdpWENUM0JsYmtGSk1RZDc3cThKeGhVbnQ3RzUxbk1a"));
            connection.setRequestProperty("Content-Type", "application/json");

            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            StorageManager.storeTranslation(extractMessageFromJSONResponse(response.toString()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Extracts the translated message from the JSON response of the OpenAI API.
     * @param response The JSON response from the OpenAI API.
     * @return The extracted translated message.
     */
    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content") + 11;
        int end = response.indexOf("\"", start);
        String message = response.substring(start, end);

        message = message.replace("\\n", "\n");
        message = message.replace("\\r", "\r");
        message = message.replace("\\t", "\t");

        return message;
    }
}