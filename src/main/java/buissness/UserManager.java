package buissness;

import bean.Eleve;
import bean.User;
import dao.DAOUser;

import java.util.List;

public class UserManager {
    private static UserManager singleton;

    public static UserManager getInstance() {
        if (singleton == null) {
            singleton = new UserManager();
        }
        return singleton;
    }

    public static DAOUser getDAOUser() {
        return DAOUser.getInstance();
    }

    public User create(User user) {
        return getDAOUser().create(user);
    }

    public User getById(Integer id) {
        return getDAOUser().getById(id);
    }

    public List<User> getAll() {
        return getDAOUser().getAll();
    }

    public void update(User user) {
        getDAOUser().update(user);
    }

    public void delete(Integer id) {
        getDAOUser().delete(id);
    }

    public User getByUsernameAndPassword(String username, String password) {
        return getDAOUser().getByUsernameAndPassword(username, password);
    }

    public List<User> getAllByIdProfil(Integer idProfil) {
        return getDAOUser().getAllByIdProfil(idProfil);
    }
}
