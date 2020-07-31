package buissness;

import bean.RelClassUser;
import dao.DAORelClasseUser;

import java.util.List;

public class RelClasseManager {
    private static RelClasseManager singleton;

    public static RelClasseManager getInstance() {
        if (singleton == null) {
            singleton = new RelClasseManager();
        }
        return singleton;
    }

    public static DAORelClasseUser getDAORelClasseUser() {
        return DAORelClasseUser.getInstance();
    }

    public RelClassUser create(RelClassUser rel) {
        return getDAORelClasseUser().create(rel);
    }

    public RelClassUser getByIdClasse(Integer idClasse) {
        return getDAORelClasseUser().getByIdClasse(idClasse);
    }

    public RelClassUser getByIdUser(Integer IdUser) {
        return getDAORelClasseUser().getByIdUser(IdUser);
    }

    public List<RelClassUser> getAll() {
        return getDAORelClasseUser().getAll();
    }

    public void update(RelClassUser rel) {
        getDAORelClasseUser().update(rel);
    }

    public void deleteByIdClasseAndIdUser(Integer idClase, Integer idUser) {
        getDAORelClasseUser().deleteByIdClasseAndIdUser(idClase,idUser);
    }

    public void deleleteByIdClasse(Integer IdClasse) {
        getDAORelClasseUser().deleleteByIdClasse(IdClasse);
    }

    public void deleteByIdUser(Integer idUser) {
        getDAORelClasseUser().deleteByIdUser(idUser);
    }

    public List<RelClassUser> getAllByIdUser(Integer idUser) {
        return getDAORelClasseUser().getAllByIdUser(idUser);
    }
}
