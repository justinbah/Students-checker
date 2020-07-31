package dao;

import bean.Classe;
import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOClasse implements DAO<Classe>{
    private static DAOClasse instance;
    private Connection conn;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Statement st = null;
    private final static Logger LOG = LogManager.getLogger(DAOClasse.class);

    private DAOClasse() {
        this.conn = ConnexionBdd.getInstance();
    }

    /**
     * Return a singleton of the DAO
     */
    public static DAOClasse getInstance() {
        if (null == instance)
            instance = new DAOClasse();
        return instance;
    }

    public Classe create(Classe classe) {
        LOG.debug("Debut create Classe");
        Classe classeToReturn = classe;
        try {
            String sql = "";

            sql = "INSERT INTO classe (nom) VALUES (?);";
            ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1,classe.getNom(), Types.VARCHAR);

            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                classeToReturn.setIdClasse(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Creating classe failed, no ID obtained.");
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
        LOG.debug("Fin create Classe");
        return classeToReturn;
    }

    public Classe getById(Integer id) {
        LOG.debug("Debut getById Classe");
        try {
            Classe classeToReturn = new Classe();
            String sql = "SELECT * FROM classe WHERE idClasse = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,id,Types.INTEGER);
            rs = ps.executeQuery();

            while(rs.next()) {
                classeToReturn.setIdClasse(rs.getInt("idClasse"));
                classeToReturn.setNom(rs.getString("nom"));
            }
            LOG.debug("Fin getById Classe");
            return classeToReturn;
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
        LOG.debug("Fin getById Classe");
        return null;
    }

    public List<Classe> getAll() {
        LOG.debug("Debut getAll Classe");
        List<Classe> listeClasses = new ArrayList<Classe>();
        try {
            String sql = "SELECT * FROM classe";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                Classe classeToReturn = new Classe();
                classeToReturn.setIdClasse(rs.getInt("idClasse"));
                classeToReturn.setNom(rs.getString("nom"));
                listeClasses.add(classeToReturn);
            }
            LOG.debug("Fin getAll Classe");
            return listeClasses;
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
        LOG.debug("Fin getAll Classe");
        return null;
    }

    public void update(Classe classe) {
        LOG.debug("Debut udpate Classe");
        try {
            String sql = "UPDATE classe SET nom = ? WHERE idClasse = ?";
            ps = conn.prepareStatement(sql);

            ps.setObject(1,classe.getNom(), Types.VARCHAR);
            ps.setObject(2,classe.getIdClasse(),Types.INTEGER);

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
        LOG.debug("Fin udpate Classe");
    }

    public void delete(Integer id) {
        LOG.debug("Debut delete Classe");
        try {
            String sql = "DELETE FROM classe WHERE idClasse = ?";
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
        LOG.debug("Fin delete Classe");
    }
}
