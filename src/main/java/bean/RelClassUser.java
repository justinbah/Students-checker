package bean;

public class RelClassUser {
    private Integer idClasse;
    private Integer idUser;

    public RelClassUser(Integer idClasse, Integer idUser) {
        this.idClasse = idClasse;
        this.idUser = idUser;
    }

    public RelClassUser() {
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Integer idClasse) {
        this.idClasse = idClasse;
    }
}
