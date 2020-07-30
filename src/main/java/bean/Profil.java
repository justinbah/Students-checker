package bean;

public class Profil {
   private Integer idProfil;
   private String libelle;

    public Profil(Integer idProfil, String libelle) {
        this.idProfil = idProfil;
        this.libelle = libelle;
    }

    public Profil(String libelle) {
        this.libelle = libelle;
    }

    public Profil() {

    }

    public Integer getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(Integer idProfil) {
        this.idProfil = idProfil;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}


