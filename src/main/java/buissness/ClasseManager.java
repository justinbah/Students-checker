package buissness;

import bean.Classe;
import dao.DAOClasse;

import java.util.List;

public class ClasseManager {
    private static ClasseManager singleton;

    public static ClasseManager getInstance() {
        if (singleton == null) {
            singleton = new ClasseManager();
        }
        return singleton;
    }

    public static DAOClasse getDAOClasse() {
        return DAOClasse.getInstance();
    }

    public Classe create(Classe classe) {
        return getDAOClasse().create(classe);
    }

    public Classe getById(Integer id) {
        return getDAOClasse().getById(id);
    }

    public List<Classe> getAll() {
        return getDAOClasse().getAll();
    }

    public void update(Classe classe) {
        getDAOClasse().update(classe);
    }

    public void delete(Integer id) {
        getDAOClasse().delete(id);
    }
}
