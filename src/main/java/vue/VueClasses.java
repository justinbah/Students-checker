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

import java.util.ArrayList;
import java.util.List;

public class VueClasses extends Scene {
    private TableView<Classe> table = new TableView<Classe>();
    // Create 2 sub column for FullName.
    private TableColumn<Classe, String> nomCol = new TableColumn<Classe, String>("Nom");
    private GridPane gridTable = new GridPane();

    private Button btnAjouterClasse = new Button("Ajouter classe");
    private Button btnModifClasse = new Button("Modifier classe");
    private Button btnSupprClasse = new Button("Supprimer classe");

    private Scene sceneEditAddClasse = null;
    private GridPane gridPaneEditAdd = new GridPane();
    private Label lblNomClasse = new Label("Nom de la classe : ");
    private TextField txtNomClasse = new TextField();
    private Button btnCreerClasse = new Button("Créer");
    private Text actiontargetEditSaveClasse = new Text();
    private Stage fenetreAjouterClasse = new Stage();
    private List<Classe> listeClasses = new ArrayList<Classe>();
    private ObservableList<Classe> list;
    private Button btnEditClasse = new Button("Sauvegarder");
    private Integer selectecIdClasse;
    private final static Integer ID_PROFIL_ADMIN = 1;

    public VueClasses(Parent root, double width, double height) {
        super(root, width, height);


        nomCol.setCellValueFactory(new PropertyValueFactory<Classe, String>("nom"));
        // Set Sort type for userName column

        // Display row data
        List<RelClassUser> listeRel = RelClasseManager.getInstance().getAllByIdUser(Vue.userConnected.getIdUser());
        for(RelClassUser rel : listeRel) {
            listeClasses.add(ClasseManager.getInstance().getById(rel.getIdClasse()));
        }
        list = FXCollections.observableArrayList(listeClasses);
        table.setItems(list);
        table.autosize();
        nomCol.setReorderable(false);
        table.getColumns().addAll(nomCol);

        gridTable.add(table,3,2);
        gridTable.add(btnAjouterClasse, 1,1);
        gridTable.add(btnModifClasse, 2,1);
        gridTable.add(btnSupprClasse, 3,1);
        gridTable.setHgap(10);
        gridTable.setVgap(10);

        btnAjouterClasse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                fenetreAjouterClasse.setTitle("Ajouter classe");

                gridPaneEditAdd = new GridPane();
                gridPaneEditAdd.add(lblNomClasse, 0, 1);
                gridPaneEditAdd.add(txtNomClasse, 1, 1);
                gridPaneEditAdd.add(btnCreerClasse, 1, 4);
                gridPaneEditAdd.add(actiontargetEditSaveClasse, 1, 6);
                gridPaneEditAdd.setAlignment(Pos.CENTER);
                gridPaneEditAdd.setHgap(10);
                gridPaneEditAdd.setVgap(10);
                gridPaneEditAdd.setPadding(new Insets(25, 25, 25, 25));
                sceneEditAddClasse = new Scene(gridPaneEditAdd);
                fenetreAjouterClasse.setScene(sceneEditAddClasse);

                fenetreAjouterClasse.show();
            }
        });

        btnCreerClasse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String nomClasse = txtNomClasse.getText();
                if(nomClasse != null && !nomClasse.equals("")) {
                    Classe classeTosave = new Classe(nomClasse);
                    Classe newClasse = ClasseManager.getInstance().create(classeTosave);

                    List<User> admins = UserManager.getInstance().getAllByIdProfil(ID_PROFIL_ADMIN);
                    for(User admin : admins) {
                        RelClassUser relToSave = new RelClassUser(newClasse.getIdClasse(), admin.getIdUser());
                        RelClasseManager.getInstance().create(relToSave);
                    }

                    reloadList();
                    fenetreAjouterClasse.close();
                    sceneEditAddClasse = null;
                    txtNomClasse.setText("");
                } else {
                    actiontargetEditSaveClasse.setText("Entrer un nom de classe");
                }
            }
        });

        btnSupprClasse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Classe selectedClasse = table.getSelectionModel().getSelectedItem();
                if(selectedClasse != null) {
                    ClasseManager.getInstance().delete(selectedClasse.getIdClasse());
                    List<Eleve> elevesDeLaClasse = EleveManager.getInstance().getByClasse(selectedClasse.getIdClasse());
                    RelClasseManager.getInstance().deleleteByIdClasse(selectedClasse.getIdClasse());
                    if(elevesDeLaClasse != null && !elevesDeLaClasse.isEmpty()) {
                        for(Eleve eleve : elevesDeLaClasse) {
                            EleveManager.getInstance().delete(eleve.getIdUser());
                        }
                    }
                    reloadList();
                } else {

                }
            }
        });

        btnModifClasse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Classe selectedClasse = table.getSelectionModel().getSelectedItem();
                selectecIdClasse = selectedClasse.getIdClasse();
                if(selectedClasse != null) {
                    fenetreAjouterClasse.setTitle("Modifier classe");

                    gridPaneEditAdd = new GridPane();
                    gridPaneEditAdd.add(lblNomClasse, 0, 1);
                    gridPaneEditAdd.add(txtNomClasse, 1, 1);
                    gridPaneEditAdd.add(btnEditClasse, 1, 4);
                    gridPaneEditAdd.add(actiontargetEditSaveClasse, 1, 6);
                    gridPaneEditAdd.setAlignment(Pos.CENTER);
                    gridPaneEditAdd.setHgap(10);
                    gridPaneEditAdd.setVgap(10);
                    gridPaneEditAdd.setPadding(new Insets(25, 25, 25, 25));
                    txtNomClasse.setText(selectedClasse.getNom());
                    sceneEditAddClasse = new Scene(gridPaneEditAdd);
                    fenetreAjouterClasse.setScene(sceneEditAddClasse);

                    fenetreAjouterClasse.show();
                } else {

                }
            }
        });

        btnEditClasse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String nomClasse = txtNomClasse.getText();

                if(nomClasse != null && !nomClasse.equals("")) {
                    Classe classeTosave = new Classe(selectecIdClasse, nomClasse);
                    ClasseManager.getInstance().update(classeTosave);
                    reloadList();
                    fenetreAjouterClasse.close();
                    txtNomClasse.setText("");
                    sceneEditAddClasse = null;
                } else {
                    actiontargetEditSaveClasse.setText("Entrer un nom de classe");
                }
            }
        });
    }

    private void reloadList() {
        List<RelClassUser> listeRel = RelClasseManager.getInstance().getAllByIdUser(Vue.userConnected.getIdUser());
        listeClasses.clear();
        list.clear();
        for(RelClassUser rel : listeRel) {
            listeClasses.add(ClasseManager.getInstance().getById(rel.getIdClasse()));
        }
        list = FXCollections.observableArrayList(listeClasses);
        table.setItems(list);
    }

    public TableView<Classe> getTable() {
        return table;
    }

    public void setTable(TableView<Classe> table) {
        this.table = table;
    }

    public TableColumn<Classe, String> getNomCol() {
        return nomCol;
    }

    public void setNomCol(TableColumn<Classe, String> nomCol) {
        this.nomCol = nomCol;
    }

    public GridPane getGridTable() {
        return gridTable;
    }

    public void setGridTable(GridPane gridTable) {
        this.gridTable = gridTable;
    }

    public Button getBtnAjouterClasse() {
        return btnAjouterClasse;
    }

    public void setBtnAjouterClasse(Button btnAjouterClasse) {
        this.btnAjouterClasse = btnAjouterClasse;
    }

    public Button getBtnModifClasse() {
        return btnModifClasse;
    }

    public void setBtnModifClasse(Button btnModifClasse) {
        this.btnModifClasse = btnModifClasse;
    }

    public Button getBtnSupprClasse() {
        return btnSupprClasse;
    }

    public void setBtnSupprClasse(Button btnSupprClasse) {
        this.btnSupprClasse = btnSupprClasse;
    }

    public Scene getSceneEditAddClasse() {
        return sceneEditAddClasse;
    }

    public void setSceneEditAddClasse(Scene sceneEditAddClasse) {
        this.sceneEditAddClasse = sceneEditAddClasse;
    }

    public GridPane getGridPaneEditAdd() {
        return gridPaneEditAdd;
    }

    public void setGridPaneEditAdd(GridPane gridPaneEditAdd) {
        this.gridPaneEditAdd = gridPaneEditAdd;
    }

    public Label getLblNomClasse() {
        return lblNomClasse;
    }

    public void setLblNomClasse(Label lblNomClasse) {
        this.lblNomClasse = lblNomClasse;
    }

    public TextField getTxtNomClasse() {
        return txtNomClasse;
    }

    public void setTxtNomClasse(TextField txtNomClasse) {
        this.txtNomClasse = txtNomClasse;
    }

    public Button getBtnCreerClasse() {
        return btnCreerClasse;
    }

    public void setBtnCreerClasse(Button btnCreerClasse) {
        this.btnCreerClasse = btnCreerClasse;
    }

    public Text getActiontargetEditSaveClasse() {
        return actiontargetEditSaveClasse;
    }

    public void setActiontargetEditSaveClasse(Text actiontargetEditSaveClasse) {
        this.actiontargetEditSaveClasse = actiontargetEditSaveClasse;
    }

    public Stage getFenetreAjouterClasse() {
        return fenetreAjouterClasse;
    }

    public void setFenetreAjouterClasse(Stage fenetreAjouterClasse) {
        this.fenetreAjouterClasse = fenetreAjouterClasse;
    }

    public List<Classe> getListeClasses() {
        return listeClasses;
    }

    public void setListeClasses(List<Classe> listeClasses) {
        this.listeClasses = listeClasses;
    }

    public ObservableList<Classe> getList() {
        return list;
    }

    public void setList(ObservableList<Classe> list) {
        this.list = list;
    }

    public Button getBtnEditClasse() {
        return btnEditClasse;
    }

    public void setBtnEditClasse(Button btnEditClasse) {
        this.btnEditClasse = btnEditClasse;
    }

    public Integer getSelectecIdClasse() {
        return selectecIdClasse;
    }

    public void setSelectecIdClasse(Integer selectecIdClasse) {
        this.selectecIdClasse = selectecIdClasse;
    }
}
