package echipa;


public class TeamFactory {
    public Echipa buildTeam(Integer id) {
        Echipa echipa = new Echipa(id, Echipa.Specializare.BACKEND);
        return echipa;
    }
}
