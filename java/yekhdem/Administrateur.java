package yekhdem;

public class Administrateur {


public Administrateur() {}

private int idAdministrateur;
private String nom;
private String prenom;

private String motDePasse;


//le role est si il est enseignant ou etudiant ou administrateur
public int getIdAdministrateur() {
    return idAdministrateur;
}

public void setIdAdministrateur(int idUtilisateur) {
    this.idAdministrateur = idAdministrateur;
}

public String getNom() {
    return nom;
}

public void setNom(String nom) {
    this.nom = nom;
}

public String getPrenom() {
    return prenom;
}

public void setPrenom(String prenom) {
    this.prenom = prenom;
}



public String getMotDePasse() {
    return motDePasse;
}

public void setMotDePasse(String motDePasse) {
    this.motDePasse = motDePasse;
}

public Administrateur(String nom, String prenom, String email, String motDePasse) {this.nom = nom;
    this.prenom = prenom;
    
    this.motDePasse = motDePasse;

}
}
