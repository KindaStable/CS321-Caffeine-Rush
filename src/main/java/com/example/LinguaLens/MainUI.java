/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.example.LinguaLens;
//import javax.swing.ImageIcon;
import java.awt.*;
import java.util.Locale;
import org.apache.commons.text.StringEscapeUtils;


public class MainUI extends javax.swing.JFrame {

    //ImageIcon imageToTranslate = new ImageIcon("userfile from file chooser");
    
    /**
     * Creates new form NewUIBelle
     */
    public MainUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        translationInternalFrame = new javax.swing.JInternalFrame();
        translationScrollPane = new javax.swing.JScrollPane();
        translationTextArea = new javax.swing.JTextArea();
        translationLabel = new javax.swing.JLabel();
        ogtextInternalFrame = new javax.swing.JInternalFrame();
        ogtextScrollPane = new javax.swing.JScrollPane();
        ogtextTextArea = new javax.swing.JTextArea();
        ogtextLabel = new javax.swing.JLabel();
        historyInternalFrame = new javax.swing.JInternalFrame();

        javax.swing.JButton historyButtons[] = new javax.swing.JButton[10];
        for (int index = 0; index < 10; index++) {
            historyButtons[index] = new javax.swing.JButton();
        }

        imageInternalFrame = new javax.swing.JInternalFrame();
        translateButton = new javax.swing.JButton();
        openfileButton = new javax.swing.JButton();
        snipButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LinguaLens");
        setBackground(new java.awt.Color(51, 51, 51));
        setForeground(new java.awt.Color(51, 51, 51));
        setMaximumSize(new java.awt.Dimension(1200, 675));
        setMinimumSize(new java.awt.Dimension(1200, 675));
        setPreferredSize(new java.awt.Dimension(1200, 675));
        setResizable(false);
        setSize(new java.awt.Dimension(1200, 675));


        translationInternalFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        translationInternalFrame.setIconifiable(true);
        translationInternalFrame.setResizable(true);
        translationInternalFrame.setTitle("Translated Text");
        translationInternalFrame.setFocusable(false);
        translationInternalFrame.setMaximumSize(new java.awt.Dimension(450, 337));
        translationInternalFrame.setPreferredSize(new java.awt.Dimension(450, 337));
        translationInternalFrame.setRequestFocusEnabled(false);
        translationInternalFrame.setVerifyInputWhenFocusTarget(false);
        translationInternalFrame.setVisible(true);

        translationTextArea.setEditable(false);
        translationTextArea.setColumns(20);
        translationTextArea.setLineWrap(true);
        translationTextArea.setRows(5);
        translationTextArea.setWrapStyleWord(true);
        translationTextArea.setFocusable(false);
        translationTextArea.setPreferredSize(new java.awt.Dimension(100, 100));

        translationScrollPane.setViewportView(translationTextArea);

        translationLabel.setText("Translated to: ");

        javax.swing.GroupLayout translationInternalFrameLayout = new javax.swing.GroupLayout(translationInternalFrame.getContentPane());
        translationInternalFrame.getContentPane().setLayout(translationInternalFrameLayout);
        translationInternalFrameLayout.setHorizontalGroup(
            translationInternalFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(translationScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
            .addComponent(translationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        translationInternalFrameLayout.setVerticalGroup(
            translationInternalFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, translationInternalFrameLayout.createSequentialGroup()
                .addComponent(translationLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(translationScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
        );


        ogtextInternalFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        ogtextInternalFrame.setIconifiable(true);
        ogtextInternalFrame.setTitle("Original Text");
        ogtextInternalFrame.setFocusable(false);
        ogtextInternalFrame.setMaximumSize(new java.awt.Dimension(450, 337));
        ogtextInternalFrame.setPreferredSize(new java.awt.Dimension(450, 337));
        ogtextInternalFrame.setRequestFocusEnabled(false);
        ogtextInternalFrame.setVerifyInputWhenFocusTarget(false);
        ogtextInternalFrame.setVisible(true);

        ogtextTextArea.setEditable(false);
        ogtextTextArea.setColumns(20);
        ogtextTextArea.setLineWrap(true);
        ogtextTextArea.setRows(5);
        ogtextTextArea.setWrapStyleWord(true);
        ogtextTextArea.setFocusable(false);

        ogtextScrollPane.setViewportView(ogtextTextArea);
        ogtextScrollPane.setPreferredSize(new java.awt.Dimension(224, 86));

        ogtextLabel.setText("Detected Language: ");


        javax.swing.GroupLayout ogtextInternalFrameLayout = new javax.swing.GroupLayout(ogtextInternalFrame.getContentPane());
        ogtextInternalFrame.getContentPane().setLayout(ogtextInternalFrameLayout);
        ogtextInternalFrameLayout.setHorizontalGroup(
            ogtextInternalFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ogtextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
            .addComponent(ogtextLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ogtextInternalFrameLayout.setVerticalGroup(
            ogtextInternalFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ogtextInternalFrameLayout.createSequentialGroup()
                .addComponent(ogtextLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ogtextScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
        );



        historyInternalFrame.setTitle("History");
        historyInternalFrame.setToolTipText("");
        historyInternalFrame.setFocusable(false);
        historyInternalFrame.setMaximumSize(new java.awt.Dimension(285, 637));
        historyInternalFrame.setMinimumSize(new java.awt.Dimension(285, 637));
        historyInternalFrame.setPreferredSize(new java.awt.Dimension(285, 637));
        historyInternalFrame.setVisible(true);

        for (int index = 0; index < 10; index++) {
             //historyButtons[index].setEnabled(false);
             historyButtons[index].setToolTipText("default...");
             historyButtons[index].setFocusable(false);
             historyButtons[index].setMaximumSize(new java.awt.Dimension(273, 60));
             historyButtons[index].setMinimumSize(new java.awt.Dimension(273, 60));
             historyButtons[index].setPreferredSize(new java.awt.Dimension(273, 60));
             historyButtons[index].addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(evt.getSource() instanceof javax.swing.JButton){
                       ((javax.swing.JButton)evt.getSource()).setBackground(Color.red);

                    }
                    historyButtonActionPerformed((javax.swing.JButton)evt.getSource());
                }
        });
        }

        javax.swing.GroupLayout historyInternalFrameLayout = new javax.swing.GroupLayout(historyInternalFrame.getContentPane());
        historyInternalFrame.getContentPane().setLayout(historyInternalFrameLayout);
        historyInternalFrameLayout.setHorizontalGroup(
            historyInternalFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(historyInternalFrameLayout.createSequentialGroup()
                .addGroup(historyInternalFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(historyButtons[1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(historyButtons[2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(historyButtons[3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(historyButtons[4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(historyButtons[5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(historyButtons[7], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(historyButtons[6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(historyButtons[8], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(historyButtons[9], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(historyButtons[0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        historyInternalFrameLayout.setVerticalGroup(
            historyInternalFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(historyInternalFrameLayout.createSequentialGroup()
                .addComponent(historyButtons[1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(historyButtons[2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(historyButtons[3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(historyButtons[4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(historyButtons[5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(historyButtons[6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(historyButtons[7], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(historyButtons[8], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(historyButtons[9], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(historyButtons[0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );



        imageInternalFrame.setTitle("Image");
        imageInternalFrame.setFocusable(false);
        imageInternalFrame.setMaximumSize(new java.awt.Dimension(900, 300));
        imageInternalFrame.setMinimumSize(new java.awt.Dimension(900, 300));
        imageInternalFrame.setPreferredSize(new java.awt.Dimension(900, 300));
        imageInternalFrame.setVisible(true);

        translateButton.setText("Translate");
        translateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                translateButtonActionPerformed(evt);
            }
        });

        openfileButton.setText("Open File");
        openfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openfileButtonActionPerformed(evt);
            }
        });

        snipButton.setText("Use Snipping Tool");
        snipButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    snipButtonActionPerformed(evt);
                }
        });
    
        javax.swing.GroupLayout imageInternalFrameLayout = new javax.swing.GroupLayout(imageInternalFrame.getContentPane());
        imageInternalFrame.getContentPane().setLayout(imageInternalFrameLayout);
        imageInternalFrameLayout.setHorizontalGroup(
            imageInternalFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imageInternalFrameLayout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addComponent(openfileButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 360, Short.MAX_VALUE)
                .addComponent(translateButton)
                .addGap(190, 190, 190))
            .addGroup(imageInternalFrameLayout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(snipButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        imageInternalFrameLayout.setVerticalGroup(
            imageInternalFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imageInternalFrameLayout.createSequentialGroup()
                .addContainerGap(200, Short.MAX_VALUE)
                .addComponent(snipButton)
                .addGap(18, 18, 18)
                .addGroup(imageInternalFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(translateButton)
                    .addComponent(openfileButton)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(historyInternalFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ogtextInternalFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(translationInternalFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(imageInternalFrame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(984, 984, 984))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(historyInternalFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ogtextInternalFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(translationInternalFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(imageInternalFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(97, Short.MAX_VALUE))
        );

        try {
            translationInternalFrame.setIcon(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        try {
            ogtextInternalFrame.setIcon(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        pack();
    }// </editor-fold>                        



    /**
     * This method is triggered when the translate button is clicked.
     * It prepares the extracted text for translation, constructs the translation prompt,
     * and if there is text to translate, it translates it, stores the translation, and displays it to the user.
     * The code inside was written by Garrett Thrower and ported into the UI from a main class that no longer exists.
     * The setup for this method was done by Belle Cowan.
     *
     * @param evt The action event triggered by clicking the translate button.
     * @author Setup by Belle Cowan, code by Garrett Thrower
     */

    private void translateButtonActionPerformed(java.awt.event.ActionEvent evt) {    
        
        // Escape special characters in the extracted text to prepare it for translation.
        String escapedText = StringEscapeUtils.escapeJson(StorageManager.getImageText());

        // Construct the prompt for the translation request.
        final String prompt = "INPUT TEXT: [" 
        + escapedText
        + "] INSTRUCTIONS: "
        + "First, detect the language of the input text and then list the language of the text after a flag named Input Language (like this. Detected Language: LANGUAGE). " 
        + "After writing the flag, translate the input text into "
        + Locale.getDefault().getDisplayLanguage()
        + " and store ALL OF THE TRANSLATED TEXT between brackets on the next line [like this.] "
        + "If the language is unknown, write 'UNKNOWN' after the flag and return the canned response '[Language not supported. Please try again.]'"
        + "SAY NOTHING ELSE AFTER THE BRACKETED TEXT.";

        // If there is text to translate, translate it, store the translation, and display it to the user.
        // TODO: It seems like the UI is incapable of showing some foreign text like Korean. This should be fixed.
        if (!escapedText.isEmpty()) {
            Translator.translate(prompt);

            translationTextArea.setText(StorageManager.getLastTranslation());
            translationLabel.setText("Translated To: " + Locale.getDefault().getDisplayLanguage());
            ogtextLabel.setText("Detected Language: " + StorageManager.getTranslationLanguage());
        }
    }   
    
    /**
     * This method resets the text in the UI to its default state.
     *
     * @author Garrett Thrower
     */
    private void resetText()
    {
        ogtextLabel.setText("Detected Language: ");
        ogtextTextArea.setText("");
        translationTextArea.setText("");
        translationLabel.setText("Translated To: ");
    }

    /**
    * This method opens a file dialog for the user to select an image.
    * It filters for image files and returns the path of the selected image.
    * This code was ported into the UI from an old UI class that no longer exists.
    *
    * @return The path of the selected image, or null if no image was selected.
    * @author Garrett Thrower
    */
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

    private void historyButtonActionPerformed(javax.swing.JButton button) {                                               
        button.setBackground(Color.red);
    }


    private void snipButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
            
        
    }     

    private void openfileButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
        resetText();

        String filePath = chooseImage();
        ogtextTextArea.setText(Reader.read(filePath));
    }     
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JInternalFrame historyInternalFrame;
    private javax.swing.JInternalFrame imageInternalFrame;
    private javax.swing.JInternalFrame ogtextInternalFrame;
    private javax.swing.JLabel ogtextLabel;
    private javax.swing.JScrollPane ogtextScrollPane;
    private javax.swing.JTextArea ogtextTextArea;
    private javax.swing.JButton openfileButton;
    private javax.swing.JButton snipButton;
    private javax.swing.JButton translateButton;
    private javax.swing.JInternalFrame translationInternalFrame;
    private javax.swing.JLabel translationLabel;
    private javax.swing.JScrollPane translationScrollPane;
    private javax.swing.JTextArea translationTextArea;
    // End of variables declaration                   
}
