package dao;

import bean.Profil;
import bean.RelClassUser;
import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAORelClasseUser implements DAO<RelClassUser>{
    private static DAORelClasseUser instance;
    private Connection conn;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Statement st = null;
    private final static Logger LOG = LogManager.getLogger(DAORelClasseUser.class);

    private DAORelClasseUser() {
        this.conn = ConnexionBdd.getInstance();
    }

    /**
     * Return a singleton of the DAO
     */
    public static DAORelClasseUser getInstance() {
        if (null == instance)
            instance = new DAORelClasseUser();
        return instance;
    }

    public RelClassUser create(RelClassUser rel) {
        LOG.debug("Debut create RelClassUser");
        RelClassUser relToReturn = rel;
        try {
            String sql = "";

            sql = "INSERT INTO rel_classe_user VALUES (?,?);";
            ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1,rel.getIdClasse(), Types.INTEGER);
            ps.setObject(2,rel.getIdUser(), Types.INTEGER);

            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                relToReturn.setIdClasse(generatedKeys.getInt(1));
                relToReturn.setIdUser(generatedKeys.getInt(2));
            }
            else {
                throw new SQLException("Creating RelClassUser failed, no ID obtained.");
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
        LOG.debug("Fin create RelClassUser");
        return relToReturn;
    }

    public RelClassUser getById(Integer id) {
        return null;
    }

    public RelClassUser getByIdClasse(Integer idClasse) {
        LOG.debug("Debut getById RelClassUser");
        RelClassUser relToReturn = new RelClassUser();
        try {
            String sql = "SELECT * FROM rel_classe_user WHERE idClasse = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,idClasse,Types.INTEGER);
            rs = ps.executeQuery();

            while(rs.next()) {
                relToReturn.setIdClasse(rs.getInt("idClasse"));
                relToReturn.setIdUser(rs.getInt("idUser"));
            }
            LOG.debug("Fin getById RelClassUser");
            return relToReturn;
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
        LOG.debug("Fin getById RelClassUser");
        return null;
    }

    public RelClassUser getByIdUser(Integer idUser) {
        LOG.debug("Debut getById RelClassUser");
        RelClassUser relToReturn = new RelClassUser();
        try {
            String sql = "SELECT * FROM rel_classe_user WHERE idUser = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,idUser,Types.INTEGER);
            rs = ps.executeQuery();

            while(rs.next()) {
                relToReturn.setIdClasse(rs.getInt("idClasse"));
                relToReturn.setIdUser(rs.getInt("idUser"));
            }
            LOG.debug("Fin getById RelClassUser");
            return relToReturn;
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
        LOG.debug("Fin getById RelClassUser");
        return null;
    }

    public List<RelClassUser> getAll() {
        LOG.debug("Debut getAll RelClassUser");
        List<RelClassUser> listeRel = new ArrayList<RelClassUser>();
        try {
            String sql = "SELECT * FROM rel_classe_user";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                RelClassUser relToReturn = new RelClassUser();
                relToReturn.setIdUser(rs.getInt("idUser"));
                relToReturn.setIdClasse(rs.getInt("idClasse"));
                listeRel.add(relToReturn);
            }
            LOG.debug("Fin getAll RelClassUser");
            return listeRel;
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
        LOG.debug("Fin getAll RelClassUser");
        return null;
    }

    public void update(RelClassUser rel) {
        LOG.debug("Debut udpate RelClassUser");
        try {
            String sql = "UPDATE rel_classe_user SET idClasse = ?, idUser = ? WHERE idClasse = ? AND idUser = ?";
            ps = conn.prepareStatement(sql);

            ps.setObject(1,rel.getIdClasse(), Types.INTEGER);
            ps.setObject(2,rel.getIdUser(),Types.INTEGER);
            ps.setObject(3,rel.getIdClasse(), Types.INTEGER);
            ps.setObject(4,rel.getIdUser(),Types.INTEGER);

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
        LOG.debug("Fin udpate RelClassUser");
    }

    public void delete(Integer id) {
    }

    public void deleteByIdClasseAndIdUser(Integer idClasse, Integer idUser) {
        LOG.debug("Debut delete RelClassUser");
        try {
            String sql = "DELETE FROM rel_classe_user WHERE idClasse = ? AND idUser = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,idClasse,Types.INTEGER);
            ps.setObject(2,idUser,Types.INTEGER);
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
        LOG.debug("Fin delete RelClassUser");
    }

    public void deleleteByIdClasse(Integer idClasse) {
        LOG.debug("Debut delete RelClassUser");
        try {
            String sql = "DELETE FROM rel_classe_user WHERE idClasse = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,idClasse,Types.INTEGER);
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
        LOG.debug("Fin delete RelClassUser");
    }

    public void deleteByIdUser(Integer idUser) {
        LOG.debug("Debut delete RelClassUser");
        try {
            String sql = "DELETE FROM rel_classe_user WHERE idUser = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,idUser,Types.INTEGER);
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
        LOG.debug("Fin delete RelClassUser");
    }
}
