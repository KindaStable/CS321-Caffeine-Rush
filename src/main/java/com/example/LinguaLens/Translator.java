package com.example.LinguaLens;

// This code reaches out to OpenAI's ChatGPT and asks it to return the translated text. 
// It then returns the translated text to the Reader class. 
public class Translator {
    Utilities u = new Utilities();
    
    // Constants to store the URL endpoint and our API key for OpenAI's ChatGPT.
    private static final String TARGET_URL = "";
    private static final String API_KEY = "";

    public String translate(String text, String language) {
        String translatedText = "";
        
        // arbitrary code to silence the warnings on target_url and api_key being unused for now.
        if (TARGET_URL == "" && API_KEY == "") {
            u.print("Silencing warnings");
        }

        return translatedText;
    }

}
