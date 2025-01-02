package org.gestionare_taskuri.echipa;


public class TeamFactory {
    public Echipa buildTeam(Integer idEchipa) {
        Echipa echipa = new Echipa(idEchipa, Echipa.Specializare.BACKEND);
        return echipa;
    }
}
