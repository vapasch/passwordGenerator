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
import java.util.Random;



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
    
    
    // runs once when the app opens AND sets up the slider listener to change the label value from slider output
    public void initialize() {
         slider1.valueProperty().addListener((observable, oldValue, newValue) -> {
        double newVal = newValue.doubleValue();
        changeCharNumber(newVal);
        });
    }
    


    
    
    //function for password generation
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
        
        Random random = new Random();
        for (int i = 0; i < totalCharLength; i++) {
            int index = random.nextInt(charPool.length());
        password.append(charPool.charAt(index));
        }
        password_output.setText(password.toString());
    }

    


    
    
    //method to change character length number label
    private void changeCharNumber(double newValue) {
        int roundedValue = (int) newValue;
        String number = String.valueOf(roundedValue);
        char_length1.setText(number);
    }




}