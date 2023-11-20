package yekhdem;

public class Etudiant {

    private Specialiter specialite;
     private int idEtudiant;
    private String nom;
    private String prenom;
   
    private String motDePasse;
    

 public Etudiant() {
        
        
    }
    

    public Etudiant(String nom, String prenom, String motDePasse, Specialiter specialite) {
        
        this.nom = nom;
        this.prenom = prenom;
        
        this.motDePasse = motDePasse;

        this.specialite = specialite;
    }

    public Specialiter getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialiter specialite) {
        this.specialite = specialite;
    }
    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudient(int idEtudiant) {
        this.idEtudiant = idEtudiant;
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

}
