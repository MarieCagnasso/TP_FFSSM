/**
 * @(#) Plongee.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Plongee {

	private Site lieu;

    private Moniteur chefDePalanquee;

    private LocalDate date;

    private int profondeur;

    private int duree;
    private ArrayList<Plongeur> participants;

	public Plongee(Site lieu, Moniteur chefDePalanquee, LocalDate date, int profondeur, int duree) {
		this.lieu = lieu;
		this.chefDePalanquee = chefDePalanquee;
		this.date = date;
		this.profondeur = profondeur;
		this.duree = duree;
		this.participants = new ArrayList();
	}

	/**
	 *
	 * @param participant
	 */
	public void ajouteParticipant(Plongeur participant) {
		for (Plongeur p : participants){
			if (p==participant){
				throw new IllegalArgumentException("Le participant est déjà enregistré.");
			}
		}
		participants.add(participant);
	}

	public LocalDate getDate() {
		return date;
	}

	/**
	 * Détermine si la plongée est conforme. 
	 * Une plongée est conforme si tous les plongeurs de la palanquée ont une
	 * licence valide à la date de la plongée
	 * @return vrai si la plongée est conforme
	 */
	public boolean estConforme() {
		for (Plongeur p : participants){
		    try{
                if (!p.getLicence().estValide(date)){
                    return false;
                }
            }catch (Exception e){
                return false;
            }
		}
		return true;
	}

    public Site getLieu() {
        return lieu;
    }

    public void setLieu(Site lieu) {
        this.lieu = lieu;
    }

    public Moniteur getChefDePalanquee() {
        return chefDePalanquee;
    }

    public void setChefDePalanquee(Moniteur chefDePalanquee) {
        this.chefDePalanquee = chefDePalanquee;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getProfondeur() {
        return profondeur;
    }

    public void setProfondeur(int profondeur) {
        this.profondeur = profondeur;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public ArrayList<Plongeur> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Plongeur> participants) {
        this.participants = participants;
    }
}
