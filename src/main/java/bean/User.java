package bean;

public class User {
    private Integer idUser;
    private String login;
    private String Password;
    private String Nom;
    private String Prenom;
    private Integer IdProfil;

    public User(Integer idUser,String login,String Password,String Nom,String Prenom,Integer IdProfil)
    {
        this.idUser=idUser;
        this.login=login;
        this.Password=Password;
        this.Nom=Nom;
        this.Prenom=Prenom;
        this.IdProfil=IdProfil;
    }
    public User(String login,String Password,String Nom,String Prenom)
    {
        this.login=login;
        this.Password=Password;
        this.Nom=Nom;
        this.Prenom=Prenom;

    }
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public Integer getIdProfil() {
        return IdProfil;
    }

    public void setIdProfil(Integer idProfil) {
        IdProfil = idProfil;
    }

}
