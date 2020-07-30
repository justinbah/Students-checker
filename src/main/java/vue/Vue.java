package vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Vue extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            //  Block of code to try

            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Students checker\n");
            primaryStage.setScene(new Scene(root, 300, 275));
            primaryStage.show();
        }
        catch(Exception e) {
            //  Block of code to handle errors
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
