package bean;

public class Eleve extends User{

    private Integer IdClasse;

    public Eleve(Integer idUser,String login,String Password,String Nom,String Prenom,Integer IdProfil,Integer IdClasse)
    {
        super(idUser,login,Password,Nom,Prenom,IdProfil) ;
        this.IdClasse=IdClasse;
    }
    public Eleve(String login,String Password,String Nom,String Prenom)
    {
        super(login,Password,Nom,Prenom) ;
    }

    public Integer getIdClasse() {
        return IdClasse;
    }

    public void setIdClasse(Integer idClasse) {
        IdClasse = idClasse;
    }
}
