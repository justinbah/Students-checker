package dao;

import bean.Classe;
import bean.Profil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOProfil implements DAO<Profil>{
    private static DAOProfil instance;
    private Connection conn;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Statement st = null;
    private final static Logger LOG = LogManager.getLogger(DAOProfil.class);

    private DAOProfil() {
        this.conn = ConnexionBdd.getInstance();
    }

    /**
     * Return a singleton of the DAO
     */
    public static DAOProfil getInstance() {
        if (null == instance)
            instance = new DAOProfil();
        return instance;
    }

    public Profil create(Profil profil) {
        LOG.debug("Debut create Profil");
        Profil profilToReturn = profil;
        try {
            String sql = "";

            sql = "INSERT INTO profil (libelle) VALUES (?);";
            ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1,profil.getLibelle(), Types.INTEGER);

            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                profilToReturn.setIdProfil(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Creating profil failed, no ID obtained.");
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
        LOG.debug("Fin create Profil");
        return profilToReturn;
    }

    public Profil getById(Integer id) {
        LOG.debug("Debut getById Profil");
        Profil profilToReturn = new Profil();
        try {
            String sql = "SELECT * FROM profil WHERE idProfil = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,id,Types.INTEGER);
            rs = ps.executeQuery();

            while(rs.next()) {
                profilToReturn.setIdProfil(rs.getInt("idProfil"));
                profilToReturn.setLibelle(rs.getString("libelle"));
            }
            LOG.debug("Fin getById Profil");
            return profilToReturn;
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
        LOG.debug("Fin getById Profil");
        return null;
    }

    public List<Profil> getAll() {
        LOG.debug("Debut getAll Profil");
        List<Profil> listeProfils = new ArrayList<Profil>();
        try {
            String sql = "SELECT * FROM profil";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                Profil profilToReturn = new Profil();
                profilToReturn.setIdProfil(rs.getInt("idProfil"));
                profilToReturn.setLibelle(rs.getString("libelle"));
                listeProfils.add(profilToReturn);
            }
            LOG.debug("Fin getAll Profil");
            return listeProfils;
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
        LOG.debug("Fin getAll Profil");
        return null;
    }

    public void update(Profil profil) {
        LOG.debug("Debut udpate Profil");
        try {
            String sql = "UPDATE profil SET libelle = ? WHERE idProfil = ?";
            ps = conn.prepareStatement(sql);

            ps.setObject(1,profil.getLibelle(), Types.VARCHAR);
            ps.setObject(2,profil.getIdProfil(),Types.INTEGER);

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
        LOG.debug("Fin udpate Profil");
    }

    public void delete(Integer id) {
        LOG.debug("Debut delete Profil");
        try {
            String sql = "DELETE FROM profil WHERE idProfil = ?";
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
        LOG.debug("Fin delete Profil");
    }
}
