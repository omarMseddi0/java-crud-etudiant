package yekhdem;

public class Note {
	private int idNote;
	private Etudiant etudiant;
	private Module module;
	private float note;


	public Note(Etudiant etudiant, Module module, float note) {
	    this.etudiant = etudiant;
	    this.module = module;
	    this.note = note;
	}

	// Getters et setters
	public int getIdNote() {
	    return idNote;
	}

	public void setIdNote(int idNote) {
	    this.idNote = idNote;
	}

	public Etudiant getEtudiant() {
	    return etudiant;
	}

	public void setEtudient(Etudiant etudiant) {
	    this.etudiant = etudiant;
	}

	public Module getModule() {
	    return module;
	}

	public void setModule(Module module) {
	    this.module = module;
	}

	public float getNote() {
	    return note;
	}

	public void setNote(float valeur) {
	    this.note = valeur;
	}


}
