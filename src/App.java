import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
        Scene scene = new Scene(root, 900, 600);

        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.setTitle("Password Generator");
        stage.setScene(scene);
        stage.setResizable(false);   
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }


}