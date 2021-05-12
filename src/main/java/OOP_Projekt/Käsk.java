package OOP_Projekt;

public class Käsk {
    private String nimi;
    private String kirjeldus;
    private Runnable tegevus;

    public Käsk(String nimi, String kirjeldus, Runnable tegevus) {
        this.nimi = nimi;
        this.kirjeldus = kirjeldus;
        this.tegevus = tegevus;
    }

    public String getNimi() {
        return nimi;
    }

    public String getKirjeldus() {
        return kirjeldus;
    }
    public void käivita() {
        tegevus.run();
    }

}
