package com.example.LinguaLens;
import java.io.IOException;

// This code accesses all the methods necessary in order to read and display the text from an image.
class Main {

    public static void main(String[] args) throws IOException {
        Utilities u = new Utilities();
        
        // TODO: Run the program again if the user wishes.
        UI.showDialogue("Please select an image with text that you would like the program to read.", "Select Image", 0);
        String filePath = UI.chooseImage();
        //UI.showDialogue(filePath, "Thanks", 0);
        String readText = Reader.read(filePath);

        if(readText != "")
        {
            UI.showDialogue(readText, "Thanks", 0);
        }

        u.print("End of Program");
        System.exit(0);
    }
}
