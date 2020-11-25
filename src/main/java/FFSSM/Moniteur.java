/**
 * @(#) Moniteur.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Moniteur extends Plongeur {

    public int numeroDiplome;
    public List<Embauche> jobs;

    public Moniteur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, int numeroDiplome, int niveau) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance,niveau);
        this.numeroDiplome = numeroDiplome;
        this.jobs = new ArrayList<>();
    }

    /**
     * Si ce moniteur n'a pas d'embauche, ou si sa dernière embauche est terminée,
     * ce moniteur n'a pas d'employeur.
     * @return l'employeur actuel de ce moniteur sous la forme d'un Optional
     */
    public Optional<Club> employeurActuel() {
        Embauche job = jobs.get(0);
         if (!job.estTerminee()){
             return Optional.of(job.getEmployeur());
         }
         else{
             return Optional.empty();
         }

    }
    
    /**
     * Enregistrer une nouvelle embauche pour cet employeur
     * @param employeur le club employeur
     * @param debutNouvelle la date de début de l'embauche
     */
    public void nouvelleEmbauche(Club employeur, LocalDate debutNouvelle) {   
         jobs.add(0,new Embauche(debutNouvelle,this,employeur));
    }

    /**
     * Met fin au job
     * @param fin date de fin de contrat
     */
    public void terminerEmbauche(LocalDate fin) {
        jobs.get(0).terminer(fin);
    }

    public List<Embauche> emplois() {
         return jobs;
    }

}
