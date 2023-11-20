package yekhdem;

public class Module {
    private int id_Module;
    private int id ;
    private Specialiter specialite;
    private String nom_Module;
    private int coefficient;



    public Module() {}
    public Module(Specialiter specialite, String nom_Module, int coefficient) {
        this.specialite = specialite;
        this.nom_Module = nom_Module;
        this.coefficient = coefficient;
    }

    // Getters et setters
    public int getIdModule() {
        return id_Module;
    }

    public void setIdModule(int id_Module) {
        this.id_Module = id_Module;
    }

    public Specialiter getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialiter specialite) {
        this.specialite = specialite;
    }

    public String getNomModule() {
        return nom_Module;
    }

    public void setNomModule(String nom_Module) {
        this.nom_Module = nom_Module;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }
    public Module(int id, String nom_Module, int coefficient) {
    	int a=this.specialite.getIdSpecialite() ;
    	this.id_Module=a;
          
        this.nom_Module = nom_Module;
        this.coefficient = coefficient;
    }
}

