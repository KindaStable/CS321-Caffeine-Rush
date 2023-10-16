package com.example.LinguaLens;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

//UI nonsense goes here.

public class UI {

    public static void showDialogue(String message, String buttonDisplay, int delaySeconds) {
        Dialog dialog = new Dialog((Frame) null, "LinguaLens", true);
        dialog.setLayout(new BorderLayout());
    
        Button okButton = new Button(buttonDisplay);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
    
        TextArea textArea = new TextArea(message, 5, 40, TextArea.SCROLLBARS_VERTICAL_ONLY);
        textArea.setEditable(false);
    
        Panel buttonPanel = new Panel();
    
        if (delaySeconds > 0) {
            // If a delay has been set, set up a Timer to close the dialog after that delay
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    dialog.dispose();
                }
            }, delaySeconds * 1000); // Convert delaySeconds to milliseconds
        } else {
            // Only add the button if no auto-close delay has been set
            buttonPanel.add(okButton);
        }
    
        dialog.add(textArea, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setVisible(true);
    }

    public static String chooseImage() {
        FileDialog fileDialog = new FileDialog((Frame) null, "Select an Image", FileDialog.LOAD);
        fileDialog.setFile("*.jpg;*.jpeg;*.png;*.gif;*.bmp"); // Filter for image files
        fileDialog.setVisible(true);

        String directory = fileDialog.getDirectory();
        String filename = fileDialog.getFile();

        if (directory != null && filename != null) {
            return directory + filename;
        }
        return null;
    } 
}
