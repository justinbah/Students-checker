#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Classe
#------------------------------------------------------------

CREATE TABLE Classe(
        idClasse Int NOT NULL AUTO INCREMENT,
        Nom      Varchar (50) NOT NULL
	,CONSTRAINT Classe_PK PRIMARY KEY (idClasse)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Profil
#------------------------------------------------------------

CREATE TABLE Profil(
        idProfil Int NOT NULL AUTO INCREMENT,
        libelle  Varchar (50) NOT NULL
	,CONSTRAINT Profil_PK PRIMARY KEY (idProfil)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: User
#------------------------------------------------------------

CREATE TABLE User(
        idUser   Int NOT NULL AUTO INCREMENT,
        login    Varchar (50) NOT NULL ,
        password Varchar (50) NOT NULL ,
        nom      Varchar (50) NOT NULL ,
        prenom   Varchar (50) NOT NULL ,
        idProfil Int NOT NULL
	,CONSTRAINT User_PK PRIMARY KEY (idUser)

	,CONSTRAINT User_Profil_FK FOREIGN KEY (idProfil) REFERENCES Profil(idProfil)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Eleve
#------------------------------------------------------------

CREATE TABLE Eleve(
        idUser   Int NOT NULL AUTO INCREMENT,
        login    Varchar (50) NOT NULL ,
        password Varchar (50) NOT NULL ,
        nom      Varchar (50) NOT NULL ,
        prenom   Varchar (50) NOT NULL ,
        idClasse Int NOT NULL ,
        idProfil Int NOT NULL
	,CONSTRAINT Eleve_PK PRIMARY KEY (idUser)

	,CONSTRAINT Eleve_Classe0_FK FOREIGN KEY (idClasse) REFERENCES Classe(idClasse)
	,CONSTRAINT Eleve_Profil1_FK FOREIGN KEY (idProfil) REFERENCES Profil(idProfil)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Rel_classe_user
#------------------------------------------------------------

CREATE TABLE Rel_classe_user(
        idClasse Int NOT NULL ,
        idUser   Int NOT NULL
	,CONSTRAINT Rel_classe_user_PK PRIMARY KEY (idClasse,idUser)

)ENGINE=InnoDB;

INSERT INTO profil VALUES
(1, "admin"),
(2, "eleve");