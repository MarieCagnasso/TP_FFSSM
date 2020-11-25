package FFSSM;

import java.time.LocalDate;

public class Plongeur extends Personne {
	private int niveau;
	private Licence licence;

	public Plongeur (String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance,int niveau){
	    super(numeroINSEE, nom, prenom, adresse, telephone, naissance);
        this.niveau=niveau;

	}

	public void ajouterLicence(String numero, LocalDate delivrance, Club club){
	    licence = new Licence(this,numero,delivrance,this.niveau,club);
    }

    public Licence getLicence() {
		if (licence == null){
			throw new IllegalArgumentException("Le plongeur n'a pas de licence");
		}
        return licence;
    }
}
