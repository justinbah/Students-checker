package dao;

import bean.Classe;
import bean.Eleve;
import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOEleve implements DAO<Eleve> {
    private static DAOEleve instance;
    private Connection conn;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Statement st = null;
    private final static Logger LOG = LogManager.getLogger(DAOEleve.class);

    private DAOEleve() {
        this.conn = ConnexionBdd.getInstance();
    }

    /**
     * Return a singleton of the DAO
     */
    public static DAOEleve getInstance() {
        if (null == instance)
            instance = new DAOEleve();
        return instance;
    }

    public Eleve create(Eleve eleve) {
        LOG.debug("Debut create Eleve");
        Eleve eleveToReturn = eleve;
        try {
            String sql = "";

            sql = "INSERT INTO eleve (login,password,nom,prenom,idClasse,idProfil) VALUES (?,?,?,?,?,?);";
            ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1,eleve.getLogin(), Types.VARCHAR);
            ps.setObject(2,eleve.getPassword(), Types.VARCHAR);
            ps.setObject(3,eleve.getNom(), Types.VARCHAR);
            ps.setObject(4,eleve.getPrenom(), Types.VARCHAR);
            ps.setObject(5,eleve.getIdClasse(), Types.INTEGER);
            ps.setObject(6,eleve.getIdProfil(), Types.INTEGER);

            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                eleveToReturn.setIdUser(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Creating eleve failed, no ID obtained.");
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
        return eleveToReturn;
    }

    public Eleve getById(Integer id) {
        LOG.debug("Debut getById Eleve");
        Eleve eleveToReturn = null;
        try {
            String sql = "SELECT * FROM eleve WHERE idUser = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,id,Types.INTEGER);
            rs = ps.executeQuery();

            while(rs.next()) {
                eleveToReturn = new Eleve(rs.getInt("idUser"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getInt("idClasse"),
                        rs.getInt("idProfil"));
            }
            LOG.debug("Fin getById Eleve");
            return eleveToReturn;
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
        LOG.debug("Fin getById Eleve");
        return null;
    }

    public List<Eleve> getAll() {
        LOG.debug("Debut getAll Eleve");
        List<Eleve> listeEleve = new ArrayList<Eleve>();
        try {
            String sql = "SELECT * FROM eleve";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                Eleve eleveToReturn = new Eleve(rs.getInt("idUser"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getInt("idClasse"),
                        rs.getInt("idProfil"));
                listeEleve.add(eleveToReturn);
            }
            LOG.debug("Fin getAll Eleve");
            return listeEleve;
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
        LOG.debug("Fin getAll Eleve");
        return null;
    }

    public void update(Eleve eleve) {
        LOG.debug("Debut udpate Eleve");
        try {
            String sql = "UPDATE eleve SET login = ?, password = ?, nom = ?, prenom = ?, idClasse = ?, idProfil = ? WHERE idUser = ?";
            ps = conn.prepareStatement(sql);

            ps.setObject(1,eleve.getLogin(), Types.VARCHAR);
            ps.setObject(2,eleve.getPassword(), Types.VARCHAR);
            ps.setObject(3,eleve.getNom(), Types.VARCHAR);
            ps.setObject(4,eleve.getPrenom(), Types.VARCHAR);
            ps.setObject(5,eleve.getIdClasse(), Types.INTEGER);
            ps.setObject(6,eleve.getIdProfil(), Types.INTEGER);
            ps.setObject(7,eleve.getIdUser(), Types.INTEGER);

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
        LOG.debug("Fin udpate Eleve");
    }

    public void delete(Integer id) {
        LOG.debug("Debut delete Eleve");
        try {
            String sql = "DELETE FROM eleve WHERE idUser = ?";
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
        LOG.debug("Fin delete Eleve");
    }
}
