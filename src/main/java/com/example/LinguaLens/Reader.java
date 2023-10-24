package com.example.LinguaLens;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Reader {

    static Utilities u = new Utilities();
    private static Map<String, List<Vertex>> wordBoundaries = new HashMap<>();

    private static final String TARGET_URL = "https://vision.googleapis.com/v1/images:annotate?";
    private static final String API_KEY = "key=AIzaSyDXUth_0LBYFrs_4OI0xX9NKjZom_C-_To";

    public static class VisionAPIResponse {
        public List<ResponseItem> responses;
    }

    public static class ResponseItem {
        public List<TextAnnotation> textAnnotations;
        public FullTextAnnotation fullTextAnnotation;
    }

    public static class TextAnnotation {
        public String description; 
        public BoundingPoly boundingPoly;
    }

    public static class BoundingPoly {
        public List<Vertex> vertices;
    }

    public static class Vertex {
        public int x;
        public int y;
    }

    public static class FullTextAnnotation {
        public String text; 
    }

    public static Map<String, List<Vertex>> getWordBoundaries() {
        return wordBoundaries;
    }
    

    public static String read(String filePath) {
        String resp = "";
        
        Map<String, List<Vertex>> wordBoundaries = new HashMap<>();

        try {
            URL serverUrl = new URL(TARGET_URL + API_KEY);
            URLConnection urlConnection = serverUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.setDoOutput(true);

            try (BufferedWriter httpRequestBodyWriter = new BufferedWriter(new OutputStreamWriter(httpConnection.getOutputStream()))) {
                String requestBody = String.format("{\"requests\":  [{ \"features\":  [ {\"type\": \"TEXT_DETECTION\"}], \"image\": {\"content\": \"%s\"}}]}", encodeImage(filePath));
                httpRequestBodyWriter.write(requestBody);
            }

            if (httpConnection.getInputStream() != null) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                VisionAPIResponse apiResponse = mapper.readValue(httpConnection.getInputStream(), VisionAPIResponse.class);

                if (apiResponse.responses != null && !apiResponse.responses.isEmpty()) {
                    for (TextAnnotation annotation : apiResponse.responses.get(0).textAnnotations) {
                        // Storing the word and its bounds in the map
                        wordBoundaries.put(annotation.description, annotation.boundingPoly.vertices);
                    }
                    if (apiResponse.responses.get(0).fullTextAnnotation != null) {
                        resp = apiResponse.responses.get(0).fullTextAnnotation.text;
                    }
                }
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return resp;
    }

    private static String encodeImage(String filePath) throws IOException {
        byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
