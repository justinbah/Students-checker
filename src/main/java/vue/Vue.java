package vue;

import bean.User;
import buissness.UserManager;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Vue extends Application {
    private BorderPane paneConnexion = new BorderPane();
    private BorderPane panePrincipale = new BorderPane();
    private BorderPane paneClasses = new BorderPane();
    private VueConnexion vueConnexion = null;
    private VuePrincipale vuePrincipale = null;
    private VueClasses vueClasses = null;
    public static User userConnected;
    private static final Integer ID_PROFIL_ELEVE = 2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        // Création de la vue connexion et affichage
        vueConnexion = new VueConnexion(paneConnexion, 500,300);
        paneConnexion.setCenter(vueConnexion.getGridConnexion());
        primaryStage.setScene(vueConnexion);
        primaryStage.show();

        // action lors de lappuie sur le btn sign in
        vueConnexion.getBtnSign().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // Récupération de l'utilisateur
                String userName = vueConnexion.getUserTextField().getText();
                String password = vueConnexion.getPwBox().getText();

                userConnected = UserManager.getInstance().getByUsernameAndPassword(userName,password);

                if(userConnected != null) {
                    vuePrincipale = new VuePrincipale(panePrincipale,700,500);
                    panePrincipale.setCenter(vuePrincipale.getGridTable());
                    panePrincipale.setTop(vuePrincipale.getTabPane());
                    primaryStage.setScene(vuePrincipale);

                    if(userConnected.getIdProfil().equals(ID_PROFIL_ELEVE)) {
                        vuePrincipale.getTabPane().setDisable(true);
                        vuePrincipale.getTable().setDisable(true);
                        vuePrincipale.getCongoBoxClasses().setDisable(true);
                        vuePrincipale.getCongoBoxClasses().getSelectionModel().select(0);
                        vuePrincipale.getBtnAjouterEleve().setDisable(true);
                        vuePrincipale.getBtnSupprEleve().setDisable(true);
                        vuePrincipale.getBtnModifEleve().setDisable(true);
                    }

                    vuePrincipale.getTabPane().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
                        @Override
                        public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
                            if(newTab.equals(vuePrincipale.getTabClasse())) {
                                if(vueClasses == null)
                                    vueClasses = new VueClasses(paneClasses,700,500);
                                paneClasses.setCenter(vueClasses.getGridTable());
                                paneClasses.setTop(vuePrincipale.getTabPane());
                                primaryStage.setScene(vueClasses);
                            } else if(newTab.equals(vuePrincipale.getTabEleve())) {
                                panePrincipale.setCenter(vuePrincipale.getGridTable());
                                panePrincipale.setTop(vuePrincipale.getTabPane());
                                vuePrincipale.reloadCongoBoxClasses(vuePrincipale.getCongoBoxClasses());
                                primaryStage.setScene(vuePrincipale);
                            }
                        }
                    });
                } else {
                    vueConnexion.getActiontarget().setText("Mauvais nom d'utilisateur ou mot de passe");
                }
            }
        });
    }

    public BorderPane getPaneConnexion() {
        return paneConnexion;
    }

    public void setPaneConnexion(BorderPane paneConnexion) {
        this.paneConnexion = paneConnexion;
    }

    public BorderPane getPanePrincipale() {
        return panePrincipale;
    }

    public void setPanePrincipale(BorderPane panePrincipale) {
        this.panePrincipale = panePrincipale;
    }

    public BorderPane getPaneClasses() {
        return paneClasses;
    }

    public void setPaneClasses(BorderPane paneClasses) {
        this.paneClasses = paneClasses;
    }

    public VueConnexion getVueConnexion() {
        return vueConnexion;
    }

    public void setVueConnexion(VueConnexion vueConnexion) {
        this.vueConnexion = vueConnexion;
    }

    public VuePrincipale getVuePrincipale() {
        return vuePrincipale;
    }

    public void setVuePrincipale(VuePrincipale vuePrincipale) {
        this.vuePrincipale = vuePrincipale;
    }

    public VueClasses getVueClasses() {
        return vueClasses;
    }

    public void setVueClasses(VueClasses vueClasses) {
        this.vueClasses = vueClasses;
    }
}