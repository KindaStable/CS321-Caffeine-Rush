package com.example.LinguaLens;
import java.io.IOException;
import java.util.Locale;
import org.apache.commons.text.StringEscapeUtils;

// This code accesses all the methods necessary in order to read and display the text from an image.
class Main {

    public static void main(String[] args) throws IOException {
        Utilities u = new Utilities();

        // TODO, GARRETT: The translator output sometimes returns mojibake characters (e.g. "â€œ" instead of "“").
        // I have no idea whether this is a problem with the way the text is being read from the image, or with the way the text is being translated.
        // I'll try to implement a fix for this, but I'm not sure how to do it. I'll figure it out eventually.
        UI.showDialogue("Please select an image with text that you would like to be translated.", "Select Image", 0);

        String filePath = UI.chooseImage();
        String readText = Reader.read(filePath);
        String escapedText = StringEscapeUtils.escapeJson(readText);
        String prompt = "Translate the below text into " + Locale.getDefault().getDisplayLanguage() + ". Make sure to display all of the text: " + escapedText;
        
        // Print out the read text from the vision API.
        // u.print(readText);

        if (readText != "") {
            String translatedText = Translator.translate(prompt);
            UI.showDialogue(translatedText, "Thanks", 0);
        }

        System.exit(0);
    }
    
}
