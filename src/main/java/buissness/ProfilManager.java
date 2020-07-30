package buissness;

import bean.Profil;
import dao.DAOProfil;

import java.util.List;

public class ProfilManager {
    private static ProfilManager singleton;

    public static ProfilManager getInstance() {
        if (singleton == null) {
            singleton = new ProfilManager();
        }
        return singleton;
    }

    public static DAOProfil getDAOProfil() {
        return DAOProfil.getInstance();
    }

    public Profil create(Profil profil) {
        return getDAOProfil().create(profil);
    }

    public Profil getById(Integer id) {
        return getDAOProfil().getById(id);
    }

    public List<Profil> getAll() {
        return getDAOProfil().getAll();
    }

    public void update(Profil profil) {
        getDAOProfil().update(profil);
    }

    public void delete(Integer id) {
        getDAOProfil().delete(id);
    }
}
