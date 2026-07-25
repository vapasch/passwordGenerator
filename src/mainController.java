import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Slider;

import javafx.scene.control.Alert;
import java.security.SecureRandom;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import javafx.scene.Node;


public class mainController {
    
    //scene builder variables
    @FXML private AnchorPane background_0;
    @FXML private AnchorPane background_1;
    @FXML private HBox char_hbox;
    @FXML private Label char_label;
    @FXML private Label char_length1;
    @FXML private CheckBox checkbox1;
    @FXML private CheckBox checkbox2;
    @FXML private CheckBox checkbox3;
    @FXML private CheckBox checkbox4;
    @FXML private Button generate_output;
    @FXML private Label label1;
    @FXML private TextField password_output;
    @FXML private Slider slider1;
    @FXML private VBox vbox_all;
    @FXML private Button copy_button;
    
    
    // runs once when the app opens AND sets up the slider listener to change the label value from slider output
    public void initialize() {
        slider1.valueProperty().addListener((observable, oldValue, newValue) -> {
        double newVal = newValue.doubleValue();
        changeCharNumber(newVal);
        updateSliderTrack();
        });

        slider1.skinProperty().addListener((obs, oldSkin, newSkin) -> {
        if (newSkin != null) {
            updateSliderTrack();
        }
        });
    }


    // slider color    
    private void updateSliderTrack() {
        Node track = slider1.lookup(".track");
        if (track == null) return;

        double percentage = (slider1.getValue() - slider1.getMin()) / (slider1.getMax() - slider1.getMin()) * 100;
        track.setStyle(String.format(
        "-fx-background-color: linear-gradient(to right, #de6238 0%%, #de6238 %1$.1f%%, white %1$.1f%%, white 100%%);",
        percentage
    ));
}


    
    
    //method for password generation
    @FXML private void onGenerateClick() {
        
        StringBuilder charPool = new StringBuilder(); //I use StringBuilder because String can't be modified
        if (checkbox1.isSelected()) {
            charPool.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
        if (checkbox2.isSelected()) {
            charPool.append("abcdefghijklmnopqrstuvwxyz");
        }
        if (checkbox3.isSelected()) {
            charPool.append("0123456789");
        }
        if (checkbox4.isSelected()) {
            charPool.append("!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~");
        }   
    
    
        //alert for 0 selected boxes
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Not selected");
        alert.setContentText("Select at least one option");

        if (charPool.length() == 0){
            alert.showAndWait();
            return;
        }
    
    
        //generate passwd
        StringBuilder password = new StringBuilder();
        int totalCharLength = Integer.parseInt(char_length1.getText());
        
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < totalCharLength; i++) {
            int index = random.nextInt(charPool.length());
        password.append(charPool.charAt(index));
        }
        password_output.setText(password.toString());
    }

    
    @FXML private void onCopyClick() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(password_output.getText());
        clipboard.setContent(content);
    }

    
    
    //method to change character length number label
    private void changeCharNumber(double newValue) {
        int roundedValue = (int) newValue;
        String number = String.valueOf(roundedValue);
        char_length1.setText(number);
    }




}