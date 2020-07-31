package bean;

public class Classe {
    private Integer idClasse;
    private String nom;

    public Classe(Integer idClasse, String nom) {
        this.idClasse = idClasse;
        this.nom = nom;
    }

    public Classe(String nom) {
        this.nom = nom;
    }

    public Classe() {

    }

    public Integer getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Integer idClasse) {
        this.idClasse = idClasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Classe{" +
                "idClasse=" + idClasse +
                ", nom='" + nom + '\'' +
                '}';
    }
}
