package com.example.LinguaLens;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.LinkedHashMap;

// We import necessary libraries to handle JSON parsing, network requests, and file operations.
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

    // We instantiate a utilities object for any helper functions we might need.
    static Utilities u = new Utilities();
    
    // This map will store the boundaries of detected words.
    private static Map<String, List<Vertex>> wordBoundaries = new HashMap<>();

    // Constants to store the URL endpoint and our API key for Google Vision API.
    private static final String TARGET_URL = "https://vision.googleapis.com/v1/images:annotate?";
    private static final String API_KEY = "key=AIzaSyDXUth_0LBYFrs_4OI0xX9NKjZom_C-_To";

    // Below, we define classes to capture the structure of Google Vision API's response.
    
    public static class VisionAPIResponse {
        public List<ResponseItem> responses;
    }

    public static class ResponseItem {
        public List<TextAnnotation> textAnnotations;
        public FullTextAnnotation fullTextAnnotation;
    }

    public static class TextAnnotation {
        public String description; // This stores the actual detected text.
        public BoundingPoly boundingPoly; // This will store the boundaries of the detected text.
    }

    public static class BoundingPoly {
        public List<Vertex> vertices; // A list of vertices defining the polygon around our text.
    }

    public static class Vertex {
        public int x; // x-coordinate
        public int y; // y-coordinate
    }

    public static class FullTextAnnotation {
        public String text; // This field stores the entire detected text.
    }

    // A method to fetch our word boundaries map from outside this class.
    public static Map<String, List<Vertex>> getWordBoundaries() {
        return wordBoundaries;
    }

    // If we don't sort the map, then the text is not stored in natural reading order. Perhaps this is just to fix a mistake I made storing things into the map earlier, but it should be fine.
    public static Map<String, List<Vertex>> sortByLeftMostVertex(Map<String, List<Vertex>> unsortedMap) {
    return unsortedMap.entrySet()
            .stream()
            .sorted((e1, e2) -> Integer.compare(e1.getValue().get(0).x, e2.getValue().get(0).x))
            .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1,
                    LinkedHashMap::new
            ));
    }


    // The main function to read and detect text from the provided image path.
    public static String read(String filePath) {
        String resp = "";
        
        wordBoundaries = sortByLeftMostVertex(wordBoundaries);

        try {
            // We initialize a POST request to the Google Vision API endpoint.
            URL serverUrl = new URL(TARGET_URL + API_KEY);
            URLConnection urlConnection = serverUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.setDoOutput(true);

            // We encode the image to base64 and send it in the request body.
            try (BufferedWriter httpRequestBodyWriter = new BufferedWriter(new OutputStreamWriter(httpConnection.getOutputStream()))) {
                String requestBody = String.format("{\"requests\":  [{ \"features\":  [ {\"type\": \"TEXT_DETECTION\"}], \"image\": {\"content\": \"%s\"}}]}", encodeImage(filePath));
                httpRequestBodyWriter.write(requestBody);
            }

            // If we receive a response, we process it.
            if (httpConnection.getInputStream() != null) {
                ObjectMapper mapper = new ObjectMapper();
                // We configure our JSON parser to ignore properties not defined in our classes.
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

    // TODO: Hey Braden! This task is for you, buddy. 😉
            
    // Your objective: Store the detected text (denoted by 'resp') and the associated word boundaries (represented by 'wordBoundaries'). 
    // Useful information:
    // - 'resp' is a String that contains the whole detected text.
    // - 'wordBoundaries' is a map with the structure: Map<String, List<Vertex>> that contains the dimensions of the words' bounding boxes.
    // - The bounding boxes provided by the 'wordBoundaries' are relative to the image's dimensions, where the top-left corner is (0,0), x-coordinates increase from left to right, and y-coordinates increase from top to bottom.
   
    // Below is a sample method call for storage. You can draw inspiration from it if you wish.

    // StorageManager.storeData(resp, wordBoundaries);

    // To view the data that has been stored in wordBoundaries, you can uncomment the below block. This will print each detected word and its vertices.

    /*
    for (Map.Entry<String, List<Vertex>> entry : wordBoundaries.entrySet()) {
        String word = entry.getKey();
        List<Vertex> vertices = entry.getValue();
        
        System.out.print(word + ": ");
        for (Vertex vertex : vertices) {
            System.out.print("(" + vertex.x + ", " + vertex.y + ") ");
        }
        System.out.println(); // Move to the next line after printing all vertices for a word
    }
    */


        //TODO: Once the storage manager is complete, this return will be obsolete, as we will be able to pull resp and any other information we want from it.
        return resp;
    }

    private static String encodeImage(String filePath) throws IOException {
        byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
