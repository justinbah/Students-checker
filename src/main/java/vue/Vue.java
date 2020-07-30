package vue;

import bean.User;
import dao.DAOUser;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Vue extends Application {
    BorderPane paneConnexion = new BorderPane();
    GridPane gridConnexion = new GridPane();
    Text scenetitle = new Text("Bienvenue");
    Label userName = new Label("Nom d'utilisateur:");
    TextField userTextField = new TextField();
    Label pw = new Label("Mot de passe :");
    PasswordField pwBox = new PasswordField();
    Button btnSign = new Button("Se connecter");
    HBox hbBtn = new HBox(10);
    final Text actiontarget = new Text();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        gridConnexion.add(scenetitle, 0, 0, 2, 1);
        gridConnexion.add(userName, 0, 1);
        gridConnexion.add(userTextField, 1, 1);
        gridConnexion.add(pw, 0, 2);
        gridConnexion.add(pwBox, 1, 2);
        gridConnexion.add(hbBtn, 1, 4);
        gridConnexion.add(actiontarget, 1, 6);
        gridConnexion.setAlignment(Pos.CENTER);
        gridConnexion.setHgap(10);
        gridConnexion.setVgap(10);
        gridConnexion.setPadding(new Insets(25, 25, 25, 25));
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btnSign);

        paneConnexion.setCenter(gridConnexion);
        Scene sceneConnexion = new Scene(paneConnexion);
        primaryStage.setScene(sceneConnexion);
        primaryStage.show();

        TableView<User> table = new TableView<User>();

        // Create column UserName (Data type of String).
        TableColumn<User, String> userNameCol //
                = new TableColumn<User, String>("User Name");

        // Create column Email (Data type of String).
        TableColumn<User, String> emailCol//
                = new TableColumn<User, String>("Email");

        // Create column FullName (Data type of String).
        TableColumn<User, String> fullNameCol//
                = new TableColumn<User, String>("Full Name");

        // Create 2 sub column for FullName.
        TableColumn<User, String> firstNameCol//
                = new TableColumn<User, String>("First Name");

        TableColumn<User, String> lastNameCol //
                = new TableColumn<User, String>("Last Name");

        // Add sub columns to the FullName
        fullNameCol.getColumns().addAll(firstNameCol, lastNameCol);

        // Active Column
        TableColumn<User, Boolean> activeCol//
                = new TableColumn<User, Boolean>("Active");

        // Defines how to fill data for each cell.
        // Get value from property of UserAccount. .
        userNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        emailCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
        activeCol.setCellValueFactory(new PropertyValueFactory<User, Boolean>("active"));

        // Set Sort type for userName column
        userNameCol.setSortType(TableColumn.SortType.DESCENDING);
        lastNameCol.setSortable(false);

        // Display row data
        ObservableList<User> list = FXCollections.observableArrayList(DAOUser.getInstance().getAll());
        table.setItems(list);

        table.getColumns().addAll(userNameCol, emailCol, fullNameCol, activeCol);

        StackPane root = new StackPane();
        root.setPadding(new Insets(5));
        root.getChildren().add(table);

        final Scene sceneTable = new Scene(root, 450, 300);

        // action lors de lappuie sur le btn sign in
        btnSign.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setText("Sign in button pressed");
                primaryStage.setScene(sceneTable);
            }
        });
    }
}