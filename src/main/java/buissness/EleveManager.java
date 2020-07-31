package buissness;

import bean.Eleve;
import dao.DAOEleve;

import java.util.List;

public class EleveManager {
    private static EleveManager singleton;

    public static EleveManager getInstance() {
        if (singleton == null) {
            singleton = new EleveManager();
        }
        return singleton;
    }

    public static DAOEleve getDAOEleve() {
        return DAOEleve.getInstance();
    }

    public Eleve create(Eleve eleve) {
        return getDAOEleve().create(eleve);
    }

    public Eleve getById(Integer id) {
        return getDAOEleve().getById(id);
    }

    public List<Eleve> getAll() {
        return getDAOEleve().getAll();
    }

    public void update(Eleve classe) {
        getDAOEleve().update(classe);
    }

    public void delete(Integer id) {
        getDAOEleve().delete(id);
    }

    public List<Eleve> getByClasse(Integer idClasse) {
        return getDAOEleve().getByClasse(idClasse);
    }
}
