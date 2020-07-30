package dao;

import bean.Eleve;
import bean.User;
import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOUser implements DAO<User> {
    private static DAOUser instance;
    private Connection conn;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Statement st = null;
    private final static Logger LOG = LogManager.getLogger(DAOUser.class);

    private DAOUser() {
        this.conn = ConnexionBdd.getInstance();
    }

    /**
     * Return a singleton of the DAO
     */
    public static DAOUser getInstance() {
        if (null == instance)
            instance = new DAOUser();
        return instance;
    }

    public User create(User user) {
        LOG.debug("Debut create User");
        User userToReturn = user;
        try {
            String sql = "";

            sql = "INSERT INTO user (login,password,nom,prenom,idProfil) VALUES (?,?,?,?,?);";
            ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1,user.getLogin(), Types.VARCHAR);
            ps.setObject(2,user.getPassword(), Types.VARCHAR);
            ps.setObject(3,user.getNom(), Types.VARCHAR);
            ps.setObject(4,user.getPrenom(), Types.VARCHAR);
            ps.setObject(6,user.getIdProfil(), Types.INTEGER);

            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                userToReturn.setIdUser(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }  catch(SQLException se) {
            LOG.error(se.getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            try {
                DbUtils.close(ps);
            } catch (Exception e) {
                LOG.warn(e.getMessage());
            }
        }
        LOG.debug("Fin create User");
        return userToReturn;
    }

    public User getById(Integer id) {
        LOG.debug("Debut getById User");
        User userToReturn = null;
        try {
            String sql = "SELECT * FROM user WHERE idUser = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,id,Types.INTEGER);
            rs = ps.executeQuery();

            while(rs.next()) {
                userToReturn = new User(rs.getInt("idUser"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getInt("idProfil"));
            }
            LOG.debug("Fin getById User");
            return userToReturn;
        }  catch(SQLException se) {
            LOG.error(se.getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            try {
                DbUtils.close(rs);
            } catch (Exception e) {
                LOG.warn(e.getMessage());
            }
            try {
                DbUtils.close(ps);
            } catch (Exception e) {
                LOG.warn(e.getMessage());
            }
        }
        LOG.debug("Fin getById User");
        return null;
    }

    public List<User> getAll() {
        LOG.debug("Debut getAll User");
        List<User> listeUser = new ArrayList<User>();
        try {
            String sql = "SELECT * FROM user";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                User userToReturn = new User(rs.getInt("idUser"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getInt("idProfil"));
                listeUser.add(userToReturn);
            }
            LOG.debug("Fin getAll User");
            return listeUser;
        }  catch(SQLException se) {
            LOG.error(se.getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            try {
                DbUtils.close(rs);
            } catch (Exception e) {
                LOG.warn(e.getMessage());
            }
            try {
                DbUtils.close(ps);
            } catch (Exception e) {
                LOG.warn(e.getMessage());
            }
        }
        LOG.debug("Fin getAll User");
        return null;
    }

    public void update(User user) {
        LOG.debug("Debut udpate User");
        try {
            String sql = "UPDATE user SET login = ?, password = ?, nom = ?, prenom = ?, idProfil = ? WHERE idUser = ?";
            ps = conn.prepareStatement(sql);

            ps.setObject(1,user.getLogin(), Types.VARCHAR);
            ps.setObject(2,user.getPassword(), Types.VARCHAR);
            ps.setObject(3,user.getNom(), Types.VARCHAR);
            ps.setObject(4,user.getPrenom(), Types.VARCHAR);
            ps.setObject(5,user.getIdProfil(), Types.INTEGER);
            ps.setObject(6,user.getIdUser(), Types.INTEGER);

            ps.executeUpdate();
        }  catch(SQLException se) {
            LOG.error(se.getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            try {
                DbUtils.close(ps);
            } catch (Exception e) {
                LOG.warn(e.getMessage());
            }
        }
        LOG.debug("Fin udpate User");
    }

    public void delete(Integer id) {
        LOG.debug("Debut delete User");
        try {
            String sql = "DELETE FROM user WHERE idUser = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,id,Types.INTEGER);
            ps.executeUpdate();
        }  catch(SQLException se) {
            LOG.error(se.getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            try {
                DbUtils.close(ps);
            } catch (Exception e) {
                LOG.warn(e.getMessage());
            }
        }
        LOG.debug("Fin delete User");
    }
}
