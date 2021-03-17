import java.util.List;

public class Ruum {
    //isendiväljad ruumi jaoks
    private String nimi;
    private String ruumi_kirjeldus;
    private boolean sisaldab_koletist;
    private List<String> esemed;

    public Ruum(String nimi, String ruumi_kirjeldus, boolean sisaldab_koletist, List<String> esemed) {
        this.nimi = nimi;
        this.ruumi_kirjeldus = ruumi_kirjeldus;
        this.sisaldab_koletist = sisaldab_koletist;
        this.esemed = esemed;
    }

    public String getNimi() {
        return nimi;
    }

    public String getRuumi_kirjeldus() {
        return ruumi_kirjeldus;
    }

    public boolean isSisaldab_koletist() {
        return sisaldab_koletist;
    }

    public List<String> getEsemed() {
        return esemed;
    }

    public String toString(){
        return "nimi: " +nimi+ ", kirjeldus: " +ruumi_kirjeldus+ "sisaldab koletist: " +
                sisaldab_koletist+ ", esemed: " + esemed;
    }
}
