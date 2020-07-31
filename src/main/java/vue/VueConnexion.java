package vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class VueConnexion extends Scene {

    private GridPane gridConnexion = new GridPane();
    private Text scenetitle = new Text("Bienvenue");
    private Label userName = new Label("Nom d'utilisateur:");
    private TextField userTextField = new TextField();
    private Label pw = new Label("Mot de passe :");
    private PasswordField pwBox = new PasswordField();
    private Button btnSign = new Button("Se connecter");
    private HBox hbBtn = new HBox(10);
    private Text actiontarget = new Text();

    public VueConnexion(Parent root, double width, double height) {
        super(root,width,height);
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
    }

    public GridPane getGridConnexion() {
        return gridConnexion;
    }

    public void setGridConnexion(GridPane gridConnexion) {
        this.gridConnexion = gridConnexion;
    }

    public Text getScenetitle() {
        return scenetitle;
    }

    public void setScenetitle(Text scenetitle) {
        this.scenetitle = scenetitle;
    }

    public Label getUserName() {
        return userName;
    }

    public void setUserName(Label userName) {
        this.userName = userName;
    }

    public TextField getUserTextField() {
        return userTextField;
    }

    public void setUserTextField(TextField userTextField) {
        this.userTextField = userTextField;
    }

    public Label getPw() {
        return pw;
    }

    public void setPw(Label pw) {
        this.pw = pw;
    }

    public PasswordField getPwBox() {
        return pwBox;
    }

    public void setPwBox(PasswordField pwBox) {
        this.pwBox = pwBox;
    }

    public Button getBtnSign() {
        return btnSign;
    }

    public void setBtnSign(Button btnSign) {
        this.btnSign = btnSign;
    }

    public HBox getHbBtn() {
        return hbBtn;
    }

    public void setHbBtn(HBox hbBtn) {
        this.hbBtn = hbBtn;
    }

    public Text getActiontarget() {
        return actiontarget;
    }

    public void setActiontarget(Text actiontarget) {
        this.actiontarget = actiontarget;
    }
}
