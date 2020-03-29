
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {

    public void start(Stage primaryStage) throws Exception {

        //Load the FXML file.
        Parent root = FXMLLoader.load(getClass().getResource("projectThree.fxml"));

        primaryStage.setTitle("Program 3 - Tuition Manager");
        primaryStage.setScene(new Scene(root, 600, 525));
        primaryStage.show();

    }

    public static void main(String[] args) {

        //Launch the application.
        launch(args);
    }
}
