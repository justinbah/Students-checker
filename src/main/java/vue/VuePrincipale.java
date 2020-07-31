package vue;

import bean.Classe;
import bean.Eleve;
import bean.RelClassUser;
import bean.User;
import buissness.ClasseManager;
import buissness.EleveManager;
import buissness.RelClasseManager;
import buissness.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;

public class VuePrincipale extends Scene {
    private TableView<Eleve> table = new TableView<Eleve>();
    // Create 2 sub column for FullName.
    private TableColumn<Eleve, String> firstNameCol = new TableColumn<Eleve, String>("Prénom");

    private TableColumn<Eleve, String> lastNameCol = new TableColumn<Eleve, String>("Nom");
    // Active Column
    private TableColumn<Eleve, String> classeCol = new TableColumn<Eleve, String>("Nom d'utilisateur");
    private GridPane gridTable = new GridPane();
    private TabPane tabPane = new TabPane();
    private Tab tabEleve = new Tab("Eleves");
    private Tab tabClasse = new Tab("Classes");

    private Button btnAjouterEleve = new Button("Ajouter éleve");
    private Button btnModifEleve = new Button("Modifier éleve");
    private Button btnSupprEleve = new Button("Supprimer éleve");
    private ObservableList<Classe> classes = FXCollections.observableArrayList();
    private ComboBox<Classe> congoBoxClasses = new ComboBox<>();
    private Label classe = new Label("Classe :");
    private List<Classe> listeClasse = new ArrayList<>();
    private Stage fenetreAjouterEleve = new Stage();
    private GridPane gridPaneEditAdd = new GridPane();
    private Scene sceneEditAddEleve = null;
    private Label lblNomEleve = new Label("Nom de l'éleve : ");
    private TextField txtNomEleve = new TextField();
    private Label lblPrenomEleve = new Label("Prénom de l'éleve : ");
    private TextField txtPrenomEleve = new TextField();
    private Label lblLoginEleve = new Label("Nom d'utilisateur de l'éleve : ");
    private TextField txtLoginEleve = new TextField();
    private Label lblMdpEleve = new Label("Mot de passe de l'éleve : ");
    private PasswordField txtMdpEleve = new PasswordField();
    private Button btnCreerEleve = new Button("Créer");
    private Text actiontargetEditSaveEleve = new Text();
    private static final Integer ID_PROFIL_ELEVE = 2;
    private ObservableList<Eleve> listEleve;
    private Button btnEditEleve = new Button("Sauvegarder");
    private Integer selectedIdUser;

    public VuePrincipale(Parent root, double width, double height) {
        super(root, width, height);

        // Defines how to fill data for each cell.
        // Get value from property of UserAccount. .
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Eleve, String>("nom"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Eleve, String>("prenom"));
        classeCol.setCellValueFactory(new PropertyValueFactory<Eleve, String>("login"));
        firstNameCol.setResizable(false);
        lastNameCol.setResizable(false);
        classeCol.setResizable(false);
        table.autosize();
        firstNameCol.setReorderable(false);
        lastNameCol.setReorderable(false);
        classeCol.setReorderable(false);

        listeClasse = ClasseManager.getInstance().getAll();
        classes.addAll(listeClasse);
        congoBoxClasses.setItems(classes);

        table.getColumns().addAll(firstNameCol, lastNameCol, classeCol);
        tabClasse.setClosable(false);
        tabEleve.setClosable(false);
        tabPane.getTabs().add(tabEleve);
        tabPane.getTabs().add(tabClasse);

        btnAjouterEleve.setDisable(true);
        btnModifEleve.setDisable(true);
        btnSupprEleve.setDisable(true);

        gridTable.add(table,3,2);
        gridTable.add(btnAjouterEleve, 1,1);
        gridTable.add(btnModifEleve, 2,1);
        gridTable.add(btnSupprEleve, 3,1);
        gridTable.add(classe, 4,1);
        gridTable.add(congoBoxClasses,5,1,2,1);
        gridTable.setHgap(10);
        gridTable.setVgap(10);

        StringConverter<Classe> congoBoxConverter = new StringConverter<Classe>() {

            @Override
            public String toString(Classe object) {
                if(object != null) {
                    return object.getNom();
                } else {
                    return "";
                }
            }

            @Override
            public Classe fromString(String string) {
                return congoBoxClasses.getItems().stream().filter(ap ->
                        ap.getNom().equals(string)).findFirst().orElse(null);
            }
        };

        congoBoxClasses.setConverter(congoBoxConverter);

        congoBoxClasses.valueProperty().addListener((obs, oldval, newval) -> {
            if(newval != null) {
                System.out.println("Selected classe: " + newval.getNom()
                        + ". ID: " + newval.getIdClasse());
                // Display row data
                List<Eleve> listeEleves = EleveManager.getInstance().getByClasse(newval.getIdClasse());
                listEleve = FXCollections.observableArrayList(listeEleves);
                table.setItems(listEleve);
                btnAjouterEleve.setDisable(false);
                btnModifEleve.setDisable(false);
                btnSupprEleve.setDisable(false);
            } else {
                btnAjouterEleve.setDisable(true);
                btnModifEleve.setDisable(true);
                btnSupprEleve.setDisable(true);
                listEleve.clear();
            }
        });

        btnAjouterEleve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                fenetreAjouterEleve.setTitle("Ajouter eleve");

                gridPaneEditAdd = new GridPane();
                gridPaneEditAdd.add(lblNomEleve, 0, 1);
                gridPaneEditAdd.add(txtNomEleve, 1, 1);
                gridPaneEditAdd.add(lblPrenomEleve, 0, 2);
                gridPaneEditAdd.add(txtPrenomEleve, 1, 2);
                gridPaneEditAdd.add(lblLoginEleve, 0, 3);
                gridPaneEditAdd.add(txtLoginEleve, 1, 3);
                gridPaneEditAdd.add(lblMdpEleve, 0, 4);
                gridPaneEditAdd.add(txtMdpEleve, 1, 4);
                gridPaneEditAdd.add(btnCreerEleve, 1, 7);
                gridPaneEditAdd.add(actiontargetEditSaveEleve, 1, 9);
                gridPaneEditAdd.setAlignment(Pos.CENTER);
                gridPaneEditAdd.setHgap(10);
                gridPaneEditAdd.setVgap(10);
                gridPaneEditAdd.setPadding(new Insets(25, 25, 25, 25));
                sceneEditAddEleve = new Scene(gridPaneEditAdd);
                fenetreAjouterEleve.setScene(sceneEditAddEleve);

                fenetreAjouterEleve.show();
            }
        });

        btnCreerEleve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String nomEleve = txtNomEleve.getText();
                String prenomEleve = txtPrenomEleve.getText();
                String loginEleve = txtLoginEleve.getText();
                String passwordEleve = txtMdpEleve.getText();
                Integer idClasseSelected = congoBoxClasses.getSelectionModel().getSelectedItem().getIdClasse();

                if(!nomEleve.isEmpty() && !prenomEleve.isEmpty() && !loginEleve.isEmpty() && !passwordEleve.isEmpty()) {
                    User userToSave = new User(loginEleve,passwordEleve,nomEleve, prenomEleve,ID_PROFIL_ELEVE);
                    User newUser = UserManager.getInstance().create(userToSave);
                    Eleve eleveToSave = new Eleve(newUser.getIdUser(), loginEleve,passwordEleve,nomEleve, prenomEleve,ID_PROFIL_ELEVE,idClasseSelected);
                    Eleve newEleve = EleveManager.getInstance().create(eleveToSave);
                    RelClassUser relToSave = new RelClassUser(newEleve.getIdClasse(),newEleve.getIdUser());
                    RelClasseManager.getInstance().create(relToSave);

                    reloadListEleve(idClasseSelected);
                    fenetreAjouterEleve.close();
                    sceneEditAddEleve = null;
                    txtNomEleve.setText("");
                    txtPrenomEleve.setText("");
                    txtLoginEleve.setText("");
                    txtMdpEleve.setText("");
                } else {
                    actiontargetEditSaveEleve.setText("Veuillez renseignez les champs");
                }
            }
        });

        btnModifEleve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Eleve selectedEleve = table.getSelectionModel().getSelectedItem();
                if(selectedEleve != null) {
                    fenetreAjouterEleve.setTitle("Modifier éleve");

                    gridPaneEditAdd = new GridPane();
                    gridPaneEditAdd.add(lblNomEleve, 0, 1);
                    gridPaneEditAdd.add(txtNomEleve, 1, 1);
                    gridPaneEditAdd.add(lblPrenomEleve, 0, 2);
                    gridPaneEditAdd.add(txtPrenomEleve, 1, 2);
                    gridPaneEditAdd.add(lblLoginEleve, 0, 3);
                    gridPaneEditAdd.add(txtLoginEleve, 1, 3);
                    gridPaneEditAdd.add(lblMdpEleve, 0, 4);
                    gridPaneEditAdd.add(txtMdpEleve, 1, 4);
                    gridPaneEditAdd.add(btnEditEleve, 1, 7);
                    gridPaneEditAdd.add(actiontargetEditSaveEleve, 1, 9);
                    gridPaneEditAdd.setAlignment(Pos.CENTER);
                    gridPaneEditAdd.setHgap(10);
                    gridPaneEditAdd.setVgap(10);
                    gridPaneEditAdd.setPadding(new Insets(25, 25, 25, 25));

                    txtNomEleve.setText(selectedEleve.getNom());
                    txtPrenomEleve.setText(selectedEleve.getPrenom());
                    txtLoginEleve.setText(selectedEleve.getLogin());
                    txtMdpEleve.setText(selectedEleve.getPassword());
                    selectedIdUser = selectedEleve.getIdUser();
                    txtMdpEleve.setDisable(true);

                    sceneEditAddEleve = new Scene(gridPaneEditAdd);
                    fenetreAjouterEleve.setScene(sceneEditAddEleve);

                    fenetreAjouterEleve.show();
                } else {

                }
            }
        });

        btnEditEleve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String nomEleve = txtNomEleve.getText();
                String prenomEleve = txtPrenomEleve.getText();
                String loginEleve = txtLoginEleve.getText();
                String passwordEleve = txtMdpEleve.getText();
                Integer idClasseSelected = congoBoxClasses.getSelectionModel().getSelectedItem().getIdClasse();

                if(!nomEleve.isEmpty() && !prenomEleve.isEmpty() && !loginEleve.isEmpty() && !passwordEleve.isEmpty()) {
                    User userToSave = new User(selectedIdUser,loginEleve,passwordEleve,nomEleve, prenomEleve,ID_PROFIL_ELEVE);
                    UserManager.getInstance().update(userToSave);
                    Eleve eleveToSave = new Eleve(selectedIdUser, loginEleve,passwordEleve,nomEleve, prenomEleve,ID_PROFIL_ELEVE,idClasseSelected);
                    EleveManager.getInstance().update(eleveToSave);

                    reloadListEleve(idClasseSelected);
                    fenetreAjouterEleve.close();
                    sceneEditAddEleve = null;
                    txtNomEleve.setText("");
                    txtPrenomEleve.setText("");
                    txtLoginEleve.setText("");
                    txtMdpEleve.setText("");
                } else {
                    actiontargetEditSaveEleve.setText("Veuillez renseignez les champs");
                }
            }
        });

        btnSupprEleve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Eleve selectedEleve = table.getSelectionModel().getSelectedItem();
                Integer idClasse = selectedEleve.getIdClasse();
                if(selectedEleve != null) {
                    RelClassUser rel = RelClasseManager.getInstance().getByIdUser(selectedEleve.getIdUser());
                    if(rel != null) {
                        RelClasseManager.getInstance().deleteByIdClasseAndIdUser(rel.getIdClasse(), rel.getIdUser());
                    }
                    EleveManager.getInstance().delete(selectedEleve.getIdUser());
                    UserManager.getInstance().delete(selectedEleve.getIdUser());
                    reloadListEleve(idClasse);
                } else {

                }
            }
        });
    }

    public void reloadCongoBoxClasses(ComboBox<Classe> congoBox) {
        listeClasse = ClasseManager.getInstance().getAll();
        classes.clear();
        classes.addAll(listeClasse);
        congoBox.setItems(classes);
    }

    public void reloadListEleve(Integer idClasse){
        List<Eleve> listeEleves = EleveManager.getInstance().getByClasse(idClasse);
        listEleve = FXCollections.observableArrayList(listeEleves);
        table.setItems(listEleve);
    }

    public TableView<Eleve> getTable() {
        return table;
    }

    public void setTable(TableView<Eleve> table) {
        this.table = table;
    }

    public TableColumn<Eleve, String> getFirstNameCol() {
        return firstNameCol;
    }

    public void setFirstNameCol(TableColumn<Eleve, String> firstNameCol) {
        this.firstNameCol = firstNameCol;
    }

    public TableColumn<Eleve, String> getLastNameCol() {
        return lastNameCol;
    }

    public void setLastNameCol(TableColumn<Eleve, String> lastNameCol) {
        this.lastNameCol = lastNameCol;
    }

    public TableColumn<Eleve, String> getClasseCol() {
        return classeCol;
    }

    public void setClasseCol(TableColumn<Eleve, String> classeCol) {
        this.classeCol = classeCol;
    }

    public GridPane getGridTable() {
        return gridTable;
    }

    public void setGridTable(GridPane gridTable) {
        this.gridTable = gridTable;
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    public Tab getTabEleve() {
        return tabEleve;
    }

    public void setTabEleve(Tab tabEleve) {
        this.tabEleve = tabEleve;
    }

    public Tab getTabClasse() {
        return tabClasse;
    }

    public void setTabClasse(Tab tabClasse) {
        this.tabClasse = tabClasse;
    }

    public Button getBtnAjouterEleve() {
        return btnAjouterEleve;
    }

    public void setBtnAjouterEleve(Button btnAjouterEleve) {
        this.btnAjouterEleve = btnAjouterEleve;
    }

    public Button getBtnModifEleve() {
        return btnModifEleve;
    }

    public void setBtnModifEleve(Button btnModifEleve) {
        this.btnModifEleve = btnModifEleve;
    }

    public Button getBtnSupprEleve() {
        return btnSupprEleve;
    }

    public void setBtnSupprEleve(Button btnSupprEleve) {
        this.btnSupprEleve = btnSupprEleve;
    }

    public ObservableList<Classe> getClasses() {
        return classes;
    }

    public void setClasses(ObservableList<Classe> classes) {
        this.classes = classes;
    }

    public ComboBox<Classe> getCongoBoxClasses() {
        return congoBoxClasses;
    }

    public void setCongoBoxClasses(ComboBox<Classe> congoBoxClasses) {
        this.congoBoxClasses = congoBoxClasses;
    }

    public Label getClasse() {
        return classe;
    }

    public void setClasse(Label classe) {
        this.classe = classe;
    }

    public List<Classe> getListeClasse() {
        return listeClasse;
    }

    public void setListeClasse(List<Classe> listeClasse) {
        this.listeClasse = listeClasse;
    }

    public Stage getFenetreAjouterEleve() {
        return fenetreAjouterEleve;
    }

    public void setFenetreAjouterEleve(Stage fenetreAjouterEleve) {
        this.fenetreAjouterEleve = fenetreAjouterEleve;
    }

    public GridPane getGridPaneEditAdd() {
        return gridPaneEditAdd;
    }

    public void setGridPaneEditAdd(GridPane gridPaneEditAdd) {
        this.gridPaneEditAdd = gridPaneEditAdd;
    }

    public Scene getSceneEditAddEleve() {
        return sceneEditAddEleve;
    }

    public void setSceneEditAddEleve(Scene sceneEditAddEleve) {
        this.sceneEditAddEleve = sceneEditAddEleve;
    }

    public Label getLblNomEleve() {
        return lblNomEleve;
    }

    public void setLblNomEleve(Label lblNomEleve) {
        this.lblNomEleve = lblNomEleve;
    }

    public TextField getTxtNomEleve() {
        return txtNomEleve;
    }

    public void setTxtNomEleve(TextField txtNomEleve) {
        this.txtNomEleve = txtNomEleve;
    }

    public Label getLblPrenomEleve() {
        return lblPrenomEleve;
    }

    public void setLblPrenomEleve(Label lblPrenomEleve) {
        this.lblPrenomEleve = lblPrenomEleve;
    }

    public TextField getTxtPrenomEleve() {
        return txtPrenomEleve;
    }

    public void setTxtPrenomEleve(TextField txtPrenomEleve) {
        this.txtPrenomEleve = txtPrenomEleve;
    }

    public Label getLblLoginEleve() {
        return lblLoginEleve;
    }

    public void setLblLoginEleve(Label lblLoginEleve) {
        this.lblLoginEleve = lblLoginEleve;
    }

    public TextField getTxtLoginEleve() {
        return txtLoginEleve;
    }

    public void setTxtLoginEleve(TextField txtLoginEleve) {
        this.txtLoginEleve = txtLoginEleve;
    }

    public Label getLblMdpEleve() {
        return lblMdpEleve;
    }

    public void setLblMdpEleve(Label lblMdpEleve) {
        this.lblMdpEleve = lblMdpEleve;
    }

    public PasswordField getTxtMdpEleve() {
        return txtMdpEleve;
    }

    public void setTxtMdpEleve(PasswordField txtMdpEleve) {
        this.txtMdpEleve = txtMdpEleve;
    }

    public Button getBtnCreerEleve() {
        return btnCreerEleve;
    }

    public void setBtnCreerEleve(Button btnCreerEleve) {
        this.btnCreerEleve = btnCreerEleve;
    }

    public Text getActiontargetEditSaveEleve() {
        return actiontargetEditSaveEleve;
    }

    public void setActiontargetEditSaveEleve(Text actiontargetEditSaveEleve) {
        this.actiontargetEditSaveEleve = actiontargetEditSaveEleve;
    }

    public static Integer getIdProfilEleve() {
        return ID_PROFIL_ELEVE;
    }

    public ObservableList<Eleve> getListEleve() {
        return listEleve;
    }

    public void setListEleve(ObservableList<Eleve> listEleve) {
        this.listEleve = listEleve;
    }

    public Button getBtnEditEleve() {
        return btnEditEleve;
    }

    public void setBtnEditEleve(Button btnEditEleve) {
        this.btnEditEleve = btnEditEleve;
    }

    public Integer getSelectedIdUser() {
        return selectedIdUser;
    }

    public void setSelectedIdUser(Integer selectedIdUser) {
        this.selectedIdUser = selectedIdUser;
    }
}
