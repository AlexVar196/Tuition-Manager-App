
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main class contains the main method which runs the entire application.
 * The program runs both in terminal and the GUI version. To run terminal
 * version, the main method is in “prog2” file. The GUI version we run is using
 * javaFX 8.0
 *
 * @author Michael Flores mof15
 * @author Alex Varshavsky av653
 */
public class main extends Application {

    public void start(Stage primaryStage) throws Exception {

        //Load the FXML file.
        Parent root = FXMLLoader.load(getClass().getResource("projectThree.fxml"));
        primaryStage.setTitle("Program 3 - Tuition Manager"); // naming window
        primaryStage.setScene(new Scene(root, 600, 525)); // setting window size
        primaryStage.show();  // presenting the stage

    }

    public static void main(String[] args) {

        //Launch the application.
        launch(args);
    }
}
