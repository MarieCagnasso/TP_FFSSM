package FFSSM;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class FFSSMTest {

    private Moniteur mono1;
    private Moniteur president;
    private Plongeur plongeur1;
    private Plongeur plongeur2;
    private Club club1;
    private Plongee plongee1;
    private Plongee plongee2;
    private Site site;

    private LocalDate today;
    private LocalDate datenaiss;

    @BeforeEach
    void setUp() {
        datenaiss =  LocalDate.of(1977, 12, 15);
        today = LocalDate.now();
        mono1 = new Moniteur("12D43","Dupont","Jean","une adresse", "056465676", datenaiss,123,1);
        president = new Moniteur("P2D43","George","Kevin","une adresse", "056465676", datenaiss,124,1);
        plongeur1 = new Plongeur("A23ER","riviere","Luc","une adresse","05456545",datenaiss.plusYears(1),1);
        plongeur2 = new Plongeur("3ER","Moulit","Flo","une adresse","05456545",datenaiss.plusYears(10),1);
        club1 = new Club(president,"Club 1","0567845674");
        site = new Site("site de plongée","des détails");
        plongee1 = new Plongee(site,mono1,today,10,60);
        plongee2 = new Plongee(site,mono1,today.minusMonths(6),10,60);
    }

    @Test
    void moniteurJobs() {
       mono1.nouvelleEmbauche(club1,today);
        assertEquals(Optional.of(club1), mono1.employeurActuel(),
                " le club actuel");
        mono1.terminerEmbauche(today);
        assertEquals(Optional.empty(), mono1.employeurActuel(),
                "pas de job");
        mono1.nouvelleEmbauche(club1,today);
        assertEquals(2,mono1.emplois().size(),"Il y a 2 job");
    }


    @Test
    void ajouterLicence() {
        plongeur1.ajouterLicence("11111",datenaiss,club1);
        Licence l = plongeur1.getLicence();
        assertFalse(l.estValide(today),"La licence est périmée.");

        plongeur1.ajouterLicence("222222",today,club1);
        assertTrue(plongeur1.getLicence().estValide(today),"La licence est valide.");
    }

    @Test
    void testPlongeeConforme (){
        plongeur1.ajouterLicence("11111",today,club1);
        plongee1.ajouteParticipant(plongeur1);
        plongee1.ajouteParticipant(plongeur2);
        assertFalse(plongee1.estConforme(),"Plongeur2 n'a pas de licience.");

        plongeur2.ajouterLicence("1",datenaiss,club1);
        assertFalse(plongee1.estConforme(),"Plongeur2 n'a pas de licience valide.");

        plongeur2.ajouterLicence("1",today,club1);
        assertTrue(plongee1.estConforme(),"La plongé est conforme.");

        assertThrows(IllegalArgumentException.class, () -> {
            plongee1.ajouteParticipant(plongeur1);
        },"Le plongeur est déjà inscrit.");

    }

    @Test
    void clubOrganisePlongee(){
        Set list = new HashSet();
        list.add(plongee1);
        list.add(plongee2);


        plongee1.ajouteParticipant(plongeur1);
        plongee2.ajouteParticipant(plongeur2);
        club1.organisePlongee(plongee1);
        club1.organisePlongee(plongee2);

        assertEquals(list,club1.plongeesNonConformes());

        plongeur1.ajouterLicence("A",today,club1);
        list.remove(plongee1);

        assertEquals(list,club1.plongeesNonConformes());
    }
}
